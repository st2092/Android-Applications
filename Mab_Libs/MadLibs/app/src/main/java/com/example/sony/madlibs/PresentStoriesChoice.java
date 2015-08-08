package com.example.sony.madlibs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PresentStoriesChoice extends ActionBarActivity {
    //Stories constants
    private final int SIMPLE = 0;
    private final int TARZAN = 1;
    private final int UNIVERSITY = 2;
    private final int CLOTHES = 3;
    private final int DANCE = 4;

    //This function gets call when the user chooses a story
    public void
    advance_to_fill_in_activity(View view)
    {
        //start a new intent fill in activity
        Intent intent = new Intent(this, FillInActivity.class);

        int chosen_story_id = view.getId(); //get the id of the button that was chosen
        int story_id = determine_chosen_story(chosen_story_id);

        intent.putExtra("MadLibsChosenStory", story_id); //add a pair into intent, where pair is <key, value>
        startActivity(intent);
    }

    //This function determines which story was chosen
    private int
    determine_chosen_story(int story_id)
    {
        if (story_id == R.id.simple_story_button) //simple story
        {
            return SIMPLE;
        }
        else if (story_id == R.id.tarzan_story_button) //tarzan story
        {
            return TARZAN;
        }
        else if (story_id == R.id.university_story_button) // university story
        {
            return UNIVERSITY;
        }
        else if (story_id == R.id.clothes_story_button) // clothes story
        {
            return CLOTHES;
        }
        else if (story_id == R.id.dance_story_button) // dance story
        {
            return DANCE;
        }
        else
        {
            return -1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_stories_choice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_present_stories_choice, menu);
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
