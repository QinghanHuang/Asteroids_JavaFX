package com.java.asteroids.scene;

import com.java.asteroids.controller.GameOverController;
import com.java.asteroids.controller.ScoreController;
import com.java.asteroids.sprite.Bullet;
import com.java.asteroids.util.Score;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HighScore {

    //store  list
    private static List<Score> scores=new ArrayList<>();

    public static List<Score> getScores() {
        return scores;
    }

    public static void load(Stage stage){
        try {
            //get controller by root
            FXMLLoader fxmlLoader=new FXMLLoader(Index.class.getResource("/fxml/score.fxml"));
            Parent root= fxmlLoader.load();
            ScoreController scoreController=fxmlLoader.getController();
            //show score by order
            scoreController.setScoreList();
            System.out.println(scores);



            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
