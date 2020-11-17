package com.company;

import java.util.ArrayList;

public class GameState {
  private ArrayList<Card> playedCards = new ArrayList<>();
  private int pointsToWin;
  private ArrayList<Player> players = new ArrayList<>();
  private int startPlayer = 0;
  private int currentPlayer = 0;

  // -1: game still going,
  //  0: host (player 1) won,
  //  1: client (player 2) won,
  //  2: game over, a tie
  private int winner = -1;

  public GameState() {
    }

  public GameState(int pointsToWin, ArrayList<Player> players) {
    this.pointsToWin = pointsToWin;
    this.players = players;
  }

  public int getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(int currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public boolean clearPlayedCards() {
    return playedCards.removeAll(playedCards);
  }

  public boolean addPlayedCard(Card playedCard) {
    return playedCards.add(playedCard);
  }

  public ArrayList<Card> getPlayedCards() {
    return playedCards;
  }

  public int changeStartPlayer() {
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

  public int getStartPlayer() {
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
    return getWinner() > -1;
  }
}