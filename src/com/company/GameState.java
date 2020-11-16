package com.company;

import java.util.ArrayList;

public class GameState {
  private ArrayList<Card> playedCards = new ArrayList<>();
  private int pointsToWin;
  private ArrayList<Player> players = new ArrayList<>();
  private byte startPlayer = 0;
  private int winner = -1;


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

  public ArrayList<Card> getPlayedCards() {
    return playedCards;
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

  public byte getStartPlayer() {
    return startPlayer;
  }

  public int getPointsToWin() {
    return pointsToWin;
  }

  public int getWinner() {
    return winner;
  }

  public void setWinner(int winner) {
    this.winner = winner;
  }

  public boolean isGameOver(){
    return true;
  }
}