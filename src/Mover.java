import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.MatteBorder;
import javax.swing.event.MouseInputAdapter;

public class Mover extends MouseInputAdapter {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200, 160);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        f.add(scrollPane, BorderLayout.CENTER);

        JPanel view = new JPanel();
        view.add(new JLabel("Some text"));
        view.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLUE));
        view.setBackground(Color.WHITE);
        view.setPreferredSize(new Dimension(230, 200));
        new Mover(view);
        scrollPane.setViewportView(view);

        f.setVisible(true);
    }

    private JComponent m_view            = null;
    private Point      m_holdPointOnView = null;

    public Mover(JComponent view) {
        m_view = view;
        m_view.addMouseListener(this);
        m_view.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        m_view.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        m_holdPointOnView = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        m_view.setCursor(null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point dragEventPoint = e.getPoint();
        JViewport viewport = (JViewport) m_view.getParent();
        Point viewPos = viewport.getViewPosition();
        int maxViewPosX = m_view.getWidth() - viewport.getWidth();
        int maxViewPosY = m_view.getHeight() - viewport.getHeight();

        if(m_view.getWidth() > viewport.getWidth()) {
            viewPos.x -= dragEventPoint.x - m_holdPointOnView.x;

            if(viewPos.x < 0) {
                viewPos.x = 0;
                m_holdPointOnView.x = dragEventPoint.x;
            }

            if(viewPos.x > maxViewPosX) {
                viewPos.x = maxViewPosX;
                m_holdPointOnView.x = dragEventPoint.x;
            }
        }

        if(m_view.getHeight() > viewport.getHeight()) {
            viewPos.y -= dragEventPoint.y - m_holdPointOnView.y;

            if(viewPos.y < 0) {
                viewPos.y = 0;
                m_holdPointOnView.y = dragEventPoint.y;
            }

            if(viewPos.y > maxViewPosY) {
                viewPos.y = maxViewPosY;
                m_holdPointOnView.y = dragEventPoint.y;
            }
        }

        viewport.setViewPosition(viewPos);
    }
}
