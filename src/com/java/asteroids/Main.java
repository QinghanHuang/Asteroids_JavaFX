package com.java.asteroids;

import com.java.asteroids.Director;
import com.java.asteroids.scene.Index;
import com.java.asteroids.util.Score;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Director.getInstance().init(stage);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Test
    public void writeInitialScoresTest(){
        ArrayList<Score> scores=new ArrayList<>();
        scores.add(new Score("Player1",5000));
        scores.add(new Score("Player2",4000));
        scores.add(new Score("Player3",3000));

        ObjectOutputStream objectOutputStream= null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("scores.txt")));
            objectOutputStream.writeObject(scores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectOutputStream!=null){

                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}