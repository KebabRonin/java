package org.example;

import java.util.List;
import java.util.StringJoiner;

public class Album {
    int id, release_year, id_artist;
    String title;
    List<Genre> genres;
    public Album(int id, int release_year, String title, int id_artist, List<Genre> genres) {
        this.id = id;
        this.release_year = release_year;
        this.title = title;
        this.id_artist = id_artist;
        this.genres = genres;
    }

    @Override
    public String toString() {
        StringJoiner k = new StringJoiner(", ", "{", "}");
        genres.forEach(x -> k.add(x.toString()));
        return "{id:" + this.id + ", release_year:" + this.release_year + ", title:" + this.title + ", id_artist:" + this.id_artist + ", genres:" +
                k + "}";
    }
}
