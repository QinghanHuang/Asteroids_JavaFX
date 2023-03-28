package com.java.asteroids.sprite;

import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import javafx.scene.image.Image;

public abstract class Role extends Sprite{
    private boolean alive=true;
    private Group group;

    private double speed;

    public Role(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public abstract void move();
}
