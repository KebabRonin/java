package org.example;

import java.io.IOException;

public interface Command {
    void execute() throws CommandException, IOException;
    void setReceiver(Catalogue c);
}
