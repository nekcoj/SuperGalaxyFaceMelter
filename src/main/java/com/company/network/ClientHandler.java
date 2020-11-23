package com.company.network;

import com.company.Card;
import com.company.Game;
import com.company.GameState;
import com.company.interfaces.Renderer;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends NetworkComHandler {

  public ClientHandler(String ipaddress) throws IOException {
    this(ipaddress, NetworkComHandler.PORT);
  }

  public ClientHandler(String ipaddress, int port) throws IOException {
    super();
    socket = new Socket(ipaddress, port);
    getStreams();
  }

  @Override
  public Card getCardFromClient(Renderer renderer, GameState gameState) {
    renderer.render(gameState, Game.CLIENT);
    Card card = renderer.getCard(gameState, Game.CLIENT);
    Packet p = new Packet(CommandType.GET_CARD_FROM_CLIENT, new Card[]{card});
    send(p);
    return card;
  }

  @Override
  public GameState addToClientVictoryPile(Card card, GameState gameState, Renderer renderer) {
    renderer.render(gameState, Game.CLIENT);
    gameState.getPlayer(Game.CLIENT).addToVictoryPile(card);
    return gameState;
  }

  @Override
  public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
    gameState.getPlayer(Game.CLIENT).addCardsToHand(cardsToClient);
    Object[] params = new Object[2];
    params[0] = cardsToClient;
    params[1] = gameState;
    Packet p = new Packet(CommandType.GET_CARD_FROM_CLIENT, params);
    send(p);
    return gameState;
  }

  public String sendPlayerNameToHost(String name) {
    Packet p = new Packet(CommandType.GET_PLAYER_NAME_FROM_CLIENT, new String[]{name});
    send(p);
    return name;
  }

  @Override
  public void renderClient(Renderer renderer, GameState gameState, int playerToDraw) {
    renderer.render(gameState, playerToDraw);
  }

  @Override
  public String getPlayerNameFromClient() {
    return null;
  }
}
