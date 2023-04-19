package com.java.asteroids.scene;

import com.java.asteroids.*;
import com.java.asteroids.sprite.*;
import com.java.asteroids.util.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.util.Duration;



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


    //store bullet list
    private List<Bullet> bullets = new ArrayList<>();

    //store lives
    private List<ShowLives> showLives = new ArrayList<>();

    //store asteroids
    private List<Asteroid> asteroids = new ArrayList<>();

    //store asteroids
    private List<Explore> explores = new ArrayList<>();


    //to store score
    //need to show in gameScene
    private int score;

    //to store level
    private int level;
    private final int maxLevel = 10;

    //to store lives
    private int lives;

    // to store extra lives from points
    private int extraLives=0;


    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public int getScore() {
        return score;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public List<Explore> getExplores() {
        return explores;
    }


    /**
     * paint of gameScene
     * will call paint of sprite
     */
    private void paint() {
        checkLevel();
        checkExtraLives();
        //paint background in gameScene
        background.paint(graphicsContext);

        //paint showLives
        for (int i = 0; i < lives; i++) {
            showLives.get(i).paint(graphicsContext);
        }

        //paint player in gameScene
        if (self.isAlive()) {
            self.paint(graphicsContext);
        }
//        self.impact(asteroids);

        //paint asteroids in gameScene
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            asteroid.paint(graphicsContext);
            asteroid.impactAirCraft(self);
            //asteroid.impactAlien(alien);

        }
        ////paint bullets in gameScene
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.paint(graphicsContext);
            bullet.impactAsteroids(asteroids);
            bullet.impactAliens(aliens);
            bullet.impactPlayer(self);

        }
        //paint aliens in gameScene
        for (int i = 0; i < aliens.size(); i++) {
            Alien alien = aliens.get(i);
            alien.impactAirCraft(self);
            alien.paint(graphicsContext);
            alien.fire();
        }


        //paint explores list
        for (int i = 0; i < explores.size(); i++) {
            explores.get(i).paint(graphicsContext);
        }


        //this part show score and lives
        graphicsContext.setFont(new Font(30));
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("Score: " + score, 20, 30);
        graphicsContext.fillText("LEVEL: " + level, 650, 30);
        if (self != null) {
            graphicsContext.fillText("HSJ Left: " + self.getHyperLeft(), 20, 900);
        }

        //
//        graphicsContext.fillText("alien: " + aliens.size(), 20, 60);
//        graphicsContext.fillText("starts: " + asteroids.size(), 20, 90);
//        graphicsContext.fillText("bullets: " + bullets.size(), 20, 120);
//        graphicsContext.fillText("lives: " + lives, 20, 150);
//        if(self!=null){
//            graphicsContext.fillText("speedX: " + self.getSpeedX(), 20, 180);
//            graphicsContext.fillText("speed: " + self.getSpeed(), 20, 210);
//
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

        //initial level and lives
        score = 0;
        level = 1;
        lives = 3;

        //initial player aircraft
        initPlayer();

        //for initial other enemies
        initSprite();
        refresh.start();
    }

    public void initPlayer() {
        self = new AirCraft(700, 450, Group.PLAYER, Movement.FLOAT, 0, this);
        // Call the blinkAndSetInvincibility method with a duration of 3 seconds and 6 blink cycles
        self.blinkAndSetInvincibility(Duration.seconds(3), 6);
    }

    // initial enemies
    private void initSprite() {
        // initial asteroids
        initAsteroids();

        // initial aliens
        aliens.clear();
        initAlien();

        //initial show lives
        //set max lives for user is 5
        for (int i = 0; i < 5; i++) {
            showLives.add(new ShowLives(20 + i * 25, 50));
        }
    }


    private void initAsteroids() {
        Random random = new Random();
        int x = 0;
        int y = 0;
        int position = random.nextInt(4);

        int aimDir = 0;
        if (position == 0) {
            y = (int) Director.HEIGHT + 90;
            x = random.nextInt((int) Director.WIDTH);
        } else if (position == 1) {
            y = random.nextInt((int) Director.HEIGHT);
            x = (int) Director.WIDTH + 90;
        } else if (position == 2) {
            y = -90;
            x = random.nextInt((int) Director.WIDTH);
        } else if (position == 3) {
            x = -90;
            y = random.nextInt((int) Director.HEIGHT);
        }

        Asteroid asteroid = new Asteroid(new Image("image/asteroid_big.png"), x, y, 120, 120, Group.ENEMY, Movement.FORWARD, this, position, AsteroidSize.ASTEROID_BIG);

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
            running = false;
            SoundEffect.stopBGM();
            Director.getInstance().toIndex();
        });

        gameOver.setOnMouseClicked(event -> {
            SoundEffect.stopBGM();
            Director.getInstance().gameOver(score);
        });

        //Don't let button get the focus
        backToIndex.setFocusTraversable(false);
        gameOver.setFocusTraversable(false);
        root.getChildren().addAll(backToIndex, gameOver);
    }

    private void initAlien() {
        for (int i = 0; i < level; i++) {
            Alien alien = new Alien(0, 0, 0, this);
            aliens.add(alien);
        }
    }

    private void checkLevel() {
        if (asteroids.size() == 0 && aliens.size() == 0) {
            if (level < maxLevel) {
                level += 1;
            }
            for (int i = 0; i <= level - 1; i++) {
                initAsteroids();
            }
            initAlien();
        }
    }
    private void checkExtraLives() {
        if (score >= (extraLives + 1) * 10000) {
            extraLives++;
            lives++;
            System.out.println(lives);
        }
    }


    public void checkGameOver() {
        if (lives == 1) {
            Director.getInstance().gameOver(score);
        } else {
            lives--;
            initPlayer();
        }
    }

    public void splitAsteroid(AsteroidSize size, double x, double y, int pos) {
        if (size == AsteroidSize.ASTEROID_BIG) {
            for (int i = 0; i < 2; i++) {
                Asteroid asteroid = new Asteroid(new Image("image/asteroid_big.png"), x, y, 90, 90, Group.ENEMY, Movement.FORWARD, this, pos, AsteroidSize.ASTEROID_MEDIUM);
                this.getAsteroids().add(asteroid);
            }

        } else if (size == AsteroidSize.ASTEROID_MEDIUM) {
            for (int i = 0; i < 2; i++) {
                Asteroid asteroid = new Asteroid(new Image("image/asteroid_big.png"), x, y, 60, 60, Group.ENEMY, Movement.FORWARD, this, pos, AsteroidSize.ASTEROID_SMALL);
                this.getAsteroids().add(asteroid);
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
        self = null;
        aliens.clear();
        showLives.clear();
        asteroids.clear();
        bullets.clear();
        explores.clear();


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