package com.company.interfaces;

import com.company.Card;
import com.company.GameState;

public interface Renderer {
    public void render(GameState gameState, int playerToDraw);
    public Card getCard(GameState gameState, int playerToDraw);

    public String getPlayerNameFromClient();
}
