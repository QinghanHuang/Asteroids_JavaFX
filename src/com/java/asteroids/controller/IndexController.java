package com.java.asteroids.controller;
import com.java.asteroids.Director;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import com.java.asteroids.util.SoundEffect;

public class IndexController {

    @FXML
    private Label play;

    @FXML
    private Label score;

    @FXML
    void playEnter(MouseEvent event) {

    }

    @FXML
    void playExit(MouseEvent event) {

    }

    @FXML
    void playClick(MouseEvent event) {
        Director.getInstance().gameStart();

    }

    @FXML
    void scoreClick(MouseEvent event) {
        Director.getInstance().toHighScores();

    }

    @FXML
    void scoreEnter(MouseEvent event) {

    }

    @FXML
    void scoreExit(MouseEvent event) {

    }

}
