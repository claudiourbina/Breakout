package logic.brick;

import controller.Game;
import logic.level.Level;

public class SuperBrick extends AbstractBrick {

    public SuperBrick(){super(20, 0);}

    @Override
    public void acceptLevel(Level level) {
        level.addSuperScore();
    }

    @Override
    public void acceptGame(Game game) {
    }

    @Override
    public boolean isSuperBrick(){return true;}
}
