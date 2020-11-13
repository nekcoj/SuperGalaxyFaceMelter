package com.company;

import com.company.interfaces.Renderer;
import com.company.utils.Menu;

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

  public GameHost(GameLobby gameLobby, Renderer renderer, GameState gameState, int handSize, int pointsToWin) {
    super(gameLobby, renderer, gameState);
    this.handSize = handSize;
    this.pointsToWin = pointsToWin;
  }

  /**
   *   Sätt startspelare till spelare ett (Hostens spelare)
   *   Dela ut kort; skicka till klienten och den egna spelaren
   *   Do... while game not over??
   *      Be startspelaren om ett kort
   *      Be motspelaren om ett kort
   *      Avgör vinnaren av rundan
   *      Kolla om spelet är slut
   *      Byt startspelare -> gameState.changeStartPlayer()
   */
  public void runGame() {
  }

  /**
   *
   *         // Är spelare1 först? Begär från den egna spelare direkt
   *         // Annars, begär kort från klienten via gameLobby
   *         //Player player =
   *
   */
  public Card getCardFromStartPlayer() {
    return null;
  }

  public Card getCardFromSecondPlayer() {
    // Är spelare1 andraspelaren? Begär från den egna spelare direkt
    // Annars, begär kort från klienten via gameLobby
    return null;
  }

  public int getRoundWinner() {
    // Jämför korten i gameState och avgör vem, om någon, som vann
    // Uppdatera gameState och vinsthögen för vinnande spelare
    // -1 = ingen vann annars 0 eller 1 för respektive spelare
    return -1;
  }

  public boolean isGameOver() {
    // Kolla om någon spelare uppnått poänggränsen
    // eller om kortleken tagit slut
    return false;
  }

  public Card getCardFromPlayer1() {
    //be den lokala spelare om ett kort
    return null;
  }

  public Card getCardFromPlayer2() {
    //be klient-spelaren om ett kort via gameLobbyn
    return null;
  }

  public void dealCards(){
    dealCardsToHost();
    dealCardsToClient();
  }

  public boolean dealCardsToHost(){
    return gameState.getPlayer(HOST).addCardsToHand((ArrayList<Card>) deck.getHand(handSize));
  }

  public boolean dealCardsToClient(){
    // TODO: 2020-11-13 GameLobby code not complete for this method to work.
    return gameLobby.sendCardToClient((ArrayList<Card>) deck.getHand(handSize));
  }

  public void handleCardMenu(Object obj){
    System.out.println("handleCardMenu: " + obj);
  }

  public Menu getCardMenu(){
    return null;
  }
}
