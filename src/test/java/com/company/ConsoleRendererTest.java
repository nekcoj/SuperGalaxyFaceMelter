package com.company;

import com.company.gameengine.Game;
import com.company.gameobjects.Card;
import com.company.gameobjects.GameState;
import com.company.gameobjects.Player;
import com.company.renderer.ConsoleRenderer;
import com.company.utils.TextUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleRendererTest {

  ConsoleRenderer sr = new ConsoleRenderer();
  ArrayList<Card> cards = new ArrayList<>();
  Player p1 = new Player("Player 1");
  Player p2 = new Player("Player 2");
  ArrayList<Player> players  = new ArrayList<>();
  GameState gs;

  @BeforeAll
  static void start(){
    System.out.println("======== STARTING ScreenRenderer Tests ========");
  }

  @BeforeEach
  void setup(){
    System.out.println("-------- @BeforeEach --------");
    cards.add(new Card(10, "Super Galaxy Face Melter"));
    cards.add(new Card(5, "Orange Menace"));
    cards.add(new Card(6, "Angry Teacher"));
    players.add(p1);
    players.add(p2);
    gs = new GameState(10, players, true);
  }

  @Test
  void generateCardsString() {
    System.out.println("-------- generatePrintString Test --------");
    assertEquals(0, sr.generateCardsString(cards).indexOf("╔"), "Missing top left corner!");
    System.out.println(sr.generateCardsString(cards));
  }

  @Test
  void generateRow(){
    System.out.println("-------- generateRow Test --------");
    String str = sr.generateRow("<", "!", ">");
    assertEquals(str.length(), ConsoleRenderer.CARD_WIDTH, "Got incorrect row length!");
    assertEquals(str.charAt(0), '<', "Wrong character at index 0!");
    assertEquals(str.charAt(1), '!', "Wrong character at index 1!");
    assertEquals(str.charAt(str.length() - 1), '>', "Wrong character at index .length() - 1!");
  }

  @Test
  void generatePowerRow(){
    System.out.println("-------- generatePowerRow Test --------");
    String str = String.format("[%s/%s]", TextUtil.pimpString(10, TextUtil.LEVEL_BOLD), TextUtil.pimpString(10, TextUtil.LEVEL_STRESSED));
    assertTrue(sr.generatePowerRow(cards.get(0)).contains(str), "Incorrect power row!");
  }

  @Test
  void generateContentRow(){
    System.out.println("-------- generateContentRow Test --------");
    String contentTest = "Content Test";
    assertTrue(sr.generateContentRow(contentTest, 0).contains(contentTest), "Got wrong value from generateContentRow!");
  }

  @Test
  void generateScoreRow(){
    System.out.println("-------- generateScoreRow Test --------");
    String str = "<Player 1>";
//    String str = "<Player 1> Player 1, 0 points, <Player 2> Player 2, 0 points";
    assertTrue(sr.generateScoreRow(gs).contains(str), "generateGameBoardRow returned wrong string!");
  }

  @Test
  void generateGameOverRow(){
    System.out.println("-------- generateGameOverRow Test --------");
    String str = "Game Over!";
    gs.setGameWinner(Game.HOST);
    assertTrue(sr.generateGameOverRow(gs).contains(str), "generateGameOverRow returned incorrect value!");
  }

  @Test
  void generateRoundWinnerRow(){
    System.out.println("-------- generateRoundWinnerRow Test --------");
    String str = "Round winner";
    gs.setRoundWinner(Game.HOST);
    assertTrue(sr.generateRoundWinnerRow(gs).contains(str), "generateRoundWinnerRow returned incorrect value!");
  }

  @Test
  void getCardMenu(){
    System.out.println("-------- getCardMenu Test --------");
    assertNotNull(sr.getCardMenu(gs, Game.HOST), "getCardMenu returned null!");
  }

  @AfterAll
  static void end(){
    System.out.println("======== ScreenRenderer Tests Ended ========");
  }
}