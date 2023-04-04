package com.java.asteroids.util;

import com.java.asteroids.controller.ScoreController;

import java.io.Serializable;

public class Score implements Serializable,Comparable<Score>{
    public static final long serialVersionUID = 868345241411312L;
    private String name;
    private int score;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score() {
    }

    public Score(String name,int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Score o) {
        return o.score-this.score;
    }
}
