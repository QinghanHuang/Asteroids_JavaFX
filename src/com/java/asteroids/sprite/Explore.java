package com.java.asteroids.sprite;


import com.java.asteroids.scene.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explore extends Sprite{

    private int count=0;
    private static Image[] images={
            new Image("image/explode1.png"),
            new Image("image/explode2.png"),
            new Image("image/explode3.png"),
            new Image("image/explode4.png"),
            new Image("image/explode5.png"),
            new Image("image/explode6.png"),
            new Image("image/explode7.png"),
            new Image("image/explode8.png"),
            new Image("image/explode9.png"),
    };


    public Explore(double x, double y, GameScene gameScene) {
        super(null, x, y, 0, 0, gameScene);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if(count>= images.length){
            gameScene.getExplores().remove(this);
            return;
        }
        setImage(images[count]);
        double ex=x-getImage().getWidth()/2;
        double ey=y-getImage().getHeight()/2;
        graphicsContext.drawImage(getImage(),ex,ey);
        count++;

    }
}
