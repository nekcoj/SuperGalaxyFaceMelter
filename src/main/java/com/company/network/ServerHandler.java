package com.company.network;

import com.company.Card;
import com.company.Game;
import com.company.GameState;
import com.company.interfaces.Renderer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerHandler extends NetworkComHandler {

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
  public void close() throws IOException {
    System.out.println("closing server");
    super.close();
    serverSocket.close();
  }

  @Override
  public String getPlayerNameFromClient() {
    Packet packet = new Packet(CommandType.GET_PLAYER_NAME_FROM_CLIENT, null);
    send(packet);
    System.out.println("Packet sent! - getPlayerNameFromClient");
    packet = receive();
    String s = (String) packet.getParams()[0];
    System.out.printf("getPlayerNameFromClient - Received %s from client\n", s);
    return s;
  }

  @Override
  public Card getCardFromClient(Renderer renderer, GameState gameState) {
    Packet packet = new Packet(CommandType.GET_CARD_FROM_CLIENT, new GameState[]{gameState});
    send(packet);
    packet = receive();
    Card card = (Card) packet.getParams()[0];
    System.out.printf("getCardFromClient - Received %s from client\n", card);
    return card;
  }

  @Override
  public GameState addToClientVictoryPile(Card card, GameState gameState, Renderer renderer) {
    Packet packet = new Packet(CommandType.ADD_TO_CLIENT_VICTORY_PILE,new Object[]{card, gameState});
    send(packet);
    packet = receive();
    GameState returnState = (GameState) packet.getParams()[0];
    return returnState;
  }

  @Override
  public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
    Packet packet = new Packet(CommandType.SEND_CARD_TO_CLIENT, new Object[]{cardsToClient, gameState});
    send(packet);

    packet = receive();
    GameState returnState = (GameState) packet.getParams()[1];
    System.out.printf("getCardFromClient - Received %s from client\n", returnState);
    return returnState;
  }

  @Override
  public void renderClient(Renderer renderer, GameState gameState, int playerToDraw) {
    Packet packet = new Packet(CommandType.RENDER_CLIENT, new Object[]{gameState, playerToDraw});
    send(packet);
  }
}
