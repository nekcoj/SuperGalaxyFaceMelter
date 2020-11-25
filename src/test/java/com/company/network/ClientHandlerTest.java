package com.company.network;

import com.company.gameengine.Dispatcher;
import com.company.gameengine.Game;
import com.company.gameobjects.Card;
import com.company.gameobjects.GameState;
import com.company.gameobjects.Player;
import com.company.renderer.ConsoleRenderer;
import com.company.interfaces.Renderer;
import com.company.utils.TextUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {
    private final String IPADRESS = "127.0.0.1";

    private void simulateUserInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @BeforeAll
    static void start(){
        System.out.println("======== STARTING ClientHandler TESTS ========");
    }

    @BeforeEach
    void setup()  {
        System.out.println("-------- @BeforeEach --------");
    }

    @Test
    void getOutputStream() throws IOException, InterruptedException {
        MyServerRunnable myServerRunnable = new MyServerRunnable(50000);
        Thread thread = new Thread(myServerRunnable);
        thread.start();
        System.out.println("-------- getOutputSteam TEST --------");
        Thread.sleep(200L);
        ClientHandler clientHandler = new ClientHandler(IPADRESS, 50000);
        System.out.println("ServerHandler in setup: " + clientHandler);
        assertNotNull(clientHandler.getOutputStream(), "OutputStream returned null!");
        clientHandler.close();
        myServerRunnable.doStop();
    }

    @Test
    void getInputStream() throws IOException, InterruptedException {
      System.out.println("-------- getInputStream TEST --------");

      MyServerRunnable myServerRunnable = new MyServerRunnable(50001);
      Thread thread = new Thread(myServerRunnable);
      thread.start();
      Thread.sleep(200L);

      ClientHandler clientHandler = new ClientHandler(IPADRESS, 50001);
        System.out.println("ServerHandler in setup: " + clientHandler);
        assertNotNull(clientHandler.getInputStream(), "InputStream returned null!");
        clientHandler.close();
        myServerRunnable.doStop();
    }

    @Test
    void getCardFromClientTest() throws IOException, InterruptedException {
        System.out.println("-------- getCardFromClient TEST --------");

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        ArrayList<Player> players  = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Card card1 = new Card(5, "Angry teacher");
        player2.addCardToHand(card1);
        GameState gs = new GameState(10, players, false);
        Renderer renderer = new ConsoleRenderer();

        MyServerRunnable myServerRunnable = new MyServerRunnable(50004);
        Thread thread = new Thread(myServerRunnable);
        thread.start();
        Thread.sleep(200L);
        ClientHandler myClientRunnable = new ClientHandler(IPADRESS,50004);
        Dispatcher dispatcher = new Dispatcher(myClientRunnable, renderer);


        simulateUserInput("1");
        Packet p = new Packet(CommandType.GET_CARD_FROM_CLIENT, new GameState[]{gs});
        myServerRunnable.serverHandler.send(p);
        Thread.sleep(200L);
        dispatcher.getCommandFromHost();
        p = myServerRunnable.serverHandler.receive();
        int receivedCard = -1;
        if (p != null) {
            receivedCard = (int) p.getParams()[0];
        }
        Card card = gs.getPlayer(Game.CLIENT).getCard(receivedCard);

        assertEquals(card1, card, "Wrong card!");
        assertEquals(1, receivedCard, "received card number not 1!");
        assertNotEquals(-1, receivedCard, "Wrong card!");
        myClientRunnable.close();
        myServerRunnable.doStop();
    }

    @Test
    void addToClientVictoryPileTest() throws IOException, InterruptedException {
        System.out.println("-------- addToClientVictoryPile TEST --------");

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        ArrayList<Player> players  = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Card card1 = new Card(5, "Angry teacher");
        GameState gs = new GameState(10, players, false);
        Renderer renderer = new ConsoleRenderer();

        MyServerRunnable myServerRunnable = new MyServerRunnable(50005);
        Thread thread = new Thread(myServerRunnable);
        thread.start();
        Thread.sleep(200L);
        ClientHandler myClientRunnable = new ClientHandler(IPADRESS,50005);

        Dispatcher dispatcher = new Dispatcher(myClientRunnable, renderer);

        Packet p = new Packet(CommandType.ADD_TO_CLIENT_VICTORY_PILE, new Object[]{card1, gs});
        myServerRunnable.serverHandler.send(p);
        Thread.sleep(200L);
        dispatcher.getCommandFromHost();
        p = myServerRunnable.serverHandler.receive();

        gs = (GameState) p.getParams()[0];

        assertEquals(card1.getCurrentPower(), gs.getPlayer(Game.CLIENT).getScore(), "Score doesn't match!");
        myServerRunnable.doStop();
    }

    @Test
    void sendAndReceive() throws IOException, InterruptedException {
      System.out.println("-------- sendAndReceive TEST --------");

      MyServerRunnable myServerRunnable = new MyServerRunnable(50002);
      Thread thread = new Thread(myServerRunnable);
      thread.start();
      Thread.sleep(200L);
      ClientHandler clientHandler = new ClientHandler(IPADRESS, 50002);

      Packet p1 = new Packet(CommandType.RENDER_CLIENT, new String[]{"Hej"});
      myServerRunnable.serverHandler.send(p1);

      Packet p2 = clientHandler.receive();
      System.out.printf("In sendAndReceive, got '%s' back from server\n",
          TextUtil.pimpString((String) p2.getParams()[0], TextUtil.LEVEL_INFO));

      assertEquals("Hej", p2.getParams()[0], "Sent message is not the same as received message!");
      clientHandler.close();
      myServerRunnable.doStop();
    }

  @Test
  void getPlayerNameFromClient() throws IOException, InterruptedException {
    System.out.println("-------- getPlayerNameFromClient TEST --------");

    MyServerRunnable myServerRunnable = new MyServerRunnable(50003);
    Thread thread = new Thread(myServerRunnable);
    thread.start();
    Thread.sleep(200L);
    ClientHandler clientHandler = new ClientHandler(IPADRESS, 50003);

    Packet p1 = new Packet(CommandType.GET_PLAYER_NAME_FROM_CLIENT, null);
    System.out.println("packet:" + p1);
    System.out.println("myRunnable:" + myServerRunnable);
    System.out.println("serverHandler:" + myServerRunnable.serverHandler);
    myServerRunnable.serverHandler.send(p1);

    Packet p2 = clientHandler.receive();
    System.out.printf("In getPlayerNameFromClient, got command '%s' back from server\n",
        TextUtil.pimpString(String.valueOf(p2.getCommandType()), TextUtil.LEVEL_INFO));
    assertEquals(CommandType.GET_PLAYER_NAME_FROM_CLIENT, p2.getCommandType(), "Command type is wrong!");

    Packet p3 = new Packet(CommandType.GET_PLAYER_NAME_FROM_CLIENT, new String[]{"Player Two"});
    clientHandler.send(p3);

    Packet p4 = myServerRunnable.serverHandler.receive();
    assertEquals("Player Two", p4.getParams()[0], "Returned name is wrong!");
    System.out.printf("In getPlayerNameFromClient, got '%s' back from server\n",
        TextUtil.pimpString((String) p4.getParams()[0], TextUtil.LEVEL_INFO));

    clientHandler.close();
    myServerRunnable.doStop();
  }

    @Test
    void sendCardToClient() throws InterruptedException, IOException {

        System.out.println("-------- sendCardToClient TEST --------");

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        ArrayList<Player> players  = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Card card1 = new Card(5, "Angry teacher");
        ArrayList<Card> cardsToAdd = new ArrayList<>();
        cardsToAdd.add(card1);
        GameState gs = new GameState(10, players, false);
        Renderer renderer = new ConsoleRenderer();

        MyServerRunnable myServerRunnable = new MyServerRunnable(50006);
        Thread thread = new Thread(myServerRunnable);
        thread.start();
        Thread.sleep(200L);
        ClientHandler myClientRunnable = new ClientHandler(IPADRESS,50006);

        Dispatcher dispatcher = new Dispatcher(myClientRunnable, renderer);

        Packet p = new Packet(CommandType.SEND_CARD_TO_CLIENT, new Object[]{cardsToAdd, gs});
        myServerRunnable.serverHandler.send(p);
        Thread.sleep(200L);
        dispatcher.getCommandFromHost();
        p = myServerRunnable.serverHandler.receive();

        gs = (GameState) p.getParams()[0];

        assertEquals(card1.getName(), gs.getPlayer(Game.CLIENT).getCard(1).getName(), "That card's name doesn't match!");
        assertEquals(card1.getPower(), gs.getPlayer(Game.CLIENT).getCard(1).getPower(), "That card's power doesn't match!");
        myServerRunnable.doStop();
        myClientRunnable.close();
    }

  @AfterAll
    static void end()  {
        System.out.println("======== ENDING ClientHandler TESTS ========");
    }

    public class MyServerRunnable implements Runnable {

    int port;
    ServerHandler serverHandler = null;

    public MyServerRunnable(int port) {
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
      System.out.println("MyRunnable in run, ClientHandler test");
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
    public class MyClientRunnable implements Runnable {

        int port;
        ClientHandler clientHandler = null;

        public MyClientRunnable(int port) {
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
            System.out.println("MyRunnable in run, ClientHandler test");
            try {
                clientHandler = new ClientHandler(IPADRESS, port);
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

            clientHandler.close();
            System.out.println("Thread stopped!");
        }
    }
}