package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.UserLog;

import java.util.ArrayList;

public class ActivityCekJawabanListening extends AppCompatActivity {
    int indexAktivitasUser = 0;
    ArrayList<UserLog> userLogArrayList = new ArrayList<>();
    TextView tvSoalCekListening, tvJawabanUserCekListening, tvKunciCekListening;
    Button btnBackCekListening, btnNextCekListening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_jawaban_listening);

        tvJawabanUserCekListening = (TextView) findViewById(R.id.tvJawabanUserCekListening);
        tvSoalCekListening = (TextView) findViewById(R.id.tvSoalCekListening);
        tvKunciCekListening = (TextView) findViewById(R.id.tvKunciCekListening);
        userLogArrayList = getIntent().getParcelableArrayListExtra("aktivitasUser");
        btnBackCekListening = (Button) findViewById(R.id.btnBackCekListening);
        btnNextCekListening = (Button) findViewById(R.id.btnNextCekListening);

        showQuestion(indexAktivitasUser);
    }

    private void showQuestion(int index){
        UserLog userLog = userLogArrayList.get(index);
        tvSoalCekListening.setText("Question "+(index + 1));
        tvJawabanUserCekListening.setText(userLog.getJawabanUser());
        tvKunciCekListening.setText(userLog.getKunci());
    }

    public void nextCekListening(View view){
        if (btnNextCekListening.getText().toString().equalsIgnoreCase("next")) {
            if (indexAktivitasUser < userLogArrayList.size() - 1){
                indexAktivitasUser++;
                showQuestion(indexAktivitasUser);
            } else {
                btnNextCekListening.setText("SHOW RESULT");
            }
        } else {
            //menampilkan ActivityHasilKuis yang memuat informasi jumlah soal benar dan salah
            Intent intent = new Intent(ActivityCekJawabanListening.this,
                    ActivityHasilKuis.class);
            //menghitung jumlah soal yang dijawab benar
            int jmlBenar = hitungListeningBenar();
            //mengirimkan data jumlah soal benar ke ActivityHasilKuis
            intent.putExtra("benar", jmlBenar);
            //menghitung dan mengirimkan data jumlah soal salah ke ActivityHasilKuis
            intent.putExtra("salah", userLogArrayList.size() - jmlBenar);
            startActivity(intent);
        }

    }

    //hitung jumlah jawaban benar
    private int hitungListeningBenar() {
        int jmlBenar = 0;
        for (int i = 0; i < userLogArrayList.size(); i++) {
            if (userLogArrayList.get(i).getJawabanUser().startsWith(userLogArrayList.get(i)
                    .getKunci())) {
                jmlBenar++;
            }
        }
        return jmlBenar;
    }

    public void backCekListening(View view){
        if (indexAktivitasUser > 0){
            indexAktivitasUser--;
            showQuestion(indexAktivitasUser);
        }
    }
}
