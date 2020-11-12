package com.company;

import java.util.ArrayList;

public class GameState {
    ArrayList<Card> playedCards = new ArrayList<>();
    int pointsToWin;
    ArrayList<Player> players = new ArrayList<>();
    byte startPlayer = 0;


    public GameState() {
    }

    public GameState(int pointsToWin, ArrayList<Player> players) {
    }

    public boolean clearPlayedCards() {
        return false;
    }

    public boolean addPlayedCard(Card playedCard) {
        return false;
    }

    public byte changeStartPlayer() {
        return 0;
    }

    public Player getPlayer(int index){
        return null;
    }
}
