package question_1;

import question_2.BlackJackTable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Adam on 18/12/2014.
 */
public class CardTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //creates a deck of cards
       question_1.Deck deck = new question_1.Deck();
        //iterate over the cards and print them out
        for(question_1.Card card:deck)
            System.out.println(card);

        //print out every other card in deck
        Iterator it = deck.iterator2();
        while(it.hasNext())
            System.out.println(it.next());
        System.out.println("type yes to continue");
        String decision = scan.nextLine();
        while(!decision.equals("yes"))
        {
           decision= scan.nextLine();
        }







        // shuffle deck
        deck.shuffle();


       //create 4 hands
        question_1.Hand[] hands = new question_1.Hand[4];
        for(int i =0; i<hands.length;i++)
            hands[i]= new question_1.Hand();

        while(deck.size()>0) {
            //add cards to the 4 hands
            for (question_1.Hand hand : hands) {
                hand.addSingleCard(deck.deal());
            }
        }
        // for each loop to print out the cards in each hand
        for(question_1.Hand hand:hands)
            System.out.println(hand);


        //save the hand to a file
        String filename ="hands.ser";

        try {
            FileOutputStream fos = new FileOutputStream(filename);

                ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(hands);
            out.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("hands saved to file");
        System.out.println("type yes to continue");
        String decision1 = scan.nextLine();
        while(!decision1.equals("yes"))
        {
            decision1= scan.nextLine();
        }



        //prints the number of each suit D, H,S,C and prints the total value if its over 100
        for(int i =0; i<hands.length;i++)
        {
            System.out.println("HAND "+i);
            System.out.println("D, H, S, C");
            for(question_1.Card.Suit suit: question_1.Card.Suit.values())
            {
                System.out.print(hands[i].countSuit(suit) + "  ");
            }
            System.out.println();

            for(int j =0; j<hands[i].gettValue().size(); j++) {
                if (hands[i].gettValue().get(j) > 100) {
                    System.out.println("question_1.Hand " + i + " Value: " + hands[i].gettValue().get(j));
                }

            }
            System.out.println("Number of each rank in hand: ");
            for(question_1.Card.Rank ranks : question_1.Card.Rank.values())
            {
                System.out.print(hands[i].countRank(ranks) + "  ");
            }
            System.out.println();
        }


        System.out.println("type yes to continue");
        String decision2 = scan.nextLine();
        while(!decision2.equals("yes"))
        {
            decision2= scan.nextLine();
        }

        hands[0].sortAscending(hands[0]);
        hands[1].sortAscending(hands[1]);
        System.out.println(hands[0]);
        System.out.println(hands[1]);
        hands[2].sortDescending(hands[2]);
        System.out.println(hands[2]);

        Collections.sort(hands[3].getInHand(), new question_1.Card.CompareSuit());
        System.out.println(hands[3]);

        System.out.println("type yes to continue");
        String decision3 = scan.nextLine();
        while(!decision3.equals("yes"))
        {
            decision3= scan.nextLine();
        }


        //loads the files that were saved stores in hands object
        try {
            FileInputStream fiss= new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fiss);
            hands =(question_1.Hand[])in.readObject();
            in.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator it2 = hands[0].iterator();
        while(it2.hasNext())
        {

            hands[1].addSingleCard(hands[0].removePosition(0));
        }
        System.out.println(hands[0]);
        System.out.println(hands[1]);


        System.out.println("type yes to continue");
        String decision4 = scan.nextLine();
        while(!decision4.equals("yes"))
        {
            decision4= scan.nextLine();
        }
//













    }

}
