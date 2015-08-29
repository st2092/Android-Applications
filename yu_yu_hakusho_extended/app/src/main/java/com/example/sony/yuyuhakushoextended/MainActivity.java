package com.example.sony.yuyuhakushoextended;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.*;
import android.os.*;
import android.view.*;
import android.media.MediaPlayer;
import android.content.*;
import android.util.Log;
import android.widget.*;


public class MainActivity extends ActionBarActivity {
    // "request codes" used to identify sub-activities that return results
    private static final int REQUEST_CODE_DETAILS_ACTIVITY = 1234;
    private static final int REQUEST_CODE_TAKE_PHOTO = 4321;

    private MediaPlayer media_player; //media player to play theme song
    private RadioButton previous_radio_button;
    private int previous_id = -1;
    private int previous_choice_group = 0;

     /*
      * This function gets call when the user clicks on the character image. This
      * function displays the character's information
      */
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
        startActivityForResult(intent, REQUEST_CODE_DETAILS_ACTIVITY);
    }

    /*
     * This function gets call when the user choose a character via radio button. This
     * function displays the character's picture
     */
    public void
    display_char_icon(View view)
    {
        //ImageButton image_button = (ImageButton) findViewById(R.id.char_image_button);

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
        int id = view.getId();
        update_character_display(id);
    }

    /*
     * This function updates the image base on the selected character.
     */
    private void
    update_character_display(int id)
    {
        ImageButton image_button = (ImageButton) findViewById(R.id.char_image_button);

        // determines which character was chosen and set the image to corresponding character
        if (id == R.id.yusuke) // yusuke
        {
            image_button.setImageResource(R.drawable.yusuke);
            previous_radio_button = (RadioButton) findViewById(R.id.yusuke);
            previous_radio_button.setChecked(true);
            previous_choice_group = 1;
        }
        else if (id == R.id.kuwabara) // kuwabara
        {
            image_button.setImageResource(R.drawable.kuwabara);
            previous_radio_button = (RadioButton) findViewById(R.id.kuwabara);
            previous_radio_button.setChecked(true);
            previous_choice_group = 1;
        }
        else if (id == R.id.hiei) // hiei
        {
            image_button.setImageResource(R.drawable.hiei);
            previous_radio_button = (RadioButton) findViewById(R.id.hiei);
            previous_radio_button.setChecked(true);
            previous_choice_group = 1;
        }
        else if (id == R.id.kurama) // kurama
        {
            image_button.setImageResource(R.drawable.kurama);
            previous_radio_button = (RadioButton) findViewById(R.id.kurama);
            previous_radio_button.setChecked(true);
            previous_choice_group = 2;
        }
        else if (id == R.id.keiko) // keiko
        {
            image_button.setImageResource(R.drawable.keiko);
            previous_radio_button = (RadioButton) findViewById(R.id.keiko);
            previous_radio_button.setChecked(true);
            previous_choice_group = 2;
        }
        else if (id == R.id.botan) //botan
        {
            image_button.setImageResource(R.drawable.botan);
            previous_radio_button = (RadioButton) findViewById(R.id.botan);
            previous_radio_button.setChecked(true);
            previous_choice_group = 2;
        }
        // adjust image to fit button
        image_button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }

    /*
     * This function gets call when the take photo button is clicked on.
     * Create a new intent to take photo and then return to this activity the image.
     */
    public void
    take_photo_for_specific_character(View v)
    {
        Intent take_photo_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(take_photo_intent, REQUEST_CODE_TAKE_PHOTO);
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

    /*
     * Event handler that is called when a sub-activity returns.
     * We can then extract data that came back from the other activity via Intent and use it here.
     */
    @Override
    protected void
    onActivityResult(int request_code, int result_code, Intent intent)
    {
        super.onActivityResult(request_code, result_code, intent);
        if (request_code == REQUEST_CODE_TAKE_PHOTO && result_code == RESULT_OK)
        {
            //returned from taking a photo with the camera; grab the photo and show it in place of current character
            Bitmap bmp = (Bitmap) intent.getExtras().get("data");
            ImageButton img_button = (ImageButton) findViewById(R.id.char_image_button);
            img_button.setImageBitmap(bmp);
            img_button.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
    }

    /*
     * Gets call when the activity is stopped and wants to save its state.
     * We save which character is selected and showing so that the will stay on that turtle
     * when the user comes back later.
     */
    @Override
    protected void
    onSaveInstanceState(Bundle out_state)
    {
        super.onSaveInstanceState(out_state);
        Log.d("testing", "onSaveInstanceState got called");
        RadioGroup radio_group = (RadioGroup) findViewById(R.id.group1);
        int id = radio_group.getCheckedRadioButtonId(); // returns -1 if no button is chosen in this group
        if (id >= 0) //a character in group 1 chosen
        {
            out_state.putInt("group", 1);
            out_state.putInt("id", id);
        }
        else //a character in group 2 chosen
        {
            //find out which character is chosen in group 2
            radio_group = (RadioGroup) findViewById(R.id.group2);
            id = radio_group.getCheckedRadioButtonId();
            out_state.putInt("group", 2);
            out_state.putInt("id", id);
            out_state.putInt("previous_choice_group", previous_choice_group);
        }
    }

    /*
     * Gets call when the activity returns to an active running state and wants to restore its state.
     * We restore which character was previously selected.
     */
    @Override
    protected void
    onRestoreInstanceState(Bundle saved_instance_state)
    {
        super.onRestoreInstanceState(saved_instance_state);
        Log.d("testing", "onRestoreInstanceState got called");
        int group_number = saved_instance_state.getInt("group");
        int id = saved_instance_state.getInt("id");
        previous_choice_group = saved_instance_state.getInt("previous_choice_group");
        RadioGroup radio_group;
        if (group_number == 1)
        {
            radio_group = (RadioGroup) findViewById(R.id.group1);
        }
        else
        {
            radio_group = (RadioGroup) findViewById(R.id.group2);
        }
        radio_group.check(id);
        update_character_display(id);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Functions for media player
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
