package com.talkjarvis.simpletictactoe;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: empty 1: cross 2: circle
    int[] gameState = {0,0,0,0,0,0,0,0,0};
    int[][] winPositions = {{0,1,2},{0,4,8},{0,3,6},{1,4,7},{2,4,6},{2,5,8},{3,4,5},{6,7,8}};
    int activePlayer = 1; // 1,2
    boolean gameActive = true;
    MediaPlayer tickPlayer;



    public void chance(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        tickPlayer.start();


        if(gameState[tappedCounter] == 0 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 2;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
            }

            int filled = 0;
            for(int i = 0; i<9; i++){
                if(gameState[i] != 0)
                    filled++;
            }
            if(filled == 9){
                TextView winnerMessage = (TextView)findViewById(R.id.textView);
                winnerMessage.setText("It's a Draw");

                Button playAgain = (Button)findViewById(R.id.playAgainButton);

                winnerMessage.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
            }

            for(int[] winPosition : winPositions){
                if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 0){
                    //Someone has won
                    String winner ="";

                    gameActive = false;

                    if(activePlayer == 1){
                        winner = "Circle";
                    }else
                        winner = "Cross";

//                    Toast.makeText(this, winner + " won", Toast.LENGTH_LONG).show();
                    TextView winnerMessage = (TextView)findViewById(R.id.textView);
                    winnerMessage.setText(winner + " Won!");

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    winnerMessage.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);

                }

            }

        }

    }

    public void playAgainButton(View view){

        TextView winnerTextMessage = (TextView)findViewById(R.id.textView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        winnerTextMessage.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0; i<grid.getChildCount(); i++){
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i = 0; i<9; i++){
            gameState[i] = 0;
        }
        gameActive = true;
        activePlayer = 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tickPlayer = MediaPlayer.create(this, R.raw.tick);
    }
}
