package com.java.asteroids.scene;

import com.java.asteroids.controller.GameOverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOver {
    public static void load(Stage stage,int score){
        try {
            //get controller by root
            FXMLLoader fxmlLoader=new FXMLLoader(Index.class.getResource("/fxml/gameover.fxml"));
            Parent root= fxmlLoader.load();
            GameOverController gameOverController=fxmlLoader.getController();

            //from gameOverController set label as score
            gameOverController.setScore(score);
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
