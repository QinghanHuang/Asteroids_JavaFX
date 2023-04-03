package com.java.asteroids.controller;

import com.java.asteroids.Director;
import com.java.asteroids.scene.HighScore;
import com.java.asteroids.util.Score;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
                String name=enterName.getText();
                Score score=new Score(name,Integer.parseInt(showScore.getText()) );
            //2.write to a file
                HighScore.getScores().add(score);
                System.out.println(score);


            Director.getInstance().toHighScores();
        }
        public void setScore(int score){
                showScore.setText(score+"");
        }




}
