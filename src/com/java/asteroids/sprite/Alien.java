package com.java.asteroids.sprite;
import com.java.asteroids.sprite.Bullet;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;

public class Alien extends Role {
    int aimDir = 0;
    private long lastShotTime = 0;
    private Random random;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public Alien(double x, double y, int aimDir, GameScene gameScene) {

        super(new Image("image/alien.png"), x, y, 60, 60, Group.ENEMY, Movement.FORWARD, gameScene);
        this.speed = 1;
        random = new Random();
        this.minX = 0;
        this.maxX = 1440;
        this.minY = 0;
        this.maxY = 960;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
        move();
    }

    @Override
    public void move() {
        aimDir += random.nextDouble() * 20 - 10; // change direction by up to 10 degrees in either direction
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
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime < 500) { // only shoot once per half second
            return; //the method simply returns without shooting
        }
        //Otherwise, the method creates a new bullet and adds it to the game scene, and updates the lastShotTime variable.
        lastShotTime = currentTime;
        // create a new bullet and add it to the game scene
        Bullet bullet = new Bullet(x, y, getGroup(), aimDir, gameScene);
        gameScene.getBullets().add(bullet);
    }

}









