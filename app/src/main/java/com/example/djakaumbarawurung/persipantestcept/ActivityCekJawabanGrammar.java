package com.example.djakaumbarawurung.persipantestcept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.UserLog;

import java.util.ArrayList;

public class ActivityCekJawabanGrammar extends AppCompatActivity {
    int indexAktivitasUser = 0;
    ArrayList<UserLog> userLogArrayList = new ArrayList<>();
    TextView tvSoalCekGrammar, tvJawabanUserCekGrammar, tvKunciCekGrammar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_jawaban_grammar);

        tvSoalCekGrammar = (TextView) findViewById(R.id.tvSoalCekGrammar);
        tvJawabanUserCekGrammar = (TextView) findViewById(R.id.tvJawabanUserCekGrammar);
        tvKunciCekGrammar = (TextView) findViewById(R.id.tvKunciCekGrammar);
    }
}
