package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void tampilkanSoalGrammar(View view) {
        Intent intent = new Intent(ActivityQuiz.this, ActivityKuisGrammar.class);
        startActivity(intent);
    }

    public void paketSoalReading(View view) {
        Intent intent = new Intent(ActivityQuiz.this, ActivityKuisReading.class);
        startActivity(intent);
    }

    public void paketSoalListening(View view) {
        Intent intent = new Intent(ActivityQuiz.this, ActivityListening.class);

//        Intent intent = new Intent(ActivityQuiz.this, ActivityCekJawabanListening.class);
        startActivity(intent);
    }
}
