package com.company.network;

import com.company.utils.TextUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientHandlerTest {
    private final String ipaddress = "0.0.0.0";
    @BeforeAll
    static void start(){
        System.out.println("======== STARTING ClientHandler TESTS ========");
    }

    @BeforeEach
    void setup()  {
        System.out.println("-------- @BeforeEach --------");
    }

    @Test
    void getOutputStream() throws IOException {
        MyRunnable myRunnable = new MyRunnable(50000);
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println("-------- getOutputSteam TEST --------");
        ClientHandler clientHandler = new ClientHandler(ipaddress, 50000);
        System.out.println("ServerHandler in setup: " + clientHandler);
        assertNotNull(clientHandler.getOutputStream(), "OutputStream returned null!");
        clientHandler.close();
        myRunnable.doStop();
    }

    @Test
    void getInputStream() throws IOException {
      System.out.println("-------- getInputStream TEST --------");

      MyRunnable myRunnable = new MyRunnable(50001);
      Thread thread = new Thread(myRunnable);
      thread.start();
        ClientHandler clientHandler = new ClientHandler(ipaddress, 50001);
        System.out.println("ServerHandler in setup: " + clientHandler);
        assertNotNull(clientHandler.getInputStream(), "InputStream returned null!");
        clientHandler.close();
        myRunnable.doStop();
    }

    @Test
    void sendAndReceive() throws IOException {
      System.out.println("-------- sendAndReceive TEST --------");

      MyRunnable myRunnable = new MyRunnable(50002);
      Thread thread = new Thread(myRunnable);
      thread.start();
      ClientHandler clientHandler = new ClientHandler(ipaddress, 50002);

      Packet p1 = new Packet(CommandType.RENDER_CLIENT, new String[]{"Hej"});
      myRunnable.serverHandler.send(p1);

      Packet p2 = clientHandler.receive();
      System.out.printf("In sendAndReceive, got '%s' back from server\n",
          TextUtil.pimpString((String) p2.getParams()[0], TextUtil.LEVEL_INFO));

      assertEquals("Hej", p2.getParams()[0], "Sent message is not the same as received message!");
      clientHandler.close();
      myRunnable.doStop();
    }

  @Test
  void getPlayerNameFromClient() throws IOException {
    System.out.println("-------- getPlayerNameFromClient TEST --------");

    MyRunnable myRunnable = new MyRunnable(50003);
    Thread thread = new Thread(myRunnable);
    thread.start();
    ClientHandler clientHandler = new ClientHandler(ipaddress, 50003);

    Packet p1 = new Packet(CommandType.GET_PLAYER_NAME_FROM_CLIENT, null);
    myRunnable.serverHandler.send(p1);

    Packet p2 = clientHandler.receive();
    System.out.printf("In getPlayerNameFromClient, got command '%s' back from server\n",
        TextUtil.pimpString(String.valueOf(p2.getCommandType()), TextUtil.LEVEL_INFO));
    assertEquals(CommandType.GET_PLAYER_NAME_FROM_CLIENT, p2.getCommandType(), "Command type is wrong!");

    Packet p3 = new Packet(CommandType.GET_PLAYER_NAME_FROM_CLIENT, new String[]{"Player Two"});
    clientHandler.send(p3);

    Packet p4 = myRunnable.serverHandler.receive();
    assertEquals("Player Two", p4.getParams()[0], "Returned name is wrong!");
    System.out.printf("In getPlayerNameFromClient, got '%s' back from server\n",
        TextUtil.pimpString((String) p4.getParams()[0], TextUtil.LEVEL_INFO));

    clientHandler.close();
    myRunnable.doStop();
  }

  @AfterAll
    static void end()  {
        System.out.println("======== ENDING ClientHandler TESTS ========");
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