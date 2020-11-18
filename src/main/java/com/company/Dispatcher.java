package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;

import java.util.ArrayList;

public class Dispatcher {

    ComHandler comHandler;
    Renderer renderer;

    public Dispatcher(ComHandler comHandler, Renderer renderer) {
        this.comHandler = comHandler;
        this.renderer = renderer;
    }

    public Card getCardFromClient(GameState gameState) {
       return comHandler.getCardFromClient(renderer, gameState);
    }

    public void addToClientVictoryPile(Card card, GameState gameState) {
        comHandler.addToClientVictoryPile(card, gameState);
    }

    public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
        return comHandler.sendCardToClient(cardsToClient, gameState);
    }

    public void renderClient(GameState gameState, int playerToDraw) {
        comHandler.renderClient(renderer, gameState, playerToDraw);
    }
}
