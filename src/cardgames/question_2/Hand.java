package question_2;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Adam on 20/12/2014.
 */
public class Hand implements Iterable<Card>,Serializable {
    private  ArrayList<Card> inHand;
    private  int[] rankCount;
    private int totalValue[];
    private ArrayList<Integer> tValue;
    private static final long serializationID = 102;




     //constructor creates a new hand
    public Hand(){
        inHand = new ArrayList<Card>();
        rankCount = new int[13];
        tValue = new ArrayList<Integer>();
        totalValue = new int[3];


    }
    //constructor takes an array of cards and adds it to this hand
    public Hand(ArrayList<Card> hand){
      inHand.addAll(hand);

    }
    //constructor takes another hand and adds it to this hand
    public Hand(Hand otherHand){
        inHand.addAll(otherHand.getInHand());
    }



    public ArrayList<Card> getInHand() {
        return inHand;
    }



    public int[] getTotalValue() {
        return totalValue;
    }

    public ArrayList<Integer> gettValue() {
        return tValue;
    }

    //calculates the total value of cards  in hand
    public void totalValue() {
        Card c;
        Arrays.fill(totalValue,0); // reset values so same cards are not re-counted
        int aceCount = 0;//checks how many aces are in hand, to calculate different values
        for (int i =0; i< inHand.size();i++)
        {

            c = inHand.get(i);
            Card.Rank ranks = c.getRank();
            totalValue[0] += ranks.getValue();
            if(ranks == Card.Rank.ACE)
            {
                aceCount++;
            }

        }
        if(aceCount >= 1)
        {
            //ace value 1 already calculated so just add 10 to get other value
            totalValue[1] = totalValue[0]+10;
            aceCount = 0;

        }
        if(aceCount == 2)
        {
            aceCount = 0;
            totalValue[2] = totalValue[1]+10;
        }
    }
    public void totalValue2(){
        Card c;
        tValue.clear();
        int aceCount =0;
        int sum =0;
        for (int i =0; i< inHand.size();i++) {
            //first adds the rank value to sum aces set at 1 to start with
            c = inHand.get(i);//get card
            Card.Rank ranks = c.getRank();
            sum +=ranks.getValue();//add card value to sum
            if(ranks== Card.Rank.ACE)
            {
                aceCount++;
            }

        }
        tValue.add(sum);//sum with aces counter as 1
        for(int i =0; i<aceCount; i++)
        {
            sum = sum+10;
            tValue.add(sum);
        }


    }

    // stores how many of cards of the same rank are in hand
    public  void storeRank(){
        Arrays.fill(rankCount,0);

        for(int i = 0; i<inHand.size(); i++)
        {
            for(int j =0 ; j<rankCount.length; j++) {
                if (inHand.get(i).getRank().ordinal() ==j)
                    rankCount[j]++;
            }


//            c = inHand.get(i);
//
//            question_1.Card.Rank ranks = c.getRank();
//            //gets the int value of the rank
//
//
//
//                    switch (ranks) {
//
//                        case ACE: rankCount[0]+=1;
//                            break;
//                        case TWO: rankCount[1]+=1;
//                            break;
//                        case THREE: rankCount[2]+=1;
//                            break;
//                        case FOUR: rankCount[3]+=1;
//                            break;
//                        case SIX: rankCount[4]+=1;
//                            break;
//                        case SEVEN: rankCount[5]+=1;
//                            break;
//                        case EIGHT: rankCount[6]+=1;
//                            break;
//                        case NINE: rankCount[7]+=1;
//                            break;
//                        case TEN: rankCount[8]+=1;
//                            break;
//                        case JACK: rankCount[9]+=1;
//                            break;
//                        case QUEEN: rankCount[10]+=1;
//                            break;
//                        case KING: rankCount[11]+=1;
//                            break;
//                    }







        }


    }
    //adds a card to hand
    public  void addSingleCard(Card card){
        inHand.add(card);
        //when a card added it then updates the total value if cards and number of each rank
        storeRank();
        totalValue2();


    }
    //a dds a array of cards to hand
    public void addCollection(ArrayList<Card> cards){
        inHand.addAll(cards);
        storeRank();
        totalValue2();

    }
    //adds a hand to this hand
    public void addHand(Hand hand){
        inHand.addAll(hand.getInHand());
        storeRank();
        totalValue2();
    }
    //removes a card from hand
    public boolean removeCard(Card card){
        if(inHand.contains(card))
        {
            inHand.remove(card);
            return true;
        }
        return false;
    }

    //remove all the cards from another hand passes as an argument
    public boolean removeHand(Hand hand){
       if(hand.getInHand().isEmpty() == false){
           hand.getInHand().removeAll(hand.getInHand());
           return true;
       }
        return false;

    }
    //removes a card from hand in a specific position
    public Card removePosition(int i){

        return inHand.remove(i);
    }

    //sorts hand in ascending order
    public void sortAscending(Hand hand){
        Collections.sort(hand.getInHand(), new Card.CompareSuit());
        Collections.sort(hand.getInHand());

    }
    //sorts hand in descending order
    public void sortDescending(Hand hand){
        Collections.sort(hand.getInHand(), new Card.CompareSuit());
        Collections.sort(hand.getInHand(), new Card.CompareDescending());

    }
    //returns int of how many of a suit in hand
    public int countSuit(Card.Suit suit){
        int noSuit =0;
        for(int i=0; i<inHand.size();i++)
        {
            if(suit.ordinal()==inHand.get(i).getSuit().ordinal())
                noSuit++;
        }
        return noSuit;


    }
    // counts how many cards of the same rank are in hand
    public int countRank(Card.Rank rank){


        for (int i =0; i < rankCount.length;i++)
        {
             if (rank.ordinal() == i)
             {
              return rankCount[i];
             }

        }
        return -1;
    }


    //if the value passed as argument is greater then the total value of cards in hand return boolean
    public boolean isOver(int n){
        if(totalValue[0]>n)
            return true;
        return false;

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("HAND: ");
        s.append(inHand);
        return s.toString();
    }

    //iterates over each card  in hand
    @Override
    public Iterator<Card> iterator() {
       return inHand.iterator();
    }
}
