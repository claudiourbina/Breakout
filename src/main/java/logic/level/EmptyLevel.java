package logic.level;

import controller.Game;

/**
 * Class that represents a empty level object.
 *
 * @author Claudio Urbina
 */
public class EmptyLevel extends AbstractLevel {
    /**
     * Constructor of a Empty Level
     */
    public EmptyLevel(){
        super("", 0, 0, 0, 0);
        setNextLevel(this);
    }
}