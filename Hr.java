package com.example.interviewguider;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.RECORD_AUDIO;

public class Hr extends AppCompatActivity {
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecognizer;
    private TextView textView;
    private List<String> questions;
    private int currentQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hr);

        // Request audio recording permission
        ActivityCompat.requestPermissions(this, new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);

        textView = findViewById(R.id.textView);
        intentRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Set maximum possible values for complete silence before and after speech input
        intentRecognizer.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, Long.MAX_VALUE);
        intentRecognizer.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, Long.MAX_VALUE);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onError(int errorCode) {
                // Handle speech recognition errors here
                // You can display an error message to the user
                textView.setText("Speech recognition error. Please try again.");
            }

            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String string = "";
                if (matches != null && matches.size() > 0) {
                    string = matches.get(0);
                    textView.setText(string);
                }
            }
        });

        // Initialize your questions and set the current question index to 0
        questions = new ArrayList<>();
        questions.add("Question 1: Introduce yourself?");
        questions.add("Question 2: Can you describe a challenging situation you faced at work or in your life?");
        questions.add("Question 3: What is your greatest strength?");
        questions.add("Question 4: Why should we hire you?");
        questions.add("Question 5: Describe any project that you had done?");
        currentQuestionIndex = 0;
        displayCurrentQuestion();
    }

    public void StartButton(View view) {
        speechRecognizer.startListening(intentRecognizer);
        textView.setText("Listening..."); // Provide feedback to the user
    }

    public void StopButton(View view) {
        speechRecognizer.stopListening();
        textView.setText("Speech recognition stopped."); // Provide feedback to the user
    }

    public void NextButton(View view) {
        // Move to the next question and display it
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayCurrentQuestion();
            textView.setText("Press 'Start' to answer the next question.");
        } else {
            // You've reached the end of the questions; you can handle this as needed
            // For example, show a message or end the interview
            textView.setText("Go to the tips to get suggestions for the HR questions");
        }
    }

    // Helper method to display the current question
    private void displayCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            String currentQuestion = questions.get(currentQuestionIndex);
            TextView questionTextView = findViewById(R.id.questionTextView);
            questionTextView.setText(currentQuestion);

            // Clear the recognition result text
            textView.setText("");
        }
    }
}
