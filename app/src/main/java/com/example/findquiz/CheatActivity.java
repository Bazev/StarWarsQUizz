package com.example.findquiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.findquiz.pojos.Question;

public class CheatActivity extends AppCompatActivity {

    private TextView tvReponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        tvReponse = findViewById(R.id.tvReponse);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //récupère l'intent qui a appelé cette activity
        Intent intent = getIntent();

        // récupère la question
        Question question = (Question)intent.getSerializableExtra(MainActivity.KEY_QUESTION);

        tvReponse.setText(String.format("La réponse est : "+question.isAnswer()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}