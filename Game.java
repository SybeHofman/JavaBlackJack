import java.util.Scanner;
public class Game {
    private Deck deck;
    
    Player player;
    Dealer dealer;

    Scanner in;

    int amountBetting;

    public Game() {
        deck = new Deck();
        
        player = new Player();
        dealer = new Dealer();

        in = new Scanner(System.in);

        amountBetting = 0;
    }

    public void start() {

        System.out.println();
        System.out.println("Welcome to Blackjack! Per standard dealer rules, the dealer hits on 16 and stands on 17. The dealer wins on a tie");
        System.out.println();

        boolean running = true;

        while(running) {
            doRound();
            player.clear();
            dealer.clear();

            if(deck.size() < 15) {
                System.out.println();
                System.out.println("Shuffling the deck");
                deck.shuffle();
            }

            if(player.getMoney() <= 0) {
                System.out.println("You ran out of money! Game over.");
                running = false;
            }

            else{
                System.out.println("Do you want to play another round? (y/n) ");
                if(!in.next().equalsIgnoreCase("y")) {
                    running = false;
                }
            }
        }
    }

    public void doRound() {

        doBetting();

        player.addCard(deck.draw());
        player.addCard(deck.draw());

        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());

        System.out.println();
        System.out.println("Dealer is showing: " + dealer.getFirstCards());
        System.out.println("You have: " + player);

        boolean hitting = true;
        boolean busted = false;

        //Player sequence
        while(hitting) {
            System.out.println();
            System.out.print("Do you want to hit (h) or stand (s)");

            String input = in.nextLine();

            if(input.equals("h")) {
                player.addCard(deck.draw());
                System.out.println();
                System.out.println("You have: " + player);
                if(player.getTotalValue() > 21) {
                    System.out.println();
                    System.out.println("You busted!");
                    hitting = false;
                    busted = true;
                }
            } else if(input.equals("s")) {
                hitting = false;
            }
        }
        System.out.println();
        System.out.println("Now it is the dealer's turn.");

        System.out.println();
        System.out.println("Dealer has: " + dealer);
        //Dealer secquence
        boolean dealerBusted = false;
        while(dealer.getTotalValue() < 17) {
            dealer.addCard(deck.draw());
            System.out.println();
            System.out.println("Dealer has: " + dealer);
        }

        if(dealer.getTotalValue() > 21) {
            dealerBusted = true;
            System.out.println();
            System.out.println("The dealer busted!");
        }

        if((player.getTotalValue() > dealer.getTotalValue() && !busted) || (dealerBusted && !busted)) {
            System.out.println();
            System.out.println("You win!");
            player.changeMoney(amountBetting);
        } else{
            System.out.println();
            System.out.println("Dealer wins");
            player.changeMoney(amountBetting * -1);
        }
    }

    public void doBetting() {
        System.out.println();
        System.out.println("You have $" + player.getMoney() + ". How much do you want to bet? ");
        amountBetting = in.nextInt();
        in.nextLine();
        while(amountBetting > player.getMoney()) {
            System.out.println();
            System.out.println("You cannot bet more money than you have!");
            System.out.println();
            System.out.println("You have $" + player.getMoney() + ". How much do you want to bet? ");
            amountBetting = in.nextInt();
            in.nextLine();
        }
    }
}
