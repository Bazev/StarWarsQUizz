package com.example.findquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findquiz.pojos.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView tvScore;
    private int score = 0;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnRestart;
    int indexQuestion = 0;
    List<Question> questions = new ArrayList<>();
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        btnRestart = findViewById(R.id.btnRestart);

        questions.add(new Question(getString(R.string.question_1), false));
        questions.add(new Question(getString(R.string.question_2), true));
        questions.add(new Question(getString(R.string.question_3), false));
        questions.add(new Question(getString(R.string.question_4), false));
        questions.add(new Question(getString(R.string.question_5), false));
        questions.add(new Question(getString(R.string.question_6), true));
        questions.add(new Question(getString(R.string.question_7), false));

        nextQuestion();

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.isAnswer()) {
                    incrementScore();
                }
                endGame();
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!question.isAnswer()) {
                    incrementScore();
                }
                endGame();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indexQuestion = 0;
                score = 0;
                nextQuestion();
                btnRestart.setVisibility(View.INVISIBLE);
                tvScore.setText(String.format("score : %d", score));
            }
        });
    }

    private void endGame() {
        indexQuestion++;
        if (indexQuestion >= questions.size()) {
            tvScore.setText(String.format("Votre score final est de : %d", score));
            btnRestart.setVisibility(View.VISIBLE);
        } else {
            nextQuestion();
            tvScore.setText(String.format("score : %d", score));
        }
    }

    private void nextQuestion() {
        question = questions.get(indexQuestion);
        tvQuestion.setText(question.getText());
    }

    private void incrementScore() {
        if (indexQuestion <= questions.size()) {
            score++;
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_SHORT);
        toast.show();
    }

}