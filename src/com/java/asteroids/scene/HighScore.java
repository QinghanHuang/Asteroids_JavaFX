package com.java.asteroids.scene;

import com.java.asteroids.Director;
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



    public static void load(Stage stage){
        try {
            //get controller by root
            FXMLLoader fxmlLoader=new FXMLLoader(Index.class.getResource("/fxml/score.fxml"));
            Parent root= fxmlLoader.load();
            ScoreController scoreController=fxmlLoader.getController();

            //get scores list(for test)
//            System.out.println(Director.getInstance().getScores());

            //pass this list to setScoreList
            scoreController.setScoreList(Director.getInstance().getScores());

            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
