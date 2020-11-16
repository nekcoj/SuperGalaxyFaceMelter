package com.company;

import com.company.utils.TextUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScreenRendererTest {

  ScreenRenderer sr = new ScreenRenderer();
  ArrayList<Card> cards = new ArrayList<>();

  @BeforeAll
  static void start(){
    System.out.println("======== STARTING ScreenRenderer Tests ========");
  }

  @BeforeEach
  void setup(){
    System.out.println("-------- @BeforeEach --------");
    cards.add(new Card(10, "Super Galaxy Face Melter"));
    cards.add(new Card(5, "Orange Menace"));
  }

  @Test
  void generatePrintString() {
    System.out.println("-------- generatePrintString Test --------");
    assertEquals(0, sr.generatePrintString(cards).indexOf("╔"), "Missing top left corner!");
  }

  @Test
  void generateRow(){
    System.out.println("-------- generateRow Test --------");
    String str = sr.generateRow("<", "!", ">");
    assertEquals(str.length(), ScreenRenderer.CARD_WIDTH, "Got incorrect row length!");
    assertTrue(str.charAt(0) == '<', "Wrong character at index 0!");
    assertTrue(str.charAt(1) == '!', "Wrong character at index 1!");
    assertTrue(str.charAt(str.length() - 1) == '>', "Wrong character at index .length() - 1!");
  }

  @Test
  void generatePowerRow(){
    System.out.println("-------- generatePowerRow --------");
    String str = String.format("[%s/%s]", TextUtil.pimpString(10, TextUtil.LEVEL_BOLD), TextUtil.pimpString(10, TextUtil.LEVEL_STRESSED));
    assertTrue(sr.generatePowerRow(cards.get(0)).contains(str), "Incorrect power row!");

  }

  @Test
  void generateContentRow(){
    System.out.println("-------- generateContentRow --------");
    String contentTest = "Content Test";
    assertTrue(sr.generateContentRow(contentTest, 0).contains(contentTest), "Got wrong value from generateContentRow!");
  }
}