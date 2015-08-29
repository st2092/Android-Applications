package com.example.sony.madlibsextended;

import android.content.Intent;
import android.content.res.AssetManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import android.util.Log.*;

public class FillInActivity extends ActionBarActivity {
    //Stories constants
    private final int SIMPLE = 0;
    private final int TARZAN = 1;
    private final int UNIVERSITY = 2;
    private final int CLOTHES = 3;
    private final int DANCE = 4;
    private Story selected_story = null;
    //private TextToSpeech tts_engine;
    //private boolean speech_is_ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in);

        Log.d("testing", "fill in activity onCreate beginning");
        //reset story

        if (selected_story != null)
        {
            selected_story.clear_story();
        }

        //Get intent
        Intent intent = getIntent();
        int story_id = intent.getIntExtra("MadLibsChosenStory", R.id.simple_story_button);

        Log.d("testing", "before opening text file");
        InputStream story_input_stream = null;
        //open the chosen story to read
        if (story_id == SIMPLE)
        {
            try {
                //story_input_stream = new FileInputStream("madlib0_simple");
                // AssetManager asset_manager = getAssets();
                story_input_stream = getAssets().open("madlib0_simple.txt");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        else if (story_id == TARZAN)
        {
            try {
                //story_input_stream = new FileInputStream("madlib1_tarzan.txt");
                story_input_stream = getAssets().open("madlib1_tarzan.txt");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        else if (story_id == UNIVERSITY)
        {
            try {
                //story_input_stream = new FileInputStream("madlib2_university.txt");
                story_input_stream = getAssets().open("madlib2_university.txt");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        else if (story_id == CLOTHES)
        {
            try {
                //story_input_stream = new FileInputStream("madlib3_clothes.txt");
                story_input_stream = getAssets().open("madlib3_clothes.txt");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        else if (story_id == DANCE)
        {
            try {
                // story_input_stream = new FileInputStream("madlib4_dance.txt");
                story_input_stream = getAssets().open("madlib4_dance.txt");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        else //default
        {
            try {
                // story_input_stream = new FileInputStream("madlib0_simple");
                story_input_stream = getAssets().open("madlib0_simple.txt");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        //initialize Story class with the selected story input stream
        //selected_story = new Story(story_input_stream);
        selected_story = new Story();
        selected_story.set_html_mode_on(); //enable bolding the words for the placeholders
        selected_story.read_from_input_stream(story_input_stream); //read from the text input stream

        //set up text-to-speech engine
        /*
        tts_engine = new TextToSpeech(this, new TextToSpeech.OnInitListener(){
            @Override
            public void
            onInit(int status)
            {
                speech_is_ready = true;
            }
        });
        */
        update_views();
    }

    // This function update the views to display the correct placeholder, number of words left, and
    // correct instruction based on placeholder
    private void
    update_views()
    {
        int placeholders_left = selected_story.get_placeholder_remaining_count();

        //update to proper hint for edit text view
        EditText edit_text_view = (EditText) findViewById(R.id.user_input_edit_text);
        edit_text_view.setText(""); //clear out what the user entered to show placeholder
        edit_text_view.setHint(selected_story.get_next_placeholder());


        //update to proper instruction text view
        TextView text_view = (TextView) findViewById(R.id.hint_text_view);
        String instruction = "Please type in a/an " + selected_story.get_next_placeholder();
        text_view.setText(instruction);

        //use text-to-speech engine to say the instruction
        /*
        if (placeholders_left > 0 && speech_is_ready)
        {
            tts_engine.speak(instruction, TextToSpeech.QUEUE_FLUSH, null);
        }
        */

        //update to proper number of words left to fill in
        if (placeholders_left > 0) {
            text_view = (TextView) findViewById(R.id.words_left_text_view);
            text_view.setText("" + selected_story.get_placeholder_remaining_count() + " word(s) left");
        }
    }

    //This function gets invoke when the user press the 'OK' button
    //The text in the EditText View will become the input for the queried placeholder
    public void
    set_placeholder_with_input(View view)
    {
        Log.d("fill-in-testing", "starting set placeholder with input function");
        //obtain the word the user type in from edit text view
        EditText edit_text = (EditText) findViewById(R.id.user_input_edit_text);
        String user_input = edit_text.getText().toString();

        //fill in the user input word in place of the placeholder
        selected_story.fill_in_placeholder(user_input);
        update_views();
        Log.d("fill-in-testing", "ending set placeholder with input function");
        //when all placeholders are filled in, disply to the user their story
        if (selected_story.all_placeholders_filled_in())
        {
            Intent intent = new Intent(this, DisplayStoryActivity.class);
            intent.putExtra("MadlibsStoryToShow", selected_story.to_string());
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fill_in, menu);
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
