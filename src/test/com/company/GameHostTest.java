package com.company;

import com.company.interfaces.ComHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameHostTest {

   private GameHost gameHost;

    @BeforeEach
    void setup() {
        ComHandler comHandler = new LocalGameHandler();
        gameHost = new GameHost(new GameLobby(false), new ScreenRenderer(), "Testis", 5, 50);
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(gameHost, "GameHost constructor returned null!");
    }

    @Test
    void getCardFromStartPlayerTest() {

    }

    @Test
    void getCardFromSecondPlayerTest() {

    }

    @Test
    void roundWinnerTest() {

    }

    @Test
    void isGameOver() {
        Assertions.assertFalse(gameHost.isGameOver(), "Game is over!");
    }
}