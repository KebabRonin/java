package org.example;

import com.opencsv.CSVReader;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.xml.crypto.Data;
import java.io.FileReader;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";
    public static void maind(String args[]) {
        try {
            ArtistDAO artists = new ArtistDAO(Database.getConnection());
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            GenreDAO genres = new GenreDAO(Database.getConnection());
            try {
                genres.create("Rock");
                genres.create("Funk");
                genres.create("Soul");
                genres.create("Pop");
            } catch (SQLException e) {
                System.out.println("Didn't add genres");
            }
            Database.getConnection().commit();
            AlbumDAO albums = new AlbumDAO(Database.getConnection());
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            //findByName

            albums.create(1982, "Thriller", "Michael Jackson","Funk,Soul,Pop");
            Database.getConnection().commit();
            //TODO: print all the albums in the database
            List<Album> albumList =  albums.getAll();
            for (Album a : albumList) {
                System.out.println(a);
            }


            System.out.println("The artist at id 1 is " + artists.findById(1));
            System.out.println("Michael Jackson has id " + artists.findByName("Michael Jackson"));
            System.out.println("The Wall is " + albums.findByTitle("The Wall"));


            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace(System.out);
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }

    public static void main(String args[]) {

        try {
            FileReader filereader = new FileReader("albumlist.csv");
            CSVReader csvReader = new CSVReader(filereader);
            AlbumDAO abd = new AlbumDAO(Database.getConnection());
            String[] record;
            csvReader.readNext();
            while ((record = csvReader.readNext()) != null)  //returns a boolean value
            {
                int yr = Integer.parseInt(record[1]); // Year
                String title = record[2]; // Album
                String artist = record[3]; // Artist
                String genre = record[4]; // Genre

                abd.create(yr, title, artist, genre);

            }

            abd.con.commit();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace(System.out);
            try {
                Database.getConnection().rollback();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }
}