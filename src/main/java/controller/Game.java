package controller;

import logic.Logic;
import logic.brick.Brick;
import logic.level.EmptyLevel;
import logic.level.Level;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva & Claudio Urbina
 */
public class Game extends Observable implements Observer {
    private int balls;
    private Level currentLevel;
    private int currentPoints;
    private boolean winner;

    /**
     * Constructor of a Game
     * @param balls Amount of balls.
     */
    public Game(int balls) {
        this.balls = balls;
        this.currentLevel = new EmptyLevel();
        this.currentPoints = 0;
        this.winner = false;
    }

    /**
     * Gets the state of the player.
     *
     * @return true if the player won the game, false otherwise
     */
    public boolean winner() {
        return winner;
    }

    /**
     * Gets the current number of available balls to play.
     *
     * @return the number of available balls
     */
    public int getBalls() {
        return this.balls;
    }

    /**
     * Gets the current {@link Level}.
     *
     * @return the current level
     * @see Level
     */
    public Level getCurrentLevel(){
        return currentLevel;
    }

    /**
     * Pass to the next level of the current {@link Level}. Ignores all conditions and skip to the next level.
     */
    public void goNextLevel(){
        if(!currentLevel.hasNextLevel()){
            this.winner = true;
        }
        setCurrentLevel(currentLevel.getNextLevel());
        setChanged();
        notifyObservers(this);
    }

    /**
     * Sets a {@link Level} as the current playing level.
     *
     * @param level the level to be used as the current level
     * @see Level
     */
    public void setCurrentLevel(Level level){
        this.currentLevel = level;
        this.acceptByBrick(level);
        level.subscribe(this);
    }

    /**
     * Makes the bricks of a level to accept this game.
     * @param level
     */
    private void acceptByBrick(Level level) {
        List<Brick> bricks = level.getBricks();
        for(Brick brick: bricks){
            brick.subscribe(this);
        }
    }

    /**
     * Gets the accumulated points through all levels and current {@link Level}.
     *
     * @return the cumulative points
     */
    public int getCurrentPoints() {
        return currentPoints;
    }

    /**
     * Sets the accumulated points through all levels and current {@link Level}.
     *
     */
    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    /**
     * Reduces the count of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int dropBall() {
        if(this.balls > 0){
            this.balls -=1;
        }
        return this.balls;
    }

    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0 or the player won the game.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        if(this.winner() || this.getBalls() == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Gets the number of {@link Brick} in the current level, that are still not destroyed
     *
     * @return the number of intact bricks in the current level
     */
    public int numberOfBricks() {
        return this.getCurrentLevel().bricksAlive();
    }

    /**
     * Update the level after a notify of ones of their observables objects (brick or level)
     * @param o the Observable
     * @param arg an Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Logic){
            ((Logic) arg).acceptGame(this);
        }
    }

    /**
     * Add the glass brick score to the current score.
     */
    public void addGlassScore() {
        currentPoints += 50;
    }

    /**
     * Add the metal brick score to the current score.
     */
    public void addMetalScore() {
        balls += 1;
    }

    /**
     * Add the wooden brick score to the current score.
     */
    public void addWoodenScore() {
        currentPoints += 200;
    }

    /**
     * Add the super brick score to the current score.
     */
    public void addSuperScore() {
        this.goNextLevel();
    }

    public void suscribe(Observer o){
        addObserver(o);
    }
}
