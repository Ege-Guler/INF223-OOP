import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.util.Random;
class canvas extends Canvas implements KeyListener{

    private static int g = 10;
    private double time;
    private int fighterX;
    private int fighterY;
    private int bombY;
    private int targetX;
    private int targetY;
    private int xSpeed = 10;
    private boolean isDropped;
    private boolean isHit;

    private Rectangle target;
    private Rectangle fighter;
    private Rectangle bomb;
    private Font font;

    private int score;
    private int missed;
    private int hit;
    private double difficultyCoefficient;

    public canvas(){
        time = 0.0;
        fighterX = 10;
        fighterY = 100;
        bombY = 100;
        targetX = randomLocation();
        targetY = 550;
        isDropped = false;
        isHit = false;
        missed = 0;
        score = 0;
        hit = 0;
        difficultyCoefficient = 1;


        font = new Font("Courier", Font.ITALIC, 50);
        /*
        target = new Rectangle(targetX, targetY, 50, 50);
        fighter = new Rectangle(fighterX, fighterY, 50, 50);
        bomb = new Rectangle(fighterX, bombY, 10, 10);
        */
        Color c = new Color(154, 239, 245);
        setBackground(c);
        addKeyListener(this);
    }
    public void paint(Graphics p){
        p.drawRect(targetX, targetY, (int)(150 * difficultyCoefficient), 50);
        p.fillRect(targetX, targetY, (int)(150 * difficultyCoefficient), 50);
        p.drawRect(fighterX, fighterY, 50, 5);
        p.drawOval(fighterX, bombY, 10, 10);
        p.fillOval(fighterX, bombY, 10, 10);
        p.setColor(Color.RED);
        p.fillOval(fighterX + 2, bombY + 2, 5, 5);
        p.setColor(Color.BLACK);
        p.setFont(font);
        Font f = new Font("Courier", Font.ROMAN_BASELINE, 20);
        p.setFont(f);
        score = - missed * 100 + hit*300;
        p.drawString("Score: %d | Missed: %d | Hit:%d".formatted(score, missed, hit), 1000, 30);
        p.drawString("---------------------------------", 1000, 50);
        if(bombY <= targetY + 50 && bombY >= targetY && fighterX >= targetX && fighterX <= targetX + 120) isHit = true;
        if(isHit) {
            p.setFont(font);
            //p.drawString("Target Hit", 600, 250);
            hit++;
            reset();
            targetX = randomLocation();
            if(hit % 3 == 0){
                difficultyCoefficient *=0.95;
                System.out.println("targetX:" + (int)(150*difficultyCoefficient));
            }
        }
        else if(fighterX >= 1200) {
            //p.drawString("Target Missed", 600, 250);
            reset();
            missed++;
        }
        try {
            Thread.sleep(50);
        }catch (Exception e){}
        time += 0.05;
        fighterX = (int) (time*xSpeed + fighterX);
        if(isDropped){
            bombY = (int)(0.5*g*Math.pow(time, 2) + bombY);
        }
        repaint();

    }
    public void reset(){
        time = 0.0;
        fighterX = 10;
        fighterY = 100;
        bombY = 100;
        isDropped = false;
        isHit = false;
    }

    public int randomLocation(){
        Random random = new Random();
        return  random.nextInt(450, 1100);
    }
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_DOWN) isDropped = true;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

}
public class Bomber implements WindowListener{
    private Frame frame;
    private canvas canvas;


    public Bomber(){
        frame = new Frame();
        canvas = new canvas();

        frame.addWindowListener(this);
        frame.add(canvas);
        frame.setSize(1400, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("Bomber 1954");
    }

    public static void main(String[] args) {
        Bomber game = new Bomber();
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
