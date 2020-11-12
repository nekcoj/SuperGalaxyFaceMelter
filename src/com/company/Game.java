package com.company;

import com.company.interfaces.Renderer;

public class Game {

    public static final int PLAYER_COUNT = 2;

    protected GameLobby gameLobby;
    protected Renderer gameBoard;
    protected Player player;

    public Game(GameLobby gameLobby, Renderer gameBoard, String playerName) {
        this.gameLobby = gameLobby;
        this.gameBoard = gameBoard;
        this.player = new Player();
    }
}
