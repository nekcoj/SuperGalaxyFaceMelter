package com.company.utils;

import com.company.GameLobby;

import java.util.ArrayList;

public class GameLobbyMenu extends Menu {

    private ArrayList<MenuChoice> mainMenu = new ArrayList<MenuChoice>();
    private ArrayList<MenuChoice> networkGameMenu = new ArrayList<MenuChoice>();

    public GameLobbyMenu(GameLobby p){
        mainMenu.add(new MenuChoice("Starta nytt lokalt spel", '1', p::startLocalGame));
        mainMenu.add(new MenuChoice("Starta nytt nätverksspel", '2', this::showNetworkGameMenu));
        mainMenu.add(new MenuChoice("Avsluta", '0', null));

        networkGameMenu.add(new MenuChoice("Starta nytt", '1', p::startNetworkGame));
        networkGameMenu.add(new MenuChoice("Anslut till spel", '2', p::connectToNetworkGame));
        networkGameMenu.add(new MenuChoice("Tillbaka", '0', this::backToMain));
    } // MainMenu

    private void showNetworkGameMenu(Object o) {
        setMenu(networkGameMenu);
    } // showNetworkGameMenu

    @Override
    public ArrayList<MenuChoice> setInitialMenu() {
        return mainMenu;
    } // setInitialMenu


    private void backToMain(Object o) {
        setMenu(mainMenu);
    } // backToMain
} // class MainMenu
