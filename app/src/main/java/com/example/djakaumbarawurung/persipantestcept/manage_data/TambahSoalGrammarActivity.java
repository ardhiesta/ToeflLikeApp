package com.example.djakaumbarawurung.persipantestcept.manage_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.djakaumbarawurung.persipantestcept.R;
import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;

/* activity untuk menambahkan soal grammar */
public class TambahSoalGrammarActivity extends AppCompatActivity {
    EditText etQuestion, etAnswer, etExplanation, etOpsi1, etOpsi2, etOpsi3, etOpsi4;
    private DataSource_PenghubungTabel dataSource_penghubungTabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_soal_grammar);

        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();

        etQuestion = (EditText) findViewById(R.id.etQuestion);
        etAnswer = (EditText) findViewById(R.id.etAnswer);
        etExplanation = (EditText) findViewById(R.id.etExplanation);
        etOpsi1 = (EditText) findViewById(R.id.etTambahOpsiGrammar1);
        etOpsi2 = (EditText) findViewById(R.id.etTambahOpsiGrammar2);
        etOpsi3 = (EditText) findViewById(R.id.etTambahOpsiGrammar3);
        etOpsi4 = (EditText) findViewById(R.id.etTambahOpsiGrammar4);
    }

    public void simpanPertanyaanGrammarBaru(View view){
        String pertanyaan = etQuestion.getText().toString();
        String jawaban = etAnswer.getText().toString();
        String penjelasan = etExplanation.getText().toString();
        String opsi1 = etOpsi1.getText().toString();
        String opsi2 = etOpsi2.getText().toString();
        String opsi3 = etOpsi3.getText().toString();
        String opsi4 = etOpsi4.getText().toString();

        long newGrammarId = 0;

        if (!pertanyaan.equalsIgnoreCase("") && !jawaban.equalsIgnoreCase("")
                && !penjelasan.equalsIgnoreCase("")){
            newGrammarId = dataSource_penghubungTabel.insertDataKeGrammarTanpaId(pertanyaan, jawaban, penjelasan);
            if (newGrammarId > 0){
                dataSource_penghubungTabel.insertOpsiGrammar(opsi1, newGrammarId);
                dataSource_penghubungTabel.insertOpsiGrammar(opsi2, newGrammarId);
                dataSource_penghubungTabel.insertOpsiGrammar(opsi3, newGrammarId);
                dataSource_penghubungTabel.insertOpsiGrammar(opsi4, newGrammarId);
            }
        }
    }
}
