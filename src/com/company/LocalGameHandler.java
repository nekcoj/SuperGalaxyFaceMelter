package com.company;

import com.company.interfaces.ComHandler;

public class LocalGameHandler implements ComHandler {

    public LocalGameHandler(){}


    @Override
    public Card getCardFromClient() {
        return null;
    }

    @Override
    public void addToVictoryPileClient(Card card) {

    }

    @Override
    public void sendCardToClient(Card card) {

    }

    @Override
    public void renderClient(GameState gameState) {

    }
}
