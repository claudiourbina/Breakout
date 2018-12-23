package test;

import controller.Game;
import logic.brick.Brick;
import logic.level.EmptyLevel;
import logic.level.RealLevel;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private RealLevel rLevel;
    private RealLevel rLevel_superBrick;

    @Before
    public void SetUp(){
        game = new Game(3);
        rLevel = new RealLevel("Level", 15, 0.5, 1, 0);
        rLevel_superBrick = new RealLevel("Super Brick", 1, 1, 0, 1, 0);

    }

    @Test
    public void WinnerTest(){
        assertFalse(game.isGameOver());
        assertFalse(game.winner());
        assertEquals(0, game.numberOfBricks());
        game.setCurrentLevel(rLevel);
        assertEquals(30, game.numberOfBricks());
        List<Brick> bricks = rLevel.getBricks();
        for(Brick brick: bricks){
            repeat(brick.remainingHits(), brick::hit);
        }
        assertTrue(game.winner());
        assertTrue(game.isGameOver());
    }

    @Test
    public void ballsAndMetalTest(){
        assertFalse(game.isGameOver());
        assertEquals(3, game.getBalls());
        game.setCurrentLevel(rLevel);
        List<Brick> bricks = rLevel.getBricks();
        for(Brick brick: bricks){
            repeat(brick.remainingHits(), brick::hit);
        }
        assertEquals(18, game.getBalls());
        game.dropBall();
        assertEquals(17, game.getBalls());
        repeat(17, game::dropBall);
        assertTrue(game.isGameOver());
    }

    @Test
    public void currentLevelTest(){
        game.setCurrentLevel(rLevel);
        assertEquals(rLevel, game.getCurrentLevel());
    }

    @Test
    public void getPointTest(){
        assertEquals(0, game.getCurrentPoints());
        int score = 0;
        game.setCurrentLevel(rLevel);
        List<Brick> bricks = rLevel.getBricks();
        for(Brick brick: bricks){
            repeat(brick.remainingHits(), brick::hit);
            score += brick.getScore();
        }
        assertEquals(score, game.getCurrentPoints());
        game.setCurrentPoints(0);
        assertEquals(0, game.getCurrentPoints());
    }

    @Test
    public void isBrickTest(){
        game.setCurrentLevel(rLevel);
        List<Brick> bricks = rLevel.getBricks();
        for(Brick brick: bricks){
            if(brick.getScore() == 50){
                assertTrue(brick.isGlassBrick());
            }else if(brick.getScore() == 200){
                assertTrue(brick.isWoodenBrick());
            }else if(brick.getScore() == 0){
                assertTrue(brick.isMetalBrick());
            }
        }

        game.setCurrentLevel(rLevel_superBrick);
        bricks = rLevel_superBrick.getBricks();
        for(Brick brick: bricks){
            if(brick.getScore() == 0){
                assertTrue(brick.isSuperBrick());
            }
        }
    }

    @Test
    public void superNextLevel(){
        game.setCurrentLevel(rLevel_superBrick);
        assertEquals(game.getCurrentLevel(),rLevel_superBrick);
        List<Brick> listBricks_super = rLevel_superBrick.getBricks();
        for(Brick brick: listBricks_super){
            if(brick.isSuperBrick())
                repeat(20, brick::hit);
        }
        assertNotEquals(game.getCurrentLevel(),rLevel_superBrick);
    }

    private void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(i -> action.run());
    }
}
