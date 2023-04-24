package org.example;

import org.example.Commands.AddCommand;
import org.example.Commands.SaveCommand;
import org.example.Exceptions.CommandException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ///Instantiate Catalogue

            Catalogue myCat = new Catalogue()/*.addDocument(new Document("C:\\Users\\KebabRonin\\Desktop\\my.txt"))*/;
            CatalogueCommander catalogueCommander = new CatalogueCommander(myCat);
            catalogueCommander.addCommand("add", new AddCommand());
            catalogueCommander.addCommand("save", new SaveCommand());

            getInput(catalogueCommander);
        }
        catch (Exception e) {
            System.out.println("Unhandled ex " + e);
        }
    }

    public static void getInput(CatalogueCommander catalogueCommander) {
        System.out.println("Welcome to ICatalogue!");
        Scanner sc = new Scanner(System.in);
        while (true){
            String line = sc.nextLine();
            try {
                catalogueCommander.executeString(line);
                System.out.println("Command executed successfully");
            }
            catch (CommandException e) {
                System.out.println(e);
            }
            catch (Exception e) {
                System.out.println("Other exc: " + e);
            }
        }
    }
}