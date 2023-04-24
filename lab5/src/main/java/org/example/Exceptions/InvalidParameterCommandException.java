package org.example.Exceptions;

public class InvalidParameterCommandException extends CommandException {
    private String msg;

    public InvalidParameterCommandException(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "Invalid parameter: " + msg;
    }
}
