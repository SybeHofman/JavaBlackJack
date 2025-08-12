public class Card {
    private int rank;
    private int suit; //1 - spades, 2 - clubs, 3 - hearts, 4 - diamonds
    private boolean soft;

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
        if(rank == 13) {
            soft = true;
        } else soft = false;
    }

    public int getValue() {
        return rank + 1;
    }

    public void usedSoft() {
        soft = false;
    }

    public boolean isSoft() {
        return soft;
    }

    public int blackJackValue() {
        if(rank >= 10 && rank != 13) {
            return 10;
        } else if(rank == 13) {
            return 11;
        } else{
            return rank + 1;
        }
    }

    public String toString() {
        String[] suits = {"", "Spades", "Clubs", "Hearts", "Diamonds"};
        String[] ranks = {"", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        return ranks[rank] + " of " + suits[suit];
    }
}