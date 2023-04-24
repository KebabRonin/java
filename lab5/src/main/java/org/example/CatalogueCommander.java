package org.example;

import org.example.Commands.Command;
import org.example.Exceptions.CatalogueNotFoundException;
import org.example.Exceptions.CommandException;
import org.example.Exceptions.CommandNotFoundException;

import java.util.*;

public class CatalogueCommander {
    private Catalogue catalogue;
    private Map<String, Command> commandList = new HashMap<>();

    public CatalogueCommander(Catalogue c) {
        this.catalogue = c;
    }

    public CatalogueCommander addCommand(String commandName, Command command) {
        this.commandList.put(commandName, command);
        return this;
    }

    public CatalogueCommander executeString(String commandString) throws CommandException {
        String[] args = commandString.split("\\s+", 2);
        Command requestedCommand = commandList.get(args[0]);

        if(null == requestedCommand) {
            throw new CommandNotFoundException(args[0]);
        }

        if (null == this.catalogue) {
            throw new CatalogueNotFoundException();
        }

        try {
            requestedCommand.execute(this.catalogue, args[1]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            requestedCommand.execute(this.catalogue, null);
        }
        return this;
    }
}
