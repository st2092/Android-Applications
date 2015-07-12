package com.example.sony.yuyuhakusho;

import android.provider.MediaStore;
import android.support.v7.app.*;
import android.os.*;
import android.view.*;
import android.media.MediaPlayer;
import android.content.*;
import android.util.Log;
import android.widget.*;


public class MainActivity extends ActionBarActivity {
    private MediaPlayer media_player; //media player to play theme song
    private RadioButton previous_radio_button;
    private int previous_id = -1;
    private int previous_choice_group = 0;

    // This function gets call when the user clicks on the character image. This
    // function displays the character's information
    public void
    display_char_info(View view)
    {
        //create intent for new activity to display chosen character's information
        Intent intent = new Intent(this, DetailsActivity.class);

        //find out which character was chosen by user
        RadioGroup radio_group1 = (RadioGroup) findViewById(R.id.group1);
        RadioGroup radio_group2 = (RadioGroup) findViewById(R.id.group2);

        int id1 = radio_group1.getCheckedRadioButtonId();
        int id2 = radio_group2.getCheckedRadioButtonId();

        // if no buttons in a radio group is chosen getCheckedRadioButtonId returns -1
        if (previous_choice_group == 1)
        {
            intent.putExtra("YuYuHakusho_char_id", id1);
        }
        //else if (id1 == -1 && id2 != -1) // a button in group 2 is chosen
        else if (previous_choice_group == 2)
        {
            intent.putExtra("YuYuHakusho_char_id", id2);
        }
        //start the new activity to display the character's info
        startActivity(intent);
    }

    // This function gets call when the user choose a character via radio button. This
    // function displays the character's picture
    public void
    display_char_icon(View view)
    {
        ImageButton image_button = (ImageButton) findViewById(R.id.char_image_button);

        RadioGroup radio_group_to_clear;
        if (previous_choice_group != 0)
        {
            switch (previous_choice_group)
            {
                case 1: // group 1 was picked previously
                    radio_group_to_clear = (RadioGroup) findViewById(R.id.group1);
                    radio_group_to_clear.clearCheck();
                    break;
                case 2: // group 2 was picked previously
                    radio_group_to_clear = (RadioGroup) findViewById(R.id.group2);
                    radio_group_to_clear.clearCheck();
                    break;
                default:
                    break;
            }
        }

        previous_id = view.getId(); // set previous id for next use

        // determines which character was chosen and set the image to corresponding character
        if (view.getId() == R.id.yusuke) // yusuke
        {
            image_button.setImageResource(R.drawable.yusuke);
            previous_radio_button = (RadioButton) findViewById(R.id.yusuke);
            previous_radio_button.setChecked(true);
            previous_choice_group = 1;
        }
        else if (view.getId() == R.id.kuwabara) // kuwabara
        {
            image_button.setImageResource(R.drawable.kuwabara);
            previous_radio_button = (RadioButton) findViewById(R.id.kuwabara);
            previous_radio_button.setChecked(true);
            previous_choice_group = 1;
        }
        else if (view.getId() == R.id.hiei) // hiei
        {
            image_button.setImageResource(R.drawable.hiei);
            previous_radio_button = (RadioButton) findViewById(R.id.hiei);
            previous_radio_button.setChecked(true);
            previous_choice_group = 1;
        }
        else if (view.getId() == R.id.kurama) // kurama
        {
            image_button.setImageResource(R.drawable.kurama);
            previous_radio_button = (RadioButton) findViewById(R.id.kurama);
            previous_radio_button.setChecked(true);
            previous_choice_group = 2;
        }
        else if (view.getId() == R.id.keiko) // keiko
        {
            image_button.setImageResource(R.drawable.keiko);
            previous_radio_button = (RadioButton) findViewById(R.id.keiko);
            previous_radio_button.setChecked(true);
            previous_choice_group = 2;
        }
        else if (view.getId() == R.id.botan) //botan
        {
            image_button.setImageResource(R.drawable.botan);
            previous_radio_button = (RadioButton) findViewById(R.id.botan);
            previous_radio_button.setChecked(true);
            previous_choice_group = 2;
        }
        // adjust image to fit button
        image_button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize media player to play theme song of yu yu hakusho
        media_player = MediaPlayer.create(this, R.raw.yu_yu_hakusho_theme);
        media_player.setLooping(true);
        // log info to check flow of app
        Log.d("testing", "onCreate got called");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Functions for media player/logging app life cycle
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void
    onPause()
    {
        super.onPause();
        media_player.pause();
        Log.d("testing", "onPause got called");
    }

    public void
    onResume()
    {
        super.onResume();
        if (media_player != null)
        {
            media_player.setLooping(true);
            media_player.start();
        }
        Log.d("testing", "onResume got called");
    }

    public void
    onStart()
    {
        super.onStart();
        Log.d("testing", "onStart got called");
    }

    public void
    onStop()
    {
        super.onStop();
        Log.d("testing", "onStop got called");
    }

    public void
    onDestroy()
    {
        super.onDestroy();
        Log.d("testing", "onDestroy got called");
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
