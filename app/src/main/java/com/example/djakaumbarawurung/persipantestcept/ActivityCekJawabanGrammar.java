package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.UserLog;

import java.util.ArrayList;

public class ActivityCekJawabanGrammar extends AppCompatActivity {
    int indexAktivitasUser = 0;
    ArrayList<UserLog> userLogArrayList = new ArrayList<>();
    TextView tvSoalCekGrammar, tvJawabanUserCekGrammar, tvKunciCekGrammar, tvPenjelasanCekGrammar;
    Button btnBackCekGrammar, btnNextCekGrammar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_jawaban_grammar);

        tvSoalCekGrammar = (TextView) findViewById(R.id.tvSoalCekGrammar);
        tvJawabanUserCekGrammar = (TextView) findViewById(R.id.tvJawabanUserCekGrammar);
        tvKunciCekGrammar = (TextView) findViewById(R.id.tvKunciCekGrammar);
        tvPenjelasanCekGrammar = (TextView) findViewById(R.id.tvPenjelasanCekGrammar);
        btnBackCekGrammar = (Button) findViewById(R.id.btnBackCekGrammar);
        btnNextCekGrammar = (Button) findViewById(R.id.btnNextCekGrammar);

        userLogArrayList = getIntent().getParcelableArrayListExtra("aktivitasUser");

        showQuestion(indexAktivitasUser);
    }

    private void showQuestion(int index){
        UserLog userLog = userLogArrayList.get(index);
        tvSoalCekGrammar.setText(userLog.getPertanyaan());
        tvJawabanUserCekGrammar.setText(userLog.getJawabanUser());
        tvKunciCekGrammar.setText(userLog.getKunci());
        tvPenjelasanCekGrammar.setText(userLog.getPenjelasan());
    }

    public void nextCekGrammar(View view){
        if (btnNextCekGrammar.getText().toString().equalsIgnoreCase("next")) {
            if (indexAktivitasUser < userLogArrayList.size() - 1) {
                indexAktivitasUser++;
                showQuestion(indexAktivitasUser);
            } else {
                //tombol cek hasil ditampilkan
                btnNextCekGrammar.setText("SHOW RESULT");
            }
        } else {
            Intent intent = new Intent(ActivityCekJawabanGrammar.this, ActivityHasilKuis.class);
            int jmlBenar = hitungGrammarBenar();
            intent.putExtra("benar", jmlBenar);
            intent.putExtra("salah", userLogArrayList.size() - jmlBenar);
            startActivity(intent);
        }
    }

    public void backCekGrammar(View view){
        if (indexAktivitasUser > 0){
            indexAktivitasUser--;
            showQuestion(indexAktivitasUser);

            //kembalikan btn next utk menampilkan next
            if (btnNextCekGrammar.getText().toString().equalsIgnoreCase("show result")){
                btnNextCekGrammar.setText("NEXT");
            }
        }
    }

    //hitung jumlah jawaban benar
    private int hitungGrammarBenar(){
        int jmlBenar = 0;
        for (int i = 0; i < userLogArrayList.size(); i++){
            if (userLogArrayList.get(i).getJawabanUser().startsWith(userLogArrayList.get(i).getKunci())){
                jmlBenar++;
            }
        }
        return jmlBenar;
    }
}
