import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> cards;
    private ArrayList<Card> discardedCards;
    public Deck() {
        cards = new ArrayList<Card>();
        discardedCards = new ArrayList<Card>();
        for (int suit = 1; suit <= 4; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    public Card draw() {
        Card removed = cards.remove(cards.size() - 1);
        discardedCards.add(removed);
        return removed;
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        cards.addAll(discardedCards);
        discardedCards.clear();
        java.util.Collections.shuffle(cards);
    }
}
