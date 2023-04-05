package org.example;

import java.io.IOException;

public class AddCommand implements Command{
    private Catalogue receiver;
    private Document document;

    public AddCommand(Document d) {
        this.document = d;
    }
    public AddCommand(String p) throws IOException {
        this.document = new Document(p);
    }
    public AddCommand(Catalogue c, Document d) {
        this.receiver = c;
        this.document = d;
    }

    @Override
    public void setReceiver(Catalogue receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.addDocument(document);
    }
}
