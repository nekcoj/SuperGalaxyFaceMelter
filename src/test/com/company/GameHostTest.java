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
  Player p1 = new Player("Player 1");
  Player p2 = new Player("Player 1");

  @BeforeAll
  static void start() {
    System.out.println("======== STARTING GAMEHOST TESTS ========");
  }

  @BeforeEach
  void setup() {
    System.out.println("-------- @BeforeEach --------");
    ArrayList<Player> players = new ArrayList<>();
    players.add(p1);
    players.add(p2);
    GameState gs = new GameState(10, players);
    ScreenRenderer renderer = new ScreenRenderer();
    ComHandler comHandler = new LocalGameHandler();
    Dispatcher dispatcher = new Dispatcher(comHandler, renderer);
    GameLobby gameLobby = new GameLobby(false);
    gameLobby.setDispatcher(dispatcher);
    gameHost = new GameHost(gameLobby, new ScreenRenderer(), gs, 5);
  }

  @Test
  void constructorTest() {
    System.out.println("-------- Constructor Test --------");
    assertNotNull(gameHost, "GameHost constructor returned null!");
  }

  @Test
  void getCardFromStartPlayerTest() {
    System.out.println("-------- getCardFromStartPlayer Test --------");
    gameHost.dealCardsToHost();
    Card card = gameHost.getCardFromStartPlayer();
    assertNotNull(card, "card is null!");
    System.out.println(card);
  }

  @Test
  void getCardFromSecondPlayerTest() {
    System.out.println("-------- getCardFromSecondPlayer Test --------");
    gameHost.dealCardsToClient();
    Card card = gameHost.getCardFromSecondPlayer();
    assertNotNull(card, "card is null!");
  }

  @Test
  void getRoundWinnerTest() {
    gameHost.dealCards();
    Card card1 = new Card(5, "Angry teacher");
    Card card2 = new Card(5, "Angry teacher");
    System.out.println("-------- roundWinner Test --------");
    assertNotNull(card1, "card1 is null!");
    assertNotNull(card2, "card2 is null!");
    assertEquals(0, gameHost.gameState.getStartPlayer());
    assertEquals(-1, gameHost.getRoundWinner(card1, card2));
  }

  @Test
  void getCardFromPlayer1Test() {
    System.out.println("-------- getCardFromPlayer1 Test --------");
    gameHost.dealCardsToHost();
    Card c1 = gameHost.gameState.getPlayer(0).getCardOnHandAsList().get(0);
    Card c2 = gameHost.getCardFromPlayer1();
    assertNotNull(c2, "card is null!");
    assertEquals(c1, c2, "Cards are not the same!");
  }

  @Test
  void getCardFromPlayer2Test() {
    System.out.println("-------- getCardFromPlayer2 Test --------");
    gameHost.dealCardsToClient();
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
    assertEquals(5,
        gameHost.gameState.getPlayer(Game.HOST)
            .getCardOnHandAsList().size(),
        "Wrong hand size!");
  }

  @Test
  void dealCardsToClient(){
    System.out.println("-------- dealCardsToClient Test --------");
    assertNotNull(gameHost.dealCardsToClient(), "Gamestate is null!");
  }

  @Test
  void getCardMenu(){
    System.out.println("-------- getCardMenu Test --------");
    assertNotNull(gameHost.getCardMenu(), "getCardMenu returned null!");
  }

  @AfterAll
  static void end(){
    System.out.println("======== GAMEHOST TESTS ENDED ========");
  }
}