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

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.UserLog;
import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Opsi;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Grammar;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ActivityKuisGrammar extends AppCompatActivity {
    TextView TvSoal, TvOpsi1, TvOpsi2, TvOpsi3, TvOpsi4, TvPenjelasan;
    Button BCheck, Bnext, Bback;
    DataSource_PenghubungTabel dataSource_penghubungTabel;
    ArrayList<Integer> alidPertanyaan;
    int urutanPertanyaanSkrng;
    String jawabanUser = "";
    boolean sudahCekJawaban = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        TvSoal = (TextView) findViewById(R.id.TVPertanyaan);
        TvOpsi1 = (TextView) findViewById(R.id.TVpilihan1);
        TvOpsi2 = (TextView) findViewById(R.id.TVpilihan2);
        TvOpsi3 = (TextView) findViewById(R.id.TVpilihan3);
        TvOpsi4 = (TextView) findViewById(R.id.TVpilihan4);
        TvPenjelasan = (TextView) findViewById(R.id.TvPenjelasan);


        BCheck = (Button) findViewById(R.id.Btncek);
        Bnext = (Button) findViewById(R.id.BtnNext);
        Bback = (Button) findViewById(R.id.BtnBack);


        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();



        alidPertanyaan = dataSource_penghubungTabel.mengambilsemuaIdGrammar();
        acakIdPertanyaan();

        BCheck.setEnabled(false);
        Bback.setEnabled(false);
        TvPenjelasan.setText("");


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
        ArrayList<Opsi> opsiArrayList = dataSource_penghubungTabel.ambilOpsiGrammarSesuaiIdSoal(idpertanyaan);

        Grammar pertanyaan = dataSource_penghubungTabel.ambilpertanyaanSesuaiId(idpertanyaan);
        TvSoal.setText(pertanyaan.getPertanyaan());
        for (int i = 0; i < opsiArrayList.size(); i++) {
            Opsi opsi = opsiArrayList.get(i);
            if (opsi.getOpsi().toLowerCase().startsWith("a")) {
                TvOpsi1.setText(opsi.getOpsi());
            } else if ((opsi.getOpsi().toLowerCase().startsWith("b"))) {
                TvOpsi2.setText(opsi.getOpsi());
            } else if (opsi.getOpsi().toLowerCase().startsWith("c")) {
                TvOpsi3.setText(opsi.getOpsi());
            } else {
                TvOpsi4.setText(opsi.getOpsi());

            }

        }
    }

    public void opsi1klik(View view) {
        //ambil Abjad Pilihan User
        jawabanUser = "a";
        setOpsi(TvOpsi1, TvOpsi2, TvOpsi3, TvOpsi4);
    }

    public void opsiKlik2(View view) {
        jawabanUser = "b";
        setOpsi(TvOpsi2, TvOpsi1, TvOpsi3, TvOpsi4);
    }

    public void opsiKlik3(View view) {
        jawabanUser = "c";
        setOpsi(TvOpsi3, TvOpsi1, TvOpsi2, TvOpsi4);
    }

    public void opsiKlik4(View view) {
        jawabanUser = "d";
        setOpsi(TvOpsi4, TvOpsi1, TvOpsi2, TvOpsi3);
    }

    private void setOpsi(TextView tvdipilih, TextView tvTdkDiplih1, TextView tvtdkDiplih2, TextView tvtdkdipilih3) {
        tvdipilih.setTypeface(null, Typeface.BOLD);
        tvTdkDiplih1.setTypeface(null, Typeface.NORMAL);
        tvtdkDiplih2.setTypeface(null, Typeface.NORMAL);
        tvtdkdipilih3.setTypeface(null, Typeface.NORMAL);
        tvdipilih.setTextColor(Color.parseColor("#303f9f"));
        tvTdkDiplih1.setTextColor(Color.parseColor("#000000"));
        tvtdkDiplih2.setTextColor((Color.parseColor("#000000")));
        tvtdkdipilih3.setTextColor(Color.parseColor("#000000"));
    }


    public void netralkanOpsi() {
        jawabanUser = "";

        TvOpsi1.setTypeface(null, Typeface.NORMAL);
        TvOpsi2.setTypeface(null, Typeface.NORMAL);
        TvOpsi3.setTypeface(null, Typeface.NORMAL);
        TvOpsi4.setTypeface(null, Typeface.NORMAL);
        TvOpsi1.setTextColor(Color.parseColor("#000000"));
        TvOpsi2.setTextColor(Color.parseColor("#000000"));
        TvOpsi3.setTextColor(Color.parseColor("#000000"));
        TvOpsi4.setTextColor((Color.parseColor("#000000")));
    }

