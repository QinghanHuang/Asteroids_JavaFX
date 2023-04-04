package com.java.asteroids.controller;

import com.java.asteroids.Director;
import com.java.asteroids.scene.Index;
import com.java.asteroids.util.Score;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;

public class GameOverController {

    @FXML
    private TextField enterName;

    @FXML
    private Label showScore;

    @FXML
    private Button submit;

    @FXML
    void submitClick(MouseEvent event) {
        //1. load showScore and name input
        String name = enterName.getText();
        Score score = new Score(name, Integer.parseInt(showScore.getText()));
        //2.write to a file
        ArrayList<Score> scores = Director.getInstance().getScores();
        scores.add(score);

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("scores.txt")));
            objectOutputStream.writeObject(scores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectOutputStream != null) {

                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Director.getInstance().toHighScores();
    }

    public void setScore(int score) {
        showScore.setText(score + "");
    }


}
