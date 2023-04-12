package org.example;

public class Project implements Comparable<Project> {
    private String name;

    public Project(String name) {
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Project o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
