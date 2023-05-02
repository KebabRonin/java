package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GenreDAO {
    Connection con;

    public GenreDAO(Connection d) {
        con = d;
    }
    public void create(String name) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
    public Genre findByName(String name) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from genres where name='" + name + "'")) {
            return rs.next() ? new Genre(rs.getInt(1), rs.getString(2)) : null;
        }
    }
    public Genre findById(int id) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from genres where id='" + id + "'")) {
            return rs.next() ? new Genre(rs.getInt(1), rs.getString(2)) : null;
        }
    }

    public List<Genre> getAll() throws SQLException {
        List myL = new LinkedList();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from genres order by id")) {
            while (rs.next()) {
                myL.add(new Genre(rs.getInt(1), rs.getString(2)));
            }
        }

        return myL;
    }

    public List<Genre> getAllOfAlbum(int id) throws SQLException {
        List myL = new LinkedList();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id_genre from album_genres where id_album = " + id)) {
            int idd;
            while (rs.next()) {
                idd = rs.getInt(1);
                myL.add(this.findById(idd));
            }
        }

        return myL;
    }
}
