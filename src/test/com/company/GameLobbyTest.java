package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameLobbyTest {

    private GameLobby gameLobby;

    @BeforeEach
    void setup() {
        gameLobby = new GameLobby();
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(gameLobby, "GameLobby constructor returned null!");
    }

    @Test
    void startLocalGame() {

    }

    @Test
    void startNetworkGame() {
    }

    @Test
    void connectToNetworkGame() {
    }
}