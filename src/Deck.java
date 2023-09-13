import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck = new Stack<>(); // class Stack, accessible to all the methods in the class

    Deck() {
        buildDeck(deck);
        shuffleDeck(deck);
    }

    public static void buildDeck(Stack<Card> deck) {
        String[] signs = { "spades", "hearts", "clubs", "diamonds" };
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "A", "J", "Q", "K" };
        for (int i = 0; i < signs.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card(signs[i], values[j]); // creates a new card using the sign and the value.
                deck.push(card); // adds the new card to our deck of cards.
            }
        }
        // System.out.println(deck); // prints the created deck.
    }

    public static void shuffleDeck(Stack<Card> deck) { // shuffles our deck of cards
        // I am using fisher - yates since it uses a for loop to shuffle once O(n),
        // unlike riffle shuffle where I have to create more arrays
        Random random = new Random();
        for (int i = deck.size() - 1; i >= 0; i--) {
            int j = random.nextInt(deck.size());
            Collections.swap(deck, i, j);
        }
        // System.out.println(deck); // prints the shuffled deck.
    }

    public Stack<Card> getDeck(){
        return this.deck;
    }

    public Card getCard(){
        return deck.pop();
    }

}
