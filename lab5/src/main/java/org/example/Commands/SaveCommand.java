package org.example.Commands;

import org.example.*;
import org.example.Exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements Command {
    private String path;

    @Override
    public void execute(Catalogue receiver, String args) throws CommandException {

        if(null == args) {
            throw new InvalidParameterCommandException("Command takes a path as a single argument");
        }

        path = args.split("\\s+")[0];

        try {
            File f = new File(path);
            f.createNewFile();

        }
        catch (IOException e) {
            throw new InvalidParameterCommandException("Invalid path");
        }

        FileWriter fr = null;
        try {
            fr = new FileWriter(path);
            fr.write(receiver.toString());
            fr.close();
        }
        catch (IOException e) {
            throw CommandException(e.toString());
        }
        finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
