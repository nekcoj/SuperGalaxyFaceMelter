package com.company;

import com.company.gameengine.GameLobby;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameLobbyTest {

    private GameLobby gameLobby;

    @BeforeEach
    void setup() {
        gameLobby = new GameLobby(false);
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