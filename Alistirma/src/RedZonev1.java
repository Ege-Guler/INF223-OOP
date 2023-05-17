import java.awt.*;
import java.awt.event.*;

class CanvasRed1 extends Canvas {
    private boolean isIn;

    public CanvasRed1() {
        setBackground(Color.gray);
    }

    public void paint(Graphics p) {
        p.setColor(isIn ? Color.RED : Color.GRAY);
        p.fillRect(250, 150, 300, 300);
    }

    public void setIsIn(boolean isIn) {
        this.isIn = isIn;
        repaint();
    }
}

public class RedZonev1 implements WindowListener, MouseListener {
    private Frame frame;
    private CanvasRed1 canvas;
    private Label label;

    public RedZonev1() {
        frame = new Frame();
        label = new Label();
        label.setBounds(250, 150, 300, 300);
        label.setBackground(Color.red);
        label.addMouseListener(this);

        canvas = new CanvasRed1();
        frame.addWindowListener(this);
        frame.add(canvas);
        frame.add(label);
        frame.setLayout(null);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    public static void main(String[] args) {
        RedZonev1 app = new RedZonev1();
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        canvas.setIsIn(true);
        label.setBackground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        canvas.setIsIn(false);
        label.setBackground(Color.GREEN);
    }
}