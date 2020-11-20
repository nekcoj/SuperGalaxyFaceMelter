package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;

import java.util.ArrayList;

public class LocalGameHandler implements ComHandler {

    public LocalGameHandler(){}

    @Override
    public String getPlayerNameFromClient() {
        return null;
    }

    @Override
    public Card getCardFromClient(Renderer renderer, GameState gameState) {
        renderer.render(gameState, Game.CLIENT);
        return renderer.getCard(gameState, Game.CLIENT);
    }

    @Override
    public GameState addToClientVictoryPile(Card card, GameState gameState) {
        gameState.getPlayer(Game.CLIENT).addToVictoryPile(card);
        return gameState;
    }

    @Override
    public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
        gameState.getPlayer(Game.CLIENT).addCardsToHand(cardsToClient);
        return gameState;
    }

    @Override
    public void renderClient(Renderer renderer, GameState gameState, int playerToDraw) {
        renderer.render(gameState, playerToDraw);
    }
}
