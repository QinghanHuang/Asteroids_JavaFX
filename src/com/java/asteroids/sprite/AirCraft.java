package com.java.asteroids.sprite;

import com.java.asteroids.scene.GameScene;
import javafx.scene.image.Image;

public class AirCraft extends Role{

    public AirCraft(Image image, double x, double y, double width, double height, GameScene gameScene) {
        super(image, x, y, width, height, gameScene);
    }

    @Override
    public void move() {

    }
}
