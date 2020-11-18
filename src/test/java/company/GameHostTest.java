package company;

import com.company.*;
import com.company.interfaces.ComHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameHostTest {

  private GameHost gameHost;
  private GameState gameState;
  Player p1 = new Player("Player 1");
  Player p2 = new Player("Player 2");

  private void simulateUserInput(String data) {
    System.setIn(new ByteArrayInputStream(data.getBytes()));
  }

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
    gameState = new GameState(1, players);
    ConsoleRenderer renderer = new ConsoleRenderer();
    ComHandler comHandler = new LocalGameHandler();
    Dispatcher dispatcher = new Dispatcher(comHandler, renderer);
    GameLobby gameLobby = new GameLobby(false);
    gameLobby.setDispatcher(dispatcher);
    gameHost = new GameHost(gameLobby, new ConsoleRenderer(), gameState, 5, true);
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
    simulateUserInput("1");
    Card card = gameHost.getCardFromStartPlayer();
    assertNotNull(card, "card is null!");
    System.out.println(card);
  }

  @Test
  void getCardFromSecondPlayerTest() {
    System.out.println("-------- getCardFromSecondPlayer Test --------");
    gameHost.dealCardsToClient();
    simulateUserInput("2");
    Card card = gameHost.getCardFromSecondPlayer();
    assertNotNull(card, "card is null!");
    System.out.println(card);
  }

  @Test
  void getRoundWinnerTest() {
    gameHost.dealCards();
    Card card1 = new Card(5, "Angry teacher");
    Card card2 = new Card(5, "Angry teacher");
    System.out.println("-------- roundWinner Test --------");
    assertNotNull(card1, "card1 is null!");
    assertNotNull(card2, "card2 is null!");
    assertEquals(0, gameHost.getGameState().getStartPlayer());
    assertEquals(2, gameHost.getRoundWinner(card1, card2));
  }

  @Test
  void finalizingRoundTest() {
    int winner = 0;
    Card card1 = new Card(10, "Super Galaxy Face Melter");
    Card card2 = new Card(5, "Angry teacher");
    gameHost.finalizingRound(winner, card1, card2);
    assertEquals(5, gameHost.getGameState().getPlayer(Game.HOST).getScore());
    assertEquals(1, gameHost.getGameState().getPlayer(Game.HOST).getCardOnHandAsList().size());
    assertEquals(1, gameHost.getGameState().getPlayer(Game.CLIENT).getCardOnHandAsList().size());
  }

  @Test
  void getCardFromPlayer1Test() {
    System.out.println("-------- getCardFromPlayer1 Test --------");
    gameHost.dealCardsToHost();
    simulateUserInput("1");
    Card c1 = gameHost.getGameState().getPlayer(0).getCardOnHandAsList().get(0);
    Card c2 = gameHost.getCardFromPlayer1();
    assertNotNull(c2, "card is null!");
    assertEquals(c1, c2, "Cards are not the same!");
  }

  @Test
  void getCardFromPlayer2Test() {
    System.out.println("-------- getCardFromPlayer2 Test --------");
    simulateUserInput("1");
    gameHost.dealCardsToClient();
    Card card = gameHost.getCardFromPlayer2();
    assertNotNull(card, "card is null!");
  }

  @Test
  void isGameOverTest() {
    Card card1 = new Card(10, "Super Galaxy Face Melter");
    Card card2 = new Card(5, "Angry teacher");
    gameHost.finalizingRound(0, card1, card2);
    System.out.println("-------- isGameOver Test --------");
    assertTrue(gameHost.isGameOver(), "Game is over!");
    assertEquals(gameState.getGameWinner(), 0, "Wrong player won!");
  }

  @Test
  void dealCardsToHost(){
    System.out.println("-------- dealCardsToHost Test --------");
    assertTrue(gameHost.dealCardsToHost(), "Cards not added to host!");
    assertEquals(5,
        gameHost.getGameState().getPlayer(Game.HOST)
            .getCardOnHandAsList().size(),
        "Wrong hand size!");
  }

  @Test
  void dealCardsToClient(){
    System.out.println("-------- dealCardsToClient Test --------");
    assertNotNull(gameHost.dealCardsToClient(), "getGameState() is null!");
  }

  @AfterAll
  static void end(){
    System.out.println("======== GAMEHOST TESTS ENDED ========");
  }
}