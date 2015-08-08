package com.example.sony.todolist;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends ActionBarActivity {
    private ArrayList<String> tasks;            // list of the tasks the user entered
    private TextToSpeech tts_engine;             // text to speech engine
    private boolean speech_is_ready = false;    // indicates whether text to speech engine is loaded
    private ArrayAdapter<String> string_adapter;// Adapter to put ArrayList to ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // read from file in internal storage
        tasks = read_entire_file();

        // set up the ListView to display the lines from the file
        ListView my_list_view = (ListView) findViewById(R.id.to_do_list);
        string_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);
        my_list_view.setAdapter(string_adapter);

        // set up event listener for clicks on the list; use TextToSpeech engine to read the clicked item
        my_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void
            onItemClick(AdapterView<?> parent, View view, int index, long id)
            {
                handle_click(index);
            }
        });

        // set up event listener for long clicks on the list; deletes the long clicked item
        my_list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean
            onItemLongClick(AdapterView<?> parent, View view, int index, long id)
            {
                handle_long_click(index);
                return true;
            }
        });

        // set up text-to-speech engine
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
     * Reads the lines of the file with the given resource ID, returning
     * them as an array of strings.
     * We assume the file with the given ID exists in the res/raw directory.
     */
    private ArrayList<String>
    read_entire_file()
    {
        Log.d("testing", "read_entire_file got called ...");
        ArrayList<String> list_of_tasks = new ArrayList<String>();

        // open the file located at the internal storage for this app; read in the list of tasks
        Scanner scan = null;
        try {
            scan = new Scanner(openFileInput("to_do_list.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan != null && scan.hasNextLine())
        {
            String task = scan.nextLine();
            list_of_tasks.add(task);
            Log.d("testing", "read_entire_file is loading from file... " + task);
        }
        return list_of_tasks;
    }

    /*
     * Handles a click on the list item at the given 0-based index.
     * The clicked item from the list of tasks is read out loud by the tts engine.
     */
    private void
    handle_click(int index)
    {
        String text_to_speak = tasks.get(index);
        if (speech_is_ready)
        {
            tts_engine.speak(text_to_speak, TextToSpeech.QUEUE_ADD, null);
        }
    }

    /*
     * Handles a long click on the list item at the given 0-based index.
     * The long clicked item is remove from the list of tasks and the view gets updated to reflect
     * the changes.
     */
    private void
    handle_long_click (int index)
    {
        tasks.remove(index);
        // update ListView
        // When the list of strings associated with the ArrayAdapter change the adapter will reference
        // the newly updated list of strings.
        string_adapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    /*
     * This function adds the user inputted task via EditText to the list of tasks.
     * Once the list gets updated with the new task, the ListView must also update to reflect the
     * change(s).
     */
    public void
    add_task_to_list(View view)
    {
        EditText edit_text = (EditText) findViewById(R.id.user_input_edit_text);
        String user_input = edit_text.getText().toString();

        tasks.add(user_input);
        string_adapter.notifyDataSetChanged(); //since list of tasks got updated we need to update ArrayAdapter

        edit_text.setText("");
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

    /*
     * Gets call when the activity is stopped and wants to save its state.
     * We save the current list of tasks into a file in internal storage.
     */
    @Override
    protected void
    onSaveInstanceState(Bundle out_state)
    {
        super.onSaveInstanceState(out_state);
        Log.d("testing", "onSaveInstanceState got called");
        // write list of tasks to file
        PrintStream out = null;
        try {
            out = new PrintStream(openFileOutput("to_do_list.txt", MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < tasks.size(); i++)
        {
            out.println(tasks.get(i));
        }
        out.close();
    }
}
