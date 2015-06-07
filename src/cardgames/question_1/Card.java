package question_1;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Adam on 17/12/2014.
 * Class creates all the cards
 */
public class Card implements Comparable<Card>,Serializable {

    private Rank rank;
    private Suit suit;
    private static final long serializationID = 100;

    public  enum Rank {//stores each rank and an int value of the card
        ACE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),JACK(10),QUEEN(10),KING(10);

        private int value;
          Rank(int value){
            this.value = value;
        }
        public Rank getNext(Rank valueOfCard){
           return valueOfCard = Rank.values()[valueOfCard.ordinal()+1];




        }
        public int getValue(){
            return value;
        }

    }
    public static enum  Suit{
        DIAMANDS, CLUBS,SPADES,HEARTS;

    }


    public Card(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;

    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }


    //adds to cards together
    public  int sum(Card c, Card b){
        int sum = c.rank.value + b.rank.value;
        return sum;

    }

    //checks if two cards are 10 value card and ace
    public  boolean isBlackJack(Card a, Card b){
        if(((a.getRank() == rank.ACE) || (b.getRank() == rank.ACE))&& ((b.rank.value ==10)||(a.rank.value ==10)))
            return true;
        else return false;

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Rank: "+rank).append(" Suit: " + suit);
        return s.toString();
    }

    //compare each card by rank ascending
    @Override
    public int compareTo(Card c) {
        if(this.rank.ordinal() > c.rank.ordinal())
            return 1;
        if(this.rank.ordinal() < c.rank.ordinal())
            return -1;
        else{
            return 0;
        }



    }

    //compare each card by rank descending
    public static class CompareDescending implements Comparator<Card>{

        @Override
        public int compare(Card a, Card b) {
            if(a.getRank().ordinal()< b.getRank().ordinal())
                return 1;
            if(a.getRank().ordinal() > b.getRank().ordinal())
                return -1;
            else{
                return 0;
            }
        }
    }
    //compare each cards suit
    public static class CompareSuit implements Comparator<Card>{

        @Override
        public int compare(Card a, Card b) {
            return a.suit.compareTo(b.suit);


        }
    }
}
