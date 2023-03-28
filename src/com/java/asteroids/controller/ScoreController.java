package com.java.asteroids.controller;

import com.java.asteroids.Director;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ScoreController {

    @FXML
    private Label newGame;

    @FXML
    void newGameClick(MouseEvent event) {
        Director.getInstance().gameStart();

    }

    @FXML
    void newGameEnter(MouseEvent event) {

    }

    @FXML
    void newGameExit(MouseEvent event) {

    }

}
