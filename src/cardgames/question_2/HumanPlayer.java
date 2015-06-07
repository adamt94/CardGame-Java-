package question_2;

import java.util.Scanner;

/**
 * Created by adam on 16-Jan-15.
 */
public class HumanPlayer extends BasicPlayer {
    private Hand hand;
    private int balance;
    Scanner scan;
    private int bet;

    public HumanPlayer(){
        hand = newHand();
        balance = 200;

    }

    @Override
    public boolean blackjack() {
        return hand.getInHand().get(0).isBlackJack(hand.getInHand().get(0),hand.getInHand().get(1));
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public Hand newHand() {
        hand = new Hand();
        return hand;
    }

    @Override
    public Hand getHand() {
        return hand;
    }

    @Override
    public boolean hit() {
        System.out.println(hand+"total: "+hand.gettValue());
        System.out.println("Hit or Stick?");
        scan = new Scanner(System.in);
        String option = scan.nextLine();

        if(option.equals("hit")) {

            return true;
        }
      else{
            return false;
        }
    }

    @Override
    public int makeBet() {
        System.out.println("Place bet       Balance: "+balance);
        scan = new Scanner(System.in);
         bet= scan.nextInt();
        while(balance-bet<0)
        {
            System.out.println("not enough in balance");
            bet = scan.nextInt();
        }

        return bet;



    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public boolean isBust() {
        if(getHandTotal()<=21)
            return false;
        return true;
    }

    @Override
    public void viewDealerCard(Card c) {
        System.out.println(c);
    }

    @Override
    public int getHandTotal() {
        int total =0;
        for(int i =0; i<hand.gettValue().size(); i++)
        {
            if(hand.gettValue().get(i)<=21&& hand.gettValue().get(i)>total)
            {
                total = hand.gettValue().get(i);
            }
            else if(total ==0){
                total = hand.gettValue().get(i);
            }
        }
        return total;
    }

    @Override
    public void newDeck() {
        System.out.println("New question_1.Deck");
    }

    @Override
    public boolean settleBet(int p) {

        balance = balance+p;

        if(balance<=0)
        {
            return false;
        }
        return true;
    }

    @Override
    public void takeCard(Card c) {
        hand.addSingleCard(c);
    }


}
