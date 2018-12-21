<<<<<<< HEAD
package logic.brick;

import controller.Game;
import logic.level.Level;
=======
package main.java.logic.brick;

import main.java.controller.Game;
import main.java.logic.level.Level;
<<<<<<< HEAD
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
=======
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
/**
 * Class that represents a metal brick object.
 *
 * @author Juan-Pablo Silva & Claudio Urbina
 */
public class MetalBrick extends AbstractBrick{

    public MetalBrick() {
        super(10, 0);
    }

    /**
     * Accept a Level level and it response to it activating an specific method.
     * @param level Accepted Level.
     */
    @Override
    public void acceptLevel(Level level) {
        level.addMetalScore();
    }

    /**
     * Accept a Game game and it response to it activating an specific method.
     * @param game Accepted Game.
     */
    @Override
    public void acceptGame(Game game) {
        game.addMetalScore();
    }

    @Override
    public boolean isMetalBrick(){return true;}
}
