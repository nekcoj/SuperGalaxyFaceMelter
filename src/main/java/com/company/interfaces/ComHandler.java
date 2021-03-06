package com.company.interfaces;

import com.company.gameobjects.Card;
import com.company.gameobjects.GameState;

import java.util.ArrayList;

public interface ComHandler {
   String getPlayerNameFromClient();
   int getCardFromClient(Renderer renderer, GameState gameState);
   GameState addToClientVictoryPile(Card card, GameState gameState, Renderer renderer);
   GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState);
   void renderClient(Renderer renderer, GameState gameState, int playerToDraw);
   void sendGameOver();

}
