public class BlackJack extends Deck {
    BlackJack() {
        startGame(); // calls the start game method
    }

    public void startGame() {
        // Deck deck = new Deck();
        Card card;

        // Dealer Hand
        Hand dealerHand = new Hand();

        // hidden card.
        final Card hiddenCard;
        hiddenCard = this.deck.pop(); // gets the hidden card for the dealer
        dealerHand.updateSum(hiddenCard); // updates the sum of the dealer's cards

        // Card on hand.
        card = this.deck.pop();
        dealerHand.updateSum(card); // updates the sum of the dealer's cards
        dealerHand.addCard(card); // adds the card to the dealer's hand
        System.out.println("Dealer Hand: " + dealerHand.showHand());
        System.out.println("Dealer Sum: " + dealerHand.getSum());

        // Player Hand.
        Hand playerHand = new Hand();
        for (int i = 0; i < 2; i++) {
            card = this.deck.pop();
            playerHand.updateSum(card);
            playerHand.addCard(card);
        }
        System.out.println("Player Hand: " + playerHand.showHand());
        System.out.println("Player Sum: " + playerHand.getSum());

    }

}