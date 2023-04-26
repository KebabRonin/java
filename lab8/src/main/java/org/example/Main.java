package org.example;

import java.sql.*;
public class Main {
    public static void main(String args[]) {
        try {
            ArtistDAO artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            GenreDAO genres = new GenreDAO();
            genres.create("Rock"); //TODO: Funk, Soul, Pop
            Database.getConnection().commit();
            //AlbumDAO albums = new AlbumDAO();
            //albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            //findByName
            //albums.create(1982, "Thriller", "Michael Jackson","Funk,Soul,Pop"
            //Database.getConnection().commit();
            //TODO: print all the albums in the database


            System.out.println("The artist at id 1 is " + artists.findById(1));
            System.out.println("Michael Jackson has id " + artists.findByName("Michael Jackson"));


            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }
}