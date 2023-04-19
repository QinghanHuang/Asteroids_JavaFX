package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public class Bullet extends Role {
    private int aimDir;

    public Bullet(double x, double y, double speed, Group group, int aimDir, GameScene gameScene) {
        super(getBulletImage(group), x, y, 10, 10, group, Movement.FORWARD, gameScene);
        this.aimDir = aimDir;
        this.speed = speed;
    }

    private static Image getBulletImage(Group group) {
        if (group == Group.PLAYER) {
            return new Image("image/player_bullet.png");
        } else {
            return new Image("image/enemy_bullet.png");
        }
    }

    @Override
    public void move() {
        //remove this bullet if it goes beyond the scene
        if (x < 0 || y < 0 || x > Director.WIDTH || y > Director.HEIGHT) {
            gameScene.getBullets().remove(this);
        }

        x += speed * Math.sin(Math.toRadians(aimDir % 360));
        y -= speed * Math.cos(Math.toRadians(aimDir % 360));

    }

    public void impactAsteroid(Asteroid asteroid) {
        if (asteroid != null && !asteroid.getGroup().equals(this.getGroup()) && this.getContour().intersects(asteroid.getContour())) {
            asteroid.setAlive(false);
            this.setAlive(false);
            SoundEffect.play("/sound/explosion.wav");
            AsteroidSize size = asteroid.getSize();

            //get score depend on size
            switch (size) {
                case ASTEROID_BIG:
                    gameScene.setScore(gameScene.getScore() + 20);
                    break;
                case ASTEROID_MEDIUM:
                    gameScene.setScore(gameScene.getScore() + 50);
                    break;
                case ASTEROID_SMALL:
                    gameScene.setScore(gameScene.getScore() + 100);
                    break;
            }
        }
    }

    public void impactAsteroids(List<Asteroid> asteroids) {
        for (int i = 0; i < asteroids.size(); i++) {
            this.impactAsteroid(asteroids.get(i));
        }
    }

    public void impactAlien(Alien alien) {
        if (alien != null && !alien.getGroup().equals(this.getGroup()) && this.getContour().intersects(alien.getContour())) {
            alien.setAlive(false);
            this.setAlive(false);
            SoundEffect.play("/sound/explosion.wav");
            gameScene.setScore(gameScene.getScore() + 200);
        }
    }

    // Check for collisions between the bullet and a list of aliens
    public void impactAliens(List<Alien> aliens) {
        for (int i = 0; i < aliens.size(); i++) {
            this.impactAlien(aliens.get(i));
        }
    }

    public void impactPlayer(AirCraft player) {
        if (player != null && !player.isInvincible() && !player.getGroup().equals(this.getGroup()) && this.getContour().intersects(player.getContour())) {
            player.setAlive(false);
            this.setAlive(false);
            SoundEffect.play("/sound/explosion.wav");
            gameScene.checkGameOver();
        }

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!isAlive()) {
            gameScene.getBullets().remove(this);
            gameScene.getExplores().add(new Explore(x, y, gameScene));
            return;
        }
        super.paint(graphicsContext);
        move();
    }

}
