package com.java.asteroids.sprite;

import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public class Alien extends Role {
    private int aimDir = 0;
    private Random random;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public Alien(double x, double y, int aimDir, GameScene gameScene) {

        super(new Image("image/alien.png"), x, y, 60, 60, Group.ENEMY, Movement.FORWARD, gameScene);
        this.speed = 2;
        random = new Random();
        this.minX = 0;
        this.maxX = 1440;
        this.minY = 0;
        this.maxY = 960;

        // generate random initial position outside the screen
        double newX;
        double newY;
        int randSeed = random.nextInt(4); // generate a random number between 0 and 3

        if (randSeed == 0) { // top
            newX = random.nextDouble() * (maxX - minX) + minX;
            newY = minY;
        } else if (randSeed == 1) { // right
            newX = maxX;
            newY = random.nextDouble() * (maxY - minY) + minY;
        } else if (randSeed == 2) { // bottom
            newX = random.nextDouble() * (maxX - minX) + minX;
            newY = maxY;
        } else { // left
            newX = minX;
            newY = random.nextDouble() * (maxY - minY) + minY;
        }

        this.x = newX;
        this.y = newY;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!isAlive()) {
            gameScene.getAliens().remove(this);
            return;
        }
        super.paint(graphicsContext);
        move();
    }

    @Override
    public void move() {
        int i = random.nextInt(50);
        if(i==1){

            aimDir += random.nextDouble() * 60 - 10; // change direction by up to 10 degrees in either direction
        }
        double newX = x + speed * Math.sin(Math.toRadians(aimDir));
        double newY = y - speed * Math.cos(Math.toRadians(aimDir));

        // Check if the new position is out of bounds
        if (newX < minX) {
            newX = maxX;
        } else if (newX > maxX) {
            newX = minX;
        }
        if (newY < minY) {
            newY = maxY;
        } else if (newY > maxY) {
            newY = minY;
        }

        // Update the position of the alien
        x = newX;
        y = newY;
    }

    // Shoot a bullet
    public void fire() {
        int i = random.nextInt(150);
        if (i == 1) {
            // create a new bullet and add it to the game scene
            Bullet bullet = new Bullet(x, y, 5, getGroup(), aimDir, gameScene);
            SoundEffect.play("/sound/alien_fire.wav");
            gameScene.getBullets().add(bullet);
        }

    }


    public boolean impactAsteroid(Asteroid asteroid) {
        if (asteroid != null && !asteroid.getGroup().equals(this.getGroup()) && this.getContour().intersects(asteroid.getContour())) {
            asteroid.setAlive(false);
            this.setAlive(false);
            return true;
        }
        return false;
    }

    public void impactAsteroid(List<Asteroid> asteroids) {
        for (Asteroid asteroid : asteroids) {
            this.impactAsteroid(asteroid);
        }
    }

    public void impactAirCraft(AirCraft airCraft) {

        if (airCraft != null &&!airCraft.isInvincible()&& this.getContour().intersects(airCraft.getContour())) {
            airCraft.setAlive(false);
            SoundEffect.play("/sound/explosion.wav");
            gameScene.getExplores().add(new Explode(x,y,gameScene));
            this.setAlive(false);
            gameScene.checkGameOver();
        }
    }
}







