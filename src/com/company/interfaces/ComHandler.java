package com.company.interfaces;

import com.company.Card;
import com.company.GameState;
import com.company.Player;

public interface ComHandler {

   public Card getCardFromClient();
   public void addToVictoryPileClient(Card card);
   public void sendCardToClient(Card card);
   public void renderClient(GameState gameState);

}
