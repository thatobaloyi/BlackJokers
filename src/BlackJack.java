import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class BlackJack extends JFrame implements ActionListener {
    Deck deck;
    Hand dealerHand;
    Hand playerHand;
    JButton hit = new JButton("HIT");
    JButton stand = new JButton("STAND");
    JButton help = new JButton("HELP");
    JButton info = new JButton("INFO");
    JButton reset = new JButton("RESET");
    Card hiddenCard;
    GamePanel panel;

    BlackJack() {
        startGame(); // calls the start game method
        gameFrameSetup();

        reset.addActionListener(this);

        hit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card card = deck.getCard();
                playerHand.aceCount += card.cardIsAce() ? 1 : 0;
                playerHand.updateSum(card);
                playerHand.addCard(card);
                if(playerCountWithAce() > 21){
                    hit.setEnabled(false);
                }
                cardDraw();
                panel.repaint();
            }
        });

        stand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dealerHand.updateSum(hiddenCard);
                stand.setEnabled(false);
                hit.setEnabled(false);
                Card card = deck.getCard();
                dealerHand.aceCount += card.cardIsAce() ? 1 : 0;
                while (dealerHand.sum < 17) {
                    dealerHand.updateSum(card);
                    dealerHand.addCard(card);
                }
                cardDraw();
                panel.repaint();
            }
        });

        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel,
                        "The goal of blackjack is to beat the dealer's hand without going over 21." +
                                "\nFace cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand." +
                                "\nEach player starts with two cards, one of the dealer's cards is hidden until the end."
                                +
                                "\nTo 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn."
                                +
                                "\nIf you go over 21 you bust, and the dealer wins regardless of the dealer's hand." +
                                "\nIf you go over 21 you bust, and the dealer wins regardless of the dealer's hand." +
                                "\nIf you are dealt 21 from the start (Ace & 10), you got a blackjack.",
                        "GUIDE", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel,
                        "This project was done by the 'Black Jokers' Team for Computer Science 102 in 2023.\n Team Members: \n - Thato Baloyi \n - Milani Tetani\n - Esihle Nogoqa\n - Nokubonga Sithole",
                        "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        

    }

    public void startGame() {
        cardDraw();
        dealerHand = new Hand();
        playerHand = new Hand();
        deck = new Deck();
        this.hiddenCard = deck.getCard();

        Card card;
        // Card on hand.

        card = deck.getCard();
        dealerHand.aceCount += this.hiddenCard.cardIsAce() ? 1 : 0;
        dealerHand.aceCount += card.cardIsAce() ? 1 : 0;

        System.out.println(dealerHand.aceCount);
        dealerHand.updateSum(card); // updates the sum of the dealer's cards
        dealerHand.addCard(card); // adds the card to the dealer's hand
        System.out.println("Dealer Hand: " + dealerHand.showHand());
        System.out.println("Dealer Sum: " + dealerHand.getSum());

        // Player Hand.
        for (int i = 0; i < 2; i++) {
            card = deck.getCard();
            playerHand.aceCount += card.cardIsAce() ? 1 : 0;
            playerHand.updateSum(card);
            playerHand.addCard(card);
        }
        System.out.println("Player Hand: " + playerHand.showHand());
        System.out.println("Player Sum: " + playerHand.getSum());

    }

    public void cardDraw() {
        try {
            File sound = new File("./sound/sounds_card.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.exit(1);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            this.remove(panel);
            startGame();
            gameFrameSetup();
            SwingUtilities.updateComponentTreeUI(this);

        }
    }

    public void gameFrameSetup() { // sets up the frame.
        panel = new GamePanel(dealerHand, playerHand, hit, stand, info, help, hiddenCard, reset);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.add(panel);
        panel.add(hit);
        hit.setEnabled(true);
        stand.setEnabled(true);
        panel.add(reset);
        panel.add(stand);
        panel.add(help);
        panel.add(info);
    }

    public int playerCountWithAce(){
        while(playerHand.sum > 21 && playerHand.aceCount > 0){
            playerHand.sum = playerHand.sum - 10;
            playerHand.aceCount -= 1;
            playerHand.sumWithAce = playerHand.sum;
        }
        return playerHand.sum;
    }

    public int dealerCountWithAce(){
        while(dealerHand.sum > 21 && dealerHand.aceCount > 0){
            dealerHand.sum = dealerHand.sum - 10;
            dealerHand.aceCount -= 1;
            dealerHand.sumWithAce = dealerHand.sum;
        }
        return dealerHand.sum;
    }

}