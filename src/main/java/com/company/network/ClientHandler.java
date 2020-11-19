package com.company.network;

import com.company.Card;
import com.company.GameState;
import com.company.interfaces.Renderer;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends NetworkGameHandler{
  Socket socket;

  public ClientHandler(String ipaddress) throws IOException {

    socket = new Socket(ipaddress, ServerHandler.PORT);

  }

  @Override
  void getStreams() {

  }

  @Override
  void closeServer() throws IOException {
    socket.close();
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
