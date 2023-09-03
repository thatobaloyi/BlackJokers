import java.util.ArrayList;

public class Hand {
    ArrayList<Card> hand = new ArrayList<>(); // class arraylist, accessible to all the methods in the class
    int sum = 0;
    int aceCount = 0;

    public void addCard(Card card) {
        hand.add(card); // adds the card to the hands array list.
    }

    public ArrayList<Card> showHand() {
        return this.hand; // returns the hands arraylist
    }

    public int getSum() {
        return this.sum; // returns the sum of the cards in the array list
    }

    public int getAceCount() {
        return this.aceCount; // returns the count of aces.
    }

    public void updateSum(Card card) {
        this.sum += card.getValue(); // updates the sum of the cards in the arraylist when the card's are added
    }
}
