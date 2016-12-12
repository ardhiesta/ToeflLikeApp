package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.djakaumbarawurung.persipantestcept.database.DataSource_PenghubungTabel;
import com.example.djakaumbarawurung.persipantestcept.database.SQLiteHelper;

public class ActivityHome extends AppCompatActivity {

 private DataSource_PenghubungTabel dataSource_penghubungTabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dataSource_penghubungTabel = new DataSource_PenghubungTabel(this);
        dataSource_penghubungTabel.open();

        // menghitung jumlah record //
        if(dataSource_penghubungTabel.hitungrecordGrammar()== 0) {
            dataSource_penghubungTabel.insertDataKeGrammar(1, "In 1796, French mathematician Pierre-Simon Laplace _____ a more detailed version of Kant’s theory.", "A", "Produced");
            dataSource_penghubungTabel.insertDataKeGrammar(2, "Abdominal pain is a common problem for people in all age groups, and ................... to frequent visits to physicians and doctors.", "B", "Leads");
            dataSource_penghubungTabel.insertDataKeGrammar(3, "Treatment adherence refers to behavior in ..................................... to a prescribed treatment regimen for an illness.", "A", "which individuals adhere");
            dataSource_penghubungTabel.insertDataKeGrammar(4, "Some factors .......................... to health outcomes among African Americans include descrimination, health issues, and nonaccess to medical care.", "D", "Contributing");
            dataSource_penghubungTabel.insertDataKeGrammar(5, "Medications of Alzheimer’s disease should target specific symptoms ...................... their effects can be monitored", "B", "So");
            dataSource_penghubungTabel.insertDataKeGrammar(6, "tRecently, stents have been inserted into the ........................... artery to help preserve blood flow.", "B", "Newly opened");
        }



        if(dataSource_penghubungTabel.hitungrecordGrammar() == 0){
            dataSource_penghubungTabel.insertDataPertanyaanReading(1,"Tanya", "jawaban", " penjelasan" , 1);
            dataSource_penghubungTabel.insertDataPertanyaanReading(2,"Tanya", "jawaban", " penjelasan" , 1);
            dataSource_penghubungTabel.insertDataPertanyaanReading(3,"Tanya", "jawaban", " penjelasan" , 1);
            dataSource_penghubungTabel.insertDataPertanyaanReading(4,"Tanya", "jawaban", " penjelasan" , 1);
            dataSource_penghubungTabel.insertDataPertanyaanReading(5,"Tanya", "jawaban", " penjelasan" , 1);
            dataSource_penghubungTabel.insertDataPertanyaanReading(6,"Tanya", "jawaban", " penjelasan" , 1);

        }

        if (dataSource_penghubungTabel.hitungrecordOpsiReading()==0){
            dataSource_penghubungTabel.insertDataKeOpsiReading(1, " A " , 1);
            dataSource_penghubungTabel.insertDataKeOpsiReading(2, " A " , 1);
            dataSource_penghubungTabel.insertDataKeOpsiReading(3, " A " , 1);
            dataSource_penghubungTabel.insertDataKeOpsiReading(4, " A " , 1);

        }

        if( dataSource_penghubungTabel.hitungrecordNarasiReading()==0){
            dataSource_penghubungTabel.insertDataKenarasiReading(1, " ini narasi");
            dataSource_penghubungTabel.insertDataKenarasiReading(2, " ini narasi");
            dataSource_penghubungTabel.insertDataKenarasiReading(3, " ini narasi");
            dataSource_penghubungTabel.insertDataKenarasiReading(4, " ini narasi");
        }





        if(dataSource_penghubungTabel.hitungrecordOpsi()== 0) {
            dataSource_penghubungTabel.insertDataKeOpsi(1 ,"A.produced" ,1);
            dataSource_penghubungTabel.insertDataKeOpsi(2 ,"B.produce" ,1);
            dataSource_penghubungTabel.insertDataKeOpsi(3 ,"C.is produced" ,1);
            dataSource_penghubungTabel.insertDataKeOpsi(4 ,"D.has produce" ,1);
            dataSource_penghubungTabel.insertDataKeOpsi(5 ,"A.is leading" ,2);
            dataSource_penghubungTabel.insertDataKeOpsi(6 ,"B.leads" ,2);
            dataSource_penghubungTabel.insertDataKeOpsi(7 ,"C.leaded" ,2);
            dataSource_penghubungTabel.insertDataKeOpsi(8 ,"D.lead" ,2);
            dataSource_penghubungTabel.insertDataKeOpsi(9 ,"A.which individuals adhere" ,3);
            dataSource_penghubungTabel.insertDataKeOpsi(10 ,"B.which adhere individuals" ,3);
            dataSource_penghubungTabel.insertDataKeOpsi(11 ,"C.individuals which adhere" ,3);
            dataSource_penghubungTabel.insertDataKeOpsi(12 ,"D.adhere individual which" ,3);
            dataSource_penghubungTabel.insertDataKeOpsi(13, "A.contribution" , 4);
            dataSource_penghubungTabel.insertDataKeOpsi(14, "B.contributed" , 4);
            dataSource_penghubungTabel.insertDataKeOpsi(15, "C.contibute" , 4);
            dataSource_penghubungTabel.insertDataKeOpsi(16, "D.contributing" , 4);
            dataSource_penghubungTabel.insertDataKeOpsi(17, "A.and" , 5);
            dataSource_penghubungTabel.insertDataKeOpsi(18, "B.so" , 5);
            dataSource_penghubungTabel.insertDataKeOpsi(19, "C.but" , 5);
            dataSource_penghubungTabel.insertDataKeOpsi(20, "D.or" , 5);
            dataSource_penghubungTabel.insertDataKeOpsi(21, "A.new opened" , 6);
            dataSource_penghubungTabel.insertDataKeOpsi(22, "B.newly opened" , 6);
            dataSource_penghubungTabel.insertDataKeOpsi(23, "C.newly open" , 6);
            dataSource_penghubungTabel.insertDataKeOpsi(24, "D.new openly" , 6);


        }


        if (dataSource_penghubungTabel.hitungJumlahData(SQLiteHelper.TABLE_READING) == 0){
            dataSource_penghubungTabel.insertDataPertanyaanReading(1,"adasdasdasdasdas","sfsdaf","sfsdfsd",1);
        }











    }
    public void paketSoal(View view){
        Intent intent=new Intent(ActivityHome.this,ActivityQuiz.class);
        startActivity(intent);
    }
    public void ShowActivityReading(View view){
        Intent intent=new Intent(ActivityHome.this,ActivityQuiz.class);
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
