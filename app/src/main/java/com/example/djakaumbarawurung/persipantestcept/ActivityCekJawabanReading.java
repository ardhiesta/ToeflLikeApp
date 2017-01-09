package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.ReadingLog;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityCekJawabanReading extends AppCompatActivity {
    TextView tvNarasiCekReading, tvSoalCekReading,
            tvJawabanUserCekReading, tvKunciCekReading, tvPenjelasanCekReading;
    int indexAktivitasUser = 0;
    ArrayList<ReadingLog> aktivitasUserDiKuisReading;
    Button btnNextReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_jawaban_reading);

        tvNarasiCekReading = (TextView) findViewById(R.id.tvNarasiCekReading);
        tvSoalCekReading = (TextView) findViewById(R.id.tvSoalCekReading);
        tvJawabanUserCekReading = (TextView) findViewById(R.id.tvJawabanUserCekReading);
        tvKunciCekReading = (TextView) findViewById(R.id.tvKunciCekReading);
        tvPenjelasanCekReading = (TextView) findViewById(R.id.tvPenjelasanCekReading);
        btnNextReading = (Button) findViewById(R.id.btnNextCekReading);

        aktivitasUserDiKuisReading = getIntent().getParcelableArrayListExtra("aktivitasUser");

        tampilkanAktivitasUserReading(indexAktivitasUser);
    }

    public void tampilkanNextAktivitasUserReading(View view){
        if (btnNextReading.getText().toString().equalsIgnoreCase("next")) {
            if (indexAktivitasUser < aktivitasUserDiKuisReading.size() - 1) {
                indexAktivitasUser++;
                tampilkanAktivitasUserReading(indexAktivitasUser);
            } else {
                btnNextReading.setText("SHOW RESULT");
            }
        } else {
            Intent intent = new Intent(ActivityCekJawabanReading.this, ActivityHasilKuis.class);
            int jmlBenar = hitungReadingBenar();
            intent.putExtra("benar", jmlBenar);
            intent.putExtra("salah", aktivitasUserDiKuisReading.size() - jmlBenar);
            startActivity(intent);
        }
    }

    /* menghitung jumlah jawaban benar, membandingkan jawaban user dengan kunci
     * jawaban user = opsi yang dipilih user
     * kunci = abjad opsi yang menunjukkan jawaban yang benar
     * kunci disimpan di database
     *
     * apabila jawaban user diawali  (.startsWith) dengan abjad kunci
     * maka jawaban dihitung benar
     *
     * misal, jawaban user: a. abc xyz
     * kunci: a
     * maka jawaban dihitung benar */
    private int hitungReadingBenar(){
        int jmlBenar = 0;
        for (int i = 0; i < aktivitasUserDiKuisReading.size(); i++){
            if (aktivitasUserDiKuisReading.get(i).getJawabanUser()
                    .startsWith(aktivitasUserDiKuisReading.get(i).getKunci())){
                jmlBenar++;
            }
        }
        return jmlBenar;
    }

    public void tampilkanBackAktivitasUserReading(View view){
        if (indexAktivitasUser > 0){
            indexAktivitasUser--;
            tampilkanAktivitasUserReading(indexAktivitasUser);

            if (btnNextReading.getText().toString().equalsIgnoreCase("show result")){
                btnNextReading.setText("NEXT");
            }
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
