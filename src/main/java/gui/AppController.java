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
import com.almasb.fxgl.ui.UIController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author Claudio Urbina
 */
public class AppController implements UIController {

    @FXML
    private Label labelActualPoints;

    @FXML
    private Label labelTotalPoints;

    @FXML
    private Label labelTotalLevel;

    @FXML
    private Label labelLevelPoints;

    @FXML
    private Label lives;

    public Label getLabelActualPoints() {
        return labelActualPoints;
    }

    public Label getLabelTotalPoints() {
        return labelTotalPoints;
    }

    public Label getTotalLevel() {return labelTotalLevel;}

    public Label getLabelLevelPoints() {
        return labelLevelPoints;
    }

    public Label getLabelLives() {
        return lives;
    }

    @Override
    public void init() {
        labelActualPoints.setFont(FXGL.getUIFactory().newFont(15));
        labelTotalPoints.setFont(FXGL.getUIFactory().newFont(15));
        labelLevelPoints.setFont(FXGL.getUIFactory().newFont(15));
        lives.setFont(FXGL.getUIFactory().newFont(15));
        labelTotalLevel.setFont(FXGL.getUIFactory().newFont(15));

        labelTotalPoints.layoutBoundsProperty().addListener((observable, oldValue, newBounds) -> {
            double width = newBounds.getWidth();
            labelTotalPoints.setTranslateX(390);
        });

        labelLevelPoints.layoutBoundsProperty().addListener((observable, oldValue, newBounds) -> {
            double width = newBounds.getWidth();
            labelLevelPoints.setTranslateX(155);
        });

        labelTotalLevel.layoutBoundsProperty().addListener((observable, oldValue, newBounds) -> {
            double width = newBounds.getWidth();
            labelTotalLevel.setTranslateX(155);
        });

        lives.layoutBoundsProperty().addListener((observable, oldValue, newBounds) -> {
            double width = newBounds.getWidth();
            lives.setTranslateX(447);
        });

        labelActualPoints.textProperty().addListener((observable, oldValue, newValue) -> {
            animateLabel(labelActualPoints);
        });

        labelTotalPoints.textProperty().addListener((observable, oldValue, newValue) -> {
            animateLabel(labelTotalPoints);
        });

        labelLevelPoints.textProperty().addListener((observable, oldValue, newValue) -> {
            animateLabel(labelLevelPoints);
        });

        lives.textProperty().addListener((observable, oldValue, newValue) -> {
            animateLabel(lives);
        });

        labelTotalLevel.textProperty().addListener((observable, oldValue, newValue) -> {
            animateLabel(labelTotalLevel);
        });
    }

    private void animateLabel(Label label) {
        FadeTransition ft = new FadeTransition(Duration.seconds(0.33), label);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

}
