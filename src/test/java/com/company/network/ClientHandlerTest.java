package com.company.network;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientHandlerTest {

    @BeforeAll
    static void start(){
        System.out.println("======== STARTING ClientHandler TESTS ========");
    }

    @BeforeEach
    void setup() throws IOException {
        System.out.println("-------- @BeforeEach --------");
    }

    @Test
    void getOutputStream() throws IOException {
        MyRunnable myRunnable = new MyRunnable(50000);
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println("-------- getOutputSteam TEST --------");
        ClientHandler clientHandler = new ClientHandler("localhost", 50000);
        System.out.println("ServerHandler in setup: " + clientHandler);
        assertNotNull(clientHandler.getOutputStream(), "OutputStream returned null!");
        clientHandler.close();
        myRunnable.doStop();
    }

    @Test
    void getInputStream() throws IOException {

        MyRunnable myRunnable = new MyRunnable(50001);
        Thread thread = new Thread(myRunnable);
        thread.start();
        System.out.println("-------- getInputStream TEST --------");
        ClientHandler clientHandler = new ClientHandler("localhost", 50001);
        System.out.println("ServerHandler in setup: " + clientHandler);
        assertNotNull(clientHandler.getInputStream(), "InputStream returned null!");
        clientHandler.close();
        myRunnable.doStop();
    }

    @AfterAll
    static void end() throws IOException {
        System.out.println("======== ENDING ClientHandler TESTS ========");
    }

  public class MyRunnable implements Runnable {

        int port;

        public MyRunnable(int port) {
            this.port = port;
        }

    private boolean doStop = false;

    public synchronized void doStop() {
      this.doStop = true;
    }

    private synchronized boolean keepRunning() {
      return this.doStop == false;
    }

    @Override
    public void run() {
        ServerHandler serverHandler = null;
      try {
        serverHandler = new ServerHandler(port);
        serverHandler.startServer();
      } catch (IOException e) {
        e.printStackTrace();
      }

      while(keepRunning()) {
        // keep doing what this thread should do.
        System.out.println("Running");
        ;
        try {
          Thread.sleep(3L * 1000L);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
        try {
            serverHandler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Thread stopped!");
    }
  }
}