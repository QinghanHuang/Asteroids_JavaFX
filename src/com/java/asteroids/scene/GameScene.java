package com.java.asteroids.scene;

import com.java.asteroids.*;
import com.java.asteroids.sprite.*;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameScene {
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    private KeyProcess keyProcess = new KeyProcess();

    private Refresh refresh = new Refresh();

    //running false means pause
    private boolean running = false;

    private BackGround background = new BackGround();

    //store player in gameScene
    private AirCraft self = null;
    // Add a list to store multiple aliens
    private List<Alien> aliens = new ArrayList<>();

    private long startTime = System.currentTimeMillis();

    //store bullet list
    private List<Bullet> bullets = new ArrayList<>();

    //store lives
    private List<ShowLives> showLives = new ArrayList<>();

    //store asteroids
    private List<Asteroid> asteroids=new ArrayList<>();
    public List<Asteroid> getAsteroids() {
        return asteroids;
    }
//    private List<Asteroid> asteroids=new ArrayList<>();

    //to store score
    //need to show in gameScene
    private int score = 100;

    //to store level
    private int level = 1;

    //to store lives
    private int lives = 3;
    // Set a score threshold for each level increase
    private final int SCORE_PER_LEVEL = 100;
    // Method to update the level based on the score
    private void updateLevel() {
        level = score / SCORE_PER_LEVEL + 1;
    }
    // Call this method whenever the player's score is updated
    public void addScore(int points) {
        score += points;
        updateLevel();
    }


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

        //paint showLives
        for (int i = 0; i < lives; i++) {
            showLives.get(i).paint(graphicsContext);
        }
        //paint player in gameScene
        self.paint(graphicsContext);
        self.impact(asteroids);
        long startTime = System.nanoTime();
        for(int i = 0; i< asteroids.size(); i++){
            Asteroid asteroid = asteroids.get(i);
            asteroid.paint(graphicsContext);
            asteroid.impactAirCraft(self);
//            asteroid.impactAlien(alien);

        }
        for (int i = 0; i <bullets.size() ; i++) {
            Bullet bullet=bullets.get(i);
            bullet.paint(graphicsContext);
            bullet.impactAsteroid(asteroids);
            bullet.impactAlien(aliens);
        }
        //paint alien
//        self.impact(alien);
        // check if the current alien is alive, if not create a new one
        checkAlien();
        // Paint and manage all aliens in the aliens list
        if (aliens != null) {
            for (Alien alien : aliens) {
                if (alien.isAlive()) {
                    alien.paint(graphicsContext);
                    alien.fire();
                }
            }
        }
        //paint bullets list
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(graphicsContext);
        }
        //this part show score and lives
        graphicsContext.setFont(new Font(30));
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("Score: " + score, 20, 30);
        graphicsContext.fillText("LEVEL: " + level, 650, 30);

        //game over condition
//        if(!self.isAlive()){
//            Director.getInstance().gameOver(false);
//        }else if(enemies.size()==0){
//            Director.getInstance().gameOver(true);
//        }

    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        initButton(root);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyPressed(keyProcess);
        stage.getScene().setOnKeyReleased(keyProcess);
        //set running true
        running = true;

        //initial player aircraft
        self = new AirCraft(700, 450, Group.PLAYER, Movement.STOP, 0, this);
//
        //for initial other enemies
        initSprite();
        refresh.start();
    }

    // initial enemies
    private void initSprite(){
        initAsteroids();
        for (int i = 0; i < 3; i++) {
            showLives.add(new ShowLives(20 + i * 25, 50));
            aliens.clear();
            checkAlien();
        }
    }
    private  void initAsteroids(){
        System.out.println("Initialised");
//        for (int i = 0; i < 6; i++) {
        Random random = new Random();
        String[] aestroid_start_position = {"Top", "Right", "Bottom", "Left"};
        int x = 0;
        int y = 0;
        int position = random.nextInt(4);
        System.out.println(position);

        int aimDir = 0;
        if (position == 0){
            y = (int) Director.HEIGHT + 90;
            x =  random.nextInt((int) Director.WIDTH);
        } else if (position == 1) {
            y = random.nextInt((int) Director.HEIGHT);
            x =  (int) Director.WIDTH + 90;
        } else if (position == 2) {
            y = -90;
            x = random.nextInt((int) Director.WIDTH );
        } else if (position == 3) {
            x = -90;
            y = random.nextInt((int) Director.HEIGHT);
        }

        System.out.println(y +  "Y direction");
        System.out.println(x + " X Direction");
        Asteroid asteroid=new Asteroid(new Image("image/asteroid_big.png"), x, y, 120, 120, Group.ASTEROID_BIG, Movement.FORWARD, this, position);

        asteroids.add(asteroid);
    }


    private void initButton(AnchorPane root) {
        //Creat two button
        //May use image later
        Button backToIndex = new Button("Back");
        Button gameOver = new Button("Over");

        //set layout
        backToIndex.setLayoutX(1350);
        backToIndex.setLayoutY(30);
        gameOver.setLayoutX(1350);
        gameOver.setLayoutY(60);

        //set MouseClick actions
        backToIndex.setOnMouseClicked(event -> {
            Director.getInstance().toIndex();
        });

        gameOver.setOnMouseClicked(event -> {
            Director.getInstance().gameOver(score);
        });

        //Don't let button get the focus
        backToIndex.setFocusTraversable(false);
        gameOver.setFocusTraversable(false);
        root.getChildren().addAll(backToIndex, gameOver);
    }

    private void checkAlien() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (aliens.size() < level) {
            if (elapsedTime >= 3000) { // 30min
                // create Alien with random starting position
                Alien alien = new Alien(0, 0, 0, this);
                aliens.add(alien);
                startTime = System.currentTimeMillis(); // reset the timer
            }
        }
    }
    public void splitAsteroid(Group group, double x, double y, int pos){
        System.out.println(group);
        if (group == Group.ASTEROID_BIG){
            for (int i = 0; i <= 1; i++){
                Asteroid asteroid=new Asteroid(new Image("image/asteroid_big.png"), x, y, 90, 90, Group.ASTEROID_MEDIUM, Movement.FORWARD, this, pos);
                this.getAsteroids().add(asteroid);
            }

        }else if (group == Group.ASTEROID_MEDIUM){
            for (int i = 0; i <= 3; i++){
                Asteroid asteroid=new Asteroid(new Image("image/asteroid_big.png"), x, y, 60, 60, Group.ASTEROID_SMALL, Movement.FORWARD, this, pos);
                this.getAsteroids().add(asteroid);
            }

        }else{
            if (asteroids.size() == 0){
                level+=1;
                for(int i = 0; i <= level-1; i++){
                    initAsteroids();
                }
            }

        }
    }

    /**
     * clear resource after game
     *
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
                paint();
            }
        }
    }

    /**
     * For interaction of keyboard
     * pass keyCode as a parameter to call self.released(keyCode);
     */
    private class KeyProcess implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                if (keyCode.equals(KeyCode.P)) {
                    running = !running;
                }
                if (self != null) {
                    self.released(keyCode);
                }
            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                if (self != null) {
                    self.pressed(keyCode);
                }
            }
        }
    }
}