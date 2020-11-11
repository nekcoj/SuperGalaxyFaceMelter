package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  Card card = new Card(10, "Super Galaxy Face Melter");
  Player player;

  @BeforeEach
  void setupBefore(){
    ArrayList<Card> cards = new ArrayList<>();
    cards.add(card);
    player = new Player(cards, "Tomten");
  }

  @Test
  void constructorTest(){
    Assertions.assertNotNull(player, "Constructor returned null");
  }

  @Test
  void getName() {
    assertEquals("Tomten", player.getName(), "Wrong name!");
  }

  @Test
  void getScore(){
    assertEquals(10, player.getScore(), "Incorrect score!");
  }

  @Test
  void getCard(){
    assertNotNull(player.getCard(1), "getCard returned null!");
  }

  @Test
  void addToVictoryPile(){
    //int scoreBefore = player.getScore();
    player.addToVictoryPile(card);
    assertEquals(10, player.getScore(), "Card not added to victory pile!");
  }
}