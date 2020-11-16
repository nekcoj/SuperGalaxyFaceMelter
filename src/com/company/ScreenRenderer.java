package com.company;

import com.company.interfaces.Renderer;

import java.util.ArrayList;

public class ScreenRenderer implements Renderer {

    private String topLeft = "╔";
    private String lineHor = "═";
    private String lineVert = "║";
    private String topRight = "╗";
    private String bottomLeft = "╚";
    private String bottomRight = "╝";
    private int cardWidth = 18;
    private int cardHeight = 12;

    @Override
    public void render(GameState gameState) {

    }

    public String generatePrintString(ArrayList<Card> cards){
        return "";
    }
}
