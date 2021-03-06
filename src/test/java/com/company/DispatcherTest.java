package com.company;

import com.company.gameengine.Dispatcher;
import com.company.gameengine.Game;
import com.company.gameobjects.Card;
import com.company.gameobjects.GameState;
import com.company.gameobjects.Player;
import com.company.renderer.ConsoleRenderer;
import com.company.network.ClientHandler;
import com.company.network.CommandType;
import com.company.network.Packet;
import com.company.network.ServerHandler;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DispatcherTest {
  private final String ipaddress = "127.0.0.1";
  private void simulateUserInput(String data) {
    System.setIn(new ByteArrayInputStream(data.getBytes()));
  }

  @BeforeAll
  static void start(){
    System.out.println("======== STARTING Dispatcher TESTS ========");
  }

  @BeforeEach
  void setUp() {
    System.out.println("-------- @BeforeEach --------");
  }

  @Test
  void getCommandFromHost() throws IOException, InterruptedException {
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    ArrayList<Player> players  = new ArrayList<>();
    players.add(p1);
    players.add(p2);
    Card card1 = new Card(5, "Angry teacher");
    Card card2 = new Card(6, "Ooga booga");
    GameState gs = new GameState(10, players, false);
    MyRunnable myRunnable = new MyRunnable(12345);
    Thread thread = new Thread(myRunnable);
    thread.start();
    Dispatcher serverDispatcher = new Dispatcher(myRunnable.serverHandler, new ConsoleRenderer());
    Thread.sleep(400L);

    ClientHandler clientHandler = new ClientHandler(ipaddress, 12345);
    Dispatcher clientDispatcher = new Dispatcher(clientHandler, new ConsoleRenderer());

    gs.getPlayer(Game.CLIENT).addCardToHand(card1);
    gs.getPlayer(Game.CLIENT).addCardToHand(card2);

    Packet packet = new Packet(CommandType.GET_PLAYER_NAME_FROM_CLIENT, null);
    myRunnable.serverHandler.send(packet);
    simulateUserInput("Kerstin");
    clientDispatcher.getCommandFromHost();
    packet = myRunnable.serverHandler.receive();
    assertEquals("Kerstin", packet.getParams()[0], "Incorrect string value!");

    packet = new Packet(CommandType.GET_CARD_FROM_CLIENT, new Object[]{gs});
    myRunnable.serverHandler.send(packet);
    simulateUserInput("1");
    clientDispatcher.getCommandFromHost();
    packet = myRunnable.serverHandler.receive();
    int c1 = (int)packet.getParams()[0];
    assertEquals(5, gs.getPlayer(Game.CLIENT).getCard(c1).getPower(), "Incorrect power value!");

  }

  @AfterAll
  static void end() {
    System.out.println("======== ENDING Dispatcher TESTS ========");
  }



  public class MyRunnable implements Runnable {

    int port;
    ServerHandler serverHandler = null;

    public MyRunnable(int port) {
      this.port = port;
    }

    private boolean doStop = false;

    public synchronized void doStop() {
      this.doStop = true;
    }

    private synchronized boolean keepRunning() {
      return !this.doStop;
    }

    @Override
    public void run() {
      System.out.println("MyRunnable in run, Dispatcher test");
      try {
        serverHandler = new ServerHandler(port);
        serverHandler.startServer();
      } catch (IOException e) {
        e.printStackTrace();
      }

      while(keepRunning()) {
        // keep doing what this thread should do.
        System.out.println("Running");

        try {
          Thread.sleep(3L * 1000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      serverHandler.close();
      System.out.println("Thread stopped!");
    }
  }
}