package com.java.asteroids.scene;

import com.java.asteroids.*;
import com.java.asteroids.sprite.AirCraft;
import com.java.asteroids.sprite.BackGround;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameScene {
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    private KeyProcess keyProcess = new KeyProcess();

    private Refresh refresh = new Refresh();

    private boolean running = false;

    private BackGround background = new BackGround();

//    储存玩家坦克
    private AirCraft self =null;
    //储存子弹list
//    private List<Bullet> bullets=new ArrayList<>();
    //储存敌方坦克
//    private List<Tank> enemies=new ArrayList<>();
    //储存爆炸效果
//    private List<Explore> explores=new ArrayList<>();
    //储存box
//    private List<Box> boxes=new ArrayList<>();
    //储存Rock
//    private List<Rock> rocks=new ArrayList<>();
    //储存Tree
//    private List<Tree> trees=new ArrayList<>();


    /**
     * paint of gameScene
     * will call paint of sprite
     */
    private void paint() {
        background.paint(graphicsContext);

        //game over 条件
//        if(!self.isAlive()){
//            Director.getInstance().gameOver(false);
//        }else if(enemies.size()==0){
//            Director.getInstance().gameOver(true);
//        }

    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyPressed(keyProcess);
        stage.getScene().setOnKeyReleased(keyProcess);
        running = true;


        //初始玩家坦克
//        self = new Tank(400, 500, Group.GREEN, Direction.STOP, Direction.UP, this);
        //初始Sprite
//        initSprite();
        refresh.start();
    }
    // 初始敌人
    private void initSprite(){

    }

    //清除
    public void clear(Stage stage) {
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        stage.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyProcess);
        refresh.stop();
//        self=null;
//        bullets.clear();
//        enemies.clear();
//        explores.clear();
//        boxes.clear();
//        rocks.clear();
//        trees.clear();


    }

    //每一帧都会自动调用AnimationTimer 来刷新页面, 我们重写handle 使用paint来重新绘制
    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long l) {
            if (running) {
                paint();
            }
        }
    }

    /**
     * For interaction of keyboard
     *   pass keyCode as a parameter to call self.released(keyCode);
     *
     */
    private class KeyProcess implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                if (keyCode.equals(KeyCode.P)) {
                    running = !running;
                }
//                if (self!=null) {
//                    self.released(keyCode);
//                }
            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
//                if (self!=null) {
//                    self.pressed(keyCode);
//                }
            }

        }
    }
}
