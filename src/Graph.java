import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    ArrayList<GraphNode> nodes;
    static int h = 120;
    static int graphHeight = 0;

    Graph() {
        nodes = new ArrayList<>();
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphNode node1 = new GraphNode(111);
        GraphNode node2 = new GraphNode(222);
        GraphNode node3 = new GraphNode(333);
        GraphNode node4 = new GraphNode(444);
        graph.addEdge(node1, node2);
        graph.addEdge(node2, node3);
        graph.addEdge(node2, node1);
        graph.addEdge(node1, node4);

        GraphicHelper graphicHelper = new GraphicHelper(graph);
        System.out.println(Arrays.toString(graphicHelper.getSortedGraph()));
        System.out.println(GraphicHelper.ACE);
        System.out.println();
        graph.printGraph();
    }

    public void addEdge(GraphNode node1, GraphNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }

        boolean hasNode1 = containsNode(node1);
        boolean hasNode2 = containsNode(node2);

        if (!hasNode1 && !hasNode2) {
            nodes.add(node1);
            nodes.add(node2);
            GraphNode n1 = findNode(node1);
            GraphNode n2 = findNode(node2);
            n1.addNeighbor(n2);
            n1.level = 1;
            n2.level = n1.level + 1;
            n1.y = 10;
            n2.y = n1.y + h;
            updateHeight(node2.level);
        } else if (hasNode1 && !hasNode2) {
            nodes.add(node2);
            GraphNode n1 = findNode(node1);
            GraphNode n2 = findNode(node2);
            n1.addNeighbor(n2);
            n2.level = n1.level + 1;
            n2.y = n1.y + h;
            updateHeight(Math.max(node1.level, node2.level));
        } else if (!hasNode1) {
            nodes.add(node1);
            GraphNode n1 = findNode(node1);
            GraphNode n2 = findNode(node2);
            n1.addNeighbor(n2);
            n1.level = n2.level + 1;
            n1.y = n2.y + h;
            updateHeight(Math.max(node1.level, node2.level));
        } else {
            GraphNode n1 = findNode(node1);
            GraphNode n2 = findNode(node2);
            n1.addNeighbor(n2);
            updateHeight(Math.max(node1.level, node2.level));
        }
    }

    private void updateHeight(int newHeight) {
        if (newHeight > graphHeight) {
            graphHeight = newHeight;
        }
    }

    private boolean containsNode(GraphNode node) {
        if (node == null) {
            return false;
        }

        for (GraphNode n : nodes) {
            if (node.isEqual(n)) {
                return true;
            }
        }

        return false;
    }

    public GraphNode findNode(GraphNode node) {
        if (node == null) {
            return null;
        }

        for (GraphNode n : nodes) {
            if (node.isEqual(n)) {
                return n;
            }
        }

        return null;
    }

    public void printGraph() {
        for (GraphNode graphNode : nodes) {
            System.out.print(graphNode.data);
            System.out.println(" #Level" +graphNode.level +" & y = " + graphNode.y +
                    " -> "+ graphNode.neighbors);
        }
    }
}
