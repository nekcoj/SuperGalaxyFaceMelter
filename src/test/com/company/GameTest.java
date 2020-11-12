package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest  {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(new GameLobby(false), new ScreenRenderer(), "Testis");
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(game, "Game constructor returned null");
    }
}