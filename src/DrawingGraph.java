import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingGraph extends JLabel {

    private final GraphicHelper graphicHelper;

    public DrawingGraph(GraphicHelper graphicHelper) {
        this.graphicHelper = graphicHelper;
    }

    public void paintComponent(Graphics g) {
        ArrayList<GraphNode>[] sorted = graphicHelper.getSortedGraph();
        graphicHelper.updateX();
        int invert = 0;
        for (ArrayList<GraphNode> graphNodes : sorted) {
            for (GraphNode node : graphNodes) {
                g.drawOval(node.x, node.y, 60, 60);
                String a = String.valueOf(node.data);
                g.drawString(a, node.x+5, node.y+35);
                for (GraphNode neighbor : node.neighbors) {
                    if (invert % 2 == 0) {
                        g.setColor(Color.green);
                    } else {
                        g.setColor(Color.red);
                    }
                    if (!neighbor.neighbors.contains(node)) {
                        if (neighbor.level >= node.level) {
                            drawArrowLine(g, node.x + 30, node.y + 60, neighbor.x + 30, neighbor.y);
                        } else {
                            drawArrowLine(g, node.x + 30, node.y, neighbor.x + 30, neighbor.y+60);
                        }
                    } else {
                        if (neighbor.level >= node.level) {
                            g.setColor(Color.blue);
                            drawArrowLine(g, node.x + 30, node.y + 60, neighbor.x + 30, neighbor.y);
                            drawArrow(g, neighbor.x + 30, neighbor.y, node.x + 30, node.y + 60);
                        }
                    }

                    invert++;
                    g.setColor(Color.black);
                }
            }
        }
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - 5, xn = xm, ym = 5, yn = -5, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - 5, xn = xm, ym = 5, yn = -5, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.setColor(Color.blue);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}
