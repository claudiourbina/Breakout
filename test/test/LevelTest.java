package test;

import logic.brick.Brick;
import logic.level.EmptyLevel;
import logic.level.RealLevel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class LevelTest {
    private EmptyLevel eLevel;
    private RealLevel rLevel_fullGlass;
    private RealLevel rLevel_fullWooden;
    private RealLevel rLevel_fullMetal;
    private RealLevel rLevel_superBrick;

    @Before
    public void SetUp(){
        eLevel = new EmptyLevel();
        int seed = 0;
        rLevel_fullGlass = new RealLevel("Full Glass", 20, 1, 0, seed);
        rLevel_fullWooden = new RealLevel("Full Wooden", 20, 0, 0, seed);
        rLevel_fullMetal = new RealLevel("Full Metal", 20, 0, 1, seed);
        rLevel_superBrick = new RealLevel("Super Brick", 1, 1, 1, 1, seed);
    }

    @Test
    public void EmptyLevelTest(){
        assertEquals(eLevel, eLevel.getNextLevel());
        assertFalse(eLevel.hasNextLevel());
        assertEquals(0, eLevel.getNumberOfBricks());
        assertEquals("", eLevel.getName());
        assertTrue(eLevel.getBricks().isEmpty());
        assertFalse(eLevel.isPlayableLevel());
        assertEquals(0, eLevel.getPoints());
        eLevel.addPlayingLevel(rLevel_fullGlass);
        assertEquals(rLevel_fullGlass, eLevel.getNextLevel());
        eLevel.setNextLevel(eLevel);
        assertEquals(eLevel, eLevel.getNextLevel());
    }

    @Test
    public void RealLevelTest_FullGlass(){
        assertEquals("Full Glass", rLevel_fullGlass.getName());
        assertEquals(20, rLevel_fullGlass.getNumberOfBricks());
        assertTrue(rLevel_fullGlass.isPlayableLevel());
        assertFalse(rLevel_fullGlass.hasNextLevel());
        assertEquals(1000, rLevel_fullGlass.getPoints());
        assertFalse(rLevel_fullGlass.getBricks().isEmpty());
        rLevel_fullGlass.addPlayingLevel(rLevel_fullGlass);
        assertTrue(rLevel_fullGlass.hasNextLevel());
        rLevel_fullGlass.setNextLevel(eLevel);
        assertFalse(rLevel_fullGlass.hasNextLevel());
    }

    @Test
    public void RealLevelTest_FullWooden(){
        assertEquals("Full Wooden", rLevel_fullWooden.getName());
        assertEquals(20, rLevel_fullWooden.getNumberOfBricks());
        assertTrue(rLevel_fullWooden.isPlayableLevel());
        assertFalse(rLevel_fullWooden.hasNextLevel());
        assertEquals(4000, rLevel_fullWooden.getPoints());
        assertFalse(rLevel_fullWooden.getBricks().isEmpty());
        rLevel_fullWooden.addPlayingLevel(rLevel_fullGlass);
        assertTrue(rLevel_fullWooden.hasNextLevel());
        rLevel_fullWooden.setNextLevel(eLevel);
        assertFalse(rLevel_fullWooden.hasNextLevel());
    }

    @Test
    public void RealLevelTest_FullMetal(){
        assertEquals("Full Metal", rLevel_fullMetal.getName());
        assertEquals(40, rLevel_fullMetal.getNumberOfBricks());
        assertTrue(rLevel_fullMetal.isPlayableLevel());
        assertFalse(rLevel_fullMetal.hasNextLevel());
        assertEquals(4000, rLevel_fullMetal.getPoints());
        assertFalse(rLevel_fullMetal.getBricks().isEmpty());
        rLevel_fullMetal.addPlayingLevel(rLevel_fullWooden);
        assertTrue(rLevel_fullMetal.hasNextLevel());
        rLevel_fullMetal.setNextLevel(eLevel);
        assertFalse(rLevel_fullMetal.hasNextLevel());
    }

    @Test
    public void RealLevelTest_superBrick(){
        /* Este nivel posee el SuperBrick, un Glass Brick y un Metal Brick */
        assertEquals("Super Brick", rLevel_superBrick.getName());
        assertEquals(3, rLevel_superBrick.getNumberOfBricks());
        assertTrue(rLevel_superBrick.isPlayableLevel());
        assertFalse(rLevel_superBrick.hasNextLevel());
        assertEquals(50, rLevel_superBrick.getPoints());
        assertFalse(rLevel_superBrick.getBricks().isEmpty());
        rLevel_superBrick.addPlayingLevel(rLevel_fullWooden);
        assertTrue(rLevel_superBrick.hasNextLevel());
        rLevel_superBrick.setNextLevel(eLevel);
        assertFalse(rLevel_superBrick.hasNextLevel());
    }

    @Test
    public void VisitAndObserverTest(){
        assertEquals(20, rLevel_fullGlass.bricksAlive());
        assertEquals(20, rLevel_fullWooden.bricksAlive());
        assertEquals(40, rLevel_fullMetal.bricksAlive());
        assertEquals(3, rLevel_superBrick.bricksAlive());

        List<Brick> listBricks_glass = rLevel_fullGlass.getBricks();
        int score = 0;
        for(Brick brick: listBricks_glass){
            assertEquals(score, rLevel_fullGlass.getCurrentScore());
            brick.hit();
            score += brick.getScore();
            assertEquals(score, rLevel_fullGlass.getCurrentScore());
        }

        List<Brick> listBricks_wooden = rLevel_fullWooden.getBricks();
        score = 0;
        for(Brick brick: listBricks_wooden){
            assertEquals(score, rLevel_fullWooden.getCurrentScore());
            repeat(3, brick::hit);
            score += brick.getScore();
            assertEquals(score, rLevel_fullWooden.getCurrentScore());
        }

        List<Brick> listBricks_metal = rLevel_fullMetal.getBricks();
        score = 0;
        for(Brick brick: listBricks_metal){
            assertEquals(score, rLevel_fullMetal.getCurrentScore());
            repeat(brick.remainingHits(), brick::hit);
            score += brick.getScore();
            assertEquals(score, rLevel_fullMetal.getCurrentScore());
        }

        List<Brick> listBricks_super = rLevel_superBrick.getBricks();
        score = 0;
        for(Brick brick: listBricks_super){
            assertEquals(score, rLevel_superBrick.getCurrentScore());
            repeat(20, brick::hit);
            score += brick.getScore();
            assertEquals(score, rLevel_superBrick.getCurrentScore());
        }

        assertEquals(0, rLevel_fullGlass.bricksAlive());
        assertEquals(0, rLevel_fullWooden.bricksAlive());
        assertEquals(0, rLevel_fullMetal.bricksAlive());
        assertEquals(0, rLevel_fullMetal.bricksAlive());
    }

    @Test
    public void addNextLevelTest(){
        rLevel_fullMetal.addPlayingLevel(rLevel_fullGlass).addPlayingLevel(rLevel_fullWooden);
        assertEquals(rLevel_fullGlass, rLevel_fullMetal.getNextLevel());
    }

    private void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(i -> action.run());
    }
}
