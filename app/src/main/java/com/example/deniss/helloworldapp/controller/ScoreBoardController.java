package com.example.deniss.helloworldapp.controller;

import android.widget.TextView;

import com.example.deniss.helloworldapp.models.ScorePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deniss on 01-Mar-16.
 */
public class ScoreBoardController {

    List<ScorePair> scoreBoardLabes;

    public ScoreBoardController() {
        scoreBoardLabes = new ArrayList<>();
    }

    public void setScoreBoardLabel(TextView scoreBoardLabel, int score){
        scoreBoardLabes.add(new ScorePair(scoreBoardLabel, score));
    }
}
