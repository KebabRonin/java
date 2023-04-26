package org.example;

public class Album {
    int id, release_year, id_artist;
    String title;

    public Album(int id, int release_year, String title, int id_artist) {
        this.id = id;
        this.release_year = release_year;
        this.title = title;
        this.id_artist = id_artist;
    }

    @Override
    public String toString() {
        return "{id:" + this.id + ", release_year:" + this.release_year + ", title:" + this.title + ", id_artist:" + this.id_artist + "}";
    }
}
