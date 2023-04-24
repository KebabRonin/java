package org.example.Exceptions;

public class CommandException extends Exception {
    String msg;

    public CommandException(String msg) {this.msg = msg;}
    @Override
    public String toString() {
        return "Command exception - " + msg;
    }
}
