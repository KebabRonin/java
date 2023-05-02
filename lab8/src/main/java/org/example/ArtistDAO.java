package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ArtistDAO {
    Connection con;

    public ArtistDAO(Connection d) {
        con = d;
    }
    public void create(String name) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
    public Artist findByName(String name) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "select id, name from artists where name=(?)")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? new Artist(rs.getInt(1), rs.getString(2)) : null;
        }
    }
    public Artist findById(int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "select id, name from artists where id=(?)")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? new Artist(rs.getInt(1), rs.getString(2)) : null;
        }
    }

    public List<Artist> getAll() throws SQLException {
        List myL = new LinkedList();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id, name from artists order by id")) {
            while (rs.next()) {
                myL.add(new Artist(rs.getInt(1), rs.getString(2)));
            }
        }

        return myL;
    }
}
