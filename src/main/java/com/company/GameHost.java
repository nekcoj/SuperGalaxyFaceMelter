package com.company;

import com.company.interfaces.Renderer;

import java.util.ArrayList;
import java.util.Arrays;
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

  public GameHost(GameLobby gameLobby, Renderer renderer, GameState gameState, int handSize) {
    super(gameLobby, renderer, gameState);
    this.handSize = handSize;
  }

  /**
   *   Sätt startspelare till spelare ett (Hostens spelare)-
   *   Dela ut kort; skicka till klienten och den egna spelaren-
   *   Do... while game not over??
   *      Be startspelaren om ett kort-
   *      Be motspelaren om ett kort-
   *      Avgör vinnaren av rundan-
   *      Kolla om spelet är slut
   *      Byt startspelare -> gameState.changeStartPlayer()
   */
  public void runGame() {
    dealCards();
    do {
      Card card1 = getCardFromStartPlayer();
      gameState.addPlayedCard(card1);
      Card card2 = getCardFromSecondPlayer();
      gameState.addPlayedCard(card2);
      getRoundWinner(card1, card2);
      gameState.clearPlayedCards();
      gameState.changeStartPlayer();
    } while (!isGameOver());
  }

  /**
   *
   *         // Är spelare1 först? Begär från den egna spelare direkt
   *         // Annars, begär kort från klienten via gameLobby
   *         //Player player =
   *
   */
  public Card getCardFromStartPlayer() {
    return gameState.getStartPlayer() == HOST ? getCardFromPlayer1() : getCardFromPlayer2();
  }

  /**
   *  Är spelare1 andraspelaren? Begär från den egna spelare direkt
   *  Annars, begär kort från klienten via gameLobby
   */
  public Card getCardFromSecondPlayer() {
    return gameState.getStartPlayer() == CLIENT ? getCardFromPlayer1() : getCardFromPlayer2();
  }

   /**
    *     Jämför korten i gameState och avgör vem, om någon, som vann-
    *     Uppdatera gameState och vinsthögen för vinnande spelare
    *     @return -1 = ingen vann annars 0 eller 1 för respektive spelare
   * */
  public int getRoundWinner(Card card1, Card card2) {
    int result = card1.getCurrentPower() - card2.getCurrentPower();
    int winner;

    if (result > 0) { winner = gameState.getStartPlayer() == HOST ? 0 : 1; }
    else if (result < 0) { winner = gameState.getStartPlayer() == HOST ? 1 : 0; }
    else  { winner = -1; }

    finalizingRound(winner, card1, card2);
    return winner;
  }

  public void finalizingRound(int winner, Card card1, Card card2) {

    if (winner >= 0) {
      if (winner == gameState.getStartPlayer()) {
        handleWinnerCardForStartPlayer(winner, card1, card2);
      } else {
        handleWinnerCardForSecondPlayer(winner, card2, card1);
      }

      if (winner == HOST) {
        gameLobby.sendCardToClient(new ArrayList<>(Arrays.asList(deck.getTopCard())), gameState);
      } else {
        gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
      }
    } else {
      gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
      gameLobby.sendCardToClient(new ArrayList<>(Arrays.asList(deck.getTopCard())), gameState);
    }
  }

  public void handleWinnerCardForStartPlayer(int winner, Card card1, Card card2){
    if (winner == HOST) {
      handleWinnerCardForPlayer1(card1, card2);
    } else {
      handleWinnerCardForPlayer2(card1, card2);
    }
  }

  public void handleWinnerCardForSecondPlayer(int winner, Card card1, Card card2){
      if (winner == CLIENT) {
      handleWinnerCardForPlayer2(card1, card2);
    } else {
      handleWinnerCardForPlayer1(card1, card2);
    }
  }

  public void handleWinnerCardForPlayer1(Card card1, Card card2){
    gameState.getPlayer(HOST).addToVictoryPile(card2);
    card1.decreasePower(card2.getCurrentPower());
    gameState.getPlayer(HOST).addCardToHand(card1);
  }

  public void handleWinnerCardForPlayer2(Card card1, Card card2){
    gameLobby.addToClientVictoryPile(card2, gameState);
    card1.decreasePower(card2.getCurrentPower());
    gameLobby.sendCardToClient(new ArrayList<>(Arrays.asList(card1)), gameState);
  }

  public boolean isGameOver() {
    return gameState.getPlayer(HOST).getScore() >= gameState.getPointsToWin() || gameState.getPlayer(CLIENT).getScore() >= gameState.getPointsToWin() || deck.isEmpty();
  }

  public Card getCardFromPlayer1() {
    //be den lokala spelaren om ett kort
    gameState.setCurrentPlayer(Game.HOST);
    gameBoard.render(gameState, Game.HOST);
    return gameBoard.getCard(gameState, Game.HOST);
  }

  public Card getCardFromPlayer2() {
    gameState.setCurrentPlayer(Game.CLIENT);
    return gameLobby.requestCardFromClient(gameState);
  }

  public void dealCards(){
    dealCardsToHost();
   gameState = dealCardsToClient();
  }

  public boolean dealCardsToHost(){
    return gameState.getPlayer(HOST).addCardsToHand((ArrayList<Card>) deck.getHand(handSize));
  }

  public GameState dealCardsToClient(){
     return gameLobby.sendCardToClient((ArrayList<Card>) deck.getHand(handSize), gameState);
  }
}
