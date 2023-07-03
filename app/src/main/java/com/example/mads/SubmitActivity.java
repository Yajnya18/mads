package com.example.mads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SubmitActivity extends AppCompatActivity {

    private TextView scoreDisplay;
    private int displayScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        scoreDisplay = findViewById(R.id.score);
        Button playAgain = findViewById(R.id.playAgain);
        playAgain.setOnClickListener(v->{
            Intent nextActivityIntent = new Intent(SubmitActivity.this, MainActivity2.class);
            startActivity(nextActivityIntent);
        });
        scoreDisplay.setText(String.valueOf(displayScore));
        ShowScore();
    }

    private void ShowScore(){
        Timer timer = new Timer();
        if(displayScore == getIntent().getExtras().getInt("score")){
            scoreDisplay.setText(String.valueOf(displayScore++));
            return;
        }else{
            scoreDisplay.setText(String.valueOf(displayScore++));
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ShowScore();
            }
        },50);
    }
}