package com.example.sony.rockpaperscissors;

import android.content.Intent;
import android.support.v7.app.*;
import android.os.*;
import android.view.*;
import android.app.*;
import android.widget.*;
import java.util.*;


public class MainActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.example.sony.Rock,Paper,Scissors.MESSAGE";
    /*
     * 0 - rock
     * 1 - paper
     * 2 - scissors
     */
    //called when player clicks the rock button
    public void
    rock_button_pressed(View view)
    {
        // send to next activity to determine winner
        Intent intent = new Intent(this, DetermineWinnerActivity.class);
        intent.putExtra(EXTRA_MESSAGE, 0);
        startActivity(intent);
    }

    //called when player clicks the paper button
    public void
    paper_button_pressed(View view)
    {
        //send to next activity to determine winner
        Intent intent = new Intent(this, DetermineWinnerActivity.class);
        intent.putExtra(EXTRA_MESSAGE, 1);
        startActivity(intent);
    }

    //called when player clicks the scissors button
    public void
    scissors_button_pressed(View view)
    {
        //send to next activity to determine winner
        Intent intent = new Intent(this, DetermineWinnerActivity.class);
        intent.putExtra(EXTRA_MESSAGE, 2);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
