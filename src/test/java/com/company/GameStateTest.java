package com.company;

import com.company.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
  Player p1 = new Player("Player 1");
  Player p2 = new Player("Player 2");
  ArrayList<Player> players  = new ArrayList<>();
  GameState gs;

  @BeforeAll
  static void start(){
    System.out.println("======== STARTING GAMESTATE TESTS ========");
  }
  @BeforeEach
  void setup(){
    System.out.println("-------- @BeforeEach --------");
    players.add(p1);
    players.add(p2);
    gs = new GameState(10, players);
  }

  @Test
  void constructorTest() {
    System.out.println("-------- Constructor Test --------");
    Assertions.assertNotNull(gs, "Constructor returned null");
  }

  @Test
  void addPlayedCard() {
    System.out.println("-------- addPlayedCard Test --------");
    Card c1 = new Card();
    assertTrue(gs.addPlayedCard(c1), "Card was not added to playedCards!");
  }

  @Test
  void clearPlayedCards() {
    System.out.println("-------- clearPlayedCards Test --------");
    Card c2 = new Card();
    gs.addPlayedCard(c2);
    assertTrue(gs.clearPlayedCards(), "Cards were not cleared!");
  }

  @Test
  void changeStartPlayer() {
    System.out.println("-------- changeStartPlayer Test --------");
    assertEquals(1, gs.changeStartPlayer(), "Starting player not changed!");
  }

  @Test
  void getPlayer (){
    System.out.println("-------- getPlayer Test --------");
    assertNotNull(gs.getPlayer(0), "getPlayer returned null!");
    //Player p = gs.getPlayer(1);
  }

  @Test
  void isGameOver(){
    System.out.println("-------- isGameOver Test --------");
    assertFalse(gs.isGameOver(), "isGameOver returned wrong boolean!");
    gs.setGameWinner(Game.HOST);
    assertTrue(gs.isGameOver(), "isGameOver returned wrong boolean!");
  }
}