package com.example.djakaumbarawurung.persipantestcept.manage_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.djakaumbarawurung.persipantestcept.R;

/* activity untuk menambahkan soal grammar */
public class TambahSoalGrammarActivity extends AppCompatActivity {
    EditText etQuestion, etAnswer, etExplanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_soal_grammar);

        etQuestion = (EditText) findViewById(R.id.etQuestion);
        etAnswer = (EditText) findViewById(R.id.etAnswer);
        etExplanation = (EditText) findViewById(R.id.etExplanation);
    }
}
