package com.company.interfaces;

import com.company.GameState;

public interface Renderer {
    public void render(GameState gameState, int playerToDraw);
    public int getCard(GameState gameState, int playerToDraw);

    public String getPlayerName();
}
