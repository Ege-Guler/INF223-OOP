import java.awt.*;
import java.awt.event.*;
public class Prime implements WindowListener, ActionListener{
    private Frame frame;
    private Panel panel1;
    private Panel panel2;
    private TextField textField;
    private Button button;
    private Label label;

    public Prime(){

        frame = new Frame();
        panel1 = new Panel();
        panel1.setPreferredSize(new Dimension(200, 100));
        panel1.setSize(new Dimension(10, 100));
        panel1.setLayout(new GridLayout(2, 2));

        textField = new TextField();

        //textField.setSize(100, 30);
        button = new Button("Proceed");
        label = new Label("Answer: ");
        //label.setSize(200, 50);

        panel1.add(textField);
        panel1.add(button);
        panel1.add(label);

        frame.add(panel1);
        //frame.add(panel2);

        panel1.setBackground(Color.GRAY);
        button.addActionListener(this);
        frame.addWindowListener(this);
        //frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setResizable(true);


    }

    public static void main(String[] args) {
        Prime app = new Prime();
    }
    public static boolean isPrime(int number){
        if(number <= 1) return false; // 1 is not prime

        int sqrt = (int) Math.sqrt(number);

        for(int i = 2; i <= sqrt; i++){
            if(number % i == 0) return false;
        }
        return true;
    }
    public void actionPerformed(ActionEvent e)
    {
        String s = textField.getText();
        try {
            label.setText("Answer: " + Integer.valueOf(s) + (isPrime(Integer.valueOf(s)) ? " Prime" : " Non-Prime"));

        }catch (Exception ex){
            label.setText("Please enter a valid number.");
        }

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
