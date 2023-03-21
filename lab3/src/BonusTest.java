import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BonusTest {

    @org.junit.jupiter.api.Test
    void getCutV() {
        Bonus b = new Bonus();

        Network net = new Network();
        Person p = new Person("Brassat", new Date(2003, Calendar.APRIL, 3));
        Person c = new Person("Glodeanu", new Date(2002, Calendar.JUNE, 2));
        //Company com = new Company("Petrom SRL.");
        net.addNode(p);
        net.addNode(c);

        assertNull(b.getCutV(net), "[TEST] Digraph not connected");

        p.addRelationship(c);

        //assertNull(b.getCutV(net), "[TEST] ");
        //net.addNode(new Person("Arhire", new Date(1996, Calendar.NOVEMBER, 12)));
        //net.addNode(com);
        //net.addNode(new Company("Restart"));
        //net.addNode(new Company("EA"));

        //c.addRelationship(com);
    }
}