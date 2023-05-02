package org.example;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class Playlist {
    String name;
    Date creation_date;
    List<Album> albums;

    public static boolean relatedAlbums(Album a, Album b) {
        return  a.id_artist == b.id_artist          ||
                a.release_year == b.release_year    ||
                a.genres.stream().anyMatch(x -> b.genres.stream().anyMatch(y -> y.id == x.id));
    }

    public static void main(String[] args) throws SQLException {

        try {
            //Load db
            List<Album> l = new AlbumDAO(Database.getConnection()).getAll();
            //Build graph, edges signify unrelated graphs
            int n = l.size();
            boolean[][] m = new boolean[n][n];
            for(int i = 0; i < n - 1; ++i) {
                for(int j = i + 1; j < n; ++j) {
                    m[i][j] = m[j][i] = !relatedAlbums(l.get(i), l.get(j));
                }
                m[i][i] = true;
            }
            //Find max
            int maxcl = 0;
            boolean[] playlist = null;
            for(int i = 0; i < n; ++i) {
                boolean[] neighbours = m[i].clone();
                int suma = 0;
                for(int j = 0; j < n; ++j) {
                    if(neighbours[j]) {
                        for(int k = 0; k < n; ++k) {
                            neighbours[k] &= m[j][k];
                        }
                        ++suma;
                    }
                }
                if(suma > maxcl) {maxcl = suma; playlist = neighbours;}
            }
            //show playlist
            if(playlist == null) {
                System.out.println("No playlist found");
                return;
            }
            System.out.println("Playlist length : " + maxcl);
            for (int i = 0; i < n; i++) {
                //System.out.println(playlist[i]);
            }
            for(int i = 0; i < n; ++i) {
                if(playlist[i]) {
                    System.out.println(l.get(i).toString());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

    }
}
