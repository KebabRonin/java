import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> nodeList = new ArrayList<>();

    public List<Node> getNodeList() {
        return nodeList;
    }

    public int computeImportance(Node n) {
        int connections = 0;

        if(!nodeList.contains(n)) {
            return 0;
        }

        if(n instanceof Person) {
            for(Node k : ((Person) n).getRelationships().values()) {
                if( nodeList.contains(k)) {
                    connections += 1;
                }
            }
        }

        for(Node i : nodeList) {
            if(i instanceof Person) {
                if (((Person) i).getRelationships().get(n.getName()) != null) {
                    connections += 1;
                }
            }
        }
        return connections;
    }

    public void addNode(Node n) {
        nodeList.add(n);
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //Comparator<Node> c = new Comparator<>()
        nodeList.sort((a,b) -> computeImportance(b) - computeImportance(a));
        for (Node n : nodeList) {
            sb.append(this.computeImportance(n)).append(". ").append(n.getName()).append("\n");
        }
        return sb.toString();
    }
}
