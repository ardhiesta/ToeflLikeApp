package com.example.djakaumbarawurung.persipantestcept;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Grammar;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Grammar2;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Opsi;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.UserLog;
import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ActivityKuisGrammarV2 extends AppCompatActivity {
    TextView tvSoal, tvOpsi1,
            tvOpsi2, tvOpsi3, tvOpsi4;
    String jawabanUser = "";
    DataSource_PenghubungTabel dataSource_penghubungTabel;
    Button bNext;
    ArrayList<Integer> alidPertanyaan = new ArrayList<>();
    int indexGrammar = 0;
    ArrayList<UserLog> userLogArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis_grammar_v2);

        tvSoal = (TextView) findViewById(R.id.tvPertanyaanGrammar);
        tvOpsi1 = (TextView) findViewById(R.id.tvPilihan1Grammar);
        tvOpsi2 = (TextView) findViewById(R.id.tvPilihan2Grammar);
        tvOpsi3 = (TextView) findViewById(R.id.tvPilihan3Grammar);
        tvOpsi4 = (TextView) findViewById(R.id.tvPilihan4Grammar);
        bNext = (Button) findViewById(R.id.bNextGrammar);

        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();

        alidPertanyaan = dataSource_penghubungTabel.mengambilsemuaIdGrammar();
        acakIdPertanyaan();

        //tombol next tidak bisa diklik kecuali user telah memilih sebuah opsi untuk menjawab soal
        bNext.setEnabled(false);
    }

    private void acakIdPertanyaan() {
        long seed = System.nanoTime();
        if (alidPertanyaan.size() > 0) {
            Collections.shuffle(alidPertanyaan, new Random(seed));

            int idPertanyaan = alidPertanyaan.get(0);
            bentukKuis(idPertanyaan);
        } else {
            Toast.makeText(this, "Pertanyaan Sudah Habis", Toast.LENGTH_LONG).show();
        }
    }

    private void bentukKuis(int idpertanyaan) {
        Grammar2 grammar2 = dataSource_penghubungTabel.ambilGrammarSesuaiId(idpertanyaan);
        tvSoal.setText(grammar2.getPertanyaan());
        ArrayList<Opsi> opsiArrayList = grammar2.getOpsiArrayList();
        for (int i = 0; i < opsiArrayList.size(); i++) {
            Opsi opsi = opsiArrayList.get(i);
            if (opsi.getOpsi().toLowerCase().startsWith("a")) {
                tvOpsi1.setText(opsi.getOpsi());
            } else if ((opsi.getOpsi().toLowerCase().startsWith("b"))) {
                tvOpsi2.setText(opsi.getOpsi());
            } else if (opsi.getOpsi().toLowerCase().startsWith("c")) {
                tvOpsi3.setText(opsi.getOpsi());
            } else {
                tvOpsi4.setText(opsi.getOpsi());
            }
        }
    }

    public void opsi1KlikGrammar(View view) {
        //ambil Abjad Pilihan User
        setOpsi(tvOpsi1);
        jawabanUser = tvOpsi1.getText().toString();
        bNext.setEnabled(true);
    }

    public void opsi2KlikGrammar(View view) {
        setOpsi(tvOpsi2);
        jawabanUser = tvOpsi2.getText().toString();
        bNext.setEnabled(true);
    }

    public void opsi3KlikGrammar(View view) {
        setOpsi(tvOpsi3);
        jawabanUser = tvOpsi3.getText().toString();
        bNext.setEnabled(true);
    }

    public void opsi4KlikGrammar(View view) {
        setOpsi(tvOpsi4);
        jawabanUser = tvOpsi4.getText().toString();
        bNext.setEnabled(true);
    }

    private void setOpsi(TextView tvdipilih) {
        netralkanOpsi();
        tvdipilih.setTypeface(null, Typeface.BOLD);
        tvdipilih.setTextColor(Color.parseColor("#303f9f"));
    }

    public void netralkanOpsi() {
        jawabanUser = "";
        bNext.setEnabled(false);

        tvOpsi1.setTypeface(null, Typeface.NORMAL);
        tvOpsi2.setTypeface(null, Typeface.NORMAL);
        tvOpsi3.setTypeface(null, Typeface.NORMAL);
        tvOpsi4.setTypeface(null, Typeface.NORMAL);
        tvOpsi1.setTextColor(Color.parseColor("#000000"));
        tvOpsi2.setTextColor(Color.parseColor("#000000"));
        tvOpsi3.setTextColor(Color.parseColor("#000000"));
        tvOpsi4.setTextColor((Color.parseColor("#000000")));
    }

    @Override
    protected void onResume() {
        dataSource_penghubungTabel.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource_penghubungTabel.close();
        super.onPause();
    }

    public void tampilkanPertanyaanGrammarSelanjutnya(View view) {
        // tombol Next setelah sampai pada pertanyaan terakhir untuk narasi terakhir akan menampilkan activityCekJawaban
        if (bNext.getText().toString().equalsIgnoreCase("CHECK ANSWER")) {
            System.out.println(userLogArrayList.size());
            System.out.println("");
        } else {
            if (!jawabanUser.equals("")) {
                UserLog userLog = new UserLog();
                userLog.setJawabanUser(jawabanUser);
                Grammar2 grammar2 = dataSource_penghubungTabel.ambilGrammarSesuaiId(alidPertanyaan.get(indexGrammar));
                userLog.setKunci(grammar2.getJawaban());
                userLog.setPenjelasan(grammar2.getPenjelasan());
                userLog.setPertanyaan(grammar2.getPertanyaan());
                System.out.println("");

                if (indexGrammar < alidPertanyaan.size() - 1){
                    indexGrammar++;
                    netralkanOpsi();
                    int idPertanyaan = alidPertanyaan.get(indexGrammar);
                    bentukKuis(idPertanyaan);
                    userLogArrayList.add(userLog);
                } else {
                    bNext.setText("CHECK ANSWER");
                }
            }
        }
    }
}
