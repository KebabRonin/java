package org.example.Commands;

import org.example.Catalogue;
import org.example.Exceptions.CommandException;

public interface Command {
    void execute(Catalogue receiver, String args) throws CommandException;
}
