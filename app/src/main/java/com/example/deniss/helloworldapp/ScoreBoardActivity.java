package com.example.deniss.helloworldapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deniss on 27-Feb-16.
 */
public class ScoreBoardActivity extends AppCompatActivity {

    List<TextView> scoreBoardLabes;
    private Integer[] labelIdx = {
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

        ActivityInfo activityInfo = getPackageManager().getActivityInfo(
                getComponentName(), PackageManager.GET_META_DATA);
        String title = activityInfo.loadLabel(getPackageManager())
                .toString();

        Intent intent = getIntent();
        // Receiving the Data
        String score = intent.getStringExtra("score");

        setTitle(title + ": " + score);

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
        
        scoreBoardLabes = new ArrayList<>();
        for (int i = 0; i < labelIdx.length; i++){
            scoreBoardLabes.add((TextView) findViewById(labelIdx[i]));
        }
    }

}
