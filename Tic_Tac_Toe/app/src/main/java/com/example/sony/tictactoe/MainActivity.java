package com.example.sony.tictactoe;

import android.support.v7.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.util.Log;

import org.w3c.dom.Text;

public class MainActivity extends ActionBarActivity {
    //global int for various grid cell types
    final int EMPTY = 0;
    final int X = 1;
    final int O = 2;

    // 0(false) - player 1[X]
    // 1(true) - player 2[O]
    boolean user_turn = false;
    boolean game_over = false;
    int marked_grid_cell_count = 0;
    public static int[][] grid_board = new int[3][3]; // 3x3 grid
    /*
        1 | 2 | 3
        ---------
        4 | 5 | 6
        ---------
        7 | 8 | 9
     */

    // Mark the specified coordinate of the grid based on which player turn it is
    public void
    mark_specific_grid_cell(int row, int col)
    {
        if (!user_turn) //player 1 [X]
        {
            grid_board[row][col] = X;
            set_button_display(row, col);
            //switch to other user turn
            user_turn = !user_turn;
            marked_grid_cell_count++; // increment the number of marked grid cells
        }
        else if(user_turn) // player 2 [O]
        {
            grid_board[row][col] = O;
            set_button_display(row, col);
            //switch to other user turn
            user_turn = !user_turn;
            marked_grid_cell_count++; // increment the number of marked grid cells
        }
    }

