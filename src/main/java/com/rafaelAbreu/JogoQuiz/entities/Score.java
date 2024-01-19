package com.rafaelAbreu.JogoQuiz.entities;

import java.io.Serializable;

public class Score implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pointScore;

    public Score() {

    }

    public Score(Integer pointScore) {
        this.pointScore = pointScore;
    }

    public Integer getPointScore() {
        return pointScore;
    }

    public void setPointScore(Integer pointScore) {
        this.pointScore = pointScore;
    }
}
