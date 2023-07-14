package com.example.mads;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.*;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private static final int noOfOptions = 4;
    private final Button[] optionButtons = new Button[noOfOptions];
    private static final int noOfQuestions = 10;
    private QuizData[] quizData = new QuizData[noOfQuestions];
    private int currentQuestion = 0;
    private int score = 0;
    private int correctOption = 0;

    private  ProgressBar proBar;

    private void loadQuizData(String category) {
        try {
            Gson g = new Gson();
            String link = String.format("https://opentdb.com/api.php?amount=10&category=%s&type=multiple", category);
            URL url = new URL(link);
            //create the connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            //set the request method to GET
            connection.setRequestMethod("GET");
            //get the output stream from the connection you created
            OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
            //write your data to the ouputstream
            request.flush();
            request.close();
            String line ;
            //create your inputsream
            InputStreamReader isr = new InputStreamReader(
                    connection.getInputStream());
            //read in the data from input stream, this can be done a variety of ways
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null){
                sb.append(line).append("\n");
            }
            //get the string version of the response data
            String response = sb.toString();
            //parse the json object to Array of quizData
            JSONObject obj = new JSONObject(response);
            quizData = g.fromJson(obj.get("results").toString(), quizData.getClass());
            //always remember to close your input and output streams
            isr.close();
            reader.close();
           // proBar.setVisibility(View.GONE);
        } catch (IOException e) {
            Log.e("HTTP GET:", e.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gkactivity);
       // proBar = findViewById(R.id.progressBar);
        Thread loadDataThread = new Thread(() -> loadQuizData(getIntent().getExtras().getString("category")));
        loadDataThread.start();
        try {
            loadDataThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        questionTextView = findViewById(R.id.questtxt);
        optionButtons[0] = findViewById(R.id.btna);
        optionButtons[1] = findViewById(R.id.btnb);
        optionButtons[2] = findViewById(R.id.btnc);
        optionButtons[3] = findViewById(R.id.btnd);
        Button submitButton = findViewById(R.id.btnsub);
        Gson g = new Gson();
        Log.i("result", g.toJson(quizData));
        submitButton.setOnClickListener(v -> {
            currentQuestion++;
            if (currentQuestion < noOfQuestions) {
                showQuestion();
            } else {
                // All questions answered, go to next activity to display the score
                Intent nextActivityIntent = new Intent(QuizActivity.this, SubmitActivity.class);
                nextActivityIntent.putExtra("score", score);
                startActivity(nextActivityIntent);
            }
        });

        showQuestion();
    }

    private void randomizeAndSetOptions(QuizData currentQuizQuestion) {
        correctOption = new Random().nextInt(noOfOptions);
        int incorrectOptionIndex = 0;
        for (int i = 0; i < noOfOptions; i++) {
            //assing the correct to a random option
            if (i == correctOption) {
                optionButtons[i].setText(StringEscapeUtils.unescapeHtml4(currentQuizQuestion.correct_answer));
            } else {
                optionButtons[i].setText(StringEscapeUtils.unescapeHtml4(currentQuizQuestion.incorrect_answers[incorrectOptionIndex]));
                incorrectOptionIndex++;
            }
            //set the button color and enable
            optionButtons[i].setBackgroundColor(Color.parseColor("#333333"));
            optionButtons[i].setEnabled(true);
            optionButtons[i].setOnClickListener(view -> checkAnswer());
        }
    }

    private void showQuestion() {
        QuizData currentQuizQuestion = quizData[currentQuestion];
        //set the question
        questionTextView.setText(StringEscapeUtils.unescapeHtml4(currentQuizQuestion.question));
        //set the options
        randomizeAndSetOptions(currentQuizQuestion);
    }

    private void checkAnswer() {
        for (int i = 0; i < noOfOptions; i++) {
            if (i == correctOption) {
                //set the button color and enable
                if (optionButtons[i].isPressed()) {
                    score++;
                }
                optionButtons[i].setBackgroundColor(Color.parseColor("#1cbc51"));
                optionButtons[i].setEnabled(false);
            } else {
                //set the button color and enable
                optionButtons[i].setBackgroundColor(Color.parseColor("#e83046"));
                optionButtons[i].setEnabled(false);
            }
        }
    }
}


