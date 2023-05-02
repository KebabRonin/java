package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlbumDAO {
    Connection con;

    public AlbumDAO(Connection d) {
        con = d;
    }
    public void create(int release_year, String title, String artistname, String genres) throws SQLException {
        try (PreparedStatement pstmt_art = con.prepareStatement("select count(*) from artists where name = (?)")) {
            pstmt_art.setString(1, artistname);
            ResultSet art = pstmt_art.executeQuery();

            art.next();

            int art_count = art.getInt(1);

            ArtistDAO a = new ArtistDAO(con);

            if (art_count < 1) {
                a.create(artistname);
            }

            Artist artist1 = a.findByName(artistname);

            if (artist1 == null) {
                System.out.println("Artist couldn't be added");
                return;
            }

            try(PreparedStatement pstmt = con.prepareStatement(
                    "insert into albums (release_year, title, id_artist) values (?,?,?)")) {
                pstmt.setInt(1, release_year);
                pstmt.setString(2, title);
                pstmt.setInt(3, artist1.id);
                pstmt.executeUpdate();

                GenreDAO g = new GenreDAO(con);
                Album self = this.findByTitleArtistYear(title, artistname, release_year);
                if (self == null) {
                    System.out.println("Album couldn't be added");
                    return;
                }

                for (String genre : genres.split(", *")) {
                    Genre myg = g.findByName(genre);
                    if (myg == null) {
                        g.create(genre);
                        myg = g.findByName(genre);
                    }

                    try (PreparedStatement pstmt1 = con.prepareStatement(
                            "insert into album_genres (id_album, id_genre) values (?,?)")) {
                        pstmt1.setInt(1, self.id);
                        pstmt1.setInt(2, myg.id);
                        pstmt1.executeUpdate();
                    }

                }
            }
        }
    }
    public Album findByTitle(String title) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, release_year, title, id_artist from albums where title='" + title + "'")) {
            if(rs.next()) {
                int id = rs.getInt(1);
                return new Album(id, rs.getInt(2), rs.getString(3), rs.getInt(4), new GenreDAO(con).getAllOfAlbum(id));
            }
            else {
                return null;
            }
        }
    }

    private Album findByTitleArtistYear(String title, String artist, int year) throws SQLException {
        Artist ar = new ArtistDAO(con).findByName(artist);
        if (ar == null) {
            System.out.println(artist + " not found");
            return null;
        }
        System.out.println(title + " " + year + " " + ar);
        try (PreparedStatement pstmt = con.prepareStatement(
                "select id, release_year, title, id_artist from albums where title = (?) and release_year = (?) and id_artist = (?)")) {
            pstmt.setString(1, title);
            pstmt.setInt(2, year);
            pstmt.setInt(3, ar.id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt(1);
                return new Album(id, rs.getInt(2), rs.getString(3), rs.getInt(4), new GenreDAO(con).getAllOfAlbum(id));
            }
            else {
                return null;
            }
        }
    }

    public Album findById(int idm) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, release_year, title, id_artist from albums where id='" + idm + "'")) {
            int ida = rs.getInt(1);
            if(rs.next()) {
                int id = rs.getInt(1);
                return new Album(id, rs.getInt(2), rs.getString(3), rs.getInt(4), new GenreDAO(con).getAllOfAlbum(id));
            }
            else {
                return null;
            }
        }
    }

    public List<Album> getAll() throws SQLException {
        List<Album> myL = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, release_year, title, id_artist from albums order by id")) {
            while (rs.next()) {
                int id = rs.getInt(1);
                myL.add(new Album(id, rs.getInt(2), rs.getString(3), rs.getInt(4), new GenreDAO(con).getAllOfAlbum(id)));
            }
        }

        return myL;
    }
}
