package com.java.asteroids;

import com.java.asteroids.util.Score;


import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("image/aircraft.png"));
        Director.getInstance().init(stage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Test
    public void writeInitialScoresTest(){
        ArrayList<Score> scores=new ArrayList<>();
        scores.add(new Score("Player1",95));
        scores.add(new Score("Player2",80));
        scores.add(new Score("Player3",70));
        scores.add(new Score("Player4",60));
        scores.add(new Score("Player5",50));
        scores.add(new Score("Player6",40));
        scores.add(new Score("Player7",30));
        scores.add(new Score("Player8",20));

        ObjectOutputStream objectOutputStream= null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("resources/scores.txt")));
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