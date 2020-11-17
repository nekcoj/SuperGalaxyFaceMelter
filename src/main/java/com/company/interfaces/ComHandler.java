package com.company.interfaces;

import com.company.Card;
import com.company.GameState;
import com.company.Player;

import java.util.ArrayList;

public interface ComHandler {

   public Card getCardFromClient(Renderer renderer, GameState gameState);
   public void addToVictoryPileClient(Card card);
   public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState);
   public void renderClient(GameState gameState, int playerToDraw);

}
