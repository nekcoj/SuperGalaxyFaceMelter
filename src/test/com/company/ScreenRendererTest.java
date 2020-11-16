package com.company;

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
}