package com.company.utils;

import com.company.GameLobby;

import java.util.ArrayList;

public class GameLobbyMenu extends Menu {

    private ArrayList<MenuChoiceBaseClass> mainMenu = new ArrayList<MenuChoiceBaseClass>();
    private ArrayList<MenuChoiceBaseClass> networkGameMenu = new ArrayList<MenuChoiceBaseClass>();

    public GameLobbyMenu(GameLobby p){
        mainMenu.add(new MenuChoiceConsumer("Starta nytt lokalt spel", '1', p::startLocalGame));
        mainMenu.add(new MenuChoiceConsumer("Starta nytt nätverksspel", '2', this::showNetworkGameMenu));
        mainMenu.add(new MenuChoiceConsumer("Avsluta", '0', null));

        networkGameMenu.add(new MenuChoiceConsumer("Starta nytt", '1', p::startNetworkGame));
        networkGameMenu.add(new MenuChoiceConsumer("Anslut till spel", '2', p::connectToNetworkGame));
        networkGameMenu.add(new MenuChoiceConsumer("Tillbaka", '0', this::backToMain));
    } // MainMenu

    private void showNetworkGameMenu(Object o) {
        setMenu(networkGameMenu);
    } // showNetworkGameMenu

    @Override
    public ArrayList<MenuChoiceBaseClass> setInitialMenu() {
        return mainMenu;
    } // setInitialMenu


    private void backToMain(Object o) {
        setMenu(mainMenu);
    } // backToMain
} // class MainMenu
