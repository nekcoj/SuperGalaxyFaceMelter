package com.company;

import com.company.interfaces.Renderer;
import com.company.utils.TextUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class ScreenRenderer implements Renderer {

  private String topLeft = "╔";
  private String lineHor = "═";
  private String lineVert = "║";
  private String topRight = "╗";
  private String bottomLeft = "╚";
  private String bottomRight = "╝";
  public final static int CARD_WIDTH = 18;
  private final static int CARD_HEIGHT = 12;
  private static final int OFFSET = 3;

  @Override
  public void render(GameState gameState, int playerToDraw) {
    String scoreRow = generateScoreRow(gameState);
    String playedCardsRow = generateCardsString(gameState.getPlayedCards());
    String playerHandRow = generateCardsString(gameState.getPlayer(playerToDraw).getCardOnHandAsList());
    //if(gameState)
  }

  public String generateCardsString(ArrayList<Card> cards){
    String [][] cardOutput = new String[cards.size()][CARD_HEIGHT];
    for (int i = 0; i < cards.size(); i++){
      var ref = new Object() {
        int rowCounter = 0;
      };
      cardOutput[i][ref.rowCounter++] = generateRow(topLeft, lineHor, topRight);
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
      cardOutput[i][ref.rowCounter++] = generateContentRow(generatePowerRow(cards.get(i)), 2);
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
      String[] cardName = cards.get(i).getName().split(" ");
      int finalI = i;
      Arrays.stream(cardName).forEach(name -> cardOutput[finalI][ref.rowCounter++] = generateContentRow(generateNameRow(name), 1));
      if(cardName.length < 4){
        for (int j = cardName.length; j < 4; j++ ){
          cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
        }
      }
      cardOutput[i][ref.rowCounter++] = generateRow(lineVert, " ", lineVert);
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
    return String.format("%s%s%s", lineVert, TextUtil.centerText(input, CARD_WIDTH + changeColorCount * 9 - 2), lineVert);
  }

  public String generatePowerRow(Card card){
    return String.format("[%s/%s]", TextUtil.pimpString(card.getCurrentPower(), TextUtil.LEVEL_BOLD), TextUtil.pimpString(card.getPower(), TextUtil.LEVEL_STRESSED));
  }

  public String generateRow(String firstLetter, String fillLetter, String lastLetter){
    return String.format("%s%s%s", firstLetter, fillLetter.repeat(CARD_WIDTH - 2), lastLetter);
  }

  public String generateNameRow(String input){
    return String.format("%s", TextUtil.pimpString(input, TextUtil.LEVEL_INFO));
  }

  public String generateScoreRow(GameState gameState){
    return String.format("<Player 1> %s, %d points, <Player 2> %s, %d points",
        gameState.getPlayer(Game.HOST).getName(),
        gameState.getPlayer(Game.HOST).getScore(),
        gameState.getPlayer(Game.CLIENT).getName(),
        gameState.getPlayer(Game.CLIENT).getScore());
  }
}
