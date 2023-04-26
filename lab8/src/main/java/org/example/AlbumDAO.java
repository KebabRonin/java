package org.example;

import java.sql.*;

public class AlbumDAO {
    public void create(int release_year, String title, int id_artist) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums (release_year, title, id_artist) values (?,?,?)")) {
            pstmt.setInt(1, release_year);
            pstmt.setString(2, title);
            pstmt.setInt(3, id_artist);
            pstmt.executeUpdate();
        }
    }
    public Album findByTitle(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, release_year, title, id_artist from albums where title='" + title + "'")) {
            return rs.next() ? new Album(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getInt(4)) : null;
        }
    }
    public Album findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, release_year, title, id_artist from albums where id='" + id + "'")) {
            return rs.next() ? new Album(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getInt(4)) : null;
        }
    }
}
