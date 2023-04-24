package org.example.Exceptions;

public class CatalogueNotFoundException extends CommandException {
    public CatalogueNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "Catalogue not found";
    }
}
