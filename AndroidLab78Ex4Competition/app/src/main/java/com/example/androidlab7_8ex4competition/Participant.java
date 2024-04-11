package com.example.androidlab7_8ex4competition;

public class Participant {
    private int index;
    private String name;
    private int score;

    public Participant( int index,String name, int score) {
        this.index=index;
        this.name = name;
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
