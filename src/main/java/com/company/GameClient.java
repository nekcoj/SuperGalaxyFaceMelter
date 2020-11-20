package com.company;

public class GameClient extends Game {
    public GameClient(GameLobby gameLobby) {
        super(gameLobby, null, null);
    }

    public void runGame() {
        while (true) {
           gameLobby.getCommandFromHost();
        }
    }
}
