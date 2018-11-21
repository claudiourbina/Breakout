package main.java.controller;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game {
    private int balls;

    public Game(int balls) {
        this.balls = balls;
    }

    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return false;
    }

    public int getBalls() {
        return this.balls;
    }
}
