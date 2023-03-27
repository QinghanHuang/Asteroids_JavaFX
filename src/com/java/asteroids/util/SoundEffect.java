package com.java.asteroids.util;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    public static void play(String src){
        //play sound by url
        AudioClip audioClip=new AudioClip(SoundEffect.class.getResource(src).toString());
        //set volume from 0 to 1
        audioClip.setVolume(0.1);
        audioClip.play();
    }
}
