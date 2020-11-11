package com.company;

import java.util.ArrayList;
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

            }
        });
    }

    public Card getTopCard() {
        return null;
    }

    public int getRemainingCardCount() {
        return 0;
    }

    public List<Card> getHand(int cardCount) {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

}
