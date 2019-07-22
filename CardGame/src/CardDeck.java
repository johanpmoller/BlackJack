import java.rmi.UnexpectedException;
import java.util.*;

public class CardDeck{

    private Rank[] rank;
    private Suit[] suit;
    protected List<String> deck = new ArrayList<>();



    public CardDeck(Rank[] rank, Suit[] suit) {
        this.rank = rank;
        this.suit = suit;
        this.deck = createDeck();

    }



    public List<String> getDeck() {
        return deck;
    }

    public void setDeck(List<String> deck) {
        this.deck = deck;
    }

    public Rank[] getRank() {
        return rank;
    }

    public void setRank(Rank[] rank) {
        this.rank = rank;
    }

    public Suit[] getSuit() {
        return suit;
    }

    public void setSuit(Suit[] suit) {
        this.suit = suit;
    }

    public List<String> createDeck(){
        for (Suit s: this.suit) {
            for (Rank r : this.rank) {
                deck.add(s + " " + r);
            }
        }
        Collections.shuffle(deck);
        return deck;
    }



    public void printDeck(){
        int i = 0;
        for (String aCard: this.deck) {
            i++;
            System.out.println(aCard + " " + i);

        }
    }




    public String getCard(int i){
        return deck.get(i);
    }

    public String drawCard(){
        if(this.deck.size() == 0){
            System.out.println("Deck empty! Play another new round!");
            return " ";
            }


        String card = deck.get(0);
        deck.remove(0);
        return card;
    }

    public void returnCard(String card){
        this.deck.add(this.deck.size(), card);
    }



    }










