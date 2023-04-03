package com.java.asteroids.util;

public class Score {
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
}
