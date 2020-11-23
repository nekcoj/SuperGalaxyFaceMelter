package com.company.network;

import com.company.interfaces.ComHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

abstract public class NetworkComHandler implements ComHandler {

  protected static final int PORT = 42069;

  protected Socket socket;
  protected ObjectOutputStream oos;
  protected ObjectInputStream ois;

  public NetworkComHandler(){
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

  public void send(Packet packet) {
    try {
      oos.reset();
      oos.writeUnshared(packet);
      oos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Packet receive() {
    Packet packet = null;
    try {
      packet = (Packet)ois.readUnshared();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return packet;
  }

  public void close() throws IOException {
    oos.close();
    ois.close();
    socket.close();
  }
}
