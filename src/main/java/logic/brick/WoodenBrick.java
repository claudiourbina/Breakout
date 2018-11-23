package main.java.logic.brick;

import main.java.controller.Game;
import main.java.logic.level.Level;

public class WoodenBrick extends AbstractBrick {

    public WoodenBrick() {
        super(3, 200);
    }

    @Override
    public void acceptLevel(Level level) {
        level.addWoodenScore();
    }

    @Override
    public void acceptGame(Game game) {
        game.addWoodenScore();
    }

}
