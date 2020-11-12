package com.company;

import com.company.interfaces.Renderer;

public class GameClient extends Game {

    public GameClient(GameLobby gameLobby, Renderer renderer, String playerName) {
        super(gameLobby, renderer, playerName);
    }
}
