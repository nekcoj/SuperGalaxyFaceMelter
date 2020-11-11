package com.company;

import com.company.interfaces.ComHandler;

public class Dispatcher implements ComHandler {

    ComHandler comHandler;

    public Dispatcher(ComHandler comHandler) {
        this.comHandler = comHandler;
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


}
