import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Suppress unused warnings for the entire class
@SuppressWarnings("unused")
public class BlackJack extends JFrame implements ActionListener {

    // Declare instance variables
    Deck deck;         // Instance of the Deck class for managing cards
    Hand dealerHand;  // Instance of the Hand class representing the dealer's hand
    Hand playerHand;  // Instance of the Hand class representing the player's hand
    JButton hit = new JButton("HIT");   // Button for the player to request another card
    JButton stand = new JButton("STAND");// Button for the player to stop requesting cards
    JButton help = new JButton("HELP"); // Button to display game rules/help
    JButton info = new JButton("INFO");// Button to display information about the project/team
    JButton reset = new JButton("RESET");// Button to reset the game

    Card hiddenCard;    // Instance of the Card class representing the hidden card of the dealer
    GamePanel panel;    // Instance of the GamePanel class for rendering the game UI
    BetPanel betPanel;  // Instance of the BetPanel class (not defined in the provided code)
    BlackJack() {
        startGame(); // calls the start game method.
        gameFrameSetup(); // calls the game Frame setup method.

        // Add ActionListener for the reset button
        reset.addActionListener(this);

        // Add ActionListener for the hit button
        hit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Draws a card from the deck for the player and updates the game state
                // Displays a message if the player's hand value exceeds 21
                // Repaints the game panel to reflect the updated game state
                // Plays a sound effect for drawing a card

                Card card = deck.getCard();
                playerHand.aceCount += card.cardIsAce() ? 1 : 0;
                playerHand.updateSum(card);
                playerHand.addCard(card);
                if (playerCountWithAce() > 21) {
                    hit.setEnabled(false);
                    stand.setEnabled(false);
                    panel.repaint();
                    JOptionPane.showMessageDialog(panel,
                            "You Have Bursted",
                            "Alert", JOptionPane.PLAIN_MESSAGE);
                }
                panel.repaint();
                cardDraw();
            }
        });

        // Add ActionListener for the stand button
        stand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Completes the dealer's hand according to game rules
                // Determines the winner and displays a corresponding message
                // Disables hit and stand buttons after the game is resolved
                // Repaints the game panel to reflect the final game state
                // Plays a sound effect for drawing a card

                dealerHand.updateSum(hiddenCard);
                stand.setEnabled(false);
                hit.setEnabled(false);
                while (dealerCountWithAce() < 17) {
                    Card card = deck.getCard();
                    dealerHand.aceCount += card.cardIsAce() ? 1 : 0;
                    dealerHand.updateSum(card);
                    dealerHand.addCard(card);
                }
                cardDraw();
                panel.repaint();

                if (dealerCountWithAce() > 21 || playerHand.getSum() > dealerHand.getSum()) {
                    hit.setEnabled(false);
                    stand.setEnabled(false);
                    panel.repaint();
                    JOptionPane.showMessageDialog(panel,
                    "You win!",
                    "Winner", JOptionPane.PLAIN_MESSAGE);
                } 
                else {
                    JOptionPane.showMessageDialog(panel,
                    "You lose!",
                    "Loser", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // Add ActionListener for the help button
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
            } // Displays a message dialog with the game rules/help information
        });

         // Add ActionListener for the info button
        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel,
                        "This project was done by the 'Black Jokers' Team for Computer Science 102 in 2023.\n Team Members: \n - Thato Baloyi \n - Milani Tetani\n - Esihle Nogoqa\n - Nokubonga Sithole",
                        "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
            } // Displays a message dialog with information about the project and team members
        });

    }

    // Method to initialize the game state at the start of the game
    private void startGame() {
         // Draws cards for the dealer and player, initializes their hands and deck
        // Prints the dealer's hand and the player's hand to the console (for debugging purposes)
        cardDraw();
        dealerHand = new Hand();
        playerHand = new Hand();
        deck = new Deck();
        this.hiddenCard = deck.getCard();

        Card card;
        // Card on hand.............

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

    // Method to play a sound effect when drawing a card
    private void cardDraw() {
        try {
            // Loads and plays a sound file representing drawing a card
            File sound = new File("./sound/sounds_card.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            // Exits the program if there is an error loading the sound file
            System.exit(1);
        }

    }

    // ActionListener implementation for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handles the reset button click event
        if (e.getSource() == reset) {
            // Removes the game panel, resets the game state, and sets up the game frame again
            this.remove(panel);
            startGame();
            gameFrameSetup();
            // SwingUtilities.updateComponentTreeUI(this);....

        }
    }

    // Method to set up the game frame and UI components
    private void gameFrameSetup() { // sets up the frame.

        // Creates a new GamePanel instance with the specified components and layout
        // Configures the game frame properties (size, location, visibility, etc.)
        // Adds the game panel and buttons to the frame for display

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

    // Method to handle special cases where an Ace card's value is considered as 1
    private int playerCountWithAce() {
        while (playerHand.sum > 21 && playerHand.aceCount > 0) {
            // Adjusts the player's hand value by subtracting 10 if it's over 21 and has an Ace
            playerHand.sum = playerHand.sum - 10;
            playerHand.aceCount -= 1;
            playerHand.sumWithAce = playerHand.sum;
        }
        // Returns the adjusted hand value
        return playerHand.sum;
    }

    // Method to handle special cases where an Ace card's value is considered as 1 for the dealer
    private int dealerCountWithAce() {
        while (dealerHand.sum > 21 && dealerHand.aceCount > 0) {
            // Adjusts the dealer's hand value by subtracting 10 if it's over 21 and has an Ace
            dealerHand.sum = dealerHand.sum - 10;
            dealerHand.aceCount -= 1;
            dealerHand.sumWithAce = dealerHand.sum;
        }
        // Returns the adjusted hand value
        return dealerHand.sum;
    }

}
