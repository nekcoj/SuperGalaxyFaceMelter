package com.company;

import java.util.ArrayList;

public class Player {

  private String name;
  private ArrayList<Card> victoryPile = new ArrayList<>();
  private ArrayList<Card> cardsOnHand = new ArrayList<>();

  public Player(){}


  public Player(ArrayList<Card> startHand, String name){
    this.cardsOnHand = startHand;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getScore(){
    int score = 0;
    for(Card card : victoryPile){
      score += card.getCurrentPower();
    }
    return score;
  }

  public Card getCard(int selectedCard){
    return selectedCard != 0 && selectedCard <= cardsOnHand.size()+1 ? cardsOnHand.get(selectedCard-1) : null;
  }

  public void addToVictoryPile(Card wonCard){
    victoryPile.add(wonCard);
  }

  public ArrayList<Card> getCardList(){
    return cardsOnHand;
  }

}
