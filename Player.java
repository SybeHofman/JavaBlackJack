import java.util.ArrayList;
import java.util.Scanner;
public class Player {
    private ArrayList<Card> cards;
    private int totalValue;
    private int money;
    private boolean soft; //true if player has an Ace and total value is 21 or less

    public Player() {
        cards = new ArrayList<>();
        totalValue = 0;
        soft = false;
        System.out.print("How much money do you want to start with? ");

        Scanner in = new Scanner(System.in);
        money = in.nextInt();
    }

    public String toString() {
        return "" + cards.toString() + " for a total of " + totalValue + " and the soft state is " + soft;
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