    // Function to set specified button to display X or O depending on which player's turn
    public void
    set_button_display(int row, int col)
    {
        Button button_pressed;
        if (row == 0 && col == 0) //button 1
        {
            button_pressed = (Button) findViewById(R.id.button1);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 0 && col == 1) //button 2
        {
            button_pressed = (Button) findViewById(R.id.button2);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 0 && col == 2) //button 3
        {
            button_pressed = (Button) findViewById(R.id.button3);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 1 && col == 0) //button 4
        {
            button_pressed = (Button) findViewById(R.id.button4);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 1 && col == 1) //button 5
        {
            button_pressed = (Button) findViewById(R.id.button5);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 1 && col == 2) //button 6
        {
            button_pressed = (Button) findViewById(R.id.button6);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 2 && col == 0) //button 7
        {
            button_pressed = (Button) findViewById(R.id.button7);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 2 && col == 1) //button 8
        {
            button_pressed = (Button) findViewById(R.id.button8);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
        else if (row == 2 && col == 2) //button 9
        {
            button_pressed = (Button) findViewById(R.id.button9);
            if (!user_turn) //player 1
            {
                button_pressed.setText("  X");
            }
            else //player 2
            {
                button_pressed.setText("  O");
            }
        }
    }

    //This function gets invoke when a player clicks on a button
    public void
    mark_grid(View view)
    {
        if (game_over)
        {
            return;
        }
        int button_pressed_id = view.getId(); //get ID of the button that was pressed by the user

        //mark the corresponding grid cell accordingly based on player turn and if cell is empty or not
        if (R.id.button1 == button_pressed_id && grid_board[0][0] == EMPTY) // button 1 was pressed
        {
            mark_specific_grid_cell(0,0);
        }
        else if (R.id.button2 == button_pressed_id && grid_board[0][1] == EMPTY) // button 2 was pressed
        {
            mark_specific_grid_cell(0,1);
        }
        else if (R.id.button3 == button_pressed_id && grid_board[0][2] == EMPTY) // button 3 was pressed
        {
            mark_specific_grid_cell(0,2);
        }
        else if (R.id.button4 == button_pressed_id && grid_board[1][0] == EMPTY) // button 4 was pressed
        {
            mark_specific_grid_cell(1,0);
        }
        else if (R.id.button5 == button_pressed_id && grid_board[1][1] == EMPTY) // button 5 was pressed
        {
            mark_specific_grid_cell(1,1);
        }
        else if (R.id.button6 == button_pressed_id && grid_board[1][2] == EMPTY) // button 6 was pressed
        {
            mark_specific_grid_cell(1,2);
        }
        else if (R.id.button7 == button_pressed_id && grid_board[2][0] == EMPTY) // button 7 was pressed
        {
            mark_specific_grid_cell(2,0);
        }
        else if (R.id.button8 == button_pressed_id && grid_board[2][1] == EMPTY) // button 8 was pressed
        {
            mark_specific_grid_cell(2,1);
        }
        else if (R.id.button9 == button_pressed_id && grid_board[2][2] == EMPTY) // button 9 was pressed
        {
            mark_specific_grid_cell(2,2);
        }

        //testing purpose
        //print_grid();
        //it takes at least 5 (3 turns for player 1 and 2 turns for player 2) marked cells before there can be a winner
        if (marked_grid_cell_count >= 5)
        {
            determine_winner();
        }
    }

    //Function to print out the internal 2d grid we are using to keep track of the game
    public void
    print_grid()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                Log.d("testing",""+grid_board[i][j] );
            }
        }
    }

    // Function to look at grid board to determine if there is a winner
    public void
    determine_winner()
    {
        boolean has_winner = false;
        //traverse through grid board looking for winning pattern(s)
        for(int i = 0; i < 3; i++)
        {
            // if there is 3 in a row of O or X there is a winner
            if ( ( (grid_board[i][0] != EMPTY && grid_board[i][1] != EMPTY && grid_board[i][2] != EMPTY) && grid_board[i][0] == grid_board[i][1] && grid_board[i][1] == grid_board[i][2]) //horizontal and valid cells
               ||( (grid_board[0][i] != EMPTY && grid_board[1][i] != EMPTY && grid_board[2][i] != EMPTY) && grid_board[0][i] == grid_board[1][i] && grid_board[1][i] == grid_board[2][i]) //vertical and valid cells
               ||( (grid_board[0][0] != EMPTY && grid_board[1][1] != EMPTY && grid_board[2][2] != EMPTY) && grid_board[0][0] == grid_board[1][1] && grid_board[1][1] == grid_board[2][2]) //diagonal (downward) and valid cells
               ||( (grid_board[0][2] != EMPTY && grid_board[1][1] != EMPTY && grid_board[2][0] != EMPTY) && grid_board[0][2] == grid_board[1][1] && grid_board[1][1] == grid_board[2][0]) ) //diagonal (upward) and valid cells
            {
                // we switched turn to the other player before this function thus logic
                // in this function ONLY is reverse
                // e.g. user_turn = true (1) -> player 1
                TextView text_view = (TextView) findViewById(R.id.winner_text_view);
                if (!user_turn) //player 2
                {
                    text_view.setText("Player 2 Wins!");
                }
                else //player 1
                {
                    text_view.setText("Player 1 Wins!");
                }
                has_winner = true;
                game_over = true;
            }
        }

        //check if there was a draw
        if (!has_winner && marked_grid_cell_count >= 9)
        {
            TextView text_view = (TextView) findViewById(R.id.winner_text_view);
            text_view.setText("Draw!");
        }
    }

    // Function to reset the game
    public void
    reset_game(View view)
    {
        game_over = false;
        marked_grid_cell_count = 0; //set grid cell count back to 0
        user_turn = false; // player 1 turn

        //reset internal 2d grid
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                grid_board[i][j] = EMPTY;
            }
        }
        //reset all the buttons
        reset_buttons();
        //reset winner message
        TextView text_view = (TextView) findViewById(R.id.winner_text_view);
        text_view.setText("");
    }

    //function to reset each button
    public void
    reset_buttons()
    {
        //reset button 1
        Button button = (Button) findViewById(R.id.button1);
        button.setText("");

        //reset button 2
        button = (Button) findViewById(R.id.button2);
        button.setText("");

        //reset button 3
        button = (Button) findViewById(R.id.button3);
        button.setText("");

        //reset button 4
        button = (Button) findViewById(R.id.button4);
        button.setText("");

        //reset button 5
        button = (Button) findViewById(R.id.button5);
        button.setText("");

        //reset button 6
        button = (Button) findViewById(R.id.button6);
        button.setText("");

        //reset button 7
        button = (Button) findViewById(R.id.button7);
        button.setText("");

        //reset button 8
        button = (Button) findViewById(R.id.button8);
        button.setText("");

        //reset button 9
        button = (Button) findViewById(R.id.button9);
        button.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
