package com.example.sony.rockpaperscissors;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class DetermineWinnerActivity extends ActionBarActivity {
    /*
     * 0 - rock
     * 1 - paper
     * 2 - scissors
     */
    public final static int ROCK = 0;
    public final static int PAPER = 1;
    public final static int SCISSORS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_determine_winner);
        Intent intent = getIntent();
        int user_choice = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        //have CPU pick randomly rock, paper, or scissors
        Random rand = new Random();
        int cpu_choice = rand.nextInt(3); // return [0, 2]
        display_choices (user_choice, cpu_choice);
        determine_winner (user_choice, cpu_choice);
    }

    // function that displays the choice of user and cpu as Image button
    public void
    display_choices(int user_choice, int cpu_choice)
    {
        ImageButton user_button = (ImageButton) findViewById(R.id.user_choice_button);
        ImageButton cpu_button = (ImageButton) findViewById(R.id.cpu_choice_button);
        display_choice_image(user_choice, user_button);
        display_choice_image(cpu_choice, cpu_button);
    }

    //function to determine which picture to display base on user and cpu choice
    public void
    display_choice_image(int choice, ImageButton button)
    {
        /*
         * 0 - rock
         * 1 - paper
         * 2 - scissors
         */
        int id = 0;
        Drawable res;
        switch (choice)
        {
            case ROCK:
                id = getResources().getIdentifier("@drawable/rock", null, getPackageName() );
                res = getResources().getDrawable(id);
                button.setImageDrawable(res);
                break;
            case PAPER:
                id = getResources().getIdentifier("@drawable/paper", null, getPackageName() );
                res = getResources().getDrawable(id);
                button.setImageDrawable(res);
                break;
            case SCISSORS:
                id = getResources().getIdentifier("@drawable/scissors", null, getPackageName() );
                res = getResources().getDrawable(id);
                button.setImageDrawable(res);
                break;
            default:
                break;
        }
    }

    // determine the winner based on user and cpu choice
    public void
    determine_winner(int user_choice, int cpu_choice)
    {
        if (user_choice == cpu_choice)
        {
            // tie
            display_outcome_message('t');
            return;
        }
        switch(user_choice)
        {
            case ROCK: //user chose rock
                if (cpu_choice == PAPER)
                {
                    // you lose
                    display_outcome_message('l');
                }
                else if (cpu_choice == SCISSORS)
                {
                    // you win
                    display_outcome_message('w');
                }
                break;
            case PAPER: //user chose paper
                if (cpu_choice == ROCK)
                {
                    // you win
                    display_outcome_message('w');
                }
                else if (cpu_choice == SCISSORS)
                {
                    // you lose
                    display_outcome_message('l');
                }
                break;
            case SCISSORS: //user chose scissors
                if (cpu_choice == ROCK)
                {
                    // you lose
                    display_outcome_message('l');
                }
                else if (cpu_choice == PAPER)
                {
                    // you win
                    display_outcome_message('w');
                }
                break;
        }
    }

    // display outcome message
    public void
    display_outcome_message (char c)
    {
        TextView outcome_textview = (TextView) findViewById(R.id.text_view_outcome);

        switch(c)
        {
            case 't': //tie
                outcome_textview.setText("Tie!");
                break;
            case 'w': // win
                outcome_textview.setText("You Win!");
                break;
            case 'l': // lose
                outcome_textview.setText("You Lose!");
                break;
        }
    }

    //this brings the user back to the main screen
    public void
    back_to_main(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
