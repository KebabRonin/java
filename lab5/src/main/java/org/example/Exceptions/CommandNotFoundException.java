package org.example.Exceptions;

public class CommandNotFoundException extends CommandException {

    private String cmdName;

    public CommandNotFoundException(String name) {
        this.cmdName = name;
    }

    @Override
    public String toString() {
        return "Unknown command: '" + cmdName + "'";
    }
}
