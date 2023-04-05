package org.example;

import java.io.IOException;
import java.util.*;

public class CatalogueCommander {
    private Catalogue catalogue;
    private Command command;

    public CatalogueCommander(Catalogue c) {
        this.catalogue = c;
    }

    public CatalogueCommander setCommand(Command command) {
        this.command = command;
        this.command.setReceiver(this.catalogue);
        return this;
    }

    public CatalogueCommander executeCommand() {
        try {
            command.execute();
        }
        catch (InvalidParameterCommandException exception) {
            System.out.println("Invalid Parameter!");
        }
        catch (CommandException e) {
            System.out.println("Generic Command Exception!");
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }
}
