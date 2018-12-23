/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import gui.control.BallComponent;
import gui.control.BatComponent;
import gui.control.BrickComponent;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Claudio Urbina
 */
public class BreakoutFactory {

    @SpawnSymbol('1')
    public static Entity newGlass(SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(BreakoutType.BRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("glassbrick.jpg", 40, 20))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with(new BrickComponent(data.get("Brick")))
                .build();
    }

    @SpawnSymbol('2')
    public static Entity newWooden(SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(BreakoutType.BRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("woodenbrick.jpg", 40, 20))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with(new BrickComponent(data.get("Brick")))
                .build();
    }

    @SpawnSymbol('3')
    public static Entity newMetal(SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(BreakoutType.BRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("metalbrick.jpg", 40, 20))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with(new BrickComponent(data.get("Brick")))
                .build();
    }

    @SpawnSymbol('4')
    public static Entity newBall(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(0.03f));

        //ParticleEmitter emitter = ParticleEmitters.newFireEmitter();
        //emitter.setNumParticles(5);
        //emitter.setEmissionRate(0.5);
        //emitter.setBlendMode(BlendMode.SRC_OVER);

        return Entities.builder()
                .from(data)
                .type(BreakoutType.BALL)
                //.at(FXGL.getSettings().getWidth() / 2 - 50, 598)
                .bbox(new HitBox("Main", BoundingShape.circle(10)))
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("ball.gif", 20, 20))
                .with(physics, new CollidableComponent(true))
                .with(new BallComponent())
                .build();
    }

    @SpawnSymbol('5')
    public static Entity newWall(SpawnData data, BreakoutType type) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .from(data)
                .type(type)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("wall.png", 20, 20))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .build();
    }

    @SpawnSymbol('6')
    public static Entity newBat(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .from(data)
                .type(BreakoutType.BAT)
                //.at(FXGL.getSettings().getWidth() / 2 - 50, 620)
                .bbox(new HitBox("Bat", BoundingShape.box(80,20)))
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("bat.gif", 80, 20))
                .with(physics, new CollidableComponent(true))
                .with(new BatComponent())
                .build();
    }

    @SpawnSymbol('7')
    public static Entity newSuper(SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(BreakoutType.BRICK)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("superBrick.gif", 40, 20))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .with(new BrickComponent(data.get("Brick")))
                .build();
    }

    @SpawnSymbol('8')
    static Entity newFireEffect(double x, double y){
        ParticleEmitter emitter = ParticleEmitters.newImplosionEmitter();
        emitter.setColor(Color.YELLOWGREEN);
        emitter.setBlendMode(BlendMode.HARD_LIGHT);
        ParticleComponent component = new ParticleComponent(emitter);
        return Entities.builder()
                .at(x,y)
                .with(component)
                .build();
    }

}
