import java.util.Calendar;
import java.util.Date;


public class Main {

    public static void main(String[] args) {

        Network net = new Network();
        Person p = new Person("Brassat", new Date(2003, Calendar.APRIL, 3));
        Person c = new Person("Glodeanu", new Date(2002, Calendar.JUNE, 2));
        Company com = new Company("Petrom SRL.");
        net.addNode(p);
        net.addNode(com);
        net.addNode(c);

        p.addRelationship(c);
        c.addRelationship(com);

        //net.sortImportance();


        for(Node i : net.getNodeList()) {
            if (i instanceof Person) {
                System.out.println("Person " + i.getName());
            }
            else if (i instanceof Company){
                System.out.println("Company " + i.getName());
            }
        }

        System.out.println(net);

        Bonus b = new Bonus();
        Node cutV = b.getCutV(net);
        if(cutV == null) {
            System.out.println("There exists no cut vertex");
        }
        else {
            System.out.println("There exists a cut vertex : " + cutV.getName());
        }

        //new BonusTest().getCutV();
    }
}