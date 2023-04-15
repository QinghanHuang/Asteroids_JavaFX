package com.java.asteroids.controller;
import com.java.asteroids.Director;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControlInfoController {

    @FXML
    private Label back;

    @FXML
    void backClick(MouseEvent event) {
        Director.getInstance().toIndex();

    }

}

