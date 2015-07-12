package com.example.sony.biggernumber;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.*;
import android.widget.*;
import java.util.*;
import android.app.*;
import android.os.*;
import android.view.*;

public class MainActivity extends ActionBarActivity {
    private int number1; //left button number
    private int number2; //right button number
    private int points; //player's total points

    // gets call when player clicks the left button
    public void click_button1(View view)
    {
        check(number1, number2);
    }

    // gets call when player clicks the right button
    public void click_button2(View view)
    {
        check(number2, number1);
    }

    // updates the player's score based on their selection
    private void check(int a, int b)
    {
        if (a > b)
        {
            points++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            points--;
            Toast.makeText(this, "You are STUPID!", Toast.LENGTH_SHORT).show();
        }

        TextView points_view = (TextView) findViewById(R.id.points_text_view);
        points_view.setText("Points: " + points);
        generate_random_number();
    }

    // chooses two new random integers to appear on the buttons
    private void generate_random_number()
    {
        // pick two random numbers
        Random rand = new Random();
        number1 = rand.nextInt(100);
        number2 = rand.nextInt(100);
        while (number1 == number2)
        {
            number2 = rand.nextInt(100);
        }

        //set the buttons to display the two numbers
        Button left_button = (Button) findViewById(R.id.button_left);
        left_button.setText("" + number1); // "" + int => converts the int into a string

        Button right_button = (Button) findViewById(R.id.button_right);
        right_button.setText("" + number2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generate_random_number(); //when application start we want to randomly generate two numbers
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
