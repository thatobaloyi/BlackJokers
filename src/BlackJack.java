import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Stack;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BlackJack extends Deck {
    JFrame frame = new JFrame();
    JPanel panel;
    Hand dealerHand = new Hand();
    Hand playerHand = new Hand();
    JButton hit = new JButton("HIT");
    JButton stand = new JButton("STAND");
    JButton help = new JButton("HELP");
    JButton info = new JButton("INFO");
    Card hiddenCard;

    BlackJack() {
        startGame(); // calls the start game method
        cardDraw();
        Stack<Card> deck = this.deck;
        int cardwidth = 121;
        int cardheight = 170;
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                hit.setFocusable(false);
                stand.setFocusable(false);
                hit.setBounds(310, 600, 80, 25);
                stand.setBounds(410, 600, 80, 25);
                hit.setBorderPainted(false);
                stand.setBorderPainted(false);
                hit.setBackground(Color.WHITE);
                stand.setBackground(Color.WHITE);

                super.paintComponent(g);
                Image backgroundImage = new ImageIcon(
                        getClass().getResource("./background/eyestetix-studio-m0EzHtexapU-unsplash.jpg")).getImage();
                g.drawImage(backgroundImage, 0, 0, 1280, 1000, null);
                Image hiddenImage;
                if ((stand.isEnabled())) {
                    hiddenImage = new ImageIcon(getClass().getResource("./cards/back_dark.png")).getImage();
                } else {
                    hiddenImage = new ImageIcon(getClass().getResource(hiddenCard.path())).getImage();
                }
                g.drawImage(hiddenImage, 5, 25, cardwidth, cardheight, null);
                for (int i = 0; i < dealerHand.hand.size(); i++) {
                    Image card = new ImageIcon(getClass().getResource(dealerHand.hand.get(i).path())).getImage();
                    g.drawImage(card, cardwidth + 10 + (cardwidth + 5) * i, 25, cardwidth, cardheight, null);
                }

                for (int i = 0; i < playerHand.hand.size(); i++) {
                    Image card = new ImageIcon(getClass().getResource(playerHand.hand.get(i).path())).getImage();
                    g.drawImage(card, 20 + (cardwidth + 5) * i, 400, cardwidth, cardheight, null);
                }

            }
        };

        panel.setSize(800, 700);
        panel.setBackground(Color.green);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(panel);
        panel.add(hit);
        panel.add(stand);
        panel.add(help);
        panel.add(info);

        hit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card card = deck.pop();
                playerHand.updateSum(card);
                playerHand.addCard(card);
                cardDraw();
                panel.repaint();
            }
        });

        stand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stand.setEnabled(false);
                hit.setEnabled(false);
                Card card = deck.pop();
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

}