package com.example.sony.madlibsextended;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class DisplayStoryActivity extends ActionBarActivity {
    private TextToSpeech tts_engine;
    private boolean speech_is_ready = false;
    private String story;

    /*
     * This function get invoke when the user press the make another story button.
     * The function reset the state of the mad lib story and returns to the story
     * selection interface (PresentStoriesChoice activity)
     */
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

        //remove '<', '>', "<b>" and "</b>" from story for reading aloud
        story = mad_lib_story;
        story = story.replace("<b>", "");
        story = story.replace("</b>", "");
        story = story.replace("<", "");
        story = story.replace(">", "");

        //set up text-to-speech engine
        tts_engine = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            @Override
            public void
            onInit(int status)
            {
                speech_is_ready = true;
            }
        });
    }

    /*
     * This function gets invoke when the user press the "Read aloud" button
     * The entire story gets pass into the text-to-speech engine and then reads
     * aloud the story.
     */
    public void
    read_story_aloud(View view)
    {
        if (speech_is_ready)
        {
            tts_engine.speak(story, TextToSpeech.QUEUE_FLUSH, null);
        }
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
