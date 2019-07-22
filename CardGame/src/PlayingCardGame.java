import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class PlayingCardGame {
    private  ArrayList<String> playerHand;
    private ArrayList<String> dealerHand;
    private boolean isPlayer;
    private int dealerScore;
    private int playerScore;
    private double money;


    CardDeck deck = new CardDeck(Rank.values(), Suit.values());

    public PlayingCardGame() {
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.deck = deck;
        this.isPlayer = isPlayer;
        this.money = money;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public int getDealerScore() {
        return getHandValue(false, this.dealerHand);
    }

    public void setDealerScore(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public int getPlayerScore() {
        return getHandValue(true, this.playerHand);
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<String> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(ArrayList<String> playerHand) {
        playerHand.add(deck.drawCard());
        this.playerHand = playerHand;
    }

    public ArrayList<String> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(ArrayList<String> dealerHand) {
        dealerHand.add(deck.drawCard());
        this.dealerHand = dealerHand;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public void setDeck(CardDeck deck) {
        this.deck = deck;
    }


    public static int calculateValue(String rank, boolean isPlayer, ArrayList<String> hand) {


        switch (rank) {

            case "ACE":
                return calculateAce(hand);
            case "TWO":
                return 2;
            case "THREE":
                return 3;
            case "FOUR":
                return 4;
            case "FIVE":
                return 5;
            case "SIX":
                return 6;
            case "SEVEN":
                return 7;
            case "EIGHT":
                return 8;
            case "NINE":
                return 9;
            case "TEN":
            case "JACK":
            case "QUEEN":
            case "KING":
                return 10;

        }
        return -1;
    }

    public static int calculateAce(ArrayList<String> hand) {
        int handValue = 0;
        int notAceValue = 0;
        for (String aCard : hand) {
            if (!aCard.equals("ACE")) {
                notAceValue = calculateValue(aCard, false, hand);
                handValue = +notAceValue;
            }
        }
        if (notAceValue + handValue  <= 21) {
            return 11;
        } else {
            return 1;
        }
    }

    public static int getHandValue(boolean isPlayer, ArrayList<String> hand) {
        int handValue = 0;
        for (String aCard : hand) {
            String[] value = aCard.split(" ");
            handValue += calculateValue(value[1], isPlayer, hand);

        }
        return handValue;
    }

    public int hit(ArrayList<String> hand, boolean isPlayer) {
        if(isPlayer){
            this.playerHand.add(this.deck.drawCard());
        }else{
            this.dealerHand.add(this.deck.drawCard());
        }

        return getHandValue(isPlayer, hand);

    }



    public void printHand(ArrayList<String> hand){
        for (String aCard: hand) {
            System.out.println(aCard);

        }
    }

    public void printDealerHand(){
        for (int i = 0; i <dealerHand.size() -1 ; i++) {
            System.out.println(dealerHand.get(i));


        }
    }


    public void returnCard(String card){
        this.deck.returnCard(card);

    }

    public boolean isBetOk(double money, double bet){
        if(money < bet){
            System.out.println("Sorry, you're out of money");
            return false;
    } else if(money == 0){
            System.out.println("Sorry, you're out of money!");
            return false;
        }
        else{
            return true;
        }

    }


}
