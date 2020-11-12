package com.company;

import com.company.interfaces.Renderer;

public class Game {

    public static final int PLAYER_COUNT = 2;

    protected GameLobby gameLobby;
    protected Renderer gameBoard;
    protected GameState gameState;

    public Game(GameLobby gameLobby, Renderer gameBoard, GameState gameState) {
        this.gameLobby = gameLobby;
        this.gameBoard = gameBoard;
        this.gameState = gameState;
    }
}
