package com.example.sony.madlibs;

import android.util.Log;

import java.io.*;
import java.util.*;

//Story class
public class Story implements Serializable
{
    ////////////////////////////////
    //private
    ////////////////////////////////
    private String text;                    // text of the story
    private List<String> placeholders;      // list of placeholders to be filled in
    private int num_filled_in;              // number of placeholders filled in
    private boolean html_mode;              // set to true to surround placeholders with <b></b> tags (bold)

    ///////////////////////////////
    //public
    ///////////////////////////////
    //instance initializer; this runs before any constructor
    {
        text = "";
        placeholders = new ArrayList<String>();
        num_filled_in = 0;
        html_mode = false;
        clear_story();
    }

    // constructor for empty story
    public
    Story()
    {
        //empty
    }

    // constructor for a new story by reading in text from a given input stream
    public
    Story(InputStream input_stream)
    {
        read_from_input_stream(input_stream);
    }

    // Read from input stream function
    // Read in text from the input stream passed in. This function replaces the placeholders
    // by bolding it in html mode and or replacing it with a number place holder (e.g. "<0>")
    public void
    read_from_input_stream(InputStream input_stream)
    {
        //create a scanner object from the file input stream
        Scanner input = new Scanner(input_stream);

        //read from input stream until end of file
        while (input.hasNext())
        {
            String word = input.next(); //read in the next word
            //determines if the word is a placeholder
            if (word.startsWith("<") && word.endsWith(">")) // is a placeholder if surrounded by "< >"
            {
                if (html_mode)
                {
                    text += " <b><" + placeholders.size() + "></b>";
                }
                else //not html, no need to bold it
                {
                    text += " <" + placeholders.size() + ">";
                }

                // replace "<plural-noun>" with plural noun
                // for any other placeholders only the angle brackets get remove
                String placeholder = word.substring(1, word.length() - 1).replace("-", " ");
                placeholders.add(placeholder); // add placeholder into list

            }
            else //regular word
            {
                // only add space if not fresh start
                if(!text.isEmpty())
                {
                    text += " ";
                }
                text += word;
            }
        }
    }

    // Fill in placeholder function
    // Replaces the next unfilled placeholder with the word that the user inputted
    public void
    fill_in_placeholder( String word )
    {
        //ensure there is at least one placeholder to fill in
        if (!all_placeholders_filled_in())
        {
            text = text.replace("<" + num_filled_in + ">", word); //replaces placeholder with word
            Log.d("story-class", "replacing placeholder <" + num_filled_in + "> with " + word);
            num_filled_in++;
        }
    }

    // all placeholder filled in function
    // Returns true if all placeholders have been filled in
    public boolean
    all_placeholders_filled_in()
    {
        return num_filled_in >= placeholders.size();
    }

    // get next placeholder function
    // Returns the next placeholder for the user to fill in
    public String
    get_next_placeholder()
    {
        if (all_placeholders_filled_in())
        {
            return ""; // return empty string if all placeholders are filled
        }
        else
        {
            return placeholders.get(num_filled_in);
        }
    }

    // get placeholder remaining count function
    // Returns how many placeholders are left that is unfilled
    public int
    get_placeholder_remaining_count()
    {
        return placeholders.size() - num_filled_in;
    }

    // get placeholders count function
    // Returns the total number of placeholders already filled in
    public int
    get_placeholders_count()
    {
        return placeholders.size();
    }

    // clear story function
    // Resets the story back to an empty initial state
    public void
    clear_story()
    {
        text = "";
        placeholders.clear();
        num_filled_in = 0;
    }

    // to string function
    // returns  the story text
    public String
    to_string()
    {
        return text;
    }

    // set html mode on function
    // Set the html flag to true and enable bold text for placeholders
    public void
    set_html_mode_on()
    {
        html_mode = true;
    }

    // set html mode off function
    // Set the html flag to false and disable bold text for placeholders
    public void
    set_html_mode_off()
    {
        html_mode = false;
    }
}