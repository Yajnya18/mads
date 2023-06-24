package com.example.mads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity2 extends AppCompatActivity {
    private Button buttonGK;
    private Button buttonBollywood;
    private Button buttonKids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttonGK = findViewById(R.id.buttonGK);
        buttonBollywood = findViewById(R.id.buttonBollywood);
        buttonKids = findViewById(R.id.buttonKids);

        buttonGK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gkIntent = new Intent(MainActivity2.this,GKActivity.class);
                startActivity(gkIntent);
            }
        });
        buttonBollywood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent byIntent = new Intent(MainActivity2.this,BYActivity.class);
                startActivity(byIntent);
            }
        });
        buttonKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kdIntent = new Intent(MainActivity2.this,KDActiyity.class);
                startActivity(kdIntent);
            }
        });


    }
}