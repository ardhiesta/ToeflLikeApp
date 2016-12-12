package com.example.djakaumbarawurung.persipantestcept;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.NarasiReading;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.OpsiReading;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.PertanyaanReading;
import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;

import java.util.ArrayList;

public class ActivityKuisReading extends AppCompatActivity {
    TextView TvOpsi1, TvOpsi2, TvOpsi3, TvOpsi4, TvSoal, TvNarasi, TvPenjelasan;
    DataSource_PenghubungTabel dataSource_penghubungTabel;
    ArrayList<NarasiReading> narasiReadingArrayList;
    int indexNarasi = 0;
    int indexPertanyaan = 0;
    String jawabanUser = "";
    //    ArrayList<ReadingLog> aktivitasUserDikuisReading = new ArrayList<>();
    boolean reCheckJawaban = false;
//    int indexAktivitasUser = 0;
    Button bNextReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();
        narasiReadingArrayList = dataSource_penghubungTabel.ambilNaskahReading();

        TvNarasi = (TextView) findViewById(R.id.TvNarasi);
        TvSoal = (TextView) findViewById(R.id.TVPertanyaanReading);
        TvOpsi1 = (TextView) findViewById(R.id.TvOpsiReading1);
        TvOpsi2 = (TextView) findViewById(R.id.TvOpsiReading2);
        TvOpsi3 = (TextView) findViewById(R.id.TvOpsiReading3);
        TvOpsi4 = (TextView) findViewById(R.id.TvOpsiReading4);
        TvPenjelasan = (TextView) findViewById(R.id.PenjelasanReading);
        bNextReading = (Button) findViewById(R.id.bNextReading);

        TvPenjelasan.setText("");
        //tombol next tidak bisa diklik kecuali user telah memilih sebuah opsi untuk menjawab soal
        bNextReading.setEnabled(false);

        tampilkanPertanyaan(indexNarasi, indexPertanyaan);
    }

    // untuk menampilkan pertanyaan reading
    public void tampilkanPertanyaan(int indexNarasi, int indexPertanyaan) {
        NarasiReading narasiReading = narasiReadingArrayList.get(indexNarasi);
        TvNarasi.setText(narasiReading.getNarasi());
        ArrayList<PertanyaanReading> pertanyaanReadingArrayList = narasiReading.getPertanyaanReadingArraylist();
        PertanyaanReading pertanyaanReading = pertanyaanReadingArrayList.get(indexPertanyaan);
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

    public void tampilkanPertanyaanReadingSelanjutnya(View view) {
        if (!jawabanUser.equals("")) {
//            ReadingLog readingLog = new ReadingLog();
//            readingLog.setNarasi(TvNarasi.getText().toString());
//            readingLog.setPertanyaan(TvSoal.getText().toString());
//            readingLog.setJawabanUser(jawabanUser);
//            readingLog.setKunci(narasiReadingArrayList.get(indexNarasi).getPertanyaanReadingArraylist().get(indexPertanyaan).getJawaban());
//            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("a", TvOpsi1.getText().toString());
//            hashMap.put("b", TvOpsi2.getText().toString());
//            hashMap.put("c", TvOpsi3.getText().toString());
//            hashMap.put("d", TvOpsi4.getText().toString());
//            readingLog.setOpsi(hashMap);


            if (indexPertanyaan < narasiReadingArrayList.get(indexNarasi).getPertanyaanReadingArraylist().size() - 1) {
//                aktivitasUserDikuisReading.add(readingLog);
                netralkanOpsi();
                indexPertanyaan++;
                tampilkanPertanyaan(indexNarasi, indexPertanyaan);

            } else {
                if (indexNarasi < narasiReadingArrayList.size() - 1) {
//                    aktivitasUserDikuisReading.add(readingLog);
                    netralkanOpsi();
                    indexNarasi++;
                    indexPertanyaan = 0;
                    tampilkanPertanyaan(indexNarasi, indexPertanyaan);
                }
            }
        } 
    }

    public void opsi1KlikReading(View view) {
        //ambil Abjad Pilihan User
        setOpsi(TvOpsi1);
        jawabanUser = "a";
        bNextReading.setEnabled(true);
    }

    public void opsi2KlikReading(View view) {
        setOpsi(TvOpsi2);
        jawabanUser = "b";
        bNextReading.setEnabled(true);
    }

    public void opsi3KlikReading(View view) {
        setOpsi(TvOpsi3);
        jawabanUser = "c";
        bNextReading.setEnabled(true);
    }

    public void opsi4KlikReading(View view) {
        setOpsi(TvOpsi4);
        jawabanUser = "d";
        bNextReading.setEnabled(true);
    }


    private void setOpsi(TextView tvdipilih) {
        netralkanOpsi();
        tvdipilih.setTypeface(null, Typeface.BOLD);
        tvdipilih.setTextColor(Color.parseColor("#303f9f"));
    }

    public void netralkanOpsi() {
        jawabanUser = "";
        bNextReading.setEnabled(false);

        TvOpsi1.setTypeface(null, Typeface.NORMAL);
        TvOpsi2.setTypeface(null, Typeface.NORMAL);
        TvOpsi3.setTypeface(null, Typeface.NORMAL);
        TvOpsi4.setTypeface(null, Typeface.NORMAL);
        TvOpsi1.setTextColor(Color.parseColor("#000000"));
        TvOpsi2.setTextColor(Color.parseColor("#000000"));
        TvOpsi3.setTextColor(Color.parseColor("#000000"));
        TvOpsi4.setTextColor((Color.parseColor("#000000")));
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