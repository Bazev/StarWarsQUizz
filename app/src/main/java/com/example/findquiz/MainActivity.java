package com.example.findquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findquiz.pojos.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    private static final String KEY_BTN_RESTART = "btnRestart";
    private static final String KEY_BTN_TRUE = "btnTrue";
    private static final String KEY_BTN_FALSE = "btnFalse";
    private static final String KEY_TV_QUESTION = "tvQuestion";

    private TextView tvQuestion;
    private TextView tvScore;
    private int score = 0;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnRestart;
    private int indexQuestion = 0;
    private int btnRestartVisibility = 0;
    private List<Question> questions = new ArrayList<>();
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate() called");

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
        questions.add(new Question(getString(R.string.question_8), true));
        questions.add(new Question(getString(R.string.question_9), false));
        questions.add(new Question(getString(R.string.question_10), true));

        if (savedInstanceState != null) {
            indexQuestion = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
            btnRestartVisibility = savedInstanceState.getInt(KEY_BTN_RESTART);
            btnTrue.setVisibility(savedInstanceState.getInt(KEY_BTN_TRUE));
            btnFalse.setVisibility(savedInstanceState.getInt(KEY_BTN_FALSE));
            tvQuestion.setVisibility(savedInstanceState.getInt(KEY_TV_QUESTION));
            btnRestart.setVisibility(btnRestartVisibility);
        }

        if (savedInstanceState != null && btnRestartVisibility == View.VISIBLE) {
            question = questions.get(questions.size() - 1);
            tvQuestion.setText(question.getText());
        } else {
            nextQuestion();
        }

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
                tvScore.setText(String.format("score : %d", score));
                btnRestart.setVisibility(View.INVISIBLE);
                makeQuizVisible();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
        outState.putInt(KEY_INDEX, indexQuestion);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_BTN_RESTART, btnRestart.getVisibility());
        outState.putInt(KEY_BTN_TRUE, btnTrue.getVisibility());
        outState.putInt(KEY_BTN_FALSE, btnFalse.getVisibility());
        outState.putInt(KEY_TV_QUESTION, tvQuestion.getVisibility());
    }

    private void endGame() {
        indexQuestion++;
        if (indexQuestion >= questions.size()) {
            tvScore.setText(String.format("Votre score final est de : %d", score));
            btnRestart.setVisibility(View.VISIBLE);
            makeQuizInvisible();
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
        if (indexQuestion < questions.size()) {
            score++;
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void makeQuizInvisible() {
        btnTrue.setVisibility(View.INVISIBLE);
        btnFalse.setVisibility(View.INVISIBLE);
        tvQuestion.setVisibility(View.INVISIBLE);
    }

    private void makeQuizVisible() {
        btnFalse.setVisibility(View.VISIBLE);
        btnTrue.setVisibility(View.VISIBLE);
        tvQuestion.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestro() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
}
