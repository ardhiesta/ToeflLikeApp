package com.example.djakaumbarawurung.persipantestcept;

import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.ReadingLog;

import java.util.ArrayList;

public class ActivityCekJawabanReading extends AppCompatActivity {
    TextView tvNarasiCekReading, tvSoalCekReading,
            tvJawabanUserCekReading, tvKunciCekReading, tvPenjelasanCekReading;
    int indexAktivitasUser = 0;
    ArrayList<ReadingLog> aktivitasUserDiKuisReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_jawaban_reading);

        tvNarasiCekReading = (TextView) findViewById(R.id.tvNarasiCekReading);
        tvSoalCekReading = (TextView) findViewById(R.id.tvSoalCekReading);
        tvJawabanUserCekReading = (TextView) findViewById(R.id.tvJawabanUserCekReading);
        tvKunciCekReading = (TextView) findViewById(R.id.tvKunciCekReading);
        tvPenjelasanCekReading = (TextView) findViewById(R.id.tvPenjelasanCekReading);

        aktivitasUserDiKuisReading = getIntent().getParcelableArrayListExtra("aktivitasUser");

        tampilkanAktivitasUserReading(indexAktivitasUser);
    }

    public void tampilkanNextAktivitasUserReading(View view){
        if (indexAktivitasUser < aktivitasUserDiKuisReading.size() - 1){
            indexAktivitasUser++;
            tampilkanAktivitasUserReading(indexAktivitasUser);
        }
    }

    public void tampilkanBackAktivitasUserReading(View view){
        if (indexAktivitasUser > 0){
            indexAktivitasUser--;
            tampilkanAktivitasUserReading(indexAktivitasUser);
        }
    }

    private void tampilkanAktivitasUserReading(int indexAktivitasUser){
        ReadingLog readingLog = aktivitasUserDiKuisReading.get(indexAktivitasUser);
        tvNarasiCekReading.setText(readingLog.getNarasi());
        tvSoalCekReading.setText(readingLog.getPertanyaan());
        tvJawabanUserCekReading.setText(readingLog.getJawabanUser());
        tvKunciCekReading.setText(readingLog.getKunci());
        tvPenjelasanCekReading.setText(readingLog.getPenjelasan());
    }
}
