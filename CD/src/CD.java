import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

class kanvas01 extends Canvas implements MouseListener, MouseMotionListener, Runnable{
    private Point m;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private BufferedImage image5;


    private int w = 96;
    private int h = 96;
    private int positionX;
    private int positionY;
    private boolean bounced;
    private int random;

    private Thread thread;

    public kanvas01()
    {
        try {
            image1 = ImageIO.read(new File("/home/ege/Desktop/INF223/CD/src/1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image2 = ImageIO.read(new File("/home/ege/Desktop/INF223/CD/src/2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image3 = ImageIO.read(new File("/home/ege/Desktop/INF223/CD/src/3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image4 = ImageIO.read(new File("/home/ege/Desktop/INF223/CD/src/4.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            image5 = ImageIO.read(new File("/home/ege/Desktop/INF223/CD/src/5.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setBackground(Color.darkGray);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.positionX = 0;
        this.positionY = 0;
        bounced = false;
        m = new Point(0, 0);
        random = 0;
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics p)
    {
        //p.drawString(m.x+" , "+m.y,200,200);

        Random rand = new Random();
        if(bounced){
            random = rand.nextInt(5);
        }
        switch (random){
            case 1 -> p.drawImage(image1, positionX, positionY, w, h, this);
            case 2 -> p.drawImage(image2, positionX, positionY, w, h, this);
            case 3 -> p.drawImage(image3, positionX, positionY, w, h, this);
            case 4 -> p.drawImage(image4, positionX, positionY, w, h, this);
            default -> p.drawImage(image5, positionX, positionY, w, h, this);
        }
        bounced = false;
    }
    @Override
    public void run(){
        boolean isXpos = true;
        boolean isYpos = true;

        while(true){

            if(positionX > 710) {
                isXpos = false;
                bounced = true;
                //bounced = bounced ? false : true;
            }
            else if(positionX < 0) {
                isXpos = true;
                bounced = true;
                //bounced = bounced ? false : true;
            }

            if(positionY > 500) {
                isYpos = false;
                bounced = true;
                //bounced = bounced ? false : true;
            }
            else if (positionY < 0) {
                isYpos = true;
                bounced = true;
                //bounced = bounced ? false : true;
            }
            if(isYpos == false) positionY-=5;
            else positionY += 5;
            if(isXpos == false) positionX -= 5;
            else positionX += 5;

            try{
                Thread.sleep(50);
            }catch (Exception e){}
            repaint();

        }



    }

    public void mouseMoved(MouseEvent e)
    {
        m=e.getPoint();
        repaint();
    }

    public void mousePressed(MouseEvent e){}
    public void mouseDragged(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

public class CD implements WindowListener
{
    private Frame a;
    private kanvas01 k;

    public CD()
    {
        a = new Frame();
        k = new kanvas01();
        a.addWindowListener(this);
        a.add(k);
        a.setSize(800,600);
        a.setResizable(false);
        a.setVisible(true);
    }

    public static void main(String args[])
    {
        CD uygulama = new CD();
    }

    public void windowOpened(WindowEvent e) {}
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}