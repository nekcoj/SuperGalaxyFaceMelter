package com.company.network;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends NetworkGameHandler{
  Socket socket;

  public ClientHandler(String ipaddress) throws IOException {

    socket = new Socket(ipaddress, ServerHandler.PORT);

  }

  @Override
  void getStreams() {

  }
}
