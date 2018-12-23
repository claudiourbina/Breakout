package logic.brick;

import controller.Game;
import logic.level.Level;

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
