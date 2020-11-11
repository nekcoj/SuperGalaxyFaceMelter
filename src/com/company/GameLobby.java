package com.company;

import com.company.interfaces.ComHandler;
import com.company.interfaces.Renderer;
import com.company.utils.GameLobbyMenu;

public class GameLobby {

    private GameLobbyMenu menu;

    public GameLobby() {
        menu = new GameLobbyMenu(this);
        runMenu();
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
        Dispatcher dispatcher = new Dispatcher(comHandler);
        ScreenRenderer renderer = new ScreenRenderer();
        GameHost host = new GameHost(this, renderer, "Player1", 5, 50);
        GameClient client = new GameClient(this, renderer, "Player2");
        System.out.println("i startLocalGame");
    }

    public void startNetworkGame(Object o) {
        System.out.println("i startNetworkGame");
    }

    public void connectToNetworkGame(Object o) {
        System.out.println("i connectToNetworkGame");
    }
}
