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
        this.pointsToWin = pointsToWin;
        this.players = players;
    }

    public boolean clearPlayedCards() {
        return playedCards.removeAll(playedCards.subList(0, 1));
    }

    public boolean addPlayedCard(Card playedCard) {
        return playedCards.add(playedCard);
    }

    public byte changeStartPlayer() {
        if(this.startPlayer == 1){
            this.startPlayer = 0;
        } else {
            this.startPlayer = 1;
        }
        return this.startPlayer;
    }

    public Player getPlayer(int index){
        return players.get(index);
    }
}
