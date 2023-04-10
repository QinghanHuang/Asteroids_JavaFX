package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class AirCraft extends Role {
    int aimDir = 0;
    double rotationSpeed = 30;
    double acceleration = 10.15;
    double deceleration = 0.05;
    double maxSpeed = 20;

    private double hyperspaceProbability = 0.01; // Probability of hyperspace jump (0 to 1)
    private boolean hyperspace = false; //
    boolean keyUp, keyDown, keyLeft, keyRight;

    public AirCraft(double x, double y, Group group, Movement mov, int aimDir, GameScene gameScene) {
        super(new Image("Images/AirCraft.png"), x, y, 100, 120, group, mov, gameScene);
        this.aimDir = aimDir;
        speed = 5;
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
            //for fire
            case SPACE:
                fire();
                break;
        }
        movChange();
        aimChange();
    }


    public void released(KeyCode keyCode) {
        switch (keyCode) {
//            for fire
            case SPACE:
                fire();
                break;

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
            mov = Movement.FORWARD;
            acceleration = 0.2; // Increase acceleration value
        } else if (!keyUp && keyDown) {
            mov = Movement.BACKWARD;
            acceleration = 0.1; // Decrease acceleration value
        } else if (!keyUp && !keyDown) {
            mov = Movement.STOP;
            acceleration = 0.15; // Reset acceleration value
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

        // Draw the fire image behind the aircraft when the thrusters are applied
        if (mov == Movement.FORWARD) {
            Image fireImage = new Image("Images/fire.png");
            double fireX = -width / 2; // Position the fire image centered horizontally behind the aircraft
            double fireY = -height / 2 - 15; // Position the fire image above the bottom of the aircraft
            graphicsContext.drawImage(fireImage, fireX, fireY, width, height); // Draw the fire image with the same size as the aircraft
        }

        graphicsContext.drawImage(image, -width / 2, -height / 2, width, height); // Draw the aircraft
        move();
        graphicsContext.restore();
    }





    @Override
    public void move() {
        double dx = speed * Math.sin(Math.toRadians(aimDir % 360));
        double dy = -speed * Math.cos(Math.toRadians(aimDir % 360));

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
        if (mov == Movement.FORWARD && speed < maxSpeed) {
            speed += acceleration;
        }

        // Implement hyperspace feature
        if (hyperspace && Math.random() < hyperspaceProbability) {
            x = Math.random() * Director.WIDTH; // Teleport to a random x-coordinate
            y = Math.random() * Director.HEIGHT; // Teleport to a random y-coordinate
            speed = 0; // Reset speed to 0 after hyperspace
            hyperspace = false; // Reset hyperspace flag
        }

        switch (mov) {
            case FORWARD:
                x += dx;
                y += dy;
//                fire(); // Call fire() method to fire projectile
                break;
            case BACKWARD:
                x -= dx;
                y -= dy;
                break;
            case STOP:
                dx *= deceleration;
                dy *= deceleration;
                x += dx;
                y += dy;

                // Decrease the speed with deceleration
                if (speed > 0) {
                    speed -= deceleration;
                } else {
                    speed = 0;
                }
                break;
            default:
                break;
        }
    }

    public void fire() {
        Bullet bullet = new Bullet(x, y, speed+10,getGroup(), aimDir, gameScene);
        gameScene.getBullets().add(bullet);

    }


}
