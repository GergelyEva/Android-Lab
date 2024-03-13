package com.example.androidlab7_8tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int scoreX = 0;
    int scoreO = 0;
    private int clickNumber = 0;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);
        final Button button7 = findViewById(R.id.button7);
        final Button button8 = findViewById(R.id.button8);
        final Button button9 = findViewById(R.id.button9);

        final TextView scorePlayerX = findViewById(R.id.scoreX);
        final TextView scorePlayerO = findViewById(R.id.scoreO);

        // Array to hold the buttons for X and 0
        final Button[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8, button9};

        final Button restartbttn = findViewById(R.id.restartbtn);
        restartbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Button button : buttons) {
                    button.setText("");
                }
                gameOver = false;
                clickNumber = 0;
            }
        });

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if the game isn;t over, it gets the text from the buttons index
                    if (!gameOver && buttons[index].getText().toString().isEmpty()) {
                        //Based on th enumber of clicks, it sets the buttons to X or 0
                        if (clickNumber % 2 == 0) {
                            buttons[index].setText("X");
                        } else {
                            buttons[index].setText("O");
                        }
                        clickNumber++;

                        // Checks if anyone won the game, if not then the game continues
                        if (checkWinCondition(buttons)) {
                            String winner = (clickNumber % 2 == 0) ? "O" : "X";
                            //Setting the values of the score keeping textviews
                            if (winner.equals("O")) {
                                scoreO++;
                                scorePlayerO.setText(String.valueOf(scoreO));
                            } else {
                                scoreX++;
                                scorePlayerX.setText(String.valueOf(scoreX));
                            }
                            //Toast to show who won
                            Toast.makeText(MainActivity.this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();

                            gameOver = true;
                        } else if (clickNumber == 9) {
                            Toast.makeText(MainActivity.this, "It's a draw!", Toast.LENGTH_SHORT).show();
                            gameOver = true;
                        }
                    }
                }
            });
        }
    }

    // Method to check win condition, 2D array as requested by the exercise description,
    //to keep track of the buttons/positions of the spaces for X and 0
    private boolean checkWinCondition(Button[] buttons) {
        int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};

        for (int[] winPos : winPositions) {
            if (!buttons[winPos[0]].getText().toString().isEmpty()
                    && buttons[winPos[0]].getText().equals(buttons[winPos[1]].getText())
                    && buttons[winPos[1]].getText().equals(buttons[winPos[2]].getText())) {
                return true;
            }
        }
        return false;
    }
}
