package org.example;

import java.sql.*;

public class GenreDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
    public Genre findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from genres where title='" + name + "'")) {
            return rs.next() ? new Genre(rs.getInt(1), rs.getString(2)) : null;
        }
    }
    public Genre findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from genres where id='" + id + "'")) {
            return rs.next() ? new Genre(rs.getInt(1), rs.getString(2)) : null;
        }
    }
}
