package main.java.controller;

import main.java.logic.Logic;
import main.java.logic.brick.Brick;
import main.java.logic.level.RealLevel;
import main.java.logic.level.EmptyLevel;
import main.java.logic.level.Level;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    private int balls;
    private Level currentLevel;
    private int currentPoints;
    private boolean winner;

    public Game(int balls) {
        this.balls = balls;
        this.currentLevel = new EmptyLevel();
        this.currentPoints = 0;
        this.winner = false;
    }
    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return winner;
    }

    public int getBalls() {
        return this.balls;
    }

    public Level getCurrentLevel(){
        return currentLevel;
    }

    public void goNextLevel(){
        if(!currentLevel.hasNextLevel()){
            this.winner = true;
        }
        setCurrentLevel(currentLevel.getNextLevel());
    }

    public void setCurrentLevel(Level level){
        this.currentLevel = level;
        this.acceptByBrick(level);
        level.suscribe(this);
    }

    private void acceptByBrick(Level level) {
        List<Brick> bricks = level.getBricks();
        for(Brick brick: bricks){
            brick.suscribe(this);
        }
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Logic){
            ((Logic) arg).acceptGame(this);
        }
    }

    public void addGlassScore() {
        currentPoints += 50;
    }

    public void addMetalScore() {
        balls += 1;
    }

    public void addWoodenScore() {
        currentPoints += 200;
    }
}
