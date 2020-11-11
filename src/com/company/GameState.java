package com.company;

import java.util.ArrayList;

public class GameState {
    ArrayList<Card> playedCards = new ArrayList<>();
    int pointsToWin;
    enum player {HOST, CLIENT};
    player roundStarter = player.HOST;

    public GameState() {
    }

    public GameState(int pointsToWin) {
    }

    public void clearPlayedCards() {
    }

    public void addPlayedCard() {
    }

    public void changeRoundStarter() {
    }
}
