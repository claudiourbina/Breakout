package main.java.logic.brick;
import java.util.Observable;

public abstract class AbstractBrick extends Observable implements Brick, Runnable{
    private int lifePoints;
    private int score;


    public AbstractBrick(int lp, int score){
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

    @Override
    public void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
