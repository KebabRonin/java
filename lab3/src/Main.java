import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class Main {
    public static void main(String[] args) {
        java.util.List<Node> network = new ArrayList<>();
        Person p = new Person("Brassat", new Date(2003, Calendar.APRIL, 3));
        Person c = new Person("Glodeanu", new Date(2002, Calendar.JUNE, 2));
        Company com = new Company("Petrom SRL.");
        network.add(p);
        network.add(c);
        network.add(new Person("Arhire", new Date(1996, Calendar.NOVEMBER, 12)));
        network.add(com);
        network.add(new Company("Restart"));
        network.add(new Company("EA"));

        p.addRelationship(c);
        c.addRelationship(com);


        for(Node i : network) {
            if (i instanceof Person) {
                System.out.println("Person " + i.getName());
            }
            else if (i instanceof Company){
                System.out.println("Company " + i.getName());
            }
        }

        Network net = new Network();
        net.setNodeList(network);
        System.out.println(net);
    }
}