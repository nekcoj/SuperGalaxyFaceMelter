package com.company;

import com.company.gameengine.Game;
import com.company.gameengine.GameLobby;
import com.company.gameobjects.GameState;
import com.company.renderer.ConsoleRenderer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest  {

    private Game game;
    GameState gs = new GameState();

    @BeforeEach
    void setUp() {
        game = new Game(new GameLobby(false), new ConsoleRenderer(), gs);
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(game, "Game constructor returned null");
    }
}