package com.company.utils;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Menu {
    protected ArrayList<MenuChoice> currentMenu;

    abstract public ArrayList<MenuChoice> setInitialMenu();

    protected void setMenu(Object o) {
        currentMenu = (ArrayList<MenuChoice>) o;
    } // setMenu

    private void printMenu() {
        System.out.println("");
        for (MenuChoice m : currentMenu) {
            System.out.printf("%s%n", m.getFullTitle());
        } // for m...
        System.out.print("Ange ditt val: ");
    } // printMenu

    // Hämta användarens val
    private MenuChoice getMenuChoice() {
        String sChoice;
        Scanner scan = new Scanner(System.in);

        // Se till så att det finns ett menyval och inte en tomrad (blir så efter nextDouble)
        do
            sChoice = scan.nextLine();
        while (sChoice.length() == 0);

        // Loopa igenom och returnera rätt menyval
        for (MenuChoice m : currentMenu) {
            if (m.getKey() == sChoice.charAt(0))
                return m;
        } // for...
        return null;
    } // getMenuChoice

    public void handleMenu() {
        currentMenu = setInitialMenu();

        MenuChoice m;
        boolean bStop = false;
        while (!bStop) {
            printMenu();

            m = getMenuChoice();
            if (m == null)
                System.out.println("Felaktigt val, försök igen!");
            else {
                System.out.printf("Du valde: %s%n", m.getTitle());
                bStop = m.getFunctionToCall() == null;
                if (!bStop) {
                    m.getFunctionToCall().accept(m.getParameter());
                } // if bStop...
            } // else
        } // while
    } // handleMenu
} // class Menu
