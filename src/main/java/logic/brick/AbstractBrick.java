package logic.brick;

import controller.Game;

import java.util.Observable;
import java.util.Observer;
/**
 * Abstract Class that represents a brick object.
 *
 * @author Juan-Pablo Silva & Claudio Urbina
 */
public abstract class AbstractBrick extends Observable implements Brick{
    private int lifePoints;
    private int score;

    /**
     * Constructor of a Brick
     * @param lp Life Points
     * @param score Score
     */
    AbstractBrick(int lp, int score){
        this.lifePoints = lp;
        this.score = score;
    }

    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    @Override
    public void hit(){
        if(!this.isDestroyed()){
            lifePoints -= 1;
            if(lifePoints == 0){
                setChanged();
                notifyObservers(this);
            }
        }
    }

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    @Override
    public boolean isDestroyed(){
        return this.lifePoints == 0;
    }

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    @Override
    public int getScore(){
        return score;
    }

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    @Override
    public int remainingHits(){
        return lifePoints;
    }

    /**
     * Allows a Level or a Game to Observe it.
     * @param o The observer.
     */
    @Override
    public void subscribe(Observer o){
        addObserver(o);
    }

    @Override
    public boolean isSuperBrick(){return false;}

    @Override
    public boolean isGlassBrick(){return false;}

    @Override
    public boolean isWoodenBrick(){return false;}

    @Override
    public boolean isMetalBrick(){return false;}

    @Override
    public void acceptGame(Game game) {
    }
}
