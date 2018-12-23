package logic.brick;

import controller.Game;
import logic.level.Level;

/**
 * Class that represents a glass brick object.
 *
 * @author Juan-Pablo Silva & Claudio Urbina
 */
public class GlassBrick extends AbstractBrick {

    public GlassBrick() {
        super(1, 50);
    }

    /**
     * Accept a Level level and it response to it activating an specific method.
     * @param level Accepted Level.
     */
    @Override
    public void acceptLevel(Level level) {
        level.addGlassScore();
    }

    /**
     * Accept a Game game and it response to it activating an specific method.
     * @param game Accepted Game.
     */
    @Override
    public void acceptGame(Game game) {
        game.addGlassScore();
    }

    @Override
    public boolean isGlassBrick(){return true;}
}
