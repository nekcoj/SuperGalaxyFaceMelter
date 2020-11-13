package com.company;

import com.company.interfaces.Renderer;

public class GameBoard implements Renderer {
    @Override
    public void render(GameState gameState) {
        System.out.println(generatePrintString());
    }

    public String generatePrintString() {
        return "Here is the screen output!";
    }
}
