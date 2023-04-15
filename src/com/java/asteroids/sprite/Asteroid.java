package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public class Asteroid extends Role {

    private int aimDir = 0;

    private int rotation = 0;
    private AsteroidSize size;

    private int position;
    public static Random random = new Random();

    public Asteroid(Image image, double x, double y, double width, double height, Group group, Movement mov, GameScene gameScene, int position, AsteroidSize size) {
        super(image, x, y, width, height, group, mov, gameScene);
        this.size = size;
        speed = getSpeed(size);
        this.position = position;
        this.aimDir = random.nextInt(360);
        rotate(x, y);
    }

    private int getSpeed(AsteroidSize size) {
        if (size == AsteroidSize.ASTEROID_BIG) {
            return 1;
        } else if (size == AsteroidSize.ASTEROID_MEDIUM) {
            return 2;
        } else {
            return 3;
        }
    }

    public AsteroidSize getSize() {
        return size;
    }

    private void rotate(double x, double y) {
        if (x > Director.WIDTH) {
            rotation = -90;
        }
//        if(x < 0){
//
//        }
    }

    @Override
    public void move() {

        switch (position) {
            case 0:
                x += speed * Math.sin(Math.toRadians(aimDir % 360));
                y -= speed * Math.cos(Math.toRadians(aimDir % 360));
                break;
            case 1:
                x -= speed * Math.sin(Math.toRadians(aimDir % 360));
                y -= speed * Math.cos(Math.toRadians(aimDir % 360));
                break;
            case 2:
                x += speed * Math.sin(Math.toRadians(aimDir % 360));
                y += speed * Math.cos(Math.toRadians(aimDir % 360));
                break;
            case 3:
                x -= speed * Math.sin(Math.toRadians(aimDir % 360));
                y += speed * Math.cos(Math.toRadians(aimDir % 360));
                break;
        }

        if (x < 0) x = Director.WIDTH;
        if (y < 0) y = Director.HEIGHT;
        if (x > Director.WIDTH) x = 0;
        if (y > Director.HEIGHT) y = 0;
    }

    public void impactAirCraft(AirCraft airCraft) {
        if (airCraft != null && this.getContour().intersects(airCraft.getContour())) {
            airCraft.setAlive(false);
            gameScene.getExplores().add(new Explore(x, y, gameScene));
            SoundEffect.play("/sound/explosion.wav");
            this.setAlive(false);
            gameScene.checkGameOver();
        }
    }


    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!isAlive()) {
            gameScene.getAsteroids().remove(this);
            gameScene.splitAsteroid(size, x, y, position);
            return;
        }
        super.paint(graphicsContext);
        move();
    }
}

