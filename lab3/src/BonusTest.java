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
        Person t = new Person("Raul", new Date(2002, Calendar.JUNE, 2));
        Company com = new Company("Petrom SRL.");
        net.addNode(p);
        net.addNode(c);
        net.addNode(t);

        assertNull(b.getCutV(net), "[TEST] Digraph not connected");

        p.addRelationship(c);
        t.addRelationship(p);

        assertEquals(b.getCutV(net), p, "[TEST] Cut vertex in middle");
        net.addNode(com);
        assertNull(b.getCutV(net), "[TEST] Company influences connected components");
        c.addRelationship(com);
        assertEquals(b.getCutV(net), p, "[TEST] Company influences connected components");
    }
}