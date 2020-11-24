package com.company.network;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ServerHandlerTest{

  @BeforeAll
  static void start(){
    System.out.println("======== STARTING ServerHandler TESTS ========");
  }

  @BeforeEach
  void setup() throws IOException {
    System.out.println("-------- @BeforeEach --------");
  }

  @Test
  void getOutputStream() throws IOException {
    System.out.println("-------- getOutputSteam TEST --------");
    ServerHandler serverHandler = new ServerHandler(12347);
    serverHandler.getStreams();
    System.out.println("ServerHandler in setup: " + serverHandler);
    assertNull(serverHandler.getOutputStream(), "OutputStream returned null!");
    serverHandler.close();
  }

  @Test
  void getInputStream() throws IOException {
    System.out.println("-------- getInputStream TEST --------");
    ServerHandler serverHandler = new ServerHandler(13246);
    serverHandler.getStreams();
    System.out.println("ServerHandler in setup: " + serverHandler);
    assertNull(serverHandler.getInputStream(), "InputStream returned null!");
    serverHandler.close();
  }

  @AfterAll
  static void end() throws IOException {
    System.out.println("======== ENDING ServerHandler TESTS ========");
  }
}