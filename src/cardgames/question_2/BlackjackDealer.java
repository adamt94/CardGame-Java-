package question_2;

import java.util.ArrayList;

/**
 * Created by Adam on 30/12/2014.
 */
public class BlackjackDealer  implements Dealer{
    private Deck deck;
    private Hand dealersHand;
    private int betsPlaced;
    private Player[] players;

    public BlackjackDealer(){
        deck = new Deck();
        dealersHand = new Hand();


    }

    public Hand getDealersHand() {
        return dealersHand;
    }

    public void checkCardsRemaining(ArrayList<Player> players){
        //get a new deck of cards if it less than a 1/4
        if(deck.size() <13)
        {
            deck = new Deck();
            for (Player player:players)
            {
                player.newDeck();
            }
        }
    }
    @Override
    public void assignPlayers(ArrayList<Player> p) {
        players = new Player[p.size()];
        for(int i=0; i<players.length;i++)
        {
           players[i] = p.get(i);
        }


    }

    @Override
    public void takeBets() {
       for(int i = 0; i<players.length;i++)
       {
           betsPlaced += players[i].makeBet();
       }

    }

    @Override
    public void dealFirstCards() {
        deck.shuffle();
        for (int i =0; i<players.length;i++)
        {
           players[i].getHand().addSingleCard(deck.deal());
           players[i].getHand().addSingleCard(deck.deal());
        }
        dealersHand.addSingleCard(deck.deal());
    }

    @Override
    public int play(Player p) {

        while (p.isBust() != true  ) {
            if(p.hit() == true) {

                p.takeCard(deck.deal());

            }
            else
            {
                return p.getHandTotal();
            }


        }




        return p.getHandTotal();
    }

    @Override
    public int playDealer() {
        while(scoreHand(dealersHand)<17)
        {
            dealersHand.addSingleCard(deck.deal());

        }

        return scoreHand(dealersHand);
    }

    @Override
    public int scoreHand(Hand h) {

        int score =0;
        for(int i =0; i<h.gettValue().size();i++)
        {
            if(h.gettValue().get(i)<=21 && h.gettValue().get(i)>score)
            {
                score = h.gettValue().get(i);
            }
            if(score==0)
            {
                score = h.gettValue().get(i);
            }

        }
        return score;
    }

    @Override
    public void settleBets() {
        boolean dealerBlackjack = false;
        for(int i =0; i<players.length;i++)
        {
            //checks if dealer hand i black jack
            if(dealersHand.getInHand().get(0).isBlackJack(dealersHand.getInHand().get(0), dealersHand.getInHand().get(1)))
            {
                dealerBlackjack =true;
            }
            //checks if player has blackjack or dealer
             if(players[i].blackjack()==true|| dealerBlackjack==true)
            {
                if(dealerBlackjack == true && players[i].blackjack()==true)
                {
                    System.out.println("BlackJACK");
                    //if dealer and player blackjack then draw
                    players[i].settleBet(0);
                }
                 //check if only dealer has blackjack return
               else  if(dealerBlackjack == true && players[i].blackjack()!=true){
                    players[i].settleBet(players[i].getBet()*-1);
                }


                else {
                    //player gets black jack
                    players[i].settleBet(players[i].getBet() * 2);
                }


            }
            //if player has a better hand the ndealer
            else if(players[i].isBust() == false &&  players[i].getHandTotal() > scoreHand(dealersHand))
            {

              players[i].settleBet(players[i].getBet());
            }
            // if player hand = dealers hand
            else if( players[i].isBust() == false && players[i].getHandTotal()== scoreHand(dealersHand)){
                players[i].settleBet(0);
            }
            //if dealers goes bust and player does not
            else if (players[i].isBust() == false && scoreHand(dealersHand)>21)
                players[i].settleBet(players[i].getBet());

            //player loses if none of above condition met (bust or dealer has better hand)
            else{

                players[i].settleBet((players[i].getBet()*-1));
            }

            //create new hand for dealer for next round
            players[i].newHand();

        }
        //create new hand for dealer for next round
        dealersHand = new Hand();

    }
}
