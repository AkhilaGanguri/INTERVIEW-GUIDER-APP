package com.example.interviewguider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class BlanksActivity extends AppCompatActivity {
    private TextView questionTextView;
    private EditText answerEditText;
    private Button checkButton;
    private int score = 0;
    private int currentQuestionIndex = 0;

    private final String[] questions = {
            "1)<a _____\"https://www.interviewguider.com\">This is a link</a>",
            "2)set the color of the paragraph to \"blue\".<p style=________>This is a paragraph.</p>",
            "3)(strikeout) the letters \"blue\" in the text below.<p>My favorite color is ____ blue ____ red.",
            "4)CSS Grid Layout:\n" +
                    ".container {\n" +
                    "   display: _____;\n" +
                    "   grid-template-columns: repeat(3, 1fr);\n" +
                    "   gap: 20px;" +
                    "}",
            "5)CSS Animations:\n" +
                    "@keyframes slide {\n" +
                    "   ______{ left: 0; }\n" +
                    "   ____{ left: 100%; }\n" +
                    "}",
            "6)Fetch API:\n" +
                    "fetch('https://api.example.com/data')\n" +
                    "   .then(response => response.json())\n" +
                    "   .then(data => console.log(data))\n" +
                    "   .catch(error => console.error('Error:',______));",
            "7)Async/Await:\n" +
                    "   let response =______fetch('https://api.example.com/data');\n" +
                    "   let data = await response.json();",
            "8)ES6 Classes:\n" +
                    "class Car {\n" +
                    "   constructor(make, model) {\n" +
                    "      this.make = make;\n" +
                    "      this.model = model;\n" +
                    "   }\n" +
                    " displayInfo() {\n" +
                    "      console.log(`Car: ${_______} ${_______}`);\n" +
                    "   }}",
            "9)Custom Checkbox:\n" +
                    "<input type=\"________\" id=\"customCheckbox\">",
            "10)React State:\n" +
                    "import React, { useState } from 'react';\n" +
                    "const Counter = () => {\n" +
                    "   const [count, setCount] = _______;"
            // Add more questions here
    };

    private final String[] answers = {
            "href",
            "\"color:blue;\"",
            "<del></del>",
            "grid",
            "from to",
            "error",
            "await",
            "this.makethis.mode",
            "checkbox",
            "useState(0)"

            // Add more answers here
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blanks);

        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        checkButton = findViewById(R.id.checkButton); // Changed to checkButton

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        displayQuestion();

        // Removed the redundant click listener from checkButton

    }


    private void displayQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        answerEditText.setText("");
    }

    private void checkAnswer() {
        String userAnswer = answerEditText.getText().toString().trim();
        String correctAnswer = answers[currentQuestionIndex];

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            score++;
        }

        // Move to the next question
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            displayQuestion();
        } else {
            // Quiz is finished, you can display a message or take further action.
            Intent intent = new Intent(BlanksActivity.this, Scoretech.class);
            intent.putExtra("score", score);
            startActivity(intent);
            answerEditText.setEnabled(false);
            checkButton.setEnabled(false);
        }
    }
    public void goTodomain(View view) {
        Intent intent = new Intent(this, DomainActivity.class);
        startActivity(intent);
    }
}
