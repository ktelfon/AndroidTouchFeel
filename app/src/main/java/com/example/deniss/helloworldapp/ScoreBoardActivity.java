package com.example.deniss.helloworldapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deniss.helloworldapp.controller.AppConst;
import com.example.deniss.helloworldapp.controller.ScoreBoardController;

import java.util.Arrays;

/**
 * Created by Deniss on 27-Feb-16.
 */
public class ScoreBoardActivity extends AppCompatActivity {

    ScoreBoardController scoreBoardController;

    final Integer[] enterKeys = {
            EditorInfo.IME_ACTION_GO,
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER
    };

    private Integer[] labelLayoutIdx = {
            R.id.score1, R.id.score2, R.id.score3,
            R.id.score4, R.id.score5, R.id.score6,
            R.id.score7, R.id.score8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            setUpLayoutObjects();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setUpLayoutObjects() throws PackageManager.NameNotFoundException {

        setContentView(R.layout.score_board_activity);
        scoreBoardController = new ScoreBoardController();
        setActivityLabel();

        Button backButton = (Button) findViewById(R.id.return_to_main_button);
        backButton.setText(" Back !");
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText userNameInput = (EditText) findViewById(R.id.user_name_input);
        userNameInput.setImeActionLabel("Done!", KeyEvent.KEYCODE_ENTER);

        userNameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(final TextView textView, final int keyCode, final KeyEvent keyEvent) {
                if (Arrays.asList(enterKeys).contains(keyCode)) {
                    //TODO: Block this edit text when this happens
                    //TODO: Fix score diplay
                    Integer score = Integer.valueOf(getIntent().getStringExtra("score"));

                     int id = scoreBoardController.setLabel(
                            userNameInput.getText().toString(),
                            score);

                    saveData(
                            id,
                            userNameInput.getText().toString(),
                            score);

                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(userNameInput.getWindowToken(), 0);

                    userNameInput.clearFocus();
                    userNameInput.setText("");

                    return true;
                }
                return false;
            }
        });

        userNameInput.setImeOptions(userNameInput.getImeOptions() | EditorInfo.IME_ACTION_DONE);

        // create empty labels
        loadData();

        // set amount of labels
        scoreBoardController.setMaxLabelCounter(labelLayoutIdx.length);
    }

    private void loadLabels(SharedPreferences sharedPref, int defaultValue){
        for (int i = 0; i < labelLayoutIdx.length; i++){

            String labelText = sharedPref.getString(String.valueOf(labelLayoutIdx[i]), String.valueOf(defaultValue));
            int score = sharedPref.getInt(labelText,defaultValue);

            TextView scoreBoardLabel = (TextView) findViewById(labelLayoutIdx[i]);
            scoreBoardLabel.setText(labelText);

            scoreBoardController.setScoreBoardLabel(scoreBoardLabel, score);
        }
    }

    private void setActivityLabel() throws PackageManager.NameNotFoundException {
        ActivityInfo activityInfo = getPackageManager().getActivityInfo(
                getComponentName(), PackageManager.GET_META_DATA);
        String title = activityInfo.loadLabel(getPackageManager())
                .toString();

        Intent intent = getIntent();
        // Receiving the Data
        final String score = intent.getStringExtra("score");

        setTitle(title + ": " + score);
    }

    private void saveData(int labelId,String textId, int score){
        // save 2131493005
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(textId, score);
        editor.putString(String.valueOf(labelId), textId);
        editor.commit();
    }

    private void loadData(){
        //load
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        loadLabels(sharedPref, AppConst.DEFAULT_SCORE);
    }

}
