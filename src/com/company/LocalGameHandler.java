package com.company;

import com.company.interfaces.ComHandler;
import com.company.utils.Menu;
import com.company.utils.MenuChoiceBaseClass;
import com.company.utils.MenuChoiceFunction;

import java.util.ArrayList;

public class LocalGameHandler implements ComHandler {

    public LocalGameHandler(){}


    @Override
    public Card getCardFromClient(GameState gameState) {
        Menu cardMenu = getCardMenu(gameState);
        System.out.println("cardmenu: " + cardMenu);
        int chosenCard = (Integer) cardMenu.handleFunctionMenu(true);
        return gameState.getPlayer(Game.CLIENT).getCard(chosenCard);
    }

    public Menu getCardMenu(GameState gameState){
        ArrayList<MenuChoiceBaseClass> cardMenuList = new ArrayList<>();
        LocalGameHandler gameHandler = this;
        return new Menu() {
            @Override
            public ArrayList<MenuChoiceBaseClass> setInitialMenu() {
                final char[] key = {'1'};
                gameState.getPlayer(Game.CLIENT).getCardOnHandAsList().forEach((card )-> {
                    cardMenuList.add(new MenuChoiceFunction(card.toString(), key[0], gameHandler::handleCardMenu, Character.getNumericValue(key[0])));
                    key[0]++;
                });
                return cardMenuList;
            }
        };
    }

    public Object handleCardMenu(Object obj){
        return obj;
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
    public void renderClient(GameState gameState) {

    }
}
