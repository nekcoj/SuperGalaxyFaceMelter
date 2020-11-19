package com.company.network;

import com.company.interfaces.ComHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

abstract public class NetworkGameHandler implements ComHandler {

  protected static final int PORT = 42069;

  protected Socket socket;
  protected ObjectOutputStream oos;
  protected ObjectInputStream ois;

  public NetworkGameHandler(){
  }

  public ObjectOutputStream getOutputStream() {
    return oos;
  }

  public ObjectInputStream getInputStream() {
    return ois;
  }

  public void getStreams() {
    try {
      oos = new ObjectOutputStream(socket.getOutputStream());
      ois = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void close() throws IOException {
    oos.close();
    ois.close();
    socket.close();
  }
}
