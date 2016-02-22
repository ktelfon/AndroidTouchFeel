package com.example.deniss.helloworldapp.controller;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.example.deniss.helloworldapp.models.ButtonPair;
import com.example.deniss.helloworldapp.models.CustomButton;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by ktelfon on 19-Feb-16.
 */
public class Controller {

    private int pairIdx = 0;
    private ButtonPair currentPair;
    private HashMap<Integer, CustomButton> buttons;
    private HashMap<Integer, ButtonPair> buttonPairs;

    public Controller() {
        buttonPairs = new HashMap<>();
        buttons = new HashMap<>();
        currentPair = new ButtonPair();
    }

    public void createPair(CustomButton first, CustomButton second) {

        Random rnd = new Random();

        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        while (color == Color.RED || color == 0) {
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }

        first.setDefaultColor(color);
        second.setDefaultColor(color);

        first.setBackgroundColor(Color.GREEN);
        second.setBackgroundColor(Color.GREEN);

        buttons.put(first.getId(), first);
        buttons.put(second.getId(), second);

        first.setPairId(pairIdx);
        second.setPairId(pairIdx);

        buttonPairs.put(pairIdx, new ButtonPair(pairIdx, first, second));

        pairIdx++;

    }

    public void checkIfPairIsOpen(CustomButton btn) {
        //TODO: fix the selection of same button
        if(!(currentPair.getFirst() != null && currentPair.getSecond() != null)) {
            //TODO: add animation to this
            btn.setBackgroundColor(btn.getDefaultColor());
            btn.setIsClicked(true);

            if (currentPair.getFirst() == null) {
                currentPair.setFirst(btn);
            } else if (currentPair.getSecond() == null) {
                currentPair.setSecond(btn);
            }

            if (currentPair.getFirst() != null && currentPair.getSecond() != null) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                            if (currentPair.getFirst().getPairId() == currentPair.getSecond().getPairId()) {
                                // pair is open
                                buttonPairs.remove(currentPair.getId());

                                buttons.remove(currentPair.getFirst().getId());
                                buttons.remove(currentPair.getSecond().getId());

                                currentPair.getFirst().setVisibility(View.INVISIBLE);
                                currentPair.getSecond().setVisibility(View.INVISIBLE);

                                currentPair.setFirst(null);
                                currentPair.setSecond(null);

                            } else {

                                currentPair.getFirst().setIsClicked(false);
                                currentPair.getSecond().setIsClicked(false);

                                //TODO: add animation to this
                                currentPair.getFirst().setBackgroundColor(Color.GREEN);
                                currentPair.getSecond().setBackgroundColor(Color.GREEN);

                                currentPair.setFirst(null);
                                currentPair.setSecond(null);
                            }
                    }

                }, AppConst.BUTTON_DELAY);
            }
        }

}
    public HashMap<Integer, CustomButton> getButtons() {
        return buttons;
    }

    public boolean showRestartButton(){
        return getButtons().size() == 0;
    }


}
