package com.java.asteroids.controller;

import com.java.asteroids.Director;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Callback;

public class ScoreController {

    @FXML
    private Label newGame;

    @FXML
    private Text scoreList;


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

    public void setScoreList(){
        scoreList.setText("Top1\tClement\t500\n" +
                "Top2\tClement\t400\n" +
                "Top3\tClement\t300\n"+
                "Top4\tClement\t300\n"+
                "Top5\tClement\t300\n"+
                "Top6\tClement\t300\n"+
                "Top7\tClement\t300\n"+
                "Top8\tClement\t300\n");

    }

}
