package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Grammar2;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Opsi;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.UserLog;
import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ActivityKuisGrammar extends AppCompatActivity {
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
        setContentView(R.layout.activity_kuis_grammar);

        tvSoal = (TextView) findViewById(R.id.tvPertanyaanGrammar);
        tvOpsi1 = (TextView) findViewById(R.id.tvPilihan1Grammar);
        tvOpsi2 = (TextView) findViewById(R.id.tvPilihan2Grammar);
        tvOpsi3 = (TextView) findViewById(R.id.tvPilihan3Grammar);
        tvOpsi4 = (TextView) findViewById(R.id.tvPilihan4Grammar);
        bNext = (Button) findViewById(R.id.bNextGrammar);

        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();

        //mengambil semua id soal kuis grammar dari tabel, disimpan ke ArrayList alidPertanyaan
        alidPertanyaan = dataSource_penghubungTabel.mengambilsemuaIdGrammar();

        //id soal kuis grammar diacak
        acakIdPertanyaan();

        //tombol next tidak bisa diklik kecuali user telah memilih sebuah opsi untuk menjawab soal
        bNext.setEnabled(false);
    }

    //untuk mengacak urutan id soal grammar
    private void acakIdPertanyaan() {
        long seed = System.nanoTime();
        if (alidPertanyaan.size() > 0) {
            Collections.shuffle(alidPertanyaan, new Random(seed));

            int idPertanyaan = alidPertanyaan.get(0);

            //menampilkan soal grammar urutan pertama hasil pengacakan beserta opsinya
            bentukKuis(idPertanyaan);
        } else {
            Toast.makeText(this, "Pertanyaan Sudah Habis", Toast.LENGTH_LONG).show();
        }
    }

    //untuk menampilkan soal kuis grammar beserta opsi
    private void bentukKuis(int idpertanyaan) {
        //mengambil detail kuis grammar (soal, opsi, kunci jawaban) berdasarkan id soal
        Grammar2 grammar2 = dataSource_penghubungTabel.ambilGrammarSesuaiId(idpertanyaan);

        //menampilkan soal ke TextView
        tvSoal.setText(grammar2.getPertanyaan());

        //mengambil opsi kuis grammar
        ArrayList<Opsi> opsiArrayList = grammar2.getOpsiArrayList();

        //menampilkan opsi kuis grammar ke TextView
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

    //yang dilakukan ketika opsi ke-1 diklik
    public void opsi1KlikGrammar(View view) {
        //memberi warna dan mem-bold opsi yang dipilih user
        setOpsi(tvOpsi1);

        //mengisi variabel jawabanUser sesuai opsi yang dipilih user
        jawabanUser = tvOpsi1.getText().toString();

        //mengEnable tombol Next
        bNext.setEnabled(true);
    }

    //yang dilakukan ketika opsi ke-2 diklik
    public void opsi2KlikGrammar(View view) {
        setOpsi(tvOpsi2);
        jawabanUser = tvOpsi2.getText().toString();
        bNext.setEnabled(true);
    }

    //yang dilakukan ketika opsi ke-3 diklik
    public void opsi3KlikGrammar(View view) {
        setOpsi(tvOpsi3);
        jawabanUser = tvOpsi3.getText().toString();
        bNext.setEnabled(true);
    }

    //yang dilakukan ketika opsi ke-4 diklik
    public void opsi4KlikGrammar(View view) {
        setOpsi(tvOpsi4);
        jawabanUser = tvOpsi4.getText().toString();
        bNext.setEnabled(true);
    }

    private void setOpsi(TextView tvdipilih) {
        netralkanOpsi();

        //memberi warna dan memBold opsi yang dipilih
        tvdipilih.setTypeface(null, Typeface.BOLD);
        tvdipilih.setTextColor(Color.parseColor("#303f9f"));
    }

    //menetralkan pilihan user
    public void netralkanOpsi() {
        //mengosongkan variabel
        jawabanUser = "";

        //menDisable tombol Next
        bNext.setEnabled(false);

        //menghilangkan tanda pilihan user pada opsi yang dipilih
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
        // tombol Next setelah sampai pada pertanyaan terakhir untuk narasi terakhir akan menampilkan ActivityCekJawaban
        if (bNext.getText().toString().equalsIgnoreCase("CHECK ANSWER")) {
            Intent intent = new Intent(ActivityKuisGrammar.this, ActivityCekJawabanGrammar.class);

            //mengirim data aktivitas user (soal serta jawaban yang telah dipilih user) ke activity selanjutnya
            intent.putParcelableArrayListExtra("aktivitasUser", userLogArrayList);
            startActivity(intent);
        } else {
            if (!jawabanUser.equals("")) {
                //menyimpan aktivitas user di kuis grammar ke obyek userLog
                UserLog userLog = new UserLog();
                userLog.setJawabanUser(jawabanUser);
                Grammar2 grammar2 = dataSource_penghubungTabel.ambilGrammarSesuaiId(alidPertanyaan.get(indexGrammar));
                userLog.setKunci(grammar2.getJawaban());
                userLog.setPenjelasan(grammar2.getPenjelasan());
                userLog.setPertanyaan(grammar2.getPertanyaan());

                if (indexGrammar < alidPertanyaan.size() - 1){
                    //menambah (increment) index grammar untuk menuju ke soal selanjutnya
                    indexGrammar++;
                    netralkanOpsi();

                    //mengambil id soal selanjutnya dari ArrayList yang berisi id soal
                    int idPertanyaan = alidPertanyaan.get(indexGrammar);

                    //menampilkan soal grammar beserta opsi
                    bentukKuis(idPertanyaan);

                    //menyimpan aktivitas user ke ArrayList userLogArrayList
                    userLogArrayList.add(userLog);
                } else {
                    bNext.setText("CHECK ANSWER");
                }
            }
        }
    }
}
