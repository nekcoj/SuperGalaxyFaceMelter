package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;
import com.company.network.ClientHandler;
import com.company.network.NetworkComHandler;
import com.company.network.Packet;

import java.util.ArrayList;

public class Dispatcher {

  ComHandler comHandler;
  Renderer renderer;

  public Dispatcher(ComHandler comHandler, Renderer renderer) {
    this.comHandler = comHandler;
    this.renderer = renderer;
  }

  public String getPlayerNameFromClient() {
    return comHandler.getPlayerNameFromClient();
  }

  public Card getCardFromClient(GameState gameState) {
    return comHandler.getCardFromClient(renderer, gameState);
  }

  public GameState addToClientVictoryPile(Card card, GameState gameState) {
   return comHandler.addToClientVictoryPile(card, gameState, renderer);
  }

  public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
    return comHandler.sendCardToClient(cardsToClient, gameState);
  }

  public void renderClient(GameState gameState, int playerToDraw) {
    comHandler.renderClient(renderer, gameState, playerToDraw);
  }

  public void getCommandFromHost() {
    ClientHandler nch = (ClientHandler)comHandler;
    Packet p = nch.receive();
    GameState gs = null;
    Card card = null;
    System.out.printf("getCommandFromHost: command is %s!\n", p.getCommandType());

    switch (p.getCommandType()) {
      case RENDER_CLIENT:
        gs = (GameState) p.getParams()[0];
        int playerToDraw = (int) p.getParams()[1];
        renderClient(gs, playerToDraw);
        break;

      case ADD_TO_CLIENT_VICTORY_PILE:
        card = (Card)p.getParams()[0];
        gs =  (GameState)p.getParams()[1];
        addToClientVictoryPile(card, gs);
        break;

      case GET_CARD_FROM_CLIENT:
        gs = (GameState) p.getParams()[0];
        getCardFromClient(gs);
        break;

      case GET_PLAYER_NAME_FROM_CLIENT:
        String name = renderer.getPlayerName();
        nch.sendPlayerNameToHost(name);
        System.out.println(name);
        break;

      case SEND_CARD_TO_CLIENT:
        ArrayList<Card> cardsToClient = (ArrayList<Card>) p.getParams()[0];
        gs = (GameState) p.getParams()[1];
        sendCardToClient(cardsToClient, gs);
        break;

      default:
        System.out.printf("Unknown command: %d!\n" + p.getCommandType());
    }
  }
}
