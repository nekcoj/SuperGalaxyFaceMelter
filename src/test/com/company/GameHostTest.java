package com.company;

import com.company.interfaces.ComHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameHostTest {

   private GameHost gameHost;

    @BeforeEach
    void setup() {
        GameState gs = new GameState();
        ComHandler comHandler = new LocalGameHandler();
        gameHost = new GameHost(new GameLobby(false), new ScreenRenderer(), gs, 5, 50);
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(gameHost, "GameHost constructor returned null!");
    }

    @Test
    void getCardFromStartPlayerTest() {
        Card card = gameHost.getCardFromStartPlayer();
        Assertions.assertNotNull(card, "card is null!");
    }

    @Test
    void getCardFromSecondPlayerTest() {
        Card card = gameHost.getCardFromSecondPlayer();
        Assertions.assertNotNull(card, "card is null!");
    }

    @Test
    void roundWinnerTest() {
        Assertions.assertEquals(-1, gameHost.getRoundWinner());
    }

    @Test
    void getCardFromPlayer1Test() {
        Card card = gameHost.getCardFromPlayer1();
        Assertions.assertNotNull(card, "card is null!");
    }

    @Test
    void getCardFromPlayer2Test() {
        Card card = gameHost.getCardFromPlayer2();
        Assertions.assertNotNull(card, "card is null!");
    }

    @Test
    void isGameOver() {
        Assertions.assertFalse(gameHost.isGameOver(), "Game is over!");
    }
}