package org.example.Commands;

import org.example.*;
import org.example.Exceptions.*;

public class AddCommand implements Command {
    private Document document;

    @Override
    public void execute(Catalogue receiver, String args) throws CommandException {

        if(null == args) {
            throw new InvalidParameterCommandException("Expecting arguments <path/URL> name ['tag'='value'] ");
        }


        receiver.addDocument(document);
    }
}
