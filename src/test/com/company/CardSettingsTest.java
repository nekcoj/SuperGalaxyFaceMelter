package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardSettingsTest {

  private CardSettings cardSettings = new CardSettings(1, "Mutated worm", 1);

  @Test
  void createCard() {
    Assertions.assertNotNull(cardSettings.createCard(), "createCard returns null");
  }
}