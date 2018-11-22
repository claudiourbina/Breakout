package main.java.logic.level;

public class EmptyLevel extends AbstractLevel {

    public EmptyLevel(){
        super("", 0, 0, 0, 0);
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public Level getNextLevel() {
        return this;
    }
}