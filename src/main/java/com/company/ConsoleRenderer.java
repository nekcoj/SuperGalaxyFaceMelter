package com.company;

import com.company.interfaces.Renderer;
import com.company.utils.Menu;
import com.company.utils.MenuChoiceBaseClass;
import com.company.utils.MenuChoiceFunction;
import com.company.utils.TextUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleRenderer implements Renderer {

  private static final String topLeft = "╔";
  private static final String lineHor = "═";
  private static final String lineVert = "║";
  private static final String topRight = "╗";
  private static final String bottomLeft = "╚";
  private static final String bottomRight = "╝";

  private static final int OFFSET = 3;
  public final static int CARD_WIDTH = 18;
  private final static int CARD_HEIGHT = 11;
  public static final int MAX_NAME_PART_COUNT = 4;

  @Override
  public void render(GameState gameState, int playerToDraw) {
    String output = "";
    output += generateScoreRow(gameState);
    output += generateCardsString(gameState.getPlayedCards());
    output += generateCardsString(gameState.getPlayer(playerToDraw).getCardOnHandAsList());
    if(gameState.isGameOver()){
      output += generateGameOverRow(gameState);
    } else {
      if (gameState.isRoundOver()) {
        output += generateRoundWinnerRow(gameState);
      } else {
        if (gameState.getCurrentPlayer() == playerToDraw) {
          output += String.format("%s%s",
                  TextUtil.pimpString(String.format("\n%s", gameState.getPlayer(playerToDraw).getName()), TextUtil.LEVEL_STRESSED),
                  ", please select a card: ");
        } else {
          output += "Waiting for player...";
        }
      }
    }
    System.out.println(output);
  }

  public String generateGameOverRow(GameState gameState) {
    return String.format("Game Over!\nWinner is %s with the score %d",
           TextUtil.pimpString(gameState.getPlayer(gameState.getGameWinner()).getName(), TextUtil.LEVEL_INFO),
            gameState.getPlayer(gameState.getGameWinner()).getScore());
  }

  public String generateRoundWinnerRow(GameState gameState) {
    if (gameState.getRoundWinner() == Game.TIE) {
      return "Round is a tie, both players lose their cards!";
    }
    return String.format("Round winner is %s with the current score %d",
            TextUtil.pimpString(gameState.getPlayer(gameState.getRoundWinner()).getName(), TextUtil.LEVEL_INFO),
            gameState.getPlayer(gameState.getRoundWinner()).getScore());
  }

  public String generateCardsString(ArrayList<Card> cards){
    String [][] cardOutput = new String[cards.size()][CARD_HEIGHT];
    for (int i = 0; i < cards.size(); i++){
      var ref = new Object() {
        int rowCounter = 0;
      };
      cardOutput[i][ref.rowCounter++] = generateRow(topLeft, lineHor, topRight);
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
      cardOutput[i][ref.rowCounter++] = generateContentRow(generatePowerRow(cards.get(i)), 2);
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
      String[] cardName = cards.get(i).getName().split(" ");
      int finalI = i;
      Arrays.stream(cardName).forEach(name -> cardOutput[finalI][ref.rowCounter++] = generateContentRow(generateNameRow(name), 1));
      if(cardName.length < 4){
        for (int j = cardName.length; j < MAX_NAME_PART_COUNT; j++ ){
          cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
        }
      }
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
      cardOutput[i][ref.rowCounter++] = generateRow(bottomLeft, lineHor, bottomRight);
    }

    StringBuilder str = new StringBuilder();
    for (int i = 0; i < CARD_HEIGHT; i++) {
      for (int j = 0; j < cards.size(); j++) {
        str.append(cardOutput[j][i]).append(" ".repeat(OFFSET));
      }
      str.append("\n");
    }
    return str.toString();
  }

  public String generateContentRow(String input, int changeColorCount){
    return String.format("%s%s%s", lineVert,
            TextUtil.centerText(input, CARD_WIDTH + changeColorCount * TextUtil.RESET_COLOR_TOKEN_COUNT - 2),
            lineVert);
  }

  public String generatePowerRow(Card card){
    return String.format("[%s/%s]",
            TextUtil.pimpString(card.getCurrentPower(), TextUtil.LEVEL_BOLD),
            TextUtil.pimpString(card.getPower(), TextUtil.LEVEL_STRESSED));
  }

  public String generateRow(String firstLetter, String fillLetter, String lastLetter){
    return String.format("%s%s%s", firstLetter, fillLetter.repeat(CARD_WIDTH - 2), lastLetter);
  }

  public String generateNameRow(String input){
    return String.format("%s", TextUtil.pimpString(input, TextUtil.LEVEL_INFO));
  }

  public String generateScoreRow(GameState gameState){
    return String.format("<Player 1> %s, %d points\n<Player 2> %s, %d points\n",
        gameState.getPlayer(Game.HOST).getName(),
        gameState.getPlayer(Game.HOST).getScore(),
        gameState.getPlayer(Game.CLIENT).getName(),
        gameState.getPlayer(Game.CLIENT).getScore());
  }

  public int getCard(GameState gameState, int playerToGetCardFrom) {
    Menu cardMenu = getCardMenu(gameState, playerToGetCardFrom);
    return (Integer) cardMenu.handleFunctionMenu(false);
  }

  public Menu getCardMenu(GameState gameState, int playerToGetCardFrom){
    ArrayList<MenuChoiceBaseClass> cardMenuList = new ArrayList<>();
    Menu cardMenu = new Menu() {
      @Override
      public ArrayList<MenuChoiceBaseClass> setInitialMenu() {
        final char[] key = {'1'};
        gameState.getPlayer(playerToGetCardFrom).getCardOnHandAsList().forEach((card )-> {
          cardMenuList.add(new MenuChoiceFunction(card.toString(), key[0], this::handleCardMenu, Character.getNumericValue(key[0])));
          key[0]++;
        });
        return cardMenuList;
      }

      private Object handleCardMenu(Object o) {
        return o;
      }
    };
    return cardMenu;
  }

  @Override
  public String getPlayerName() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\nEnter name: ");
    return scanner.nextLine();
  }
}
