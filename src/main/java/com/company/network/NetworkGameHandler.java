package com.company.network;

import com.company.Card;
import com.company.GameState;
import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

abstract public class NetworkGameHandler implements ComHandler {

  protected ObjectOutputStream oos;
  protected ObjectInputStream ois;

  public NetworkGameHandler(){
    getStreams();
  }

  public ObjectOutputStream getOutputStream() {
    return oos;
  }

  public ObjectInputStream getInputStream() {
    return ois;
  }

  abstract void getStreams();

  abstract void closeServer() throws IOException;
}
