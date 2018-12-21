package test;

<<<<<<< HEAD
import controller.Game;
import logic.brick.Brick;
import logic.level.EmptyLevel;
import logic.level.RealLevel;
=======
import main.java.controller.Game;
import main.java.logic.brick.Brick;
import main.java.logic.level.EmptyLevel;
import main.java.logic.level.RealLevel;
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;
    private RealLevel rLevel;

    @Before
    public void SetUp(){
        game = new Game(3);
        rLevel = new RealLevel("Level", 15, 0.5, 1, 0);
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

    private void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(i -> action.run());
    }
}
