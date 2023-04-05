package com.java.asteroids.controller;

import com.java.asteroids.Director;
import com.java.asteroids.util.Score;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * get the scores list
     * convert it to string
     * set it to Text to show
     */
    public void setScoreList(ArrayList<Score> scores){
        Collections.sort(scores);
        String scoreString="";
        for (int i = 0; i < 8; i++) {
            scoreString+=("Top"+(i+1)+"\t"+scores.get(i).getName()+"\t"+scores.get(i).getScore()+"\n");
        }
        scoreList.setText(scoreString);

    }

}
