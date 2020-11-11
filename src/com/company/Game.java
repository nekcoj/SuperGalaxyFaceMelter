package com.company;

import com.company.interfaces.Renderer;

public class Game {

    public static final int PLAYER_COUNT = 2;

    protected GameLobby gameLobby;
    protected Renderer renderer;
    protected Player player;

    public Game(GameLobby gameLobby, Renderer renderer, String playerName) {
        this.gameLobby = gameLobby;
        this.renderer = renderer;
        this.player = new Player();
    }
}
