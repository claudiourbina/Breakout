package test;

<<<<<<< HEAD
<<<<<<< HEAD
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
=======
import main.java.logic.brick.GlassBrick;
import main.java.logic.brick.MetalBrick;
import main.java.logic.brick.WoodenBrick;
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
=======
import main.java.logic.brick.GlassBrick;
import main.java.logic.brick.MetalBrick;
import main.java.logic.brick.WoodenBrick;
>>>>>>> 83d15590235311c6929ddb8476415091ccf75fc0
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class BrickTest {
    private GlassBrick gBrick;
    private WoodenBrick wBrick;
    private MetalBrick mBrick;

    @Before
    public void SetUp(){
        gBrick = new GlassBrick();
        wBrick = new WoodenBrick();
        mBrick = new MetalBrick();
    }

    @Test
    public void hitLimit(){
        repeat(1, gBrick::hit);
        repeat(3, wBrick::hit);
        repeat(10, mBrick::hit);
        assertEquals(0, gBrick.remainingHits());
        assertEquals(0, wBrick.remainingHits());
        assertEquals(0, mBrick.remainingHits());
        gBrick.hit();
        wBrick.hit();
        mBrick.hit();
        assertEquals(0, gBrick.remainingHits());
        assertEquals(0, wBrick.remainingHits());
        assertEquals(0, mBrick.remainingHits());
    }

    @Test
    public void isDetroyedTest(){
        assertFalse(gBrick.isDestroyed());
        assertFalse(wBrick.isDestroyed());
        assertFalse(mBrick.isDestroyed());
        repeat(1, gBrick::hit);
        repeat(3, wBrick::hit);
        repeat(10, mBrick::hit);
        assertTrue(gBrick.isDestroyed());
        assertTrue(wBrick.isDestroyed());
        assertTrue(mBrick.isDestroyed());
    }

    @Test
    public void getScoreTest(){
        assertEquals(50, gBrick.getScore());
        assertEquals(200, wBrick.getScore());
        assertEquals(0, mBrick.getScore());
    }

    private void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(i -> action.run());
    }
}
