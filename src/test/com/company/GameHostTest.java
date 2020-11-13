package com.company;

import com.company.interfaces.ComHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameHostTest {

  private GameHost gameHost;

  @BeforeAll
  static void start() {
    System.out.println("======== STARTING GAMEHOST TESTS ========");
  }

  @BeforeEach
  void setup() {
    System.out.println("-------- @BeforeEach --------");
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 1");
    ArrayList<Player> players = new ArrayList<>();
    players.add(p1);
    players.add(p2);
    GameState gs = new GameState(10, players);
    ComHandler comHandler = new LocalGameHandler();
    gameHost = new GameHost(new GameLobby(false), new ScreenRenderer(), gs, 5, 50);
  }

  @Test
  void constructorTest() {
    System.out.println("-------- Constructor Test --------");
    assertNotNull(gameHost, "GameHost constructor returned null!");
  }

  @Test
  void getCardFromStartPlayerTest() {
    System.out.println("-------- getCardFromStartPlayer Test --------");
    Card card = gameHost.getCardFromStartPlayer();
    assertNotNull(card, "card is null!");
  }

  @Test
  void getCardFromSecondPlayerTest() {
    System.out.println("-------- getCardFromSecondPlayer Test --------");
    Card card = gameHost.getCardFromSecondPlayer();
    assertNotNull(card, "card is null!");
  }

  @Test
  void roundWinnerTest() {
    System.out.println("-------- roundWinner Test --------");
    assertEquals(-1, gameHost.getRoundWinner());
  }

  @Test
  void getCardFromPlayer1Test() {
    System.out.println("-------- getCardFromPlayer1 Test --------");
    Card card = gameHost.getCardFromPlayer1();
    assertNotNull(card, "card is null!");
  }

  @Test
  void getCardFromPlayer2Test() {
    System.out.println("-------- getCardFromPlayer2 Test --------");
    Card card = gameHost.getCardFromPlayer2();
    assertNotNull(card, "card is null!");
  }

  @Test
  void isGameOver() {
    System.out.println("-------- isGameOver Test --------");
    assertFalse(gameHost.isGameOver(), "Game is over!");
  }

  @Test
  void dealCardsToHost(){
    System.out.println("-------- dealCardsToHost Test --------");
    assertTrue(gameHost.dealCardsToHost(), "Cards not added to host!");
  }

  @Test
  void dealCardsToClient(){
    System.out.println("-------- dealCardsToClient Test --------");
    assertTrue(gameHost.dealCardsToClient(), "Cards not added to client!");
  }

  @AfterAll
  static void end(){
    System.out.println("======== GAMEHOST TESTS ENDED ========");
  }
}