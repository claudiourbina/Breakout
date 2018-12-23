package logic.brick;

import controller.Game;
import logic.level.Level;

public class SuperBrick extends AbstractBrick {

    public SuperBrick(){super(20, 0);}

    /**
     * Accept a Level level and it response to it activating an specific method.
     * @param level Accepted Level.
     */
    @Override
    public void acceptLevel(Level level) {
        level.addSuperScore();
    }

    /**
     * Accept a Game game and it response to it activating an specific method.
     * @param game Accepted Game.
     */
    @Override
    public void acceptGame(Game game) {
        game.addSuperScore();
    }

    @Override
    public boolean isSuperBrick(){return true;}
}
