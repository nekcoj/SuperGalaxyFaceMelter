package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card;

    @BeforeEach
    void setUpBefore() {
        card = new Card(10, "SuperGalaxyFaceMelter");
    }

    @Test
    void constructorTest() {
        Assertions.assertNotNull(card, "Constructor returned null");
    }

    @Test
    void decreasePower() {
        card.decreasePower(3);
        Assertions.assertEquals(7, card.getCurrentPower(), "Wrong current power!");
    }

    @Test
    void getPower() {
        Assertions.assertEquals(10, card.getPower(), "Wrong power");
    }

    @Test
    void setPower() {
        card.setPower(7);
        Assertions.assertEquals(7, card.getPower(), "Wrong power");
    }

    @Test
    void getName() {
        Assertions.assertEquals("SuperGalaxyFaceMelter", card.getName(), "Wrong name!");
    }

    @Test
    void setName() {
        card.setName("Orange menace");
        Assertions.assertEquals("Orange menace", card.getName(), "Wrong name!");
    }

    @Test
    void getCurrentPower() {
        Assertions.assertEquals(10, card.getCurrentPower(), "Wrong power!");
    }

    @Test
    void setCurrentPower() {
        card.setCurrentPower(card.getPower());
        Assertions.assertEquals(10, card.getCurrentPower(), "Wrong power!");
    }

    @Test
    void isDead() {
        assertFalse(card.isDead(), "isDead is wrong state!");
    }
}