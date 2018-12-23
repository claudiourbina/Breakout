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

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.net.Server;
import com.almasb.fxgl.parser.text.TextLevelParser;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import controller.Game;
import facade.HomeworkTwoFacade;
import gui.control.BallComponent;
import gui.control.BatComponent;
import gui.control.BrickComponent;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.Logic;
import logic.brick.Brick;
import gui.control.BrickComponent;

import java.util.Collections;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Claudio Urbina
 */
public class BreakoutApp extends GameApplication implements Observer {

    private HomeworkTwoFacade game = new HomeworkTwoFacade();
    private boolean follow = true;
    private int firstLevel = 0;

    private BatComponent getBatControl() {
        return getGameWorld().getSingleton(BreakoutType.BAT).get().getComponent(BatComponent.class);
    }

    private BallComponent getBallControl() {
        return getGameWorld().getSingleton(BreakoutType.BALL).get().getComponent(BallComponent.class);
    }

    private void spawnLevel(){
        int x = 80;
        int y = 80;
        Collections.shuffle(game.getCurrentLevel().getBricks());
        for(Brick brick: game.getCurrentLevel().getBricks()){
            SpawnData spawnData = new SpawnData(x, y);
            spawnData.put("Brick", brick);
            if(brick.isGlassBrick()){
                Entity glass = BreakoutFactory.newGlass(spawnData);
                getGameWorld().addEntity(glass);
            }else if(brick.isMetalBrick()){
                Entity metal = BreakoutFactory.newMetal(spawnData);
                getGameWorld().addEntity(metal);
            }else if(brick.isWoodenBrick()){
                Entity wooden = BreakoutFactory.newWooden(spawnData);
                getGameWorld().addEntity(wooden);
            }else if(brick.isSuperBrick()) {
                Entity superBrick = BreakoutFactory.newSuper(spawnData);
                getGameWorld().addEntity(superBrick);
            }
            x+=40;
            if(x>=520){
                x=80;
                y+=20;
            }
        }
        getGameState().setValue("levelPoints", game.getLevelPoints());
        desplegarLevel(game.getCurrentLevel().getName());
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Breakout");
        settings.setVersion("Pre-Alpha");
        settings.setWidth(600);
        settings.setHeight(800);
        settings.setFontUI("pixelmix.ttf");
    }

