package org.example;


import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.file.InvalidPathException;
import java.util.*;
public class Document {
    Map<String, Object> tags = new HashMap<>();
    File file;

    public Document(String p) throws InvalidPathException, MalformedURLException, IOException {
        URL url = new URL("http://some.where/file.html");
        ((HttpURLConnection)(url.openConnection())).getResponseCode();
        this.file = new File(p);
        if(!file.exists()) {
            throw new InvalidPathException(p, "File does not exist");
        }
    }

    public Object get() {
        return this.file.toString();
    }

    @Override
    public String toString() {
        return file.getAbsolutePath();
    }
}
