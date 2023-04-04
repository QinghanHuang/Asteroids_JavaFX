package com.java.asteroids;

import com.java.asteroids.scene.*;
import com.java.asteroids.util.Score;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Director {

    //set global WIDTH and HEIGHT
    public static final double WIDTH = 1440, HEIGHT = 960;

    //store stage
    private Stage stage;

    //singleton mode (only one instance)
    private static Director instance = new Director();

    //store gameScene
    private GameScene gameScene=new GameScene();
    private Director() {
    }

    private ArrayList<Score> scores;

    public ArrayList<Score> getScores() {
        return scores;
    }

    //singleton mode (only one instance)
    public static Director getInstance() {
        return instance;
    }

    /**
     * initial scene
     *
     * @param stage
     */
    public void init(Stage stage) {
        //set Parent root
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Asteroids");
        //for set icon in future
        //stage.getIcons().add(new Image("/image/logo.png"));

        //can resize window
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        //load Scores From File
        loadScoresFromFile();
        //set stage
        this.stage=stage;
        toIndex();
        stage.show();
    }

    /**
     * load index
     */
    public void toIndex() {
        Index.load(stage);
    }

    /**
     * load HighScores Scene
     */
    public void toHighScores() {
        HighScore.load(stage);

    }

    /**
     * load gameScene
     */
    public void gameStart() {
        gameScene.init(stage);

    }

    /**
     * load game over
     * @param score
     */
    public void gameOver(int score) {
//        gameScene.clear(stage);
        GameOver.load(stage,score);
    }

    private void loadScoresFromFile(){
        ObjectInputStream objectInputStream= null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(new File("scores.txt")));
            Object obj= objectInputStream.readObject();
            scores=(ArrayList<Score>)obj;
            Collections.sort(scores);
            System.out.println(scores);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

