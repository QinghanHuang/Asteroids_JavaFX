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
        super(new Image("image/bullet.png"), x, y, 10, 10, group, Movement.FORWARD, gameScene);
        this.aimDir=aimDir;
        this.speed=speed;
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
        // 判断子弹是否不同组
        //并且 Contour 相交
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

    @Override
    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
        move();
    }

}
