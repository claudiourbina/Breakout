package logic.level;

import controller.Game;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import java.util.*;

/**
 * Abstract Class that represents a level object.
 *
 * @author Juan-Pablo Silva & Claudio Urbina
 */
public abstract class AbstractLevel extends Observable implements Level, Observer {
    private List<Brick> bricksList = new ArrayList<>();
    private String levelName;
    private Level nextLevel;
    private int scoreLevel;
    private int currentScore;

    /**
     * Constructor of a Level.
     * @param name Name of the level
     * @param numberOfBricks Number of bricks of the level.
     * @param probOfGlass Probability to get a Glass Brick.
     * @param probOfMetal Probability to get a Metal Brick.
     * @param seed Seed to set de Random number.
     */
    AbstractLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        this.levelName = name;
        this.scoreLevel = 0;
        this.currentScore = 0;

        if(numberOfBricks > 0){
            Random rand = new Random(seed);
            for(int i = 0; i < numberOfBricks; i++){
                double randomNumber = rand.nextDouble();
                if(randomNumber <= probOfGlass){
                    GlassBrick gBrick = new GlassBrick();
                    gBrick.subscribe(this);
                    this.bricksList.add(gBrick);
                    scoreLevel += 50;
                }else{
                    WoodenBrick wBrick = new WoodenBrick();
                    wBrick.subscribe(this);
                    this.bricksList.add(wBrick);
                    scoreLevel += 200;
                }
            }

            for(int i = 0; i < numberOfBricks; i++){
                double randomNumber = rand.nextDouble();
                if(randomNumber <= probOfMetal){
                    MetalBrick mBrick = new MetalBrick();
                    mBrick.subscribe(this);
                    this.bricksList.add(mBrick);
                }
            }
        }
    }

    /**
     * Update the level after a notify of ones of their observables objects (brick)
     * @param o the Observable
     * @param arg an Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Brick) {
            ((Brick) arg).acceptLevel(this);
        }
    }

    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the table's name
     */
    @Override
    public String getName() {
        return levelName;
    }

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    @Override
    public int getNumberOfBricks() {
        return bricksList.size();
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    @Override
    public List<Brick> getBricks() {
        return bricksList;
    }

    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    @Override
    public Level getNextLevel() {
        return nextLevel;
    }

    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    @Override
    public boolean hasNextLevel() {
        return nextLevel.isPlayableLevel();
    }

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    @Override
    public int getPoints() {
        return scoreLevel;
    }

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    @Override
    public Level addPlayingLevel(Level level) {
        if(this.hasNextLevel()){
            nextLevel.addPlayingLevel(level);
        }else{
            setNextLevel(level);
        }
        return this;
    }

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    @Override
    public void setNextLevel(Level level) {
        this.nextLevel = level;
    }

    /**
     * Gets the number of brick alive in the level.
     * @return the number of bricks alive.
     */
    @Override
    public int bricksAlive() {
        int counter = 0;
        for(Brick brick: bricksList){
            if(!brick.isDestroyed()){
                counter+=1;
            }
        }
        return counter;
    }

    /**
     * Add the wooden brick score to the current score.
     */
    @Override
    public void addWoodenScore(){
        currentScore += 200;
        if(currentScore == scoreLevel){
            setChanged();
            notifyObservers(this);
        }
    }

    /**
     * Add the glass brick score to the current score.
     */
    @Override
    public void addGlassScore(){
        currentScore += 50;
        if(currentScore == scoreLevel){
            setChanged();
            notifyObservers(this);
        }
    }

    /**
     * Add the metal brick score to the current score.
     */
    @Override
    public void addMetalScore(){
        currentScore += 0;
    }

    /**
     * Allows a Game to Observe it.
     * @param o The observer.
     */
    @Override
<<<<<<< HEAD
<<<<<<< HEAD
    public void addSuperScore(){ setChanged(); this.notifyObservers(); }


    /**
     * Allows a Game to Observe it.
     * @param o The observer.
     */
    @Override
=======
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
=======
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
    public void subscribe(Observer o) {
        addObserver(o);
    }

    /**
     * Accept a Game game and it response to it activating an specific method.
     * @param game Accepted Game.
     */
    @Override
    public void acceptGame(Game game){
        game.goNextLevel();
    }

    /**
     * Gets the current score of the player in the level.
     * @return the current score.
     */
    @Override
    public int getCurrentScore(){return currentScore;}
}
