public class Card {
    String value;
    String sign;

    Card(String sign, String value) { // constructor method for card
        this.value = value;
        this.sign = sign;
    }

    public String toString() {
        return value + "-" + sign;
    }

    public int getValue() { // gets the value of the card so we can be able to sum up the cards
        if ("AJQK".contains(value)) {
            if (value.equals("A")) {
                return 11;
            }
            return 10;
        }
        return Integer.parseInt(value);
    }

    public boolean cardIsAce() {
        return value.equals("A"); // return true if value == A else false.
    }   

    public String path(){
        return "./cards/" + toString() + ".png";
    }
}