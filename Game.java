import java.util.Scanner;
public class Game {
    private Deck deck;

    public Game() {
        deck = new Deck();
    }

    public void start() {
        Player player = new Player();
        Player dealer = new Player();

        Scanner in = new Scanner(System.in);

        player.addCard(deck.draw());
        player.addCard(deck.draw());

        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());

        System.out.println("Welcome to Blackjack! Per standard dealer rules, the dealer hits on 16 and stands on 17. The dealer wins on a tie");
        System.out.println();

        System.out.println("Dealer has: " + dealer);
        System.out.println("You have: " + player);

        boolean hitting = true;
        boolean busted = false;

        //Player sequence
        while(hitting) {
            System.out.print("Do you want to hit? (y/n) ");
            String input = in.nextLine();

            if(input.equals("y")) {
                player.addCard(deck.draw());
                System.out.println("You have: " + player);
                if(player.getTotalValue() > 21) {
                    System.out.println("You busted!");
                    hitting = false;
                    busted = true;
                }
            } else{
                hitting = false;
            }
        }
        System.out.println("Now it is the dealer's turn.");

        //Dealer secquence
        boolean dealerBusted = false;
        while(dealer.getTotalValue() < 17) {
            dealer.addCard(deck.draw());
            System.out.println("Dealer has: " + dealer);
        }

        if(dealer.getTotalValue() > 21) {
            dealerBusted = true;
            System.out.println("The dealer busted!");
        }

        if((player.getTotalValue() > dealer.getTotalValue() && !busted) || (dealerBusted && !busted)) {
            System.out.println("You win!");
        } else{
            System.out.println("Dealer wins");
        }
    }
}
