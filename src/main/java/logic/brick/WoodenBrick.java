<<<<<<< HEAD
package logic.brick;

import controller.Game;
import logic.level.Level;
=======
package main.java.logic.brick;

import main.java.controller.Game;
import main.java.logic.level.Level;
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
/**
 * Class that represents a wooden brick object.
 *
 * @author Juan-Pablo Silva & Claudio Urbina
 */
public class WoodenBrick extends AbstractBrick {

    public WoodenBrick() {
        super(3, 200);
    }

    /**
     * Accept a Level level and it response to it activating an specific method.
     * @param level Accepted Level.
     */
    @Override
    public void acceptLevel(Level level) {
        level.addWoodenScore();
    }

    /**
     * Accept a Game game and it response to it activating an specific method.
     * @param game Accepted Game.
     */
    @Override
    public void acceptGame(Game game) {
        game.addWoodenScore();
    }

    @Override
    public boolean isWoodenBrick(){return true;}

}
