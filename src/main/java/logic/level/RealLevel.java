package main.java.logic.level;

import main.java.controller.Game;

public class RealLevel extends AbstractLevel {

    public RealLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        super(name, numberOfBricks, probOfGlass, probOfMetal, seed);
        setNextLevel(new EmptyLevel());
    }

    @Override
    public boolean isPlayableLevel() {
        return true;
    }
}
