import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        java.util.List<Node> network = new ArrayList<>();
        network.add(new Person("Brassat", new Date(2003, Calendar.APRIL, 3)));
        network.add(new Person("Glodeanu", new Date(2002, Calendar.JUNE, 2)));
        network.add(new Person("Arhire", new Date(1996, Calendar.NOVEMBER, 12)));
        network.add(new Company("Petrom SRL."));
        network.add(new Company("Restart"));
        network.add(new Company("EA"));
        for(Node i : network) {
            if (i instanceof Person) {
                System.out.println("Person " + i.getName());
            }
            else if (i instanceof Company){
                System.out.println("Company " + i.getName());
            }
        }
    }
}