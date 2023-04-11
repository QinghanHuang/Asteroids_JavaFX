package com.java.asteroids.sprite;

import com.java.asteroids.Director;
import com.java.asteroids.scene.GameScene;
import com.java.asteroids.util.Group;
import com.java.asteroids.util.Movement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Random;

public class Asteroid extends Role{

    double oldX,oldY;
    int aimDir = 0;

    int rotation = 0;

    private int position;
    public static Random random=new Random();
    public Asteroid(Image image, double x, double y, double width, double height, Group group, Movement mov, GameScene gameScene, int position) {
        super(image, x, y, width, height, group, mov, gameScene);
        speed = 1;
        this.position = position;
        this.aimDir = random.nextInt(360);
        rotate(x,y);
    }

    private void rotate(double x, double y) {
        if(x > Director.WIDTH){
            rotation = -90;
        }
//        if(x < 0){
//
//        }
    }

    @Override
    public void move() {

        oldX=x;
        oldY=y;
//        aimDir = 120;
        switch (position) {
            case 0:
//                y -= speed;
                x+=speed*Math.sin(Math.toRadians(aimDir%360));
                y-=speed*Math.cos(Math.toRadians(aimDir%360));
                break;
            case 1:
                x-=speed*Math.sin(Math.toRadians(aimDir%360));
                y-=speed*Math.cos(Math.toRadians(aimDir%360));
                break;
            case 2:
                x+=speed*Math.sin(Math.toRadians(aimDir%360));
                y+=speed*Math.cos(Math.toRadians(aimDir%360));
                break;
            case 3:
                x-=speed*Math.sin(Math.toRadians(aimDir%360));
                y+=speed*Math.cos(Math.toRadians(aimDir%360));
                break;
        }

        if (x < -90) x = Director.WIDTH;
        if (y < -90) y = Director.HEIGHT;
        if (x > Director.WIDTH + 90) x = 0;
        if (y > Director.HEIGHT + 90) y = 0;
//        Movement[] randomDirs=Movement.values();
//        mov=randomDirs[random.nextInt(randomDirs.length)];
        }

    public boolean impactAirCraft(AirCraft airCraft) {

        if (airCraft != null  && this.getContour().intersects(airCraft.getContour())) {
            System.out.println("Killed");
            airCraft.setAlive(false);
            this.setAlive(false);
            return true;
        }
        return false;
    }

    public void impactAirCraft(List<AirCraft> airCrafts) {
        for(AirCraft airCraft:airCrafts){
            this.impactAirCraft(airCraft);
        }
    }

    public void splitAsteroids(){
        System.out.println(getGroup());
        if (getGroup() == Group.ASTEROID_BIG){
            for (int i = 0; i <= 1; i++){
                Asteroid asteroid=new Asteroid(new Image("image/asteroid_big.png"), x, y, 90, 90, Group.ASTEROID_MEDIUM, Movement.FORWARD, gameScene, position);
                gameScene.getAsteroids().add(asteroid);
            }

        }else if (getGroup() == Group.ASTEROID_MEDIUM){
            for (int i = 0; i <= 3; i++){
                Asteroid asteroid=new Asteroid(new Image("image/asteroid_big.png"), x, y, 60, 60, Group.ASTEROID_SMALL, Movement.FORWARD, gameScene, position);
                gameScene.getAsteroids().add(asteroid);
            }

        }


    }


    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!isAlive()) {
//            System.out.println(gameScene.getAsteroids().size()+ " Before Removing");
            System.out.println(gameScene.getAsteroids());
            gameScene.getAsteroids().remove(this);
//            System.out.println(gameScene.getAsteroids().size()+ " Intermediate Removing");
            splitAsteroids();
//            System.out.println(gameScene.getAsteroids().size()+ " After Removing");
            return;
        }

//        switch (aimDir) {
//            case UP:
//                image = imageMap.get("UP");
//                break;
//            case DOWN:
//                image = imageMap.get("DOWN");
//                break;
//            case LEFT:
//                image = imageMap.get("LEFT");
//                break;
//            case RIGHT:
//                image = imageMap.get("RIGHT");
//                break;
//        }

//        System.out.println("CHECK PAINT");
//        System.out.println(x);
//        System.out.println(y);
//        graphicsContext.rotate(rotation);
        super.paint(graphicsContext);
        move();
    }
    }

