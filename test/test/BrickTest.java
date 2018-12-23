package test;

import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.SuperBrick;
import logic.brick.WoodenBrick;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class BrickTest {
    private GlassBrick gBrick;
    private WoodenBrick wBrick;
    private MetalBrick mBrick;
    private SuperBrick sBrick;

    @Before
    public void SetUp(){
        gBrick = new GlassBrick();
        wBrick = new WoodenBrick();
        mBrick = new MetalBrick();
        sBrick = new SuperBrick();
    }

    @Test
    public void hitLimit(){
        repeat(1, gBrick::hit);
        repeat(3, wBrick::hit);
        repeat(10, mBrick::hit);
        repeat(20, sBrick::hit);
        assertEquals(0, gBrick.remainingHits());
        assertEquals(0, wBrick.remainingHits());
        assertEquals(0, mBrick.remainingHits());
        assertEquals(0, sBrick.remainingHits());
        gBrick.hit();
        wBrick.hit();
        mBrick.hit();
        sBrick.hit();
        assertEquals(0, gBrick.remainingHits());
        assertEquals(0, wBrick.remainingHits());
        assertEquals(0, mBrick.remainingHits());
        assertEquals(0, sBrick.remainingHits());
    }

    @Test
    public void isDetroyedTest(){
        assertFalse(gBrick.isDestroyed());
        assertFalse(wBrick.isDestroyed());
        assertFalse(mBrick.isDestroyed());
        assertFalse(sBrick.isDestroyed());
        repeat(1, gBrick::hit);
        repeat(3, wBrick::hit);
        repeat(10, mBrick::hit);
        repeat(20, sBrick::hit);
        assertTrue(gBrick.isDestroyed());
        assertTrue(wBrick.isDestroyed());
        assertTrue(mBrick.isDestroyed());
        assertTrue(sBrick.isDestroyed());
    }

    @Test
    public void getScoreTest(){
        assertEquals(50, gBrick.getScore());
        assertEquals(200, wBrick.getScore());
        assertEquals(0, mBrick.getScore());
        assertEquals(0, sBrick.getScore());
    }

    @Test
    public void isTest(){
        assertTrue(gBrick.isGlassBrick());
        assertTrue(wBrick.isWoodenBrick());
        assertTrue(mBrick.isMetalBrick());
        assertTrue(sBrick.isSuperBrick());
    }

    private void repeat(int n, Runnable action) {
        IntStream.range(0, n).forEach(i -> action.run());
    }
}
