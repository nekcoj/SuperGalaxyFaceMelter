package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    ArrayList<Player> players  = new ArrayList<>();
    GameState gs;

    @BeforeEach
    void setup(){
        players.add(p1);
        players.add(p2);
        gs = new GameState(10, players);
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(gs, "Constructor returned null");
    }

    @Test
    void addPlayedCard() {
        Card c1 = new Card();
        assertTrue(gs.addPlayedCard(c1), "Card was not added to playedCards!");
    }

    @Test
    void clearPlayedCards() {
        assertTrue(gs.clearPlayedCards(), "Cards were not cleared!");
    }

    @Test
    void changeStartPlayer() {
        assertEquals(1, gs.changeStartPlayer(), "Starting player not changed!");
    }

    @Test
    void getPlayer (){
        assertNotNull(gs.getPlayer(0), "getPlayer returned null!");
        //Player p = gs.getPlayer(1);

    }
}