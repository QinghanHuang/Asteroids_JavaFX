package com.java.asteroids;

import com.java.asteroids.scene.GameScene;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.java.asteroids.scene.Index;

public class Director {

    //set global WIDTH and HEIGHT
    public static final double WIDTH = 1440, HEIGHT = 960;

    //stage 作为成员变量用于保存
    private Stage stage;
    //单例模式的导演类
    private static Director instance = new Director();
    //gameScene 作为成员变量用于保存
    private GameScene gameScene=new GameScene();
    private Director() {
    }

    public static Director getInstance() {
        return instance;
    }

    /**
     * 初始场景
     *
     * @param stage
     */
    public void init(Stage stage) {
        //设置Parent root
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Asteroids");
//        stage.getIcons().add(new Image("/image/logo.png"));
        //设置窗口不能改变大小
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        //通过更改成员变量 stage 来保存stage
        this.stage=stage;
        toIndex();
        stage.show();
    }

    /**
     * 加载首页,使用stage变量
     */
    public void toIndex() {
        Index.load(stage);

    }

    public void gameStart() {
        gameScene.init(stage);

    }

    public void gameOver(boolean success) {
//        gameScene.clear(stage);
//        GameOver.load(stage,success);
    }
}

