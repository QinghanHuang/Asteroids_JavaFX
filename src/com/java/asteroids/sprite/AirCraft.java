package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


import java.util.List;

public class AirCraft extends Role {
    private int aimDir = 0;
    private double rotationSpeed = 30;
    private double acceleration = 0.05;
    private double speedX=0;
    private double speedY=0;
    private double maxSpeed = 3;

    private double hyperspaceProbability = 0.01; // Probability of hyperspace jump (0 to 1)
    private boolean hyperspace = false; //
    private boolean keyUp,keyLeft, keyRight;

    public double getSpeedY() {
        return speedY;
    }

    public double getSpeedX() {
        return speedX;
    }

    @Override
    public double getSpeed() {
        return Math.sqrt(speedX*speedX+speedY*speedY);
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
        } else if (!keyUp ) {
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
        if (getMov() == Movement.TRUST ) {
                speedX += acceleration * Math.sin(Math.toRadians(aimDir % 360));
                speedY -= acceleration * Math.cos(Math.toRadians(aimDir % 360));
                if (speedX>maxSpeed) speedX=maxSpeed;
                if (speedX<-maxSpeed) speedX=-maxSpeed;
                if (speedY>maxSpeed) speedY=maxSpeed;
                if (speedY<-maxSpeed) speedY=-maxSpeed;

            }
        x += speedX;
        y += speedY;
        // Implement hyperspace feature
//        if (hyperspace && Math.random() < hyperspaceProbability) {
//            x = Math.random() * Director.WIDTH; // Teleport to a random x-coordinate
//            y = Math.random() * Director.HEIGHT; // Teleport to a random y-coordinate
//            speed = 0; // Reset speed to 0 after hyperspace
//            hyperspace = false; // Reset hyperspace flag
//        }
    }
    public void fire() {
//        System.out.println(speed+ " Speed of Fire");
        Bullet bullet = new Bullet(x, y, 10+getSpeed(), getGroup(), aimDir, gameScene);
        SoundEffect.play("/sound/attack.mp3");
        gameScene.getBullets().add(bullet);

    }

    // Add a new property for invincibility
    private boolean invincible;

    // Add a getter for the invincible property
    public boolean isInvincible() {
        return invincible;
    }

    // Add a setter for the invincible property
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    // Add a new property for visibility
    private boolean visible = true;

    // Add a getter for the visible property
    public boolean isVisible() {
        return visible;
    }

    // Add a setter for the visible property
    public void setVisible(boolean visible) {
        this.visible = visible;
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



}
