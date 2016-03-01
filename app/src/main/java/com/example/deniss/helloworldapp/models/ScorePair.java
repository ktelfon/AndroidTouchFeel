package com.example.deniss.helloworldapp.models;

import android.widget.TextView;

/**
 * Created by Deniss on 01-Mar-16.
 */
public class ScorePair {
    private TextView label;
    private int score;

    public ScorePair(TextView scoreBoardLabel, int score) {
        this.label = scoreBoardLabel;
        this.score = score;
    }

    public TextView getLabel() {
        return label;
    }

    public void setLabel(TextView label) {
        this.label = label;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
