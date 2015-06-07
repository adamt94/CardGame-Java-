package question_2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Adam on 30/12/2014.
 */
public class BlackJackTable {
    private Dealer dealer;
    private static ArrayList<Player> players = new ArrayList<Player>();
    private int maxPlayers =8;
    private int minBet = 1;
    private int maxBet = 500;


    public static void main(String[] args) {
        System.out.println("type 1: Basic Game \n type 2: Human Game");
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if(option==1)
        {
            basicGame();
        }
        if (option ==2)
        {
            humanGame();
        }

    }

    public static void basicGame() {
        boolean playersBankrupt = false;//check if players are all bankrupt
        boolean iscountinue = true; //check if user wants to enter more hands


        BasicPlayer p = new BasicPlayer();
        BasicPlayer p2 = new BasicPlayer();
        BasicPlayer p3 = new BasicPlayer();
        BasicPlayer p4 = new BasicPlayer();
        players.add(p);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        BlackjackDealer d = new BlackjackDealer();

        while (iscountinue) {
            Scanner scan = new Scanner(System.in);
            System.out.println("How many Hands?");
            int noHands = scan.nextInt();

            for (int i = 0; i < noHands; i++) {
                d.assignPlayers(players);
                for (int j = 0; j < players.size(); j++)
                    System.out.println("question_2.Player " + j + " Balance: " + players.get(j).getBalance());
                d.checkCardsRemaining(players);
                d.takeBets();

                d.dealFirstCards();
                p.viewDealerCard(d.getDealersHand().getInHand().get(0));
                //  d.play(p);
                for (int j = 0; j < players.size(); j++) {
                    d.play(players.get(j));

                    // System.out.println("question_2.Player play: " + d.scoreHand(p.getHand()));
                    System.out.println("question_2.Player plays " + j + ": " + d.scoreHand(players.get(j).getHand()));
                }

                System.out.println("Dealers play: " + d.playDealer());


                d.settleBets();
                for (int j = 0; j < players.size(); j++) {
                    if (players.get(j).getBalance() <= 0) {
                        players.remove(j);


                    }
                    if (players.size() <= 0)
                        playersBankrupt = true;
                }
                if (playersBankrupt == true) {
                    System.out.println("All players went Bankrupt");
                    break;
                }

            }
            System.out.println("Would you like to continue? yes/no");
            Scanner sin = new Scanner(System.in);
            String decisions = sin.nextLine();
            if (decisions.equals("yes")) {
                iscountinue = true;
                if(playersBankrupt == true)
                {
                    players.clear();
                    for(int i =0; i<4; i++) {

                       Player pl = new BasicPlayer();
                        players.add(pl);
                        System.out.println("Creating new players");
                    }

                }
            }
            else {
                iscountinue = false;
            }


        }


    }

    public static void humanGame(){
        BlackjackDealer d = new BlackjackDealer();
        BasicPlayer p = new BasicPlayer();
        HumanPlayer h = new HumanPlayer();
        players.add(h);
        players.add(p);
        while(h.getBalance()>0)
        {

                d.assignPlayers(players);
            System.out.println("your Balance: "+h.getBalance());
            System.out.println("Player 2 Balance: "+p.getBalance());
                d.checkCardsRemaining(players);
                d.takeBets();

                d.dealFirstCards();
                p.viewDealerCard(d.getDealersHand().getInHand().get(0));
                //  d.play(p);
                for (int j = 0; j < players.size(); j++) {
                    d.play(players.get(j));


                }
            System.out.println("You play: "+d.scoreHand(h.getHand()));
            System.out.println("Player 2 plays: "+d.scoreHand(p.getHand()));

                System.out.println("Dealers play: " + d.playDealer());


                d.settleBets();


            }
        System.out.println("you ran out of balance. Play again?(yes/no) ");
        Scanner s = new Scanner(System.in);
        String option = s.nextLine();
        if(option.equals("yes"))
        {
            players.clear();
            humanGame();
        }



        }







    public static void advancedGame(){

    }








}
