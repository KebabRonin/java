package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Catalogue myCat = new Catalogue().addDocument(new Document("C:\\Users\\KebabRonin\\Desktop\\my.txt"));
            CatalogueCommander c = new CatalogueCommander(myCat);
            //c.setCommand(new AddCommand("C:\\Users\\KebabRonin\\Desktop\\my.txt")).executeCommand();
            //c.setCommand(new SaveCommand("C:\\Users\\KebabRonin\\Desktop\\alt.txt")).executeCommand();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Hello world!");
    }
}