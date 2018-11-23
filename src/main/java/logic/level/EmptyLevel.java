package main.java.logic.level;

import main.java.controller.Game;

public class EmptyLevel extends AbstractLevel {

    public EmptyLevel(){
        super("", 0, 0, 0, 0);
        setNextLevel(this);
    }
}