package com.company.interfaces;

import com.company.Card;
import com.company.GameState;

import java.util.ArrayList;

public interface ComHandler {

   public String getPlayerNameFromClient(String name);
   public Card getCardFromClient(Renderer renderer, GameState gameState);
   public void addToClientVictoryPile(Card card, GameState gameState);
   public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState);
   public void renderClient(Renderer renderer, GameState gameState, int playerToDraw);

}
