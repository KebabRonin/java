package org.example;

//import org.graph4j.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> students = List.of("S1", "S2", "S0");
        List<Student> linkedList = new LinkedList<>();
        linkedList.addAll(students.stream().map(Student::new).collect(Collectors.toList()));
        linkedList.sort(Student::compareTo);
        System.out.print("Students: ");
        for (Student student : linkedList) {
            System.out.print(student.getName() + " ");
        }
        System.out.println();
        List<String> projects = List.of("P2", "P0", "P1");
        TreeSet<Project> projectTreeSet = new TreeSet<>();
        projects.stream().map(Project::new).forEach(projectTreeSet::add);
        System.out.print("Projects: ");
        for (Project project : projectTreeSet) {
            System.out.print(project.getName() + " ");
        }

    }
}