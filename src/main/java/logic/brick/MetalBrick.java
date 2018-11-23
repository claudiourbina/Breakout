package main.java.logic.brick;

import main.java.controller.Game;
import main.java.logic.level.Level;

public class MetalBrick extends AbstractBrick{

    public MetalBrick() {
        super(10, 0);
    }

    @Override
    public void acceptLevel(Level level) {
        level.addMetalScore();
    }


    @Override
    public void acceptGame(Game game) {
        game.addMetalScore();
    }
}
