package com.company.network;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkGameHandlerTest {

  NetworkGameHandler network;

  @BeforeAll
  static void start(){
    System.out.println("======== STARTING NetworkGameHandler TESTS ========");
  }

  @BeforeEach
  void setup(){
    System.out.println("-------- @BeforeEach --------");
    network = new NetworkGameHandler() {
      @Override
      void getStreams() {
      }
    };
  }

  @Test
  void getOutputStream() {
    System.out.println("-------- getOutputSteam TEST --------");
    assertNotNull(network.getOutputStream(), "OutputStream returned null!");
  }

  @Test
  void getInputStream() {
    System.out.println("-------- getInputStream TEST --------");
    assertNotNull(network.getInputStream(), "InputStream returned null!");
  }
}