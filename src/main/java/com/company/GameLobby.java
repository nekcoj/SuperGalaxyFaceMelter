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
        menu.handleConsumerMenu();
    }

    public void startLocalGame(Object o) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        GameState gs = new GameState(15, players);
        ComHandler comHandler = new LocalGameHandler();
        ScreenRenderer renderer = new ScreenRenderer();
        dispatcher = new Dispatcher(comHandler, renderer);
        GameHost host = new GameHost(this, renderer, gs, 5);
//        GameClient client = new GameClient(this, renderer, gs);
        host.runGame();
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void startNetworkGame(Object o) {
        // Create a GameHost
        System.out.println("i startNetworkGame");
    }

    public void connectToNetworkGame(Object o) {
        // Create a GameClient
        System.out.println("i connectToNetworkGame");
    }

    public Card requestCardFromClient(GameState gameState){
       return dispatcher.getCardFromClient(gameState);
    }

    // TODO: 2020-11-18 Use dispatcher...
    public void addToClientVictoryPile(){

    }

    public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState){
      return dispatcher.sendCardToClient(cardsToClient, gameState);
    }

    public void renderClient(GameState gs, int playerToDraw){
        dispatcher.renderClient(gs, playerToDraw);
    }
}
