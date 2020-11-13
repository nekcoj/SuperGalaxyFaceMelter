package com.company;

import com.company.interfaces.ComHandler;
import com.company.utils.GameLobbyMenu;

import java.util.ArrayList;

public class GameLobby {

    private GameLobbyMenu menu;
    private Dispatcher dispatcher;

    public GameLobby(Boolean runGame) {
        menu = new GameLobbyMenu(this);
        if (runGame) runMenu();
    }

    public void runMenu() {
        menu.handleMenu();
    }

    public void startLocalGame(Object o) {
        System.out.println("i startLocalGame");

        // be om namnet på spelare 1
        // be om namnet på spelare 2
        // starta en GameHost med spelare 1
        // starta en GameClient med spelare 2
        // kör GameHost
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        GameState gs = new GameState();
        ComHandler comHandler = new LocalGameHandler();
        ScreenRenderer renderer = new ScreenRenderer();
        dispatcher = new Dispatcher(comHandler, renderer);
        GameHost host = new GameHost(this, renderer, gs, 5, 50);
        GameClient client = new GameClient(this, renderer, gs);

        host.runGame();
    }

    public void startNetworkGame(Object o) {
        // Create a GameHost
        System.out.println("i startNetworkGame");
    }

    public void connectToNetworkGame(Object o) {
        // Create a GameClient
        System.out.println("i connectToNetworkGame");
    }

    public Card requestCardFromClient(){
        return null;
    }

    public void addToClientVictoryPile(){

    }

    public boolean sendCardToClient(ArrayList<Card> cardsToClient){
        return false;
    }

    public void renderClient(GameState gs, byte playerToDraw){

    }
}
