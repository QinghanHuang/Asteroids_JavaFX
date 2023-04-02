package com.java.asteroids;

import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.sprite.Alien;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Director.getInstance().init(stage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}