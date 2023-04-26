package org.example;

public class Artist {
    int id;
    String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id:" + this.id + ", name:" + this.name +"}";
    }
}
