public class Company implements java.lang.Comparable<Node>, Node {
    private String name;

    public Company(String name) {
        this.setName(name);
    }
    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.getName());
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
