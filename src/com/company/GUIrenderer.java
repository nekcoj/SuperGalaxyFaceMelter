package com.company;

import com.company.interfaces.Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIrenderer implements Renderer {

  JFrame jf;
  JPanel jp;
  JLabel jl1;
  GameState gs;

  @Override
  public void render(GameState gameState) {
    gs = gameState;
    jf = new JFrame();
    jp = new JPanel();
    jl1 = new JLabel("Points to Win: " + gameState.getPointsToWin());
    jp.setBorder(BorderFactory.createEmptyBorder(50, 50, 250, 250));
    jp.setLayout(new GridLayout(0,1));
    jp.add(jl1);
    ArrayList<JButton> buttonList = new ArrayList<>();
    gameState.getPlayer(gameState.getStartPlayer()).getCardOnHandAsList().forEach(card -> {
      JButton button = new JButton(card.toString());
      buttonList.add(button);
      jp.add(button);
    });
    for(int index = 0; index < buttonList.size(); index++) {
      int finalIndex = index;
      buttonList.get(index).addActionListener(e -> {
        gameState.getPlayer(gameState.getStartPlayer()).getCard(finalIndex);
        buttonList.get(finalIndex).setVisible(false);
      });

    }


    buttonList.get(0).setForeground(Color.RED);

    jf.add(jp, BorderLayout.CENTER);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setTitle("Super Galaxy Face Melter");
    jf.pack();
    jf.setVisible(true);

  }
}
