package com.java.asteroids.scene;

import com.java.asteroids.*;
import com.java.asteroids.sprite.*;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class GameScene {
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    private KeyProcess keyProcess = new KeyProcess();

    private Refresh refresh = new Refresh();

    //running false means pause
    private boolean running = false;

    private BackGround background = new BackGround();

    //store player in gameScene
    private AirCraft self =null;
    private Alien alien=null;

    private long startTime = System.currentTimeMillis();

    //store bullet list
    private List<Bullet> bullets=new ArrayList<>();

    //store asteroids
//    private List<Asteroid> asteroids=new ArrayList<>();


    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * paint of gameScene
     * will call paint of sprite
     */
    private void paint() {
        //paint background in gameScene
        background.paint(graphicsContext);
        //paint player in gameScene
        self.paint(graphicsContext);
        //paint alien
        if (alien != null && alien.isAlive()) {
            alien.paint(graphicsContext);
            alien.fire();
        }
        //paint bullets list
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(graphicsContext);
        }


        //game over condition
//        if(!self.isAlive()){
//            Director.getInstance().gameOver(false);
//        }else if(enemies.size()==0){
//            Director.getInstance().gameOver(true);
//        }

    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyPressed(keyProcess);
        stage.getScene().setOnKeyReleased(keyProcess);
        //set running true
        running = true;

        //initial player aircraft
        self = new AirCraft(700, 450, Group.PLAYER, Movement.STOP, 0, this);

        //for initial other enemies
        checkAlien();
        refresh.start();
    }

    // initial enemies

    private void checkAlien() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (alien == null || !alien.isAlive()) {
            // check elapsed time since the game started
            if (elapsedTime >= 60000) { // 1 minute in milliseconds
                // create Alien with random starting position
                alien = new Alien(0, 0, 0, this);
            }
        }
    }

    /**
     * clear resource after game
     * @param stage
     */
    public void clear(Stage stage) {
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        stage.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyProcess);
        refresh.stop();
//        self=null;
//        bullets.clear();
//        enemies.clear();
//        explores.clear();
//        boxes.clear();
//        rocks.clear();
//        trees.clear();


    }

    //every frame it will paint whole scene once
    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long l) {
            if (running) {
                // check if the current alien is alive, if not create a new one
                checkAlien();
                // call fire method of alien continuously
                if (alien != null) {
                    alien.fire();
                }
                paint();
            }
        }
    }

    /**
     * For interaction of keyboard
     *   pass keyCode as a parameter to call self.released(keyCode);
     *
     */
    private class KeyProcess implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                if (keyCode.equals(KeyCode.P)) {
                    running = !running;
                }
                if (self!=null) {
                    self.released(keyCode);
                }
            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                if (self!=null) {
                    self.pressed(keyCode);
                }
            }
        }
    }
}