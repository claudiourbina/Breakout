package main.java.logic.level;

public class RealLevel extends AbstractLevel {

    public RealLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        super(name, numberOfBricks, probOfGlass, probOfMetal, seed);
    }

    @Override
    public boolean isPlayableLevel() {
        return true;
    }
}
