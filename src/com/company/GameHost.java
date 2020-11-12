package com.company;

import com.company.interfaces.Renderer;

import java.util.ArrayList;
import java.util.List;

public class GameHost extends Game {

    private final List<CardSettings> cardSettings = new ArrayList<>() {{
        add(new CardSettings(1, "Mutated worm", 8));
        add(new CardSettings(2, "Irate rat", 8));
        add(new CardSettings(3, "Orange menace", 8));
        add(new CardSettings(4, "Sleepy Joe", 10));
        add(new CardSettings(5, "Angry teacher", 10));
        add(new CardSettings(6, "Screaming toddler", 8));
        add(new CardSettings(7, "Space nerd", 8));
        add(new CardSettings(8, "Anonymous hacker", 4));
        add(new CardSettings(9, "Radiated zombie", 4));
        add(new CardSettings(10, "Super Galaxy Face Melter", 2));
    }};

    private Deck deck = new Deck(cardSettings);
    private int handSize;
    private int pointsToWin;
    private GameState gameState;

    public GameHost(GameLobby gameLobby, Renderer renderer, String playerName, int handSize, int pointsToWin) {
        super(gameLobby, renderer, playerName);
        this.handSize = handSize;
        this.pointsToWin = pointsToWin;
        gameState = new GameState();
    }

    public void runGame() {
        // Sätt startspelare till spelare ett (Hostens spelare)
        // Dela ut kort; skicka till klienten och den egna spelaren
        // Do... while game not over??
            // Be startspelaren om ett kort
            // Be motspelaren om ett kort
            // Avgör vinnaren av rundan
            // Kolla om spelet är slut
            // Byt startspelare -> gameState.changeStartPlayer()
    }

    public Card getCardFromStartPlayer() {
        // Är spelare1 först? Begär från den egna spelare direkt
        // Annars, begär kort från klienten via gameLobby
        return null;
    }

    public Card getCardFromSecondPlayer() {
        // Är spelare1 andraspelaren? Begär från den egna spelare direkt
        // Annars, begär kort från klienten via gameLobby
        return null;
    }

    public int roundWinner() {
        // Jämför korten i gameState och avgör vem, om någon, som vann
        // Uppdatera gameState och vinsthögen för vinnande spelare
        return 0;
    }

    public boolean isGameOver() {
        // Kolla om någon spelare uppnått poänggränsen
        // eller om kortleken tagit slut
        return true;
    }
}
