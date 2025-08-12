import java.util.ArrayList;
public class Dealer {
    private ArrayList<Card> cards;
    private int totalValue;
    private boolean soft; //true if dealer has an Ace and total value is 21 or less

    public Dealer() {
        cards = new ArrayList<>();
        totalValue = 0;
        soft = false;
    }
    
    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getFirstCards() {
        return "" + cards.get(0).toString();
    }

    public void clear() {
        cards.clear();
        totalValue = 0;
        soft = false;
    }

    public String toString() {
        return "" + cards.toString() + " for a total of " + totalValue;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void addCard(Card card) {
        cards.add(card);
        soft = checkSoft();

        int cardValue = card.blackJackValue();
        totalValue += cardValue;

        while(soft && (totalValue > 21)) {
            totalValue -= 10;
            System.out.println("total value is now " + totalValue);
            cardValue = 1;
            removeSoft();
            soft = checkSoft();
        }
    }

    private boolean checkSoft() {
        for(Card card : cards) {
            if(card.isSoft()) {
                return true;
            }
        }
        return false;
    }

    private void removeSoft() {
        for(Card card : cards) {
            System.out.println(card);
            System.out.println(card.isSoft());
            if(card.isSoft()) {
                card.usedSoft();
                break;
            }
        }
    }
}
