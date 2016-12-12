package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Grammar;

import java.util.ArrayList;

public class ActivityHasil extends AppCompatActivity {


        DataSource_PenghubungTabel dataSource_penghubungTabel;
        TextView tvBenar,tvSalah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        tvBenar = (TextView)findViewById(R.id.tvBenar);
        tvSalah = (TextView) findViewById(R.id.tvSalah);

        dataSource_penghubungTabel =new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();

        hitungPoin();
    }
    // keluar aplikasi
    public void exitApp(View view){
        this.finishAffinity();
    }

    // kembali kehalaman awal ( main menu activity )
    public void goToHome(View view){
        Intent intent = new Intent(ActivityHasil.this,ActivityHome.class);
        startActivity(intent);
    }
    // hitung benar dan salah
    public void hitungPoin(){
        ArrayList<Integer>alidPertanyaan = dataSource_penghubungTabel.mengambilsemuaIdGrammar();
        int jmlBenar=0;
        for (int i=0; i<alidPertanyaan.size(); i++){
            Grammar grammar = dataSource_penghubungTabel.ambilpertanyaanSesuaiId(alidPertanyaan.get(i));
            String kunciJawaban = grammar.getJawaban();


            String jawabanUser =
                    dataSource_penghubungTabel.bacaAktivitasUser(alidPertanyaan.get(i));
                if(jawabanUser.equalsIgnoreCase(kunciJawaban)){
                    jmlBenar++;
                }
        }
        int jmlSalah = alidPertanyaan.size() -jmlBenar;
        tvBenar.setText(String.valueOf(jmlBenar));
        tvSalah.setText(String.valueOf(jmlSalah));
    }

    protected void onResume(){
        dataSource_penghubungTabel.open();
        super.onResume();
    }
    protected void onPause(){
        dataSource_penghubungTabel.close();
        super.onPause();
    }
}

