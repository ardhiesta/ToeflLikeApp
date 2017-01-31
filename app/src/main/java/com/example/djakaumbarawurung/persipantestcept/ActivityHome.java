package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;
import com.example.djakaumbarawurung.persipantestcept.database.SQLiteHelper;
import com.example.djakaumbarawurung.persipantestcept.tutor_screen.TutorActivity;

public class ActivityHome extends AppCompatActivity {

    private DataSource_PenghubungTabel dataSource_penghubungTabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();

        // menghitung jumlah record //
        if (dataSource_penghubungTabel.hitungrecordGrammar() == 0) {
            dataSource_penghubungTabel.insertDataKeGrammar(1, "In 1796, French mathematician Pierre-Simon Laplace _____ a more detailed version of Kant’s theory.", "A", "Produced");
            dataSource_penghubungTabel.insertDataKeGrammar(2, "Abdominal pain is a common problem for people in all age groups, and ................... to frequent visits to physicians and doctors.", "B", "Leads");
            dataSource_penghubungTabel.insertDataKeGrammar(3, "Treatment adherence refers to behavior in ..................................... to a prescribed treatment regimen for an illness.", "A", "which individuals adhere");
            dataSource_penghubungTabel.insertDataKeGrammar(4, "Some factors .......................... to health outcomes among African Americans include descrimination, health issues, and nonaccess to medical care.", "D", "Contributing");
            dataSource_penghubungTabel.insertDataKeGrammar(5, "Medications of Alzheimer’s disease should target specific symptoms ...................... their effects can be monitored", "B", "So");
            dataSource_penghubungTabel.insertDataKeGrammar(6, "tRecently, stents have been inserted into the ........................... artery to help preserve blood flow.", "B", "Newly opened");
        }

        //----------- START OF READING PART
        //masukkan data untuk soal tipe reading
        //insert narasi reading
        if (dataSource_penghubungTabel.hitungJumlahRecordTabel(SQLiteHelper.TABLE_NARASI_READING) == 0) {
            dataSource_penghubungTabel.insertDataNarasiReading(1, "narasi 1 narasi 1 narasi 1 narasi 1 narasi 1 narasi 1 narasi 1 narasi 1 narasi 1");
            dataSource_penghubungTabel.insertDataNarasiReading(2, "narasi 2 narasi 2 narasi 2 narasi 2 narasi 2 narasi 2 narasi 2 narasi 2 narasi 2");
            dataSource_penghubungTabel.insertDataNarasiReading(3, "narasi 3 narasi 3 narasi 3 narasi 3 narasi 3 narasi 3 narasi 3 narasi 3 narasi 3");
        }

        //insert soal reading
        if (dataSource_penghubungTabel.hitungJumlahRecordTabel(SQLiteHelper.TABLE_SOAL_READING) == 0) {
            dataSource_penghubungTabel.insertDataSoalReading(1, "soal 1 narasi 1", "a", "penjelasan 1", 1);
            dataSource_penghubungTabel.insertDataSoalReading(2, "soal 2 narasi 1", "b", "penjelasan 2", 1);
            dataSource_penghubungTabel.insertDataSoalReading(3, "soal 3 narasi 1", "c", "penjelasan 3", 1);
            dataSource_penghubungTabel.insertDataSoalReading(4, "soal 1 narasi 2", "a", "penjelasan 4", 2);
            dataSource_penghubungTabel.insertDataSoalReading(5, "soal 2 narasi 2", "a", "penjelasan 5", 2);
            dataSource_penghubungTabel.insertDataSoalReading(6, "soal 3 narasi 2", "a", "penjelasan 6", 2);
            dataSource_penghubungTabel.insertDataSoalReading(7, "soal 1 narasi 3", "a", "penjelasan 7", 3);
            dataSource_penghubungTabel.insertDataSoalReading(8, "soal 2 narasi 3", "a", "penjelasan 8", 3);
            dataSource_penghubungTabel.insertDataSoalReading(9, "soal 3 narasi 3", "a", "penjelasan 9", 3);
        }

