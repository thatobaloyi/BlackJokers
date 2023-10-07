import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class GamePanel extends JPanel {
    final int cardwidth = 121;  // Width of a card image
    final int cardheight = 170; // Height of a card image

    Hand dealerHand;                // Reference to the dealer's hand
    Hand playerHand;                // Reference to the player's hand
    JButton hit;                    // Button for the player to request another card
    JButton stand;                  // Button for the player to stop requesting cards
    JButton help;                   // Button to display game rules/help
    JButton info;                   // Button to display information about the project/team
    Card hiddenCard;                // Reference to the hidden card of the dealer
    PlayerBet bet;

    GamePanel(Hand dealerHand, Hand playerhHand, JButton hit, JButton stand, JButton info, JButton help,
            Card hiddencard, PlayerBet bet) {
        // Assigns the provided parameters to the corresponding instance variables
        this.bet = bet;
        this.dealerHand = dealerHand;
        this.playerHand = playerhHand;
        this.hit = hit;
        this.info = info;
        this.help = help;
        this.stand = stand;
        this.hiddenCard = hiddencard;
        this.setSize(800, 700); // Also sets the size of the panel to 800x700
    } // Constructor for the GamePanel class

    // Overrides the paintComponent method of JPanel for custom rendering
    @Override
    public void paintComponent(Graphics g) {
        // Disables focus for buttons and sets their positions and sizes
        hit.setFocusable(false);
        stand.setFocusable(false);
        info.setFocusable(false);
        help.setFocusable(false);
        hit.setBounds(310, 600, 80, 25);
        stand.setBounds(410, 600, 80, 25);
        info.setBounds(700, 5, 80, 25);
        help.setBounds(610, 5, 80, 25);

        // Configures button appearance (background color, border)
        info.setBorderPainted(false);
        help.setBorderPainted(false);
        hit.setBorderPainted(false);
        stand.setBorderPainted(false);
        
        hit.setBackground(Color.WHITE);
        stand.setBackground(Color.WHITE);
        info.setBackground(Color.WHITE);
        help.setBackground(Color.WHITE);

        // Configures font and color for text rendering
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        // Calls the superclass paintComponent method to ensure proper rendering
        super.paintComponent(g);
        // Loads a background image and renders it on the panel
        Image backgroundImage = new ImageIcon(
                getClass().getResource("./background/eyestetix-studio-m0EzHtexapU-unsplash.jpg")).getImage();
        g.drawImage(backgroundImage, 0, 0, 1280, 1000, null);
        Image hiddenImage;
        String dealSum = Integer.toString(dealerHand.getSum());
        String plyrSum = Integer.toString(playerHand.getSum());
        String plyrSumAce = Integer.toString(playerHand.getAceSum());
        String balance = Integer.toString(bet.getBalance());
        String betAmount = Integer.toString(bet.getBet());

          // Renders dealer's and player's hands along with their total sums
         // Determines the image of the hidden card (face down or face up based on the game state)
         // Renders the hidden card or the actual hidden card of the dealer

        g.drawString(String.format("Dealer Sum: %s", dealSum), 330, 28);

        g.drawString(String.format("Balance: $%s", balance), 10, 600);
        g.drawString(String.format("BetAmount: $%s", betAmount), 10, 630);

        if (playerHand.getAceSum() > 0) {
            g.drawString(String.format("Player Sum: %s/%s", plyrSum, plyrSumAce), 330, 360);

        } else {
            g.drawString(String.format("Player Sum: %s", plyrSum), 330, 360);
        }

        if ((stand.isEnabled())) {
            hiddenImage = new ImageIcon(getClass().getResource("./cards/back_dark.png")).getImage();
        } else {
            hiddenImage = new ImageIcon(getClass().getResource(hiddenCard.path())).getImage();
        }
        g.drawImage(hiddenImage, 20, 40, cardwidth, cardheight, null);
              // Renders cards of the dealer's hand
        for (int i = 0; i < dealerHand.showHand().size(); i++) {
            // Loads card images and renders them in the appropriate positions
            Image card = new ImageIcon(getClass().getResource(dealerHand.showHand().get(i).path())).getImage();
            g.drawImage(card, cardwidth + 25 + (cardwidth + 5) * i, 40, cardwidth, cardheight, null);
        }

        // Renders cards of the player's hand
        for (int i = 0; i < playerHand.showHand().size(); i++) {
            // Loads card images and renders them in the appropriate positions
            Image card = new ImageIcon(getClass().getResource(playerHand.showHand().get(i).path())).getImage();
            g.drawImage(card, 20 + (cardwidth + 5) * i, 400, cardwidth, cardheight, null);
        }

    }
}
