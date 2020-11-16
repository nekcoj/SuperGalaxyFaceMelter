package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;

import java.util.ArrayList;

public class Dispatcher implements ComHandler {

    ComHandler comHandler;
    Renderer renderer;

    public Dispatcher(ComHandler comHandler, Renderer renderer) {
        this.comHandler = comHandler;
        this.renderer = renderer;
    }

    @Override
    public Card getCardFromClient(GameState gameState) {
       return comHandler.getCardFromClient(gameState);
    }

    @Override
    public void addToVictoryPileClient(Card card) {
        comHandler.addToVictoryPileClient(card);
    }

    @Override
    public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
        return comHandler.sendCardToClient(cardsToClient, gameState);
    }

    @Override
    public void renderClient(GameState gameState, int playerToDraw) {
        renderer.render(gameState, playerToDraw);
    }


}
