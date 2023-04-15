package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public class Bullet  extends Role{
    int aimDir;
    public Bullet( double x, double y,double speed, Group group, int aimDir, GameScene gameScene) {
        super(getBulletImage(group), x, y, 10, 10, group, Movement.FORWARD, gameScene);
        this.aimDir=aimDir;
        this.speed=speed;
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
        if (x<0||y<0||x>Director.WIDTH||y>Director.HEIGHT) {
            gameScene.getBullets().remove(this);
        }

        x+=speed*Math.sin(Math.toRadians(aimDir%360));
        y-=speed*Math.cos(Math.toRadians(aimDir%360));

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
        for(Asteroid asteroid:asteroids){
            this.impactAsteroid(asteroid);
        }
    }

    public boolean impactAlien(Alien alien) {
        if (alien != null && !alien.getGroup().equals(this.getGroup()) && this.getContour().intersects(alien.getContour())) {
            alien.setAlive(false);
            this.setAlive(false);
            return true;
        }
        return false;
    }

    // Check for collisions between the bullet and a list of aliens
    public void impactAlien(List<Alien> aliens) {
        for (Alien alien : aliens) {
            this.impactAlien(alien);
        }
    }
    
    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!isAlive()) {
            gameScene.getBullets().remove(this);
            return;
        }
        super.paint(graphicsContext);
        move();
    }

}
