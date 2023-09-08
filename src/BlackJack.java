import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlackJack extends Deck {
    JFrame frame = new JFrame();
    Hand dealerHand = new Hand();
    Hand playerHand = new Hand();
    JButton hit = new JButton("HIT");
    JButton stay = new JButton("STAY");
    Card hiddenCard;

    BlackJack() {
        startGame(); // calls the start game method
        
        Stack<Card> deck = this.deck;
        int cardwidth = 120;
        int cardheight = 168;
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon(getClass().getResource("./background/eyestetix-studio-m0EzHtexapU-unsplash.jpg")).getImage();
                g.drawImage(backgroundImage, 0,0,1280,1000,null);
                Image hiddenImage;
                if((stay.isEnabled())){
                    hiddenImage = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                }else{
                    hiddenImage = new ImageIcon(getClass().getResource(hiddenCard.path())).getImage();
                }
                g.drawImage(hiddenImage, 5, 5, cardwidth, cardheight, null);
                for (int i = 0; i < dealerHand.hand.size(); i++){
                    Image card = new ImageIcon(getClass().getResource(dealerHand.hand.get(i).path())).getImage();
                    g.drawImage(card,cardwidth + 20 + (cardwidth + 5)*i,5, cardwidth,cardheight,null);
                }

                for (int i = 0; i < playerHand.hand.size(); i++){
                    Image card = new ImageIcon(getClass().getResource(playerHand.hand.get(i).path())).getImage();
                    g.drawImage(card,20 + (cardwidth + 5)*i,400, cardwidth,cardheight,null);
                }
                
            }
        };

        JPanel buttons = new JPanel();
        hit.setFocusable(false);
        buttons.add(hit);
        stay.setFocusable(false);
        buttons.add(stay);

        frame.add(buttons, BorderLayout.SOUTH);
        panel.setSize(800,700);
        panel.setBackground(Color.green);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(panel);

        hit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Card card = deck.pop();
                playerHand.updateSum(card);
                playerHand.addCard(card);
                panel.repaint();
            }
        });

        stay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                stay.setEnabled(false);
                hit.setEnabled(false);
                Card card = deck.pop();
                while(dealerHand.sum < 17){
                    dealerHand.updateSum(card);
                    dealerHand.addCard(card);
                }
                panel.repaint();
            }
        });
    }


    public void startGame() {
        // Deck deck = new Deck();
        Card card;

        // hidden card.
        hiddenCard = this.deck.pop(); // gets the hidden card for the dealer
        System.out.println(hiddenCard.toString());
        dealerHand.updateSum(hiddenCard); // updates the sum of the dealer's cards

        // Card on hand.
        card = this.deck.pop();
        dealerHand.updateSum(card); // updates the sum of the dealer's cards
        dealerHand.addCard(card); // adds the card to the dealer's hand
        System.out.println("Dealer Hand: " + dealerHand.showHand());
        System.out.println("Dealer Sum: " + dealerHand.getSum());

        // Player Hand.
        for (int i = 0; i < 2; i++) {
            card = this.deck.pop();
            playerHand.updateSum(card);
            playerHand.addCard(card);
        }
        System.out.println("Player Hand: " + playerHand.showHand());
        System.out.println("Player Sum: " + playerHand.getSum());

    }

}