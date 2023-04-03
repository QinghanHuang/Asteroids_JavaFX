package com.java.asteroids.sprite;

import java.awt.geom.Area;
import java.awt.Polygon;
import java.awt.Shape;

import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Movement;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien extends Role {
    int aimDir = 0;

    //    double dx = 0;
//    double dy = 0;
    double cx = Math.random() * width, cy = Math.random() * height;
    double[] xArr = {-25, -20, -15, -15, 15, 15, 20, 25, 20, -20};
    double[] yArr = {0, -15, -15, -25, -25, -15, -15, 0, 15, 15};

    public Alien(double x, double y, int aimDir, GameScene gameScene) {

        super(new Image("image/alien.png"), x, y, 60, 60, com.java.asteroids.util.Group.ENEMY, Movement.FORWARD, gameScene);
        this.speed=1;
    }

//        this.aimDir = aimDir;
//        speed = 5;
//        double a = Math.random();
//        dx = speed + Math.random();
//        dy = speed + Math.random();
//        if (a < 0.25) {
//            cx = 0;
//        } else if (a >= 0.25 && a < 0.5) {
//            cy = 0;
//        } else if (a >= 0.5 && a < 0.75) {
//            cx = 0;
//            cy = -cy;
//        } else if (a >= 0.75) {
//            cy = 0;
//            cx = -cx;
//        }
//        if (Math.random() <= 0.5) {
//            dx = -dx;
//        }
//        if (Math.random() <= 0.5) {
//            dy = -dy;
//        }
//        for (int i = 0; i < 10; i++) {
//            xArr[i] += cx;
//            yArr[i] += cy;
//        }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
        move();


//        graphicsContext.save();
//        graphicsContext.translate(x, y);
//        graphicsContext.drawImage(new Image("image/alien.png"), -20, -30, width, height);
//        move();
//        graphicsContext.restore();
//        int[] x1 = {(int) xArr[0], (int) xArr[1], (int) xArr[2], (int) xArr[3], (int) xArr[4], (int) xArr[5], (int) xArr[6], (int) xArr[7], (int) xArr[8], (int) xArr[9]};
//        int[] y1 = {(int) yArr[0], (int) yArr[1], (int) yArr[2], (int) yArr[3], (int) yArr[4], (int) yArr[5], (int) yArr[6], (int) yArr[7], (int) yArr[8], (int) yArr[9]};
    }


    public void move() {
                x+=speed*Math.sin(Math.toRadians(aimDir%360));
                y-=speed*Math.cos(Math.toRadians(aimDir%360));

        }

//        for (int i = 0; i < 10; i++) {
//            xArr[i] += dx;
//            yArr[i] += dy;
//        }
//        cx += dx;
//        cy += dy;
//        if (cx < 0) cx += width;
//        else if (cx > width) cx -= width;
//        if (cy < 0) cy += height;
//        else if (cy > height) cy -= height;
//        newAlien();

    public void newAlien() {
        if (yArr[0] < 0 && yArr[1] < 0 && yArr[2] < 0 && yArr[3] < 0 && yArr[4] < 0 && yArr[5] < 0 && yArr[6] < 0 && yArr[7] < 0 && yArr[8] < 0 && yArr[9] < 0) {
            for (int i = 0; i < 10; i++) {
                yArr[i] += height;
            }
        } else if (yArr[0] > height && yArr[1] > height && yArr[2] > height && yArr[3] > height && yArr[4] > height && yArr[5] > height && yArr[6] > height && yArr[7] > height && yArr[8] > height && yArr[9] > height) {
            for (int i = 0; i < 10; i++) {
                yArr[i] -= height;
            }
        }
        if (xArr[0] < 0 && xArr[1] < 0 && xArr[2] < 0 && xArr[3] < 0 && xArr[4] < 0 && xArr[5] < 0 && xArr[6] < 0 && xArr[7] < 0 && xArr[8] < 0 && xArr[9] < 0) {
            for (int i = 0; i < 10; i++) {
                xArr[i] += width;
            }
        } else if (xArr[0] > width && xArr[1] > width && xArr[2] > width && xArr[3] > width && xArr[4] > width && xArr[5] > width && xArr[6] > width && xArr[7] > width && xArr[8] > width && xArr[9] > width) {
            for (int i = 0; i < 10; i++) {
                xArr[i] -= width;
            }
        }
    }

    public boolean hit(Shape a) {
        Polygon p = new Polygon(new int[]{(int) xArr[0], (int) xArr[1], (int) xArr[2], (int) xArr[3], (int) xArr[4], (int) xArr[5], (int) xArr[6], (int) xArr[7], (int) xArr[8], (int) xArr[9]},
                new int[]{(int) yArr[0], (int) yArr[1], (int) yArr[2], (int) yArr[3], (int) yArr[4], (int) yArr[5], (int) yArr[6], (int) yArr[7], (int) yArr[8], (int) yArr[9]}, 10);
        Area area = new Area(p);
        area.intersect(new Area(a));
        return area.isEmpty();
    }


}
