package com.company.network;

import com.company.Card;
import com.company.GameState;
import com.company.interfaces.Renderer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerHandler extends NetworkGameHandler{

  protected static final int PORT = 42069;
  private ServerSocket serverSocket;

  public ServerHandler() throws IOException {
    serverSocket = new ServerSocket(PORT);
  }
  public ServerHandler(int port) throws IOException {
    serverSocket = new ServerSocket(port);
  }

  public void startServer() throws IOException {
    serverSocket.accept();
  }

  @Override
  void getStreams() {

  }

  @Override
  void closeServer() throws IOException {
    serverSocket.close();
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
