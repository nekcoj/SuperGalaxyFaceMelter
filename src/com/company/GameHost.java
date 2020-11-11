package com.company;

import com.company.interfaces.Renderer;

import java.util.ArrayList;
import java.util.List;

public class GameHost extends Game {

    private final List<CardSettings> cardSettings = new ArrayList<>() {{
        add(new CardSettings(1, "Mutated worm", 8));
        add(new CardSettings(2, "Irate rat", 8));
        add(new CardSettings(3, "Orange menace", 8));
        add(new CardSettings(4, "Sleepy Joe", 10));
        add(new CardSettings(5, "Angry teacher", 10));
        add(new CardSettings(6, "Screaming toddler", 8));
        add(new CardSettings(7, "Space nerd", 8));
        add(new CardSettings(8, "Anonymous hacker", 4));
        add(new CardSettings(9, "Radiated zombie", 4));
        add(new CardSettings(10, "Super Galaxy Face Melter", 2));
    }};

    private Deck deck = new Deck(cardSettings);
    private int handSize;
    private int pointsToWin;


    public GameHost(GameLobby gameLobby, Renderer renderer, String playerName, int handSize, int pointsToWin) {
        super(gameLobby, renderer, playerName);
        this.handSize = handSize;
        this.pointsToWin = pointsToWin;
    }

    public boolean isGameOver() {
        return true;
    }
}
