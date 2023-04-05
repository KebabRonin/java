package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand implements Command{
    private Catalogue receiver;
    private String path;

    public SaveCommand(String p) {
        this.path = p;
    }
    public SaveCommand(Catalogue c, String p) {
        this.receiver = c;
        this.path = p;
    }

    @Override
    public void setReceiver(Catalogue receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws CommandException {
        try {
            File f = new File(path);
            f.createNewFile();

        }
        catch (IOException e) {
            System.out.println(e);
        }
        FileWriter fr = null;
        try {
            fr = new FileWriter(path);
            fr.write(receiver.toString());
            fr.close();
        }
        catch (IOException e) {
            throw new CommandException();
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
