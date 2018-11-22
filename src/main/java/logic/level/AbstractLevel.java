package main.java.logic.level;

import main.java.logic.brick.Brick;
import main.java.logic.brick.GlassBrick;
import main.java.logic.brick.MetalBrick;
import main.java.logic.brick.WoodenBrick;

import java.util.*;

public class AbstractLevel implements Level, Observer {
    private List<Brick> bricksList = new ArrayList<>();
    private String levelName;
    private Level nextLevel;
    private int scoreLevel;

    public AbstractLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        this.levelName = name;
        this.scoreLevel = 0;
        this.nextLevel = null;

        if(numberOfBricks > 0){
            Random rand = new Random(seed);
            for(int i = 0; i < numberOfBricks; i++){
                double randomNumber = rand.nextDouble();
                if(randomNumber <= probOfGlass){
                    GlassBrick gBrick = new GlassBrick();
                    this.bricksList.add(gBrick);
                    scoreLevel += 50;
                }else{
                    WoodenBrick wBrick = new WoodenBrick();
                    this.bricksList.add(wBrick);
                    scoreLevel += 200;
                }
            }

            for(int i = 0; i < numberOfBricks; i++){
                double randomNumber = rand.nextDouble();
                if(randomNumber <= probOfMetal){
                    MetalBrick mBrick = new MetalBrick();
                    this.bricksList.add(mBrick);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Brick) {

        }
    }

    @Override
    public String getName() {
        return levelName;
    }

    @Override
    public int getNumberOfBricks() {
        return bricksList.size();
    }

    @Override
    public List<Brick> getBricks() {
        return bricksList;
    }

    @Override
    public Level getNextLevel() {
        return nextLevel;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel() {
        return nextLevel != null;
    }

    @Override
    public int getPoints() {
        return scoreLevel;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        if(this.nextLevel == null){
            setNextLevel(level);
            return this;
        }else{
            return this.nextLevel.addPlayingLevel(level);
        }
    }

    @Override
    public void setNextLevel(Level level) {
        this.nextLevel = level;
    }

    @Override
    public int bricksAlive() {
        int counter = 0;
        for(Brick brick: bricksList){
            if(!brick.isDestroyed()){
                counter+=1;
            }
        }
        return counter;
    }
}
