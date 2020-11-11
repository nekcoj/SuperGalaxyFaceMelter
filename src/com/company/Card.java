package com.company;

public class Card {

    private int power;
    private String name;
    private int currentPower;

    public Card() {}

    public Card(int power, String name) {
        this.power = power;
        this.name = name;
        this.currentPower = power;
    }

    public void decreasePower(int decreaseBy) {
        setCurrentPower(currentPower - decreaseBy);
    }
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(int currentPower) {
        if (currentPower < 0) {
            this.currentPower = 0;
        } else {
            this.currentPower = currentPower;
        }
    }
    public boolean isDead() {
        return currentPower == 0;
    }
}
