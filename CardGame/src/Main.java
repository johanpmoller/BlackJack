import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();


        Scanner sc = new Scanner(System.in);


        System.out.println("Good evening Sir and Madame. Let's play some Black Jack. Do you want to know the rules? (y/n)");
        if(sc.next().equals("y")){
            printRules();
        }
        System.out.println("");

        System.out.println("How much moneny do you want to play for? (not bet, total playing amount)");
        double money = sc.nextDouble();


        boolean gameOn = true;


        while (gameOn) {
            boolean isPlaying = true;



            System.out.println("Place your bet: ");
            System.out.println("Your money: " + money);
            double bet = sc.nextDouble();
            System.out.println();

            PlayingCardGame blackJack = new PlayingCardGame();
            if(!blackJack.isBetOk(money, bet)){
                System.out.println("Please come again!");
                break;

            }

            System.out.println("Cards are being dealt");
            System.out.println();
            blackJack.setPlayerHand(playerHand);
            blackJack.setDealerHand(dealerHand);
            blackJack.setPlayerHand(playerHand);
            blackJack.setDealerHand(dealerHand);

            while (isPlaying) {


                System.out.println("Your hand: ");
                blackJack.printHand(playerHand);
                System.out.println();
                System.out.println("Dealer hand:");
                blackJack.printDealerHand();
                System.out.println("[Hidden]");
                System.out.println();
                if(blackJack.getPlayerScore() == 21){
                    System.out.println("You've got Black Jack!");
                    money += bet;
                    isPlaying = false;
                    break;
                }
                System.out.println("Do you want another card? y/n");
                System.out.println("Your current score is: " + blackJack.getPlayerScore());
                String hit = sc.next();


                // player action
                while(hit.equals("y") && blackJack.getPlayerScore() < 21) {
                    blackJack.hit(playerHand, true);
                    System.out.println("You draw a " + playerHand.get(playerHand.size() - 1));
                    if (blackJack.getPlayerScore() > 21) {
                        System.out.println("Busted. Your cards are worth: " + blackJack.getPlayerScore());
                        money -= bet;
                        isPlaying = false;
                        break;
                    }
                    if(blackJack.getPlayerScore() == 21){
                        System.out.println("You've got Black Jack!");
                        money += bet;
                        isPlaying = false;
                        break;
                    }
                    System.out.println("hit? y/n");
                    System.out.println("Your current score is: " + blackJack.getPlayerScore());
                    hit = sc.next();
                    if (!hit.equals("y")) {
                        break;

                    }
                }


                //reveal dealer cards

                while (isPlaying) {
                    System.out.println("Dealer hand: ");
                    blackJack.printHand(dealerHand);

                    while ((blackJack.getDealerScore() < 17 && isPlaying) || (blackJack.getDealerScore() == blackJack.getPlayerScore() && isPlaying)) {
                        blackJack.hit(dealerHand, false);
                        System.out.println();
                        System.out.println("Delar draws: ");
                        System.out.println(dealerHand.get(dealerHand.size() - 1));
                        System.out.println("Dealer score: " + blackJack.getDealerScore());
                    }


                        if (blackJack.getDealerScore() > 21 && isPlaying) {
                            System.out.println("Dealer busted. You win! ");
                            money += bet;
                            isPlaying = false;
                            break;

                        }
                        if (blackJack.getDealerScore() == blackJack.getPlayerScore() && isPlaying) {
                            System.out.println("Push");
                            isPlaying = false;
                            break;

                        }

                        if(blackJack.getDealerScore() == 21){
                            System.out.println("Dealer has Black Jack");
                            money -=bet;
                            isPlaying = false;
                            break;
                        }

                        if (blackJack.getDealerScore() < blackJack.getPlayerScore() && isPlaying) {
                            System.out.println("You win! ");
                            money += bet;
                            isPlaying = false;
                            break;
                        }
                        if(blackJack.getDealerScore() > blackJack.getPlayerScore() && isPlaying){
                            System.out.println("Dealer won!");
                            money -=bet;
                            isPlaying = false;
                            break;

                        }
                    }
                }
            System.out.println();
            System.out.println("Do you want to play another round? y/n");
            gameOn = sc.next().equals("y");

            //remove cards.

            dealerHand = new ArrayList<String>();
            playerHand = new ArrayList<String>();

            }
        }

    public static void printRules(){
        String rules =
                " First player to 21 wins the amount of the players bet. If there is a push the player nor win or lose any money. If the player get more than 21 the player is 'busted', i.e. losing." +
                        "If the player receives an ace its worth one or 11 depending one if it will bust your hand or not. It is OK to bet zero if you want to do a training round!";

        System.out.println(rules);

    }




}



































