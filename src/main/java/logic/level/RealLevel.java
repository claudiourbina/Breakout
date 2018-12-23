package logic.level;

import controller.Game;

/**
 * Class that represents a real level object.
 *
 * @author Claudio Urbina
 */
public class RealLevel extends AbstractLevel {
    /**
     * Constructor of a Real Level.
     * @param name Name of the level
     * @param numberOfBricks Number of bricks of the level.
     * @param probOfGlass Probability to get a Glass Brick.
     * @param probOfMetal Probability to get a Metal Brick.
     * @param seed Seed to set de Random number.
     */
    public RealLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        super(name, numberOfBricks, probOfGlass, probOfMetal, seed);
        setNextLevel(new EmptyLevel());
    }

    public RealLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, double probOfSuper, int seed){
        super(name, numberOfBricks, probOfGlass, probOfMetal, probOfSuper, seed);
        setNextLevel(new EmptyLevel());
    }

    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    @Override
    public boolean isPlayableLevel() {
        return true;
    }
}
