package com.java.asteroids.sprite;

import com.java.asteroids.scene.GameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class  Sprite {
    private Image image;
     double x,y, width,height;
     GameScene gameScene;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Sprite(Image image, double x, double y, double width, double height, GameScene gameScene) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameScene = gameScene;
    }

    public Sprite(Image image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * draw in location x,y with width and height
     * using image
     * @param graphicsContext in gameScene
     */
    public void paint(GraphicsContext graphicsContext){

        graphicsContext.drawImage(image,x,y,width,height);
    }

    /**
     * get the outline of a sprite for checking impact bullet or stars
     * @return a rectangle
     */
    public Rectangle2D getContour(){
        return new Rectangle2D(x,y,width,height);
    }

}
