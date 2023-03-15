import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> nodeList = new ArrayList<>();

    public List<Node> getNodeList() {
        return nodeList;
    }

    public int computeImportance(Node n) {
        int connections = 0;

        if(n instanceof Person) {
            connections += ((Person) n).getRelationships().size();
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
}
