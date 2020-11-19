package com.company.network;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerHandler extends NetworkGameHandler{

  protected static final int PORT = 42069;
  private ServerSocket serverSocket;

  public ServerHandler() throws IOException {
    serverSocket = new ServerSocket(PORT);

  }

  @Override
  void getStreams() {

  }
}
