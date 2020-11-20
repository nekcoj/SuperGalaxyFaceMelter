package com.company;

import com.company.interfaces.ComHandler;
import com.company.network.ClientHandler;
import com.company.utils.GameLobbyMenu;

import java.io.IOException;
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
        GameState gs = new GameState(
                inputGameSettings("Enter points to win", 10, 20, 15), players);
        ComHandler comHandler = new LocalGameHandler();
        ConsoleRenderer renderer = new ConsoleRenderer();
        dispatcher = new Dispatcher(comHandler, renderer);
        GameHost host = new GameHost(this, renderer, gs,
                inputGameSettings("Enter amount of cards on hand", 1, 8, 5), true);
//        GameClient client = new GameClient(this, renderer, gs);
        host.runGame();
    }

    // TODO: 2020-11-20 Move to Renderer...
    public String inputPlayerName(String player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter name for " + player + ": ");
        return scanner.nextLine();
    }

    // TODO: 2020-11-20 Move to Renderer...
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
        ComHandler comHandler = null;

        try {
            comHandler = new ClientHandler("localhost");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        ConsoleRenderer renderer = new ConsoleRenderer();
        dispatcher = new Dispatcher(comHandler, renderer);
        GameClient gameClient = new GameClient(this);
        gameClient.runGame();
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

    public String getPlayerNameFromClient() {
        return dispatcher.getPlayerNameFromClient();
    }

    public void getCommandFromHost() {
        dispatcher.getCommandFromHost();
    }
}
