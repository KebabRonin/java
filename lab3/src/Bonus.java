import java.util.ArrayList;
import java.util.List;


public class Bonus {

    private List<Node> nodes;
    private Node cutV;
    private Node root;
    private boolean[] viz;
    private int[] depth;
    private int[] low;
    private boolean finishedFlag;

    private void DFS(Node n, int d) {
        if (!(n instanceof Person)) {
            return;
        }

        viz  [nodes.indexOf(n)] = true;
        depth[nodes.indexOf(n)] = d;
        low  [nodes.indexOf(n)] = d;

        for (Node nei : ((Person) n).getRelationships().values()) {
            if (!finishedFlag) {
                if (!nodes.contains(nei)) {
                    finishedFlag = true;
                    cutV = null;
                    System.out.println("Node relationship with outside node!");
                    return;
                }
                if ((nei instanceof Person) && !viz[nodes.indexOf(nei)]) {
                    DFS(nei, d + 1);
                }
                if (low[nodes.indexOf(nei)] < low[nodes.indexOf(n)]) {
                    low[nodes.indexOf(n)] = low[nodes.indexOf(nei)];
                }
                if(low[nodes.indexOf(nei)] >= depth[nodes.indexOf(n)]) {
                    if(nei != root || ((Person) n).getRelationships().values().size() > 1) {
                        cutV = n;
                        finishedFlag = true;
                        return;
                    }
                }
            }
        }
    }
    public Node getCutV(Network net) {
         nodes = net.getNodeList();
         finishedFlag = false;
         cutV = null;
         int nr_nodes = nodes.size();


        if (nr_nodes < 1) {
            return null;
        }
        //assertFalse("Empty digraph", nr_nodes < 1);

        if (!isConnected(net)) {
            //throw new Exception("Digraph not connected");
            System.out.println("Digraph not connected");
            return null;
        }

        low   = new     int[nr_nodes];
        depth = new     int[nr_nodes];
        viz   = new boolean[nr_nodes];

        for (int i = 0; i < nr_nodes; i++) {
            low[i] = depth[i] = 0;
        }

        int k = 0;
        while(! (nodes.get(k) instanceof Person) && k < nr_nodes) {
            k += 1;
        }

        // No People, so no Relationships
        if (k >= nr_nodes) {
            return null;
        }

        root = nodes.get(k);
        DFS(root, 0);

        if(!finishedFlag) {
            return null;
        }

        return cutV;
    }

    private boolean isConnected(Network net) {
        List<Node> q = new ArrayList<>();
        boolean isConn = false;
        net.sortImportance();

        int k = 0;
        while(k < net.getNodeList().size() && !isConn) {

            if ((net.getNodeList().get(k) instanceof Person) ) {
                q.clear();
                q.add(net.getNodeList().get(k));

                int cursor = 0;

                while(cursor < q.size()) {
                    Node i = q.get(cursor);
                    if (i instanceof Person) {
                        for(Node x : ((Person) i).getRelationships().values()) {
                            if (!q.contains(x)) {
                                q.add(x);
                            }
                        }
                    }
                    cursor += 1;
                }
            }
            isConn = isConn || (net.getNodeList().size() == q.size());
            k += 1;
        }

        return isConn;
    }
}
