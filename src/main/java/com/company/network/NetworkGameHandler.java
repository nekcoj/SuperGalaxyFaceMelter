package com.company.network;

import com.company.Card;
import com.company.GameState;
import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

abstract public class NetworkGameHandler implements ComHandler {

  protected ObjectOutputStream oos;
  protected ObjectInputStream ois;

  public NetworkGameHandler(){
    getStreams();
  }

  @Override
  public Card getCardFromClient(Renderer renderer, GameState gameState) {
    return null;
  }

  @Override
  public void addToClientVictoryPile(Card card) {

  }

  @Override
  public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
    return null;
  }

  @Override
  public void renderClient(GameState gameState, int playerToDraw) {

  }

  public ObjectOutputStream getOutputStream() {
    return oos;
  }

  public ObjectInputStream getInputStream() {
    return ois;
  }

  abstract void getStreams();
}
