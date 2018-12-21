package logic.brick;

import logic.Logic;
import logic.level.Level;

import java.util.Observer;

import java.util.Observer;

/**
 * Interface that represents a brick object.
 * <p>
 * All bricks should implement this interface.
 *
 * @author Juan-Pablo Silva & Claudio Urbina
 */
public interface Brick extends Logic {
    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    void hit();

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    int getScore();

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    int remainingHits();

    /**
     * Accept a Level level to visit the Brick.
     * @param level
     */
    void acceptLevel(Level level);

    boolean isSuperBrick();

    boolean isGlassBrick();

    boolean isWoodenBrick();

    boolean isMetalBrick();

}