        //insert opsi reading
        if (dataSource_penghubungTabel.hitungrecordOpsiReading() == 0) {
            //opsi untuk soal reading 1
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 1a", 1);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 1b", 1);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 1c", 1);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 1d", 1);

            //opsi untuk soal reading 2
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 2a", 2);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 2b", 2);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 2c", 2);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 2d", 2);

            //opsi untuk soal reading 3
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 3a", 3);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 3b", 3);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 3c", 3);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 3d", 3);

            //opsi untuk soal reading 4
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 4a", 4);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 4b", 4);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 4c", 4);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 4d", 4);

            //opsi untuk soal reading 5
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 5a", 5);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 5b", 5);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 5c", 5);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 5d", 5);

            //opsi untuk soal reading 6
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 6a", 6);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 6b", 6);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 6c", 6);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 6d", 6);

            //opsi untuk soal reading 7
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 7a", 7);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 7b", 7);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 7c", 7);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 7d", 7);

            //opsi untuk soal reading 8
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 8a", 8);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 8b", 8);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 8c", 8);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 8d", 8);

            //opsi untuk soal reading 9
            dataSource_penghubungTabel.insertDataOpsiReading("a. opsi 9a", 9);
            dataSource_penghubungTabel.insertDataOpsiReading("b. opsi 9b", 9);
            dataSource_penghubungTabel.insertDataOpsiReading("c. opsi 9c", 9);
            dataSource_penghubungTabel.insertDataOpsiReading("d. opsi 9d", 9);
        }
        //----------- END OF READING PART

        if (dataSource_penghubungTabel.hitungrecordOpsi() == 0) {
            dataSource_penghubungTabel.insertDataKeOpsi(1, "A.produced", 1);
            dataSource_penghubungTabel.insertDataKeOpsi(2, "B.produce", 1);
            dataSource_penghubungTabel.insertDataKeOpsi(3, "C.is produced", 1);
            dataSource_penghubungTabel.insertDataKeOpsi(4, "D.has produce", 1);
            dataSource_penghubungTabel.insertDataKeOpsi(5, "A.is leading", 2);
            dataSource_penghubungTabel.insertDataKeOpsi(6, "B.leads", 2);
            dataSource_penghubungTabel.insertDataKeOpsi(7, "C.leaded", 2);
            dataSource_penghubungTabel.insertDataKeOpsi(8, "D.lead", 2);
            dataSource_penghubungTabel.insertDataKeOpsi(9, "A.which individuals adhere", 3);
            dataSource_penghubungTabel.insertDataKeOpsi(10, "B.which adhere individuals", 3);
            dataSource_penghubungTabel.insertDataKeOpsi(11, "C.individuals which adhere", 3);
            dataSource_penghubungTabel.insertDataKeOpsi(12, "D.adhere individual which", 3);
            dataSource_penghubungTabel.insertDataKeOpsi(13, "A.contribution", 4);
            dataSource_penghubungTabel.insertDataKeOpsi(14, "B.contributed", 4);
            dataSource_penghubungTabel.insertDataKeOpsi(15, "C.contibute", 4);
            dataSource_penghubungTabel.insertDataKeOpsi(16, "D.contributing", 4);
            dataSource_penghubungTabel.insertDataKeOpsi(17, "A.and", 5);
            dataSource_penghubungTabel.insertDataKeOpsi(18, "B.so", 5);
            dataSource_penghubungTabel.insertDataKeOpsi(19, "C.but", 5);
            dataSource_penghubungTabel.insertDataKeOpsi(20, "D.or", 5);
            dataSource_penghubungTabel.insertDataKeOpsi(21, "A.new opened", 6);
            dataSource_penghubungTabel.insertDataKeOpsi(22, "B.newly opened", 6);
            dataSource_penghubungTabel.insertDataKeOpsi(23, "C.newly open", 6);
            dataSource_penghubungTabel.insertDataKeOpsi(24, "D.new openly", 6);
        }
    }

    public void paketSoal(View view) {
        Intent intent = new Intent(ActivityHome.this, ActivityQuiz.class);
        startActivity(intent);
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

    public void tampilkanTutorial(View view) {
        Intent intent = new Intent(ActivityHome.this, TutorActivity.class);
        startActivity(intent);
    }
}
