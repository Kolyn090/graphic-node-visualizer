import javax.swing.*;
import java.awt.*;

public class GraphicTree extends Tree {

    static Node[] tab;
    static int i = 0;
    JFrame fenetre = null;

    GraphicTree(int nodeNumber) {
        tab = new Node[nodeNumber];
        fenetre = new JFrame();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(600, 600);
        fenetre.setLocationRelativeTo(null);
        fenetre.setLayout(new BorderLayout());
    }

    public void getReady(Node node) {
        tab[i] = node;
        i++;
        if (node.left != null) {
            getReady(node.left);
        }
        if (node.right != null) {
            getReady(node.right);
        }
    }

    public void drawTree() {
        JScrollPane scrollPane = new JScrollPane();
        fenetre.add(scrollPane, BorderLayout.CENTER);
        JLabel label = new DrawingTree(tab, howmany);
        JPanel drawPanel = new JPanel();
        drawPanel.setLayout(new BorderLayout());
        drawPanel.add(label, "Center");
        drawPanel.setPreferredSize(new Dimension((int) ((Math.pow(2, getHeight(root)))*20+40)*2, (getHeight(root)-1)*(h+20)+20));
        new Mover(drawPanel);
        scrollPane.setViewportView(drawPanel);

        fenetre.setVisible(true);
    }
}
