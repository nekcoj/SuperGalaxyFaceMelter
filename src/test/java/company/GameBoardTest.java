package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    @Test
    void testGeneratePrintString() {
        GameBoard gb = new GameBoard();
        String printOut = gb.generatePrintString();
        assertEquals("Here is the screen output!", printOut);
    }
}