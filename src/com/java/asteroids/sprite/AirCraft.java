package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.List;

public class AirCraft extends Role {
    int aimDir = 0;
    double rotationSpeed = 30;
    double acceleration = 0.05;
    private double speedX=0;
    private double speedY=0;
    double maxSpeed = 3;

    private double hyperspaceProbability = 0.01; // Probability of hyperspace jump (0 to 1)
    private boolean hyperspace = false; //
    boolean keyUp, keyDown, keyLeft, keyRight;

    public double getSpeedY() {
        return speedY;
    }

    public double getSpeedX() {
        return speedX;
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
            case DOWN:
                keyDown = true;
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
            case DOWN:
                keyDown = false;
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
        if (keyUp && !keyDown) {
            mov = Movement.TRUST;
        } else if (!keyUp && !keyDown) {
            mov = Movement.FLOAT;
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
        graphicsContext.save();
        graphicsContext.translate(x, y);
        graphicsContext.rotate(aimDir);

        if (mov == Movement.TRUST) {
            graphicsContext.drawImage(new Image("image/aircraft_fire_new.png"), -width / 2, -height / 2, width, height);
        }

        graphicsContext.drawImage(image, -width / 2, -height / 2, width, height); // Draw the aircraft
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
        if (mov == Movement.TRUST ) {
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
        Bullet bullet = new Bullet(x, y, 5, getGroup(), aimDir, gameScene);
        gameScene.getBullets().add(bullet);

    }


}
