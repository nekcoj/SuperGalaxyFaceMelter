package com.company;

import java.util.ArrayList;

public class Player {

  private String name;
  private ArrayList<Card> victoryPile = new ArrayList<>();
  private ArrayList<Card> cardsOnHand = new ArrayList<>();

  public Player(){}


  public Player(String name){
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
    return selectedCard != 0 && selectedCard <= cardsOnHand.size() ? cardsOnHand.remove(selectedCard-1) : null;
  }

  public boolean addToVictoryPile(Card wonCard){
    return victoryPile.add(wonCard);
  }

  public ArrayList<Card> getCardOnHandAsList(){
    return cardsOnHand;
  }

  public boolean addCardsToHand(ArrayList<Card> startHand) {
    return this.cardsOnHand.addAll(startHand);
  }

}
