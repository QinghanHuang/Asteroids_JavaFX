package com.java.asteroids.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class HighScore {
    public static void load(Stage stage){
        try {
            Parent root= FXMLLoader.load(Index.class.getResource("/fxml/score.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
