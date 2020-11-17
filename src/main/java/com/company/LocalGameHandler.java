package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;
import com.company.utils.Menu;
import com.company.utils.MenuChoiceBaseClass;
import com.company.utils.MenuChoiceFunction;

import java.util.ArrayList;

public class LocalGameHandler implements ComHandler {

    public LocalGameHandler(){}

    @Override
    public Card getCardFromClient(Renderer renderer, GameState gameState) {
        renderer.render(gameState, Game.CLIENT);
        return renderer.getCard(gameState, Game.CLIENT);
    }

    @Override
    public void addToVictoryPileClient(Card card) {

    }

    @Override
    public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState) {
        gameState.getPlayer(Game.CLIENT).addCardsToHand(cardsToClient);
        return gameState;
    }

    @Override
    public void renderClient(GameState gameState, int playerToDraw) {

    }
}
