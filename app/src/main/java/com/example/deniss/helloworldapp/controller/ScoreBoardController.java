package com.example.deniss.helloworldapp.controller;

import android.widget.TextView;

import com.example.deniss.helloworldapp.models.ScorePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deniss on 01-Mar-16.
 */
public class ScoreBoardController {

    private int maxLabelCounter = 0;
    private int freeLabelCounter = 0;
    private List<ScorePair> scoreBoardLabels;

    public ScoreBoardController() {
        scoreBoardLabels = new ArrayList<>();
    }

    public void setScoreBoardLabel(TextView scoreBoardLabel, int score) {
        scoreBoardLabels.add(new ScorePair(scoreBoardLabel, score));
    }

    public void setMaxLabelCounter(int maxLabelCounter) {
        this.maxLabelCounter = maxLabelCounter;
    }

    public int setLabel(String text, int score) {
        scoreBoardLabels.get(freeLabelCounter).getLabel().setText(text + " " + score);
        scoreBoardLabels.get(freeLabelCounter).setScore(score);
        freeLabelCounter++;
        if (freeLabelCounter == maxLabelCounter) {
            freeLabelCounter = 0;
            return scoreBoardLabels.get(maxLabelCounter - 1).getLabel().getId();
        }
        return scoreBoardLabels.get(freeLabelCounter - 1).getLabel().getId();
    }

    public int getFreeLabelCounter() {
        return freeLabelCounter;
    }

    public void setFreeLabelCounter(int freeLabelCounter) {
        this.freeLabelCounter = freeLabelCounter;
    }
}
