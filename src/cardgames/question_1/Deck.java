package question_1;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Adam on 18/12/2014.
 */
public class Deck implements Iterable<Card>,Serializable {
    private ArrayList<Card> deck;
    private int counter =-1;
    private static final long serializationID = 101;

//creates a new deck of cards and adds them to the arraylist
    public Deck() {
        this.deck = new ArrayList<Card>();

        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {

                deck.add(new Card(suit,rank));

            }
        }




    }

    public ArrayList getDeck()
    {
        return deck;
    }

    //shuffles the deck
    public void shuffle() {
       Collections.shuffle(deck);

        }
    //returns cards and removes it from the deck
    public Card deal() {
        //try to remove card from deck
        try {
            return deck.remove(0);
            // if run out of cards create new deck and remove
        }catch (IndexOutOfBoundsException e)
        {
             deck = newDeck();
            return deck.remove(0);
        }

    }

    //gets the size of the deck
    public int size(){
        return deck.size();
    }

    //calls the constructor which creates a new deck
    public ArrayList newDeck(){

        Deck newDeck = new Deck();
        return newDeck.getDeck();
    }


    // iterates over each card in the deck
    @Override
    public Iterator<Card> iterator() {
       return deck.iterator();
    }
    //iterates over every other card
    public Iterator<Card> iterator2() {
        return new SecondCardIterator();
    }


    //nested class to iterate over every other card
    public class SecondCardIterator implements Iterator<Card>{
        int pos =-1;




        @Override
        public boolean hasNext() {
          if(pos<size()-1)
              return true;
            return false;
        }

        @Override
        public Card next() {
          pos +=2;
          return deck.get(pos);
        }

        @Override
        public void remove() {

        }



    }
}



