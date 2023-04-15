package com.java.asteroids.util;

import javafx.scene.media.*;

public class SoundEffect {
    private static Media media=new Media(SoundEffect.class.getResource("/sound/BGM.mp3").toString());
    private static MediaPlayer mediaPlayer = new MediaPlayer(media);

    public static void play(String src){
        //play sound by url
        AudioClip audioClip=new AudioClip(SoundEffect.class.getResource(src).toString());
        //set volume from 0 to 1
        audioClip.setVolume(0.5);
        audioClip.play();
    }
    public static void playBGM(){
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static void stopBGM(){
        mediaPlayer.stop();
    }
}
