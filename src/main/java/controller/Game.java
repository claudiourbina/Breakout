package main.java.controller;

import main.java.logic.brick.Brick;
import main.java.logic.level.RealLevel;
import main.java.logic.level.EmptyLevel;
import main.java.logic.level.Level;

import java.util.List;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game {
    private int balls;
    private Level currentLevel;
    private int currentPoints;

    public Game(int balls) {
        this.balls = balls;
        this.currentLevel = new EmptyLevel();
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

    public Level getCurrentLevel(){
        return currentLevel;
    }

    public void goNextLevel(){
        this.currentLevel = this.currentLevel.getNextLevel();
    }

    public void setCurrentLevel(Level level){
        this.currentLevel = level;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int dropBall() {
        if(this.balls > 0){
            this.balls -=1;
        }
        return this.balls;
    }

    public boolean isGameOver() {
        if(this.winner() || this.getBalls() == 0){
            return true;
        }else{
            return false;
        }
    }

    public int numberOfBricks() {
        return this.getCurrentLevel().bricksAlive();
    }
}
