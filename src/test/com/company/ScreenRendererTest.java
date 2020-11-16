package com.company;

import com.company.utils.TextUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScreenRendererTest {

  ScreenRenderer sr = new ScreenRenderer();
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
    players.add(p1);
    players.add(p2);
    gs = new GameState(10, players);
  }

  @Test
  void generateCardsString() {
    System.out.println("-------- generatePrintString Test --------");
    assertEquals(0, sr.generateCardsString(cards).indexOf("╔"), "Missing top left corner!");
  }

  @Test
  void generateRow(){
    System.out.println("-------- generateRow Test --------");
    String str = sr.generateRow("<", "!", ">");
    assertEquals(str.length(), ScreenRenderer.CARD_WIDTH, "Got incorrect row length!");
    assertTrue(str.charAt(0) == '<', "Wrong character at index 0!");
    assertTrue(str.charAt(1) == '!', "Wrong character at index 1!");
    assertTrue(str.charAt(str.length() - 1) == '>', "Wrong character at index .length() - 1!");
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
    String str = "<Player 1> Player 1, 0 points, <Player 2> Player 2, 0 points";
    assertTrue(sr.generateScoreRow(gs).equals(str), "generateGameBoardRow returned wrong string!");
  }

  @Test
  void generateGameOverRow(){
    System.out.println("-------- generateGameOverRow Test --------");
    String str = "Game Over!\nWinner is Player 1";
    gs.setWinner(GameHost.HOST);
    assertTrue(sr.generateGameOverRow(gs).equals(str), "generateGameOverRow returned incorrect value!");
  }

  @AfterAll
  void end(){
    System.out.println("======== ScreenRenderer Tests Ended ========");
  }
}