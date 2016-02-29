package com.example.deniss.helloworldapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.deniss.helloworldapp.controller.AppConst;
import com.example.deniss.helloworldapp.controller.Controller;
import com.example.deniss.helloworldapp.models.CustomButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Controller controller;
    private int resetButtonIndex = R.id.resetButton;
    private int guessCounterIndex = R.id.guessCounterLabel;

    private Button resetButton;
    private TextView guessCounterLabel;

    private Integer[] buttonIdx = {
            R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9,
            R.id.button10, R.id.button11, R.id.button12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        guessCounterLabel = (TextView) findViewById(guessCounterIndex);
        guessCounterLabel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        guessCounterLabel.setTextSize(30);
        guessCounterLabel.setText(AppConst.GUESS_COUNTER_LABEL_TEXT + 0);

        resetButton = (Button) findViewById(resetButtonIndex);
        resetButton.setVisibility(View.INVISIBLE);
        resetButton.setText(AppConst.RESET_BUTTON_TEXT);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButton.setVisibility(View.INVISIBLE);
                guessCounterLabel.setVisibility(View.VISIBLE);
                setupApp();
            }
        });

        setupApp();
    }

    private void setupApp(){

        controller = new Controller();

        long seed = System.nanoTime();
        List<Integer> listOfButtonIndexes = Arrays.asList(buttonIdx);
        Collections.shuffle(listOfButtonIndexes, new Random(seed));

        for(int i = 0 ; i < listOfButtonIndexes.size(); i += 2){
            //TODO: make this without XML
            controller.createPair(
                    createButton(listOfButtonIndexes.get(i + 1)),
                    createButton(listOfButtonIndexes.get(i)));
        }
    }

    private CustomButton createButton(int buttonId)
    {
        final CustomButton btn = (CustomButton) findViewById(buttonId);

        btn.setTextSize(50);
        btn.setText("?");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreBoard();
                controller.checkIfPairIsOpen(btn);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (controller.showRestartButton()) {
                            switchToScoreBoard();
                            resetButton.setVisibility(View.VISIBLE);
                            guessCounterLabel.setVisibility(View.INVISIBLE);
                            controller.resetCounter();
                        }
                        guessCounterLabel.setText(AppConst.GUESS_COUNTER_LABEL_TEXT + controller.getGuessCounter());
                    }
                }, AppConst.BUTTON_DELAY);
            }
        });
        btn.setVisibility(View.VISIBLE);

        return btn;
    }

    private void switchToScoreBoard(){
        Intent i = new Intent(getApplicationContext(), ScoreBoardActivity.class);
        i.putExtra("score", String.valueOf(controller.getGuessCounter()));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
