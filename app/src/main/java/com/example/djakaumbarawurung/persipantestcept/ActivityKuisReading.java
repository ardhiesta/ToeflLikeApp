package com.example.djakaumbarawurung.persipantestcept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.NarasiReading;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.OpsiReading;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.PertanyaanReading;
import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;

import java.util.ArrayList;

public class ActivityKuisReading extends AppCompatActivity {
    TextView TvOpsi1, TvOpsi2, TvOpsi3, TvOpsi4, TvSoal, TvNarasi, TvPenjelasan;
    DataSource_PenghubungTabel dataSource_penghubungTabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();


        TvNarasi = (TextView) findViewById(R.id.TvNarasi);
        TvSoal = (TextView) findViewById(R.id.TVPertanyaanReading);
        TvOpsi1 = (TextView) findViewById(R.id.TvOpsiReading1);
        TvOpsi2 = (TextView) findViewById(R.id.TvOpsiReading2);
        TvOpsi3 = (TextView) findViewById(R.id.TvOpsiReading3);
        TvOpsi4 = (TextView) findViewById(R.id.TvOpsiReading4);
        TvPenjelasan = (TextView) findViewById(R.id.PenjelasanReading);

        TvPenjelasan.setText("");


        // untuk menampilkan pertanyaan reading
        ArrayList<NarasiReading> NarasiReadingArrayList = dataSource_penghubungTabel.ambilNaskahReading();
        NarasiReading narasiReading = NarasiReadingArrayList.get(0);
        TvNarasi.setText(narasiReading.getNarasi());
        ArrayList<PertanyaanReading> pertanyaanReadingArrayList = narasiReading.getPertanyaanReadingArraylist();
        PertanyaanReading pertanyaanReading = pertanyaanReadingArrayList.get(0);
        TvSoal.setText(pertanyaanReading.getPetanyaan());
        ArrayList<OpsiReading> opsiReadingArrayList = pertanyaanReading.getOpsiReadingArrayList();
        for (int i = 0; i < opsiReadingArrayList.size(); i++) {
            OpsiReading opsiReading = opsiReadingArrayList.get(i);
            if (opsiReading.getOpsi().toLowerCase().startsWith("a")) {
                TvOpsi1.setText(opsiReading.getOpsi());
            } else if (opsiReading.getOpsi().toLowerCase().startsWith("b")) {
                TvOpsi2.setText(opsiReading.getOpsi());
            } else if (opsiReading.getOpsi().toLowerCase().startsWith("c")) {
                TvOpsi3.setText(opsiReading.getOpsi());
            } else {
                TvOpsi4.setText(opsiReading.getOpsi());
            }
        }

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
}