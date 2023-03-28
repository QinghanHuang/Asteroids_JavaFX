package com.java.asteroids.sprite;

import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.scene.image.Image;

public class Asteroid extends Role{


    public Asteroid(Image image, double x, double y, double width, double height, Group group, Movement mov, GameScene gameScene) {
        super(image, x, y, width, height, group, mov, gameScene);
    }

    @Override
    public void move() {

    }
}
