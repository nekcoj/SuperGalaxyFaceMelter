package com.company.interfaces;

import com.company.GameState;

public interface Renderer {
  void continueGame();
  String getPlayerName();
  void render(GameState gameState, int playerToDraw);
  int getCard(GameState gameState, int playerToDraw);
}
