import javax.swing.*;
import java.awt.*;

public class DrawingTree extends JLabel {

    static Node[] copietao = new Node[100];
    int n = 0;


    public DrawingTree(Node[] nodes, int n) {
        copietao = nodes.clone();
        this.n = n;
    }

    public void paintComponent(Graphics g) {
        for (int x = 0; x < n; x++) {
            g.drawOval(copietao[x].x, copietao[x].y, 40, 40);
            String a = String.valueOf(copietao[x].data);
            g.drawString(a, copietao[x].x+5, copietao[x].y+25);
            if (copietao[x].right != null) {
                g.setColor(Color.red);
                drawArrowLine(g, copietao[x].x + 20, copietao[x].y+40, copietao[x].right.x+20, copietao[x].right.y);
                //drawArrow(g,copietao[x].right.x, copietao[x].right.y, copietao[x].x+20, copietao[x].y+40);
            }
            if (copietao[x].left != null) {
                g.setColor(Color.green);
                drawArrowLine(g, copietao[x].x + 20, copietao[x].y+40, copietao[x].left.x+20, copietao[x].left.y);
                //drawArrow(g,copietao[x].left.x, copietao[x].left.y, copietao[x].x+20, copietao[x].y+40);
            }
            g.setColor(Color.black);
            //g.drawString("", 1, 2);
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

        g.fillPolygon(xpoints, ypoints, 3);
    }
}
