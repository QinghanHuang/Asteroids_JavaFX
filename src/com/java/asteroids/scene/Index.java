package com.java.asteroids.scene;

import com.java.asteroids.util.Score;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Index {

    public static void load(Stage stage){

        try {
            Parent root= FXMLLoader.load(Index.class.getResource("/fxml/index.fxml"));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}