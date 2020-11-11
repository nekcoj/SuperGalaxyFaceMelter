package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> discardPile = new ArrayList<>();

    public Deck() {

    }

    public Deck(List <CardSettings> cardSettings) {
        generateCards(cardSettings);
    }

    public void generateCards(List <CardSettings> cardSettings) {
        cardSettings.forEach( setting -> {
            for(int i = 0; i < setting.amount; i++){
                cards.add(setting.createCard());
            }
        });
    }

    public Card getTopCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public List<Card> getHand(int cardCount) {
        List<Card> hand = new ArrayList<>();
        for(int i = 0; i < Math.min(getRemainingCardCount(), cardCount); i++){
            hand.add(getTopCard());
        }
        return hand;
    }

    public int getRemainingCardCount() {
        return cards.size();
    }


    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    /*public void addToDiscardPile(Card card){

    }*/

}
