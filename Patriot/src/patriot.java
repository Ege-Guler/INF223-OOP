import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

class canvas extends Canvas implements KeyListener{

    private double time;

    private int aircraftX;
    private static int aircraftY = 50;

    private int aircraftSpeed;

    private int aaX;
    private static int aaY = 400; // assumption

    private double missileX;
    private double missileY;
    private static int missileSpeed = 5;
    private static int barrelX;
    private static int barrelY;
    private int barrelAngle;
    private boolean isShot;
    private boolean isHit;


    private Rectangle aircraft;
    private Rectangle aa;
    private Rectangle barrel;

    public canvas(){

        time = 0.0;
        aircraftX = 20;
        aircraftSpeed = 5;

        aaX = random(450, 1100);
        aaY = 500;

        missileX = aaX + 20;
        missileY = 520;

        barrelAngle = 45;

        // fill

        isShot = false;
        isHit = false;

        addKeyListener(this);
    }
    public void paint(Graphics p){
        p.setColor(Color.BLACK);
        p.fillRect(aircraftX, aircraftY, 100,30);
        p.fillRect(aaX, aaY, 100, 50);
        p.setColor(Color.RED);
        p.fillOval((int)missileX, (int)missileY, 20, 20);
        p.setColor(Color.GRAY);
        p.drawString("Angle: " + barrelAngle, 1120, 40);
        p.drawString(String.format("X_Speed: %.2f Y_Speed %.2f", (-missileSpeed*Math.cos(Math.toRadians(barrelAngle))), (missileSpeed*Math.sin(Math.toRadians(barrelAngle)))), 1120, 65);

        if(isShot){
            missileX = (time * (-missileSpeed*Math.cos(Math.toRadians(barrelAngle))) + missileX);
            missileY = (time * (-missileSpeed*Math.sin(Math.toRadians(barrelAngle))) + missileY);
        }
        if(aircraftX > 1200){
            reset();
        }
        if(missileX >= aircraftX && missileX <=aircraftX + 100 && missileY >= aircraftY && missileY <= aircraftY + 30){
            isHit = true;
            System.out.println("yey");
        }
        if(isHit){
            p.setColor(Color.RED);
            p.fillOval(aircraftX + random(-25, 25), aircraftY + random(-25, 25), 10, 10);
            p.fillOval(aircraftX + random(-15, 15), aircraftY + random(-15, 15), 15, 15);
            p.drawString("Mayday!", aircraftX + 110, aircraftY);
        }

        try{
            Thread.sleep(50);
        }catch (Exception e){}

        time += 0.05;

        aircraftX = (int) (time*aircraftSpeed + aircraftX);
        repaint();
    }
    public void reset(){
        time = 0.0;
        aircraftX = 20;
        aaX = random(450, 1100);
        aaY = 500;
        missileX = aaX + 20;
        missileY = 520;

        barrelAngle = 45;

        isShot = false;
        isHit = false;

    }
    public int random(int origin, int bound){
        Random random = new Random();
        return random.nextInt(origin, bound);
    }
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        switch (key){
            case(KeyEvent.VK_RIGHT)-> barrelAngle += 5;
            case(KeyEvent.VK_LEFT)-> barrelAngle -= 5;
            case(KeyEvent.VK_UP)-> isShot = true;
        }
        //if(key == KeyEvent.VK_RIGHT) barrelAngle += 5;
        //if(key == KeyEvent.VK_LEFT) barrelAngle -= 5;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}

public class patriot implements WindowListener {
    private Frame frame;
    private canvas canvas;


    public patriot(){
        frame = new Frame();
        canvas = new canvas();

        frame.addWindowListener(this);
        frame.add(canvas);
        frame.setSize(1400, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("AA");

    }

    public static void main(String[] args) {
        patriot game = new patriot();
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
}
