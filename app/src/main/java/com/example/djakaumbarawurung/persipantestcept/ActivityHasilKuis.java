package com.example.djakaumbarawurung.persipantestcept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

//activity ini digunakan untuk menampilkan hasil semua kuis (grammar, reading, maupun listening)
//jumlah jawaban benar dan jumlah jawaban salah ditampilkan di sini
public class ActivityHasilKuis extends AppCompatActivity {
    TextView tvBenar, tvSalah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);

        tvBenar = (TextView) findViewById(R.id.tvJmlBenar);
        tvSalah = (TextView) findViewById(R.id.tvJmlSalah);

        //mengambil data jumlah jawaban benar dan jumlah jawaban salah yang dikirimkan activity sebelumnya
        int jmlBenar = getIntent().getExtras().getInt("benar");
        int jmlsalah = getIntent().getExtras().getInt("salah");

        //menampilkan jumlah jawaban benar dan jumlah jawaban salah
        tvBenar.setText(String.valueOf(jmlBenar));
        tvSalah.setText(String.valueOf(jmlsalah));
    }
}
