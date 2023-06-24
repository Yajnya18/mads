package com.example.mads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GKActivity extends AppCompatActivity {
    private TextView questionTextView;
    private ImageView questionImageView;
    private Button option1Button;
    private Button option2Button;
    private Button option3Button;
    private Button option4Button;
    private Button submitButton;

    private String[] questions = {
            "Question 1",
            "Question 2",
            // Add more questions here
            "Question 10"
    };

    /*private int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            // Add more images here
            R.drawable.image10
    };*/

    private String[][] options = {
            {"Option A1", "Option B1", "Option C1", "Option D1"},
            {"Option A2", "Option B2", "Option C2", "Option D2"},
            // Add more options for each question here
            {"Option A10", "Option B10", "Option C10", "Option D10"}
    };

    private int[] correctAnswers = {1, 2, 3, 4, 1, 2, 3, 4, 1, 2};
    private int currentQuestion = 1;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gkactivity);

        questionTextView = findViewById(R.id.questtxt);
        questionImageView = findViewById(R.id.imageView);
        option1Button = findViewById(R.id.btna);
        option2Button = findViewById(R.id.btnb);
        option3Button = findViewById(R.id.btnc);
        option4Button = findViewById(R.id.btnd);
        submitButton = findViewById(R.id.btnsub);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    showQuestion();
                } else {
                    // All questions answered, go to next activity to display the score
                    Intent nextActivityIntent = new Intent(GKActivity.this, SubmitActivity.class);
                    nextActivityIntent.putExtra("score", score);
                    startActivity(nextActivityIntent);
                }
            }
        });

        showQuestion();
    }

    private void showQuestion() {
        questionTextView.setText(questions[currentQuestion]);
        //questionImageView.setImageResource(images[currentQuestion]);

        option1Button.setText(options[currentQuestion][0]);
        option2Button.setText(options[currentQuestion][1]);
        option3Button.setText(options[currentQuestion][2]);
        option4Button.setText(options[currentQuestion][3]);

        // Reset button background and enable all buttons for new question
        option1Button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        option2Button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        option3Button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        option4Button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        option1Button.setEnabled(true);
        option2Button.setEnabled(true);
        option3Button.setEnabled(true);
        option4Button.setEnabled(true);
    }

    private void checkAnswer() {
        int selectedOption = 0;

        if (option1Button.isPressed()) {
            selectedOption = 1;
        } else if (option2Button.isPressed()) {
            selectedOption = 2;
        } else if (option3Button.isPressed()) {
            selectedOption = 3;
        } else if (option4Button.isPressed()) {
            selectedOption = 4;
        }

        if (selectedOption == correctAnswers[currentQuestion]) {
            score++;
            // Set button background to green for the selected option
            switch (selectedOption) {
                case 1:
                    option1Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 2:
                    option2Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 3:
                    option3Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 4:
                    option4Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
            }
        } else {
            // Set button background to red for the selected option
            switch (selectedOption) {
                case 1:
                    option1Button.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    break;
                case 2:
                    option2Button.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    break;
                case 3:
                    option3Button.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    break;
                case 4:
                    option4Button.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    break;
            }

            // Set button background to green for the correct option
            switch (correctAnswers[currentQuestion]) {
                case 1:
                    option1Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 2:
                    option2Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 3:
                    option3Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 4:
                    option4Button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    break;
            }
        }

        // Disable all buttons after submitting the answer
        option1Button.setEnabled(false);
        option2Button.setEnabled(false);
        option3Button.setEnabled(false);
        option4Button.setEnabled(false);
    }
}


