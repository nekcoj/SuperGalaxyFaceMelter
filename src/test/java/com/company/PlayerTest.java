package com.company;

import com.company.*;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  Card card = new Card(10, "Super Galaxy Face Melter");
  Player player;
  ArrayList<Card> cards;

  @BeforeAll
  static void setup(){
    System.out.println("======== STARTING PLAYER TESTS ========");
  }
  @BeforeEach
  void setupBefore(){
    System.out.println("-------- @BeforeEach --------");
    cards = new ArrayList<>();
    cards.add(card);
    player = new Player("Tomten");
  }

  @Test
  void constructorTest(){
    System.out.println("-------- Constructor test --------");

    Assertions.assertNotNull(player, "Constructor returned null");
  }

  @Test
  void getName() {
    System.out.println("-------- getName test --------");

    assertEquals("Tomten", player.getName(), "Wrong name!");
  }

  @Test
  void getScore(){
    System.out.println("-------- getScore test --------");
    player.addToVictoryPile(card);
    assertEquals(10, player.getScore(), "Incorrect score!");
  }

  @Test
  void getCard(){
    System.out.println("-------- getCard test --------");
    player.addCardsToHand(cards);
    assertNotNull(player.getCard(1), "Got null from getCard");
  }

  @Test
  void addToVictoryPile(){
    System.out.println("-------- addToVictoryPile test --------");
    assertTrue(player.addToVictoryPile(card), "Card not added to victory pile!");
    //assertEquals(10, player.getScore(), "Card not added to victory pile!");
  }

  @Test
  void getCardOnHandAsList(){
    System.out.println("-------- getCardOnHandAsList test --------");
    player.addCardsToHand(cards);
    assertEquals(1, player.getCardOnHandAsList().size(), "Got wrong value from getCardOnHandAsList");
  }

  @Test
  void addCardsToHand() {
    System.out.println("-------- addCardsToHand test --------");
    assertTrue(player.addCardsToHand(cards), "Failed to add cards");
  }

  @Test
  void addCardToHandTest() {
    System.out.println("-------- addCardToHand test --------");
    assertTrue(player.addCardToHand(card), "Failed to add card");
  }

  @AfterAll
  static void end(){
    System.out.println("======== PLAYER TESTS ENDED ========");
  }
}