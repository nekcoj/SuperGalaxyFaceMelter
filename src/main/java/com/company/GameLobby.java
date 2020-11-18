package com.company;

import com.company.interfaces.ComHandler;
import com.company.utils.GameLobbyMenu;

import java.util.ArrayList;
import java.util.Scanner;

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
        Player player1 = new Player(inputPlayerName("player 1"));
        Player player2 = new Player(inputPlayerName("player 2"));
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        int test = inputGameSettings("Enter points to win", 10, 20, 15);
        GameState gs = new GameState(15, players);
        ComHandler comHandler = new LocalGameHandler();
        ScreenRenderer renderer = new ScreenRenderer();
        dispatcher = new Dispatcher(comHandler, renderer);
        GameHost host = new GameHost(this, renderer, gs, 5);
//        GameClient client = new GameClient(this, renderer, gs);
        host.runGame();
    }

    public String inputPlayerName(String player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter name for " + player + ": ");
        return scanner.nextLine();
    }

    public int inputGameSettings(String prompt, int min, int max, int def) {
        boolean isValueOk;
        int value;
        String input;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("\n%s (%d - %d) [%d]: ", prompt, min, max, def);
            input = scanner.nextLine();
            if (input.isEmpty()) {
                input = String.format("%d", def);
            }
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                value = -1;
            }
            isValueOk = value >= min && value <= max;
        } while (!isValueOk);
        return value;
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

    public void addToClientVictoryPile(Card cardToClient, GameState gameState){
        dispatcher.addToClientVictoryPile(cardToClient, gameState);
    }

    public GameState sendCardToClient(ArrayList<Card> cardsToClient, GameState gameState){
      return dispatcher.sendCardToClient(cardsToClient, gameState);
    }

    public void renderClient(GameState gs, int playerToDraw){
        dispatcher.renderClient(gs, playerToDraw);
    }
}
