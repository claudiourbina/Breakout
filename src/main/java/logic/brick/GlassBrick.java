package main.java.logic.brick;

import main.java.controller.Game;
import main.java.logic.level.Level;

public class GlassBrick extends AbstractBrick {

    public GlassBrick() {
        super(1, 50);
    }

    @Override
    public void acceptLevel(Level level) {
        level.addGlassScore();
    }

    @Override
    public void acceptGame(Game game) {
        game.addGlassScore();
    }
}
