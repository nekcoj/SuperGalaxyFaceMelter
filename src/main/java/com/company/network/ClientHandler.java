package com.company.network;

import com.company.Card;
import com.company.GameState;
import com.company.interfaces.Renderer;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends NetworkGameHandler{


  public ClientHandler(String ipaddress) throws IOException {
    this(ipaddress, NetworkGameHandler.PORT);
  }

  public ClientHandler(String ipaddress, int port) throws IOException {
    super();
    socket = new Socket(ipaddress, port);
    getStreams();
  }

  @Override
  public Card getCardFromClient(Renderer renderer, GameState gameState) {
    return null;
  }

  @Override
  public void addToClientVictoryPile(Card card, GameState gameState) {

  }

  @Override
  public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
    return null;
  }

  @Override
  public void renderClient(Renderer renderer, GameState gameState, int playerToDraw) {

  }
}
