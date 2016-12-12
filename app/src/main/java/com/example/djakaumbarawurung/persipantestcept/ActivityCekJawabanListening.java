package com.example.djakaumbarawurung.persipantestcept;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Listening;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityCekJawabanListening extends AppCompatActivity {
    ArrayList<Listening> arListening = new ArrayList<>();
    TextView tvQuestion, tvOpsi1, tvOpsi2, tvOpsi3;//, tvOpsi4;
    int currentQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_jawaban_listening);

        tvQuestion = (TextView) findViewById(R.id.TvSoalListeningCek);
        tvOpsi1 = (TextView) findViewById(R.id.TvOpsi1ListeningCek);
        tvOpsi2 = (TextView) findViewById(R.id.TvOpsi2ListeningCek);
        tvOpsi3 = (TextView) findViewById(R.id.TvOpsi3ListeningCek);
//        tvOpsi4 = (TextView) findViewById(R.id.TvOpsi4ListeningCek);

        setDummyData();

        showQuestion(0);
    }

    private void showQuestion(int index){
        netralkanOpsi();
        Listening l = arListening.get(index);
        tvQuestion.setText(l.getQuestion());
        tvOpsi1.setText(l.getOpsi().get("a"));
        tvOpsi2.setText(l.getOpsi().get("b"));
        tvOpsi3.setText(l.getOpsi().get("c"));
//        tvOpsi4.setText(l.getOpsi().get("d"));
        if (l.getRealAnswer().equalsIgnoreCase("a")){
            setKunci(tvOpsi1);
        } else if (l.getRealAnswer().equalsIgnoreCase("b")){
            setKunci(tvOpsi2);
        } else if (l.getRealAnswer().equalsIgnoreCase("c")){
            setKunci(tvOpsi3);
        }

        if (l.getUserAnswer().equalsIgnoreCase("a")){
            setOpsi(tvOpsi1);
        } else if (l.getUserAnswer().equalsIgnoreCase("b")){
            setOpsi(tvOpsi2);
        } else if (l.getUserAnswer().equalsIgnoreCase("c")){
            setOpsi(tvOpsi3);
        }
    }

    public void nextCekListening(View view){
        if (currentQuestion < arListening.size() - 1){
            currentQuestion++;
            showQuestion(currentQuestion);
        }
    }

    public void backCekListening(View view){
        if (currentQuestion > 0){
            currentQuestion--;
            showQuestion(currentQuestion);
        }
    }

    public void setKunci(TextView tvKunci) {
        tvKunci.setTypeface(null, Typeface.BOLD);
        tvKunci.setTextColor(Color.parseColor("#64dd17"));
    }

    public void setOpsi(TextView tvOpsi) {
        tvOpsi.setTypeface(null, Typeface.BOLD);
        tvOpsi.setTextColor(Color.parseColor("#303f9f"));
    }

    public void netralkanOpsi() {
        tvOpsi1.setTypeface(null, Typeface.NORMAL);
        tvOpsi2.setTypeface(null, Typeface.NORMAL);
        tvOpsi3.setTypeface(null, Typeface.NORMAL);
//        tvOpsi4.setTypeface(null, Typeface.NORMAL);
        tvOpsi1.setTextColor(Color.parseColor("#000000"));
        tvOpsi2.setTextColor(Color.parseColor("#000000"));
        tvOpsi3.setTextColor(Color.parseColor("#000000"));
//        tvOpsi4.setTextColor((Color.parseColor("#000000")));
    }

//    private void setOpsi(TextView tvdipilih, TextView tvTdkDiplih1, TextView tvtdkDiplih2, TextView tvtdkdipilih3) {
//        tvdipilih.setTypeface(null, Typeface.BOLD);
//        tvTdkDiplih1.setTypeface(null, Typeface.NORMAL);
//        tvtdkDiplih2.setTypeface(null, Typeface.NORMAL);
//        tvtdkdipilih3.setTypeface(null, Typeface.NORMAL);
//        tvdipilih.setTextColor(Color.parseColor("#303f9f"));
//        tvTdkDiplih1.setTextColor(Color.parseColor("#000000"));
//        tvtdkDiplih2.setTextColor((Color.parseColor("#000000")));
//        tvtdkdipilih3.setTextColor(Color.parseColor("#000000"));
//    }

    private void setDummyData(){
//        ArrayList<Listening> arListening = new ArrayList<>();
        Listening l = new Listening();
        l.setQuestion("question 1");
        l.setRealAnswer("a");
        l.setUserAnswer("c");
        HashMap<String, String > hmOpsi = new HashMap<>();
        hmOpsi.put("a", "a. opsi 1a");
        hmOpsi.put("b", "b. opsi 1a");
        hmOpsi.put("c", "c. opsi 1a");
//        hmOpsi.put("d", "d. opsi 1a");
        l.setOpsi(hmOpsi);
        arListening.add(l);

        Listening l1 = new Listening();
        l1.setQuestion("question 2");
        l1.setRealAnswer("b");
        l1.setUserAnswer("b");
        HashMap<String, String > hmOpsi1 = new HashMap<>();
        hmOpsi1.put("a", "a. opsi 1b");
        hmOpsi1.put("b", "b. opsi 1b");
        hmOpsi1.put("c", "c. opsi 1b");
//        hmOpsi1.put("d", "d. opsi 1b");
        l1.setOpsi(hmOpsi1);
        arListening.add(l1);

        Listening l2 = new Listening();
        l2.setQuestion("question 3");
        l2.setRealAnswer("c");
        l2.setUserAnswer("a");
        HashMap<String, String > hmOpsi2 = new HashMap<>();
        hmOpsi2.put("a", "a. opsi 1c");
        hmOpsi2.put("b", "b. opsi 1c");
        hmOpsi2.put("c", "c. opsi 1c");
//        hmOpsi2.put("d", "d. opsi 1c");
        l2.setOpsi(hmOpsi2);
        arListening.add(l2);
    }
}
