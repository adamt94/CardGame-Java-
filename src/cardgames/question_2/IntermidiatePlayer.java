package question_2;

import java.util.ArrayList;

/**
 * Created by adam on 17-Jan-15.
 */
public class IntermidiatePlayer extends BasicPlayer {
    private Hand hand;
    private int balance;
    private int bet = 10;
    private int dealerscard;

    public IntermidiatePlayer(){
        hand = new Hand();
        balance = 200;

    }


    @Override
    public Hand newHand() {

        hand = new Hand();
        return hand;
    }

    @Override
    public int makeBet() {
        if(balance>0) {

            return bet;
        }else{
            return 0;
        }
    }

    @Override
    public int getBet() {
        return bet;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean hit() {
        while(dealerscard>=7 && getHandTotal()<=17)
        {
            return true;

        }
        while(dealerscard<6 && getHandTotal()<=12)
        {
            return  true;
        }


        return false;
    }

    @Override
    public void takeCard(Card c) {
        hand.addSingleCard(c);

    }

    @Override
    public boolean settleBet(int p) {

        balance = balance+p;
        System.out.println("new "+balance+" "+p);
        if(balance<=0)
        {
            return false;
        }
        return true;
    }

    @Override
    public int getHandTotal() {
        int total =0;
        for(int i =0; i<hand.getTotalValue().length; i++)
        {
            if(hand.getTotalValue()[i]<=21&& hand.getTotalValue()[i]>total)
            {
                total = hand.getTotalValue()[i];
            }
            else if(total ==0){
                total = hand.getTotalValue()[i];
            }
        }
        return total;
    }

    @Override
    public boolean blackjack() {

        return hand.getInHand().get(0).isBlackJack(hand.getInHand().get(0),hand.getInHand().get(1));
    }

    @Override
    public boolean isBust() {
        if(getHandTotal()<=21)
            return false;
        return true;
    }

    @Override
    public Hand getHand() {
        return hand;
    }

    @Override
    public void viewDealerCard(Card c) {
        System.out.println(c);
        dealerscard = c.getRank().getValue();


    }

    @Override
    public void viewCards(ArrayList<Card> cards) {

    }

    @Override
    public void newDeck() {
        System.out.println("NEW DECK");

    }
}
