package com.company;

import java.util.ArrayList;

public class Player {

  private String name;
  private ArrayList<Card> victoryPile = new ArrayList<>();
  private ArrayList<Card> cardsOnHand = new ArrayList<>();

  public Player(){}


  public Player(ArrayList<Card> startHand, String name){
    
  }

  public String getName() {
    return name;
  }

  public int getScore(){
    return 0;
  }

  public Card getCard(int selectedCard){
    return null;
  }

  public void addToVictoryPile(Card wonCard){

  }

  public ArrayList<Card> getCardList(){
    return null;
  }

}
