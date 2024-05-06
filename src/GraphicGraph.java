import javax.swing.*;
import java.awt.*;

public class GraphicGraph extends Graph{

    private final JFrame window;

    GraphicGraph() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());
    }

    public void drawGraph() {
        JScrollPane scrollPane = new JScrollPane();
        window.add(scrollPane, BorderLayout.CENTER);
        GraphicHelper graphicHelper = new GraphicHelper(this);
        JLabel label = new DrawingGraph(graphicHelper);
        JPanel drawPanel = new JPanel();
        drawPanel.setLayout(new BorderLayout());
        drawPanel.add(label, "Center");
        drawPanel.setPreferredSize(new Dimension(GraphicHelper.ACE*60, (Graph.graphHeight-1)*(h+30)+30));
        new Mover(drawPanel);
        scrollPane.setViewportView(drawPanel);

        window.setVisible(true);
    }
}
