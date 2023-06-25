package com.example.mads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button buttonGK = findViewById(R.id.buttonGK);
        Button buttonFilm = findViewById(R.id.buttonBollywood);
        Button buttonSports = findViewById(R.id.buttonKids);
        /*general knowledge button*/
        buttonGK.setOnClickListener(v -> {
            Intent gkIntent = new Intent(MainActivity2.this, QuizActivity.class);
            gkIntent.putExtra("category","9");
            startActivity(gkIntent);
        });
        /* Film button */
        buttonFilm.setOnClickListener(v -> {
            Intent byIntent = new Intent(MainActivity2.this, QuizActivity.class);
            byIntent.putExtra("category","11");
            startActivity(byIntent);
        });
        /*Sports button*/
        buttonSports.setOnClickListener(v-> {
            Intent kdIntent = new Intent(MainActivity2.this, QuizActivity.class);
            kdIntent.putExtra("category","21");
            startActivity(kdIntent);
        });


    }

}