package com.company;

import com.company.interfaces.Renderer;

public class GameClient extends Game {

    public GameClient(GameLobby gameLobby, Renderer renderer, GameState gameState) {
        super(gameLobby, renderer, gameState);
    }
}
