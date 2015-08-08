package com.example.sony.madlibs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class DisplayStoryActivity extends ActionBarActivity {

    //This function get invoke when the user press the make another story button
    //The function reset the state of the mad lib story and returns to the story
    //selection interface (PresentStoriesChoice activity)
    public void
    make_another_mad_lib_story(View view)
    {
        //Create intent object to go back to present stories choice interface
        Intent intent = new Intent(this, PresentStoriesChoice.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);

        //Get intent from Fill In Activity
        Intent intent = getIntent();

        //obtain the mad lib story created by the user in fill in activity
        String mad_lib_story = intent.getStringExtra("MadlibsStoryToShow");

        //set the text view for mad lib story to display the story
        TextView text_view = (TextView) findViewById(R.id.mad_lib_story_text_view);
        text_view.setText(Html.fromHtml(mad_lib_story));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_story, menu);
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
