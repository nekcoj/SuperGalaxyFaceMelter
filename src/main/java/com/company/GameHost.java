package com.company;

import com.company.interfaces.Renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
  private final boolean isLocalGame;
  private final Deck deck = new Deck(cardSettings);
  private final int handSize;

  public GameHost(GameLobby gameLobby, Renderer renderer, GameState gameState, int handSize, boolean isLocalGame) {
    super(gameLobby, renderer, gameState);
    this.handSize = handSize;
    this.isLocalGame = isLocalGame;
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
    if (!isLocalGame) {
      getPlayerNameFromClient();
    }

    dealCards();
    do {
      gameState.setRoundWinner(-1);
      int index1 = getCardFromStartPlayer();
      Card c1 = gameState.getPlayer(gameState.getStartPlayer()).getCard(index1);
      gameState.addPlayedCard(c1);
      int index2 = getCardFromSecondPlayer();
      Card c2 = gameState.getPlayer(gameState.getCurrentPlayer()).getCard(index2);
      gameState.addPlayedCard(c2);
      gameState.setRoundWinner(getRoundWinner(c1, c2));
      redrawGameBoard();
      continueGame();
      gameState.clearPlayedCards();
      gameState.changeStartPlayer();
    } while (!isGameOver());

    redrawGameBoard();
  }

  private void getPlayerNameFromClient() {
    gameState.getPlayer(Game.CLIENT).setName(gameLobby.getPlayerNameFromClient());
  }

  private void redrawGameBoard() {
    gameBoard.render(gameState, Game.HOST);
    if (!isLocalGame) {
      System.out.println("är vi i redraw?");
      gameLobby.renderClient(gameState, Game.CLIENT);
    }
  }

  // TODO: 2020-11-20 Move keyboard input to rendererererer
  private void continueGame() {
    if (isLocalGame) {
      System.out.println("Press enter to continue game..");
      try {
        System.in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   *
   * Är spelare1 först? Begär från den egna spelare direkt
   * Annars, begär kort från klienten via gameLobby
   *
   * @return
   */
  public int getCardFromStartPlayer() {
    return gameState.getStartPlayer() == HOST ? getCardFromPlayer1() : getCardFromPlayer2();
  }

  /**
   *  Är spelare1 andraspelaren? Begär från den egna spelare direkt
   *  Annars, begär kort från klienten via gameLobby
   * @return
   */
  public int getCardFromSecondPlayer() {
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
    else  { winner = Game.TIE; }

    finalizingRound(winner, card1, card2);
    return winner;
  }

  public void finalizingRound(int winner, Card card1, Card card2) {
    gameState.getPlayer(gameState.getStartPlayer()).getCardOnHandAsList().remove(card1);
    gameState.getPlayer(gameState.getCurrentPlayer()).getCardOnHandAsList().remove(card2);
    if (winner >= 0) {
      if (winner == gameState.getStartPlayer()) {
        handleWinnerCardForStartPlayer(winner, card1, card2);
      } else {
        handleWinnerCardForSecondPlayer(winner, card2, card1);
      }

      //Deal new card to LOSER, if TIE both players receive a new card.
      switch (winner) {
        case HOST:
          gameState = gameLobby.sendCardToClient(new ArrayList<>(Collections.singletonList(deck.getTopCard())), gameState);
          break;

        case CLIENT:
          gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
          break;

        case TIE:
          gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
          gameState = gameLobby.sendCardToClient(new ArrayList<>(Collections.singletonList(deck.getTopCard())), gameState);
          break;

        default:
          System.out.println("Wrong winner state!");
          break;
      }
    }
      /*if (winner == HOST) {
        gameState = gameLobby.sendCardToClient(new ArrayList<>(Collections.singletonList(deck.getTopCard())), gameState);
      } else {
        gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
      }
    } else {
      gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
      gameState = gameLobby.sendCardToClient(new ArrayList<>(Collections.singletonList(deck.getTopCard())), gameState);
    }*/
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
    gameState = gameLobby.addToClientVictoryPile(card2, gameState);
    card1.decreasePower(card2.getCurrentPower());
    gameState = gameLobby.sendCardToClient(new ArrayList<>(Collections.singletonList(card1)), gameState);
  }

  public boolean isGameOver() {
    boolean playerOneScore = gameState.getPlayer(HOST).getScore() >= gameState.getPointsToWin();
    boolean playerTwoScore = gameState.getPlayer(CLIENT).getScore() >= gameState.getPointsToWin();
    if (playerOneScore || playerTwoScore || deck.isEmpty()) {
        gameState.setGameWinner(gameState.getPlayer(Game.HOST).getScore() == gameState.getPlayer(Game.CLIENT).getScore()
                ? Game.TIE : gameState.getPlayer(Game.HOST).getScore() > gameState.getPlayer(Game.CLIENT).getScore()
                ? Game.HOST : Game.CLIENT);
        return true;
    }
    return false;
  }

  public int getCardFromPlayer1() {
    //be den lokala spelaren om ett kort
    gameState.setCurrentPlayer(Game.HOST);
    gameBoard.render(gameState, Game.HOST);
    return gameBoard.getCard(gameState, Game.HOST);
  }

  public int getCardFromPlayer2() {
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
