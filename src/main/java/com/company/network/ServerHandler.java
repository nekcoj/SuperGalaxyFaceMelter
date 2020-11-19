package com.company.network;

import com.company.Card;
import com.company.GameState;
import com.company.interfaces.Renderer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerHandler extends NetworkGameHandler{

  private ServerSocket serverSocket;

  public ServerHandler() throws IOException {
    super();
    serverSocket = new ServerSocket(PORT);

  }
  public ServerHandler(int port) throws IOException {
    super();
    serverSocket = new ServerSocket(port);
  }

  public void startServer() throws IOException {
    System.out.println("Server will accept");
    socket = serverSocket.accept();
    getStreams();
  }

  @Override
  void close() throws IOException {
    System.out.println("closing server");
    super.close();
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
