package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck deck;
    List<CardSettings> cardSettings = new ArrayList<>() {{
        add(new CardSettings(1, "Mutated worm", 8));
        add(new CardSettings(2, "Irate rat", 8));
        add(new CardSettings(3, "Orange menace", 8));
        add(new CardSettings(4, "Sleepy Joe", 10));
        add(new CardSettings(5, "Angry teacher", 10));
        add(new CardSettings(6, "Screaming toddler", 8));
        add(new CardSettings(7, "Space nerd", 8));
        add(new CardSettings(8, "Anonymous hacker", 4));
        add(new CardSettings(9, "Radiated zombie", 4));
        add(new CardSettings(10, "Super Galaxy Face Melter", 2));
    }};

    @BeforeEach
    void setUpBefore() {
        deck = new Deck(cardSettings);
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(deck, "Constructor returned null");
    }

    @Test
    void generateCards() {
        Assertions.assertEquals(70, deck.getRemainingCardCount(), "Wrong number of cards!");
    }

    @Test
    void getTopCard() {
        Assertions.assertNotNull(deck.getTopCard(), "No top card!");
    }

    @Test
    void getRemainingCardCount() {
        Assertions.assertEquals(70, deck.getRemainingCardCount(), "Wrong card count!");
    }

    @Test
    void getHand() {
        List<Card> list;
        list = deck.getHand(5);
        Assertions.assertNotNull(list, "Hand is null!");
        Assertions.assertEquals(5, list.size(), "Wrong number of cards");
    }

    @Test
    void isEmpty() {
        Assertions.assertFalse(deck.isEmpty(), "Deck is empty!");
    }

    @Test
    void getDeck(){
        List<Card> cards = deck.getCards();
        assertNotNull(cards, "Deck returned null");
    }

    @Test
    void shuffleDeck(){
        List<Card> cards = (List<Card>) deck.getCards().clone();
        deck.shuffle();
        List<Card> cardsAfter = deck.getCards();
        assertFalse(Arrays.equals(cards.toArray(), cardsAfter.toArray()), "Arrays are equal");
    }
}