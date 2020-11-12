package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;

public class Dispatcher implements ComHandler {

    ComHandler comHandler;
    Renderer renderer;

    public Dispatcher(ComHandler comHandler, Renderer renderer) {
        this.comHandler = comHandler;
        this.renderer = renderer;
    }

    @Override
    public Card getCardFromClient() {
       return comHandler.getCardFromClient();
    }

    @Override
    public void addToVictoryPileClient(Card card) {
        comHandler.addToVictoryPileClient(card);
    }

    @Override
    public void sendCardToClient(Card card) {
        comHandler.sendCardToClient(card);
    }

    @Override
    public void renderClient(GameState gameState) {
        renderer.render(gameState);
    }


}
