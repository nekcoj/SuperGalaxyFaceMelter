package com.company;

import com.company.gameobjects.CardSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardSettingsTest {

  private CardSettings cardSettings = new CardSettings(1, "Mutated worm", 1);

  @Test
  void createCard() {
    Assertions.assertNotNull(cardSettings.createCard(), "createCard returns null");
  }
}