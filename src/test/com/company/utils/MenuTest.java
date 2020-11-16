package com.company.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    Menu menu;
    ArrayList<MenuChoiceBaseClass> cardMenuList;

    @BeforeEach
    void setUp() {
        cardMenuList = new ArrayList<>();
        cardMenuList.add(new MenuChoiceFunction("Val 1", '1', this::handleCardMenu, 1));
        cardMenuList.add(new MenuChoiceFunction("Val 2", '2', this::handleCardMenu, 2));
        cardMenuList.add(new MenuChoiceFunction("Val 3", '3', this::handleCardMenu, 3));
        menu = new Menu() {
            @Override
            public ArrayList<MenuChoiceBaseClass> setInitialMenu() {
                return cardMenuList;
            }
        };
    }

    private Object handleCardMenu(Object o) {
        return o;
    }

    @Test
    void handleFunctionMenu() {
        assertEquals(1, menu.handleFunctionMenu(true), "Wrong menu choice");
    }
}