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
    int indexQuestion = 0;
    List<Question> questions = new ArrayList<>();
    Question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvQuestion = findViewById(R.id.tvQuestion);
        TextView tvScore = findViewById(R.id.tvScore);
        Button btnTrue = findViewById(R.id.btnTrue);
        Button btnFalse = findViewById(R.id.btnFalse);

        questions.add(new Question(getString(R.string.question_1), false));
        questions.add(new Question(getString(R.string.question_2), true));
        questions.add(new Question(getString(R.string.question_3), false));
        questions.add(new Question(getString(R.string.question_4), false));
        questions.add(new Question(getString(R.string.question_5), false));
        questions.add(new Question(getString(R.string.question_6), true));
        questions.add(new Question(getString(R.string.question_7), false));

        question = questions.get(indexQuestion);
        tvQuestion.setText(question.getText());

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.isAnswer()) {
                    score++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_SHORT);
                    toast.show();
                }

                question = questions.get(++indexQuestion);
                tvQuestion.setText(question.getText());
                tvScore.setText("score = " +score);
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!question.isAnswer()) {
                    score++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_SHORT);
                    toast.show();
                }
                question = questions.get(++indexQuestion);
                tvQuestion.setText(question.getText());
                tvScore.setText(String.format("score : %d" , score));
            }
        });
    }
}