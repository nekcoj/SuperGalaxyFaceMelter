package com.company;

import com.company.interfaces.Renderer;
import com.company.utils.Menu;
import com.company.utils.MenuChoiceBaseClass;
import com.company.utils.MenuChoiceFunction;

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
  //private int pointsToWin;

  public GameHost(GameLobby gameLobby, Renderer renderer, GameState gameState, int handSize/*, int pointsToWin*/) {
    super(gameLobby, renderer, gameState);
    this.handSize = handSize;
    //this.pointsToWin = pointsToWin;
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
    System.out.println("P1: " + gameState.getPlayer(0).getScore());
    System.out.println("P2: " + gameState.getPlayer(1).getScore());
    System.out.println("kort kvar: " + deck.getCards().size());
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

  public int getRoundWinner(Card card1, Card card2) {
    int result = card1.getCurrentPower() - card2.getCurrentPower();
    int winner;

    if (result > 0) { winner = gameState.getStartPlayer() == HOST ? 0 : 1; }
    else if (result < 0) { winner = gameState.getStartPlayer() == HOST ? 1 : 0; }
    else  { winner = -1; }

    finalizingRound(winner, card1, card2);
    return winner;
    // Jämför korten i gameState och avgör vem, om någon, som vann-
    // Uppdatera gameState och vinsthögen för vinnande spelare
    // -1 = ingen vann annars 0 eller 1 för respektive spelare
  }

  public void finalizingRound(int winner, Card card1, Card card2) {
    if (winner >= 0) {
      if (winner == gameState.getStartPlayer()) {
        gameState.getPlayer(winner).addToVictoryPile(card2);
        card1.decreasePower(card2.getCurrentPower());
        gameState.getPlayer(winner).addCardToHand(card1);
      } else {
        gameState.getPlayer(winner).addToVictoryPile(card1);
        card2.decreasePower(card1.getCurrentPower());
        gameState.getPlayer(winner).addCardToHand(card2);
      }

      if (winner == HOST) {
        gameState.getPlayer(CLIENT).addCardToHand(deck.getTopCard());
      } else {
        gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
      }
    } else {
      gameState.getPlayer(HOST).addCardToHand(deck.getTopCard());
      gameState.getPlayer(CLIENT).addCardToHand(deck.getTopCard());
    }
  }

  public boolean isGameOver() {
    return gameState.getPlayer(HOST).getScore() >= gameState.getPointsToWin() || gameState.getPlayer(CLIENT).getScore() >= gameState.getPointsToWin() || deck.isEmpty();
  }

  public Card getCardFromPlayer1() {
    //be den lokala spelare om ett kort
    Menu cardMenu = getCardMenu();
    int chosenCard = (Integer) cardMenu.handleFunctionMenu(true);
    return gameState.getPlayer(HOST).getCard(chosenCard);
  }

  public Card getCardFromPlayer2() {
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
    // TODO: 2020-11-13 GameLobby code not complete for this method to work.
     return gameLobby.sendCardToClient((ArrayList<Card>) deck.getHand(handSize), gameState);
  }

  public Object handleCardMenu(Object obj){
    return obj;
  }

  public Menu getCardMenu(){
    ArrayList<MenuChoiceBaseClass> cardMenuList = new ArrayList<>();
    GameHost host = this;
    Menu cardMenu = new Menu() {
      @Override
      public ArrayList<MenuChoiceBaseClass> setInitialMenu() {
        final char[] key = {'1'};
        gameState.getPlayer(HOST).getCardOnHandAsList().forEach((card )-> {
            cardMenuList.add(new MenuChoiceFunction(card.toString(), key[0], host::handleCardMenu, Character.getNumericValue(key[0])));
            key[0]++;
          });
        return cardMenuList;
      }
    };
    return cardMenu;
  }
}
