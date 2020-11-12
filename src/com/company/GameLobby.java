package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;
import com.company.utils.GameLobbyMenu;

public class GameLobby {

    private GameLobbyMenu menu;

    public GameLobby(Boolean runGame) {
        menu = new GameLobbyMenu(this);
        if (runGame) runMenu();
    }

    public void runMenu() {
        menu.handleMenu();
    }

    public void startLocalGame(Object o) {
        // be om namnet på spelare 1
        // be om namnet på spelare 2
        // starta en GameHost med spelare 1
        // starta en GameClient med spelare 2
        // kör GameHost
        ComHandler comHandler = new LocalGameHandler();
        ScreenRenderer renderer = new ScreenRenderer();
        Dispatcher dispatcher = new Dispatcher(comHandler, renderer);
        GameHost host = new GameHost(this, renderer, "Player1", 5, 50);
        GameClient client = new GameClient(this, renderer, "Player2");
        System.out.println("i startLocalGame");
    }

    public void startNetworkGame(Object o) {
        // Create a GameHost
        System.out.println("i startNetworkGame");
    }

    public void connectToNetworkGame(Object o) {
        // Create a GameClient
        System.out.println("i connectToNetworkGame");
    }
}
