package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import javafx.scene.image.Image;

public class BackGround extends Sprite{
    public BackGround() {
        super(new Image("image/game_bg.png"), 0, 0, Director.WIDTH, Director.HEIGHT);
    }
}
