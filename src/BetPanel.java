import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BetPanel extends JPanel {
    private JTextField betAmountField;       //to enter the bet amount
    private JButton placeBetButton;         //button to place the bet

    public BetPanel() {
        setLayout(new FlowLayout());         //set the layout for this panel
        JLabel betLabel = new JLabel("Enter Bet Amount:");         //input for the bet
        betAmountField = new JTextField(10);                       //text fild for entering the bet amount
        placeBetButton = new JButton("Place Bet");                 //button to place the bet

        this.add(betLabel);                                             //add bet lebel to the main panel
        this.add(betAmountField);                                       //add the bet amount field to the panel
        this.add(placeBetButton);                                       //add the 'placebet' button the the panel
        this.setBackground(Color.BLUE);
        placeBetButton.addActionListener(new ActionListener() {     //add action listener to the button
            public void actionPerformed(ActionEvent e) {
                // Handle the bet placement logic here
                String betAmountStr = betAmountField.getText();      //get the text
                int betAmount = Integer.parseInt(betAmountStr);      //parse it to an interger
                System.out.println("The Button is Pressed");
            }
        });
    }

    public static void main(String[] args){
        BetPanel panelo = new BetPanel();
        JFrame wow = new JFrame();

        wow.setSize(800,800);
        wow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wow.setVisible(true);
        wow.add(panelo);
    }
}