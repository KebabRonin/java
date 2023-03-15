import java.util.Date;
import java.util.Map;

public class Person implements java.lang.Comparable<Person>, Node {
    private String name;
    private Date dateOfBirth;
    private Map<String, Node> relationships;

    public Person(String name, Date dateOfBirth) {
        this.setName(name);
        this.setDateOfBirth(dateOfBirth);
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.getName());
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Map<String, Node> getRelationships() {
        return relationships;
    }
    public void addRelationship(Node p) {
        relationships.put(p.getName(), p);
    }

    public void addRelationship(Company p) {
        relationships.put(p.getName(), p);
    }
    public void setRelationships(Map<String, Node> relationships) {
        this.relationships = relationships;
    }
}
