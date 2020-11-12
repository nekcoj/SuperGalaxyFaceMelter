package com.company;

import com.company.interfaces.ComHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertNotNull(gameHost, "GameHost constructor returned null!");
    }

    @Test
    void getCardFromStartPlayerTest() {
        Card card = gameHost.getCardFromStartPlayer();
        assertNotNull(card, "card is null!");
    }

    @Test
    void getCardFromSecondPlayerTest() {
        Card card = gameHost.getCardFromSecondPlayer();
        assertNotNull(card, "card is null!");
    }

    @Test
    void roundWinnerTest() {
        assertEquals(-1, gameHost.getRoundWinner());
    }

    @Test
    void getCardFromPlayer1Test() {
        Card card = gameHost.getCardFromPlayer1();
        assertNotNull(card, "card is null!");
    }

    @Test
    void getCardFromPlayer2Test() {
        Card card = gameHost.getCardFromPlayer2();
        assertNotNull(card, "card is null!");
    }

    @Test
    void isGameOver() {
        assertFalse(gameHost.isGameOver(), "Game is over!");
    }
}