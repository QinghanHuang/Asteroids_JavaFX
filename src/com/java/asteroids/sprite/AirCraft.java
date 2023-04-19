package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;


public class AirCraft extends Role {
    private int aimDir = 0;
    private double rotationSpeed = 30;
    private double acceleration = 0.05;
    private double speedX = 0;
    private double speedY = 0;
    private double maxSpeed = 3;

    // Add a new property for invincibility
    private boolean invincible;
    // Add a new property for visibility
    private boolean visible = true;
    // store hyperspace count
    private int hyperLeft = 3;

    private boolean keyUp, keyLeft, keyRight;

    public boolean isInvincible() {
        return invincible;
    }

    public int getHyperLeft() {
        return hyperLeft;
    }

    // Add a setter for the invincible property
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    // Add a getter for the visible property
    public boolean isVisible() {
        return visible;
    }

    // Add a setter for the visible property
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getSpeedX() {
        return speedX;
    }

    @Override
    public double getSpeed() {
        return Math.sqrt(speedX * speedX + speedY * speedY);
    }

    public AirCraft(double x, double y, Group group, Movement mov, int aimDir, GameScene gameScene) {
        super(new Image("image/aircraft_new.png"), x, y, 40, 60, group, mov, gameScene);
        this.aimDir = aimDir;
        speed = 0;
    }

    public void pressed(KeyCode keyCode) {
        switch (keyCode) {
            //for movement
            case UP:
                keyUp = true;
                break;
            case LEFT:
                keyLeft = true;
                break;
            case RIGHT:
                keyRight = true;
                break;
        }
        movChange();
        aimChange();
    }


    public void released(KeyCode keyCode) {
        switch (keyCode) {
            //for fire
            case SPACE:
                fire();
                break;
            // for hyperspace
            case SHIFT:
                hyperspace();
                break;
            //for movement
            case UP:
                keyUp = false;
                break;
            case LEFT:
                keyLeft = false;
                break;
            case RIGHT:
                keyRight = false;
                break;
        }
        movChange();
        aimChange();
    }

    public void movChange() {
        if (keyUp) {
            setMov(Movement.TRUST);
        } else if (!keyUp) {
            setMov(Movement.FLOAT);
        }
    }

    public void aimChange() {
        if (keyLeft) {
            aimDir -= rotationSpeed;
        } else if (keyRight) {
            aimDir += rotationSpeed;
        }
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!isVisible()) {
            return;
        }
        graphicsContext.save();
        graphicsContext.translate(x, y);
        graphicsContext.rotate(aimDir);

        if (getMov() == Movement.TRUST) {
            graphicsContext.drawImage(new Image("image/aircraft_fire_new.png"), -width / 2, -height / 2, width, height);
        }

        graphicsContext.drawImage(getImage(), -width / 2, -height / 2, width, height); // Draw the aircraft
        move();
        graphicsContext.restore();
    }

    public void blinkAndSetInvincibility(Duration duration, int blinkCount) {
        // Set invincibility
        setInvincible(true);

        // Create a Timeline for blinking
        Timeline blinkTimeline = new Timeline();
        blinkTimeline.setCycleCount(blinkCount * 2);

        // Create KeyFrames for the Timeline
        KeyFrame blinkToggleKeyFrame = new KeyFrame(Duration.millis(100), e -> setVisible(!isVisible()));

        // Add the KeyFrames to the Timeline
        blinkTimeline.getKeyFrames().addAll(blinkToggleKeyFrame);

        // Start the blinking animation
        blinkTimeline.play();

        // Set up another Timeline to disable invincibility after the specified duration
        Timeline invincibilityTimeline = new Timeline(new KeyFrame(duration, e -> {
            setInvincible(false);
            setVisible(true);
        }));

        // Start the invincibility Timeline
        invincibilityTimeline.play();
    }

    private void hyperspace() {
        if (hyperLeft > 0) {
            SoundEffect.play("/sound/hyperspace.wav");
            Random random = new Random();
            x = random.nextInt((int) Director.WIDTH);
            y = random.nextInt((int) Director.HEIGHT);
            speedX=0;
            speedY=0;
            blinkAndSetInvincibility(Duration.seconds(3), 5);
            hyperLeft--;
        }

    }

    @Override
    public void move() {

        // Check if the aircraft has gone off the screen
        if (x < 0) {
            x = Director.WIDTH; // Wrap around to the other side
        } else if (x > Director.WIDTH) {
            x = 0; // Wrap around to the other side
        }
        if (y < 0) {
            y = Director.HEIGHT; // Wrap around to the other side
        } else if (y > Director.HEIGHT) {
            y = 0; // Wrap around to the other side
        }

        // Increase the speed with acceleration
        if (getMov() == Movement.TRUST) {
            speedX += acceleration * Math.sin(Math.toRadians(aimDir % 360));
            speedY -= acceleration * Math.cos(Math.toRadians(aimDir % 360));
            if (speedX > maxSpeed) speedX = maxSpeed;
            if (speedX < -maxSpeed) speedX = -maxSpeed;
            if (speedY > maxSpeed) speedY = maxSpeed;
            if (speedY < -maxSpeed) speedY = -maxSpeed;

        }
        x += speedX;
        y += speedY;

    }

    public void fire() {
//        System.out.println(speed+ " Speed of Fire");
        Bullet bullet = new Bullet(x, y, 10 + getSpeed(), getGroup(), aimDir, gameScene);
        SoundEffect.play("/sound/attack.mp3");
        gameScene.getBullets().add(bullet);

    }
}