// METHOD UNTUK NEXT
    public void tampilkanPertanyaanSelanjutnya(View view) {
        if (!sudahCekJawaban) {
            if (!jawabanUser.equals("")) {
                //SIMPAN DULU JAWABAN USER, KEMUDIAN LANJUT KE PERTANYAAN BERIKUTNYA
                if (urutanPertanyaanSkrng < alidPertanyaan.size() - 1) {
                    dataSource_penghubungTabel.insertLogUser(alidPertanyaan.get(urutanPertanyaanSkrng), String.valueOf(jawabanUser));
                    urutanPertanyaanSkrng = urutanPertanyaanSkrng + 1;
                } else {
                    dataSource_penghubungTabel.insertLogUser(alidPertanyaan.get(urutanPertanyaanSkrng), String.valueOf(jawabanUser));
                    // disable tombol next setelah sampai pertanyaan akhir.
                    Bnext.setEnabled(false);
                    Bback.setEnabled(false);
                    BCheck.setEnabled(true);


                }
                int idPertanyaan = alidPertanyaan.get(urutanPertanyaanSkrng);
                bentukKuis(idPertanyaan);

            } else {
                Toast.makeText(this, "silahkan pilih jawaban terlebih dahulu", Toast.LENGTH_LONG).show();
            }
            // KEMBALIKAN WARNA OPSI
            netralkanOpsi();
        } else {
            // dipanggil setelah cek jawaban
//            String penjelasan = dataSource_penghubungTabel.ambilPenjelasanSesuaiId(alidPertanyaan.get(urutanPertanyaanSkrng));
//            TvPenjelasan.setText(penjelasan);

            if (urutanPertanyaanSkrng<alidPertanyaan.size()-1){
                urutanPertanyaanSkrng = urutanPertanyaanSkrng + 1;
                tampilkanJawabanUserDanKunci();
                String penjelasan = dataSource_penghubungTabel.ambilPenjelasanSesuaiId(alidPertanyaan.get(urutanPertanyaanSkrng));
                TvPenjelasan.setText(penjelasan);
            }else {
                Bnext.setText("Finish");
                if (Bnext.getText().toString().equalsIgnoreCase("finish")) {
                    Intent intent = new Intent(ActivityKuisGrammar.this, ActivityHasil.class);
                    startActivity(intent);
                }
            }

        }
    }


    // tampilkan pertanyaan sebel;umnya ini adalah tampilan fungsi untuk back
    public void tampilkanPertanyaanSebelumnya (View view) {
       if ( urutanPertanyaanSkrng > 0){
           urutanPertanyaanSkrng = urutanPertanyaanSkrng - 1;
           tampilkanJawabanUserDanKunci();
       }

    }


    public void tampilkanJawabanUserDanKunci(){
        int idPertanyaan = alidPertanyaan.get(urutanPertanyaanSkrng);
        bentukKuis(idPertanyaan);
        Grammar grammar =dataSource_penghubungTabel.ambilpertanyaanSesuaiId(alidPertanyaan.get(urutanPertanyaanSkrng));
        String kunciJawaban = grammar.getJawaban();

        String jawabanUserDrLog = dataSource_penghubungTabel.bacaAktivitasUser(alidPertanyaan.get(urutanPertanyaanSkrng));
        if (jawabanUserDrLog.equalsIgnoreCase("a")) {
            setOpsi(TvOpsi1, TvOpsi2, TvOpsi3, TvOpsi4);
        } else if (jawabanUserDrLog.equalsIgnoreCase("b")) {
            setOpsi(TvOpsi2, TvOpsi1, TvOpsi3, TvOpsi4);
        }else if (jawabanUserDrLog.equalsIgnoreCase("c")){
            setOpsi(TvOpsi3, TvOpsi1, TvOpsi2, TvOpsi4);
        }
        else {
            setOpsi(TvOpsi4, TvOpsi1, TvOpsi2, TvOpsi3);
        }

        if (kunciJawaban.equalsIgnoreCase("a")) {
            setKunci(TvOpsi1);
        } else if (kunciJawaban.equalsIgnoreCase("b")) {
            setKunci(TvOpsi2);
        } else if (kunciJawaban.equalsIgnoreCase("c")){
            setKunci(TvOpsi3);
        } else {
            setKunci(TvOpsi4);
        }
    }
    public void setKunci(TextView tvKunci) {
        tvKunci.setTypeface(null, Typeface.BOLD);
        tvKunci.setTextColor(Color.parseColor("#64dd17"));
    }


    public void cekJawaban(View view) {
        urutanPertanyaanSkrng = 0;
        sudahCekJawaban = true;
        Bnext.setEnabled(true);
        Bback.setEnabled(true);
        tampilkanJawabanUserDanKunci();
        if(sudahCekJawaban) {
            String penjelasan = dataSource_penghubungTabel.ambilPenjelasanSesuaiId(alidPertanyaan.get(urutanPertanyaanSkrng));
            TvPenjelasan.setText(penjelasan);
        }
    }



}



