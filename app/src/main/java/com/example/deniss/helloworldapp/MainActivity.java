package com.example.deniss.helloworldapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.deniss.helloworldapp.controller.Controller;
import com.example.deniss.helloworldapp.models.CustomButton;

public class MainActivity extends AppCompatActivity {

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupApp();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupApp(){

        //TODO: Add reset button
        controller = new Controller();

        //TODO: make this better without XML so it can go to a list
        CustomButton button1 = createButton(R.id.button1);
        CustomButton button2 = createButton(R.id.button2);
        CustomButton button3 = createButton(R.id.button3);
        CustomButton button4 = createButton(R.id.button4);
        CustomButton button5 = createButton(R.id.button5);
        CustomButton button6 = createButton(R.id.button6);
        CustomButton button7 = createButton(R.id.button7);
        CustomButton button8 = createButton(R.id.button8);
        CustomButton button9 = createButton(R.id.button9);
        CustomButton button10 = createButton(R.id.button10);
        CustomButton button11 = createButton(R.id.button11);
        CustomButton button12 = createButton(R.id.button12);

        //TODO: Randomize pairs
        controller.createPair(button1, button2);
        controller.createPair(button3, button4);
        controller.createPair(button5, button6);
        controller.createPair(button7, button8);
        controller.createPair(button9, button10);
        controller.createPair(button11, button12);
    }

    private CustomButton createButton(int buttonId)
    {
        final CustomButton btn = (CustomButton) findViewById(buttonId);

        btn.setText("Click To change!");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.checkIfPairIsOpen(btn);
            }
        });
        return btn;
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
