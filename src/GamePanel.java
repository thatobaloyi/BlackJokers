import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class GamePanel extends JPanel{
    final int cardwidth = 121;
    final int cardheight = 170;

    Hand dealerHand;
    Hand playerHand;
    JButton hit;
    JButton stand;
    JButton help;
    JButton info;
    JButton reset;
    Card hiddenCard;
    
    GamePanel(Hand dealerHand, Hand playerhHand, JButton hit, JButton stand,JButton info, JButton help,  Card hiddencard, JButton reset){
            this.dealerHand = dealerHand;
            this.playerHand = playerhHand;
            this.hit = hit;
            this.reset = reset;
            this.info = info;
            this.help = help;
            this.stand = stand;
            this.hiddenCard = hiddencard;
            this.setSize(800, 700);
        }

    @Override
    public void paintComponent(Graphics g) {
        hit.setFocusable(false);
        stand.setFocusable(false);
        info.setFocusable(false);
        help.setFocusable(false);
        hit.setBounds(310, 600, 80, 25);
        stand.setBounds(410, 600, 80, 25);
        info.setBounds(700, 5, 80, 25);
        help.setBounds(610, 5, 80, 25);
        reset.setBounds(2, 5, 80, 25);
        info.setBorderPainted(false);
        help.setBorderPainted(false);
        hit.setBorderPainted(false);
        stand.setBorderPainted(false);
        hit.setBackground(Color.WHITE);
        stand.setBackground(Color.WHITE);
        info.setBackground(Color.WHITE);
        help.setBackground(Color.WHITE);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));

        super.paintComponent(g);
        Image backgroundImage = new ImageIcon(
            getClass().getResource("./background/eyestetix-studio-m0EzHtexapU-unsplash.jpg")).getImage();
            g.drawImage(backgroundImage, 0, 0, 1280, 1000, null);
        Image hiddenImage;
        String dealSum = Integer.toString(dealerHand.getSum());
        String plyrSum = Integer.toString(playerHand.getSum());

        g.drawString(String.format("Dealer Sum: %s", dealSum), 300, 28);
        g.drawString(String.format("Player Sum: %s", plyrSum), 300, 360);
        if ((stand.isEnabled())) {
            hiddenImage = new ImageIcon(getClass().getResource("./cards/back_dark.png")).getImage();
        } else {
            hiddenImage = new ImageIcon(getClass().getResource(hiddenCard.path())).getImage();
        }
        g.drawImage(hiddenImage, 20, 40, cardwidth, cardheight, null);
        for (int i = 0; i < dealerHand.hand.size(); i++) {
            Image card = new ImageIcon(getClass().getResource(dealerHand.hand.get(i).path())).getImage();
            g.drawImage(card, cardwidth + 25 + (cardwidth + 5) * i, 40, cardwidth, cardheight, null);
        }

        for (int i = 0; i < playerHand.hand.size(); i++) {
            Image card = new ImageIcon(getClass().getResource(playerHand.hand.get(i).path())).getImage();
            g.drawImage(card, 20 + (cardwidth + 5) * i, 400, cardwidth, cardheight, null);
        }

        
    }
}

