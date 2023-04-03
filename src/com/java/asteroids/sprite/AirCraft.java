package com.java.asteroids.sprite;

import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class AirCraft extends Role {
    int aimDir = 0;
    double rotationSpeed = 30;
    boolean keyUp, keyDown, keyLeft, keyRight;

    public AirCraft(double x, double y, Group group, Movement mov, int aimDir, GameScene gameScene) {
        super(new Image("image/aircraft.png"), x, y, 40, 60, group, mov, gameScene);
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
        }
        movChange();
        aimChange();
    }

    public void released(KeyCode keyCode) {
        switch (keyCode) {
            //for fire
//            case SPACE:
//                fire();
//                break;

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
        if (keyUp && !keyDown) mov = Movement.FORWARD;
        else if (!keyUp && keyDown) mov = Movement.BACKWARD;
        else if (!keyUp && !keyDown) mov = Movement.STOP;
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
        graphicsContext.drawImage(image,-20,-30,width,height);
        move();
        graphicsContext.restore();
    }

    @Override
    public void move() {


        switch (mov) {
            case FORWARD:
                x+=speed*Math.sin(Math.toRadians(aimDir%360));
                y-=speed*Math.cos(Math.toRadians(aimDir%360));
                break;
            case BACKWARD:
                x-=speed*Math.sin(Math.toRadians(aimDir%360));
                y+=speed*Math.cos(Math.toRadians(aimDir%360));
                break;
        }
    }

    public void fire() {

    }


}