    public void followAction(int difference){
        ((Entity)getGameState().getObject("Ball")).removeFromWorld();
        Entity player = ((Entity)getGameState().getObject("Player"));
        Entity ball = BreakoutFactory.newBall(new SpawnData(player.getX()+difference, player.getY()-20));
        follow = true;
        getGameWorld().addEntity(ball);
        getGameState().setValue("Ball", ball);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                if(firstLevel >= 1) {
                    getBatControl().left();
                    if (follow) {
                        followAction(+10);
                    }
                }
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                if(firstLevel >= 1) {
                    getBatControl().right();
                    if (follow) {
                        followAction(+55);
                    }
                }
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Release Ball") {
            @Override
            protected void onAction() {
                if(follow && firstLevel >= 1) {
                    getBallControl().release();
                    follow = false;
                }
            }
        }, KeyCode.SPACE);

        getInput().addAction(new UserAction("New Level") {
            @Override
            protected void onActionEnd() {
                addPlayingLevel();
            }
        }, KeyCode.N);

    }

    private void showEffect(double x, double y){
        Entity hitEffect = BreakoutFactory.newFireEffect(x,y);
        getGameWorld().addEntity(hitEffect);
        getMasterTimer().runOnceAfter(hitEffect::removeFromWorld, Duration.seconds(0.3));
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("lives", game.getBallsLeft());
        vars.put("actualPoints", game.getCurrentLevel().getCurrentScore());
        vars.put("levelPoints", game.getLevelPoints());
        vars.put("totalPoints", game.getCurrentPoints());
        vars.put("totalLevels", firstLevel);
    }

    protected void addPlayingLevel(){
        if(firstLevel==0){
            game.setCurrentLevel(game.newRandomLevel("Level 0", firstLevel + 1));
            spawnLevel();
        }else{
            game.addPlayingLevel(game.newRandomLevel("Level ".concat(Integer.toString(firstLevel)), firstLevel + 1));
        }
        firstLevel++;
        getGameState().setValue("totalLevels", firstLevel);
    }

    @Override
    protected void initGame() {
        game.suscribe(this);
        getGameState().setValue("lives", game.getBallsLeft());
        initBGM();
        initBackground();
        Entity player = BreakoutFactory.newBat(new SpawnData(FXGL.getSettings().getWidth() / 2 - 50, 620));
        getGameState().setValue("Player", player);
        Entity ball = BreakoutFactory.newBall(new SpawnData(player.getX()+30, player.getY()-20));
        getGameWorld().addEntities(player, ball);
        getGameState().setValue("Ball", ball);
        initWalls();
    }

    private void initBGM() {
        getAudioPlayer().loopBGM("BG2.mp3");
    }

    private void initWalls() {
        for(int x = 0; x < 600; x+=20){
            for(int y = 0; y < 40; y+=20){
                SpawnData spawnData = new SpawnData(x, y);
                Entity wall = BreakoutFactory.newWall(spawnData, BreakoutType.WALL);
                getGameWorld().addEntity(wall);
            }
        }

        for(int x = 0; x < 40; x+=20){
            for(int y = 40; y < 660; y+=20){
                SpawnData spawnData = new SpawnData(x, y);
                Entity wall = BreakoutFactory.newWall(spawnData, BreakoutType.WALL);
                getGameWorld().addEntity(wall);
            }
        }

        for(int x = 560; x < 600; x+=20){
            for(int y = 40; y < 660; y+=20){
                SpawnData spawnData = new SpawnData(x, y);
                Entity wall = BreakoutFactory.newWall(spawnData, BreakoutType.WALL);
                getGameWorld().addEntity(wall);
            }
        }

        for(int x = 0; x < 600; x+=20){
            for(int y = 680; y < 800; y+=20){
                SpawnData spawnData = new SpawnData(x, y);
                Entity wall = BreakoutFactory.newWall(spawnData, BreakoutType.BOTTOMWALL);
                getGameWorld().addEntity(wall);
            }
        }
    }

    private void initBackground() {
        Entities.builder()
                .renderLayer(RenderLayer.BACKGROUND)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("back.png", 600, 800))
                .with(new IrremovableComponent())
                .buildAndAttach(getGameWorld());

        Entity screenBounds = Entities.makeScreenBounds(120);
        screenBounds.addComponent(new IrremovableComponent());

        getGameWorld().addEntity(screenBounds);
    }



    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BreakoutType.BALL, BreakoutType.BRICK) {
            @Override
            protected void onCollisionBegin(Entity ball, Entity brick) {
                showEffect(brick.getX(),brick.getY());
                brick.getComponent(BrickComponent.class).onHit();
                getGameState().setValue("actualPoints", game.getCurrentLevel().getCurrentScore());
                getGameState().setValue("totalPoints", game.getCurrentPoints());
                getGameState().setValue("lives", game.getBallsLeft());
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BreakoutType.BALL, BreakoutType.BAT) {
            @Override
            protected void onCollisionBegin(Entity ball, Entity brick) {
                if(!follow)
                    FXGL.getAudioPlayer().playSound("bat_ball.wav");
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(BreakoutType.BALL, BreakoutType.BOTTOMWALL) {
            @Override
            protected void onCollisionBegin(Entity ball, Entity Wall) {
                bottomHit();
            }

        });
    }

    private void bottomHit() {
        game.dropBall();
        if(game.getBallsLeft()==0){
            Entity ball = ((Entity)getGameState().getObject("Ball"));
            ball.removeFromWorld();
            gameOver();
        }else{
            followAction(30);
            getGameState().setValue("lives", game.getBallsLeft());
        }
    }

    protected void desplegarLevel(String levelName){
        Text text = getUIFactory().newText(levelName, Color.WHITE, 30);
        getGameScene().addUINode(text);

        QuadCurve curve = new QuadCurve(-100, 0, getWidth() / 2, getHeight(), getWidth() + 100, 0);

        PathTransition transition = new PathTransition(Duration.seconds(5), curve, text);
        transition.setOnFinished(e -> {
            getGameScene().removeUINode(text);
        });
        transition.play();
    }

    @Override
    protected void initUI() {

        AppController controller = new AppController();
        UI ui = getAssetLoader().loadUI("main.fxml", controller);

        controller.getLabelActualPoints().textProperty().bind(getGameState().intProperty("actualPoints").asString());
        controller.getLabelTotalPoints().textProperty().bind(getGameState().intProperty("totalPoints").asString());
        controller.getLabelLevelPoints().textProperty().bind(getGameState().intProperty("levelPoints").asString());
        controller.getLabelLives().textProperty().bind(getGameState().intProperty("lives").asString());
        controller.getTotalLevel().textProperty().bind(getGameState().intProperty("totalLevels").asString());

        getGameScene().addUI(ui);
    }

    private void showWinner() {
        getDisplay().showMessageBox("You won!\nThanks for playing", this::exit);
    }

    private void gameOver() {
        getDisplay().showMessageBox("GAME OVER", this::exit);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Game){
            if(!game.winner()) {
                getGameWorld().removeEntities(getGameWorld().getEntitiesByType(BreakoutType.BRICK));
                followAction(30);
                this.spawnLevel();
            }else{
                followAction(30);
                showWinner();
            }
        }
    }
}
