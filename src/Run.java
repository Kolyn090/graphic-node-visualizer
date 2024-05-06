import java.util.Scanner;
import java.io.File;

// Followed this tutorial on YouTube:
// https://www.youtube.com/watch?v=WHuRRpvNcDU

public class Run {
    public static void main(String[] args) {
        /*
        GraphicTree tree = new GraphicTree(511);
        for (int i = 0; i < 511; i++) {
            tree.add((int) (Math.random() * 100));
        }

        Tree.levelOrderTraversal(tree.root);
        tree.getReady(tree.root);
        tree.drawTree();
         */

        try {
            File file = new File("lib/GraphFile");
            Scanner scanner = new Scanner(file);
            GraphicGraph graph = new GraphicGraph();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitResult = line.split(" ");
                int integer1 = Integer.parseInt(splitResult[0]);
                int integer2 = Integer.parseInt(splitResult[1]);
                graph.addEdge(new GraphNode(integer1), new GraphNode(integer2));
            }
            graph.drawGraph();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
