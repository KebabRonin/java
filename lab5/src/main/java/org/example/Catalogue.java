package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Catalogue {
    private Set<Document> documents = new HashSet<>();

    public Catalogue addDocument(Document d) {
        this.documents.add(d);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Document i : documents) {
            sb.append(i.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
