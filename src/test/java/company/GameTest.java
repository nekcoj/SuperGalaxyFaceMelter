package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest  {

    private Game game;
    GameState gs = new GameState();

    @BeforeEach
    void setUp() {
        game = new Game(new GameLobby(false), new ScreenRenderer(), gs);
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(game, "Game constructor returned null");
    }
}