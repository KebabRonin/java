package org.example;

import java.sql.*;

public class ArtistDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
    public Artist findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from artists where name='" + name + "'")) {
            return rs.next() ? new Artist(rs.getInt(1), rs.getString(2)) : null;
        }
    }
    public Artist findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from artists where id='" + id + "'")) {
            return rs.next() ? new Artist(rs.getInt(1), rs.getString(2)) : null;
        }
    }
}
