package com.example.djakaumbarawurung.persipantestcept;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.UserLog;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ActivityListening extends AppCompatActivity {

    TextView TvSoalListening, TvOpsi1Listening, TvOpsi2Listening,
            TvOpsi3Listening, TvOpsi4Listening, tvTime;
    Button ListeningStart, bCheckListening;
    private MediaPlayer mediaPlayer;
    String jawabanUser = "";
//    ArrayList<JawabanUser> arJul = new ArrayList<>();
    ArrayList<UserLog> userLogArrayList = new ArrayList<>();
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);

        TvSoalListening = (TextView) findViewById(R.id.TvSoalListening);
        TvOpsi1Listening = (TextView) findViewById(R.id.TvOpsi1Listening);
        TvOpsi2Listening = (TextView) findViewById(R.id.TvOpsi2Listening);
        TvOpsi3Listening = (TextView) findViewById(R.id.TvOpsi3Listening);
        TvOpsi4Listening = (TextView) findViewById(R.id.TvOpsi4Listening);
        ListeningStart = (Button) findViewById(R.id.ListeningStart);
        bCheckListening = (Button) findViewById(R.id.bCheckListening);
        bCheckListening.setVisibility(View.INVISIBLE); //tombol cek jawaban dihidden dulu
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTime.setText("");

        TvSoalListening.setText("");
        TvOpsi1Listening.setText("");
        TvOpsi2Listening.setText("");
        TvOpsi3Listening.setText("");
        TvOpsi4Listening.setText("");

        int songId = this.getRawResIdByName("soal_lengkap");

        this.mediaPlayer = MediaPlayer.create(this, songId);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TvSoalListening.setText("Question 1");
                TvOpsi1Listening.setText("a. opsi 1");
                TvOpsi2Listening.setText("b. opsi 2");
                TvOpsi3Listening.setText("c. opsi 3");
                TvOpsi4Listening.setText("d. opsi 4");
            }
        }, 110000);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //simpan dulu jawaban user dr pertanyaan 1
                UserLog userLog = new UserLog();
                userLog.setPertanyaan("Question 1");
                userLog.setJawabanUser(jawabanUser);
                userLog.setKunci("a");
                userLogArrayList.add(userLog);
//                listeningLog.setPenjelasan();

                netralkanOpsi();

                TvSoalListening.setText("Question 2");
                TvOpsi1Listening.setText("a. opsi 1b");
                TvOpsi2Listening.setText("b. opsi 2b");
                TvOpsi3Listening.setText("c. opsi 3b");
                TvOpsi4Listening.setText("d. opsi 4b");
            }
        }, 127000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserLog userLog = new UserLog();
                userLog.setPertanyaan("Question 2");
                userLog.setJawabanUser(jawabanUser);
                userLog.setKunci("a");
                userLogArrayList.add(userLog);

                netralkanOpsi();

                TvSoalListening.setText("Question 3");
                TvOpsi1Listening.setText("a. opsi 1c");
                TvOpsi2Listening.setText("b. opsi 2c");
                TvOpsi3Listening.setText("c. opsi 3c");
                TvOpsi4Listening.setText("d. opsi 4c");
            }
        }, 147000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserLog userLog = new UserLog();
                userLog.setPertanyaan("Question 3");
                userLog.setJawabanUser(jawabanUser);
                userLog.setKunci("a");
                userLogArrayList.add(userLog);

                netralkanOpsi();

                TvSoalListening.setText("Question 4");
                TvOpsi1Listening.setText("a. opsi 1d");
                TvOpsi2Listening.setText("b. opsi 2d");
                TvOpsi3Listening.setText("c. opsi 3d");
                TvOpsi4Listening.setText("d. opsi 4d");
            }
        }, 166000);

        //event ketika mediaplayer telah selesai memutar audio
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //tombol cek jawaban ditampilkan
                bCheckListening.setVisibility(View.VISIBLE);
            }
        });
    }

    public void saveJawaban(View view) {
        UserLog userLog = new UserLog();
        userLog.setPertanyaan("Question 4");
        userLog.setJawabanUser(jawabanUser);
        userLog.setKunci("a");
        userLogArrayList.add(userLog);

        netralkanOpsi();

        Intent intent = new Intent(ActivityListening.this, ActivityCekJawabanListening.class);
        intent.putParcelableArrayListExtra("aktivitasUser", userLogArrayList);
        startActivity(intent);
    }

    public void netralkanOpsi() {
        jawabanUser = "";

        TvOpsi1Listening.setTypeface(null, Typeface.NORMAL);
        TvOpsi2Listening.setTypeface(null, Typeface.NORMAL);
        TvOpsi3Listening.setTypeface(null, Typeface.NORMAL);
        TvOpsi4Listening.setTypeface(null, Typeface.NORMAL);
        TvOpsi1Listening.setTextColor(Color.parseColor("#000000"));
        TvOpsi2Listening.setTextColor(Color.parseColor("#000000"));
        TvOpsi3Listening.setTextColor(Color.parseColor("#000000"));
        TvOpsi4Listening.setTextColor((Color.parseColor("#000000")));
    }

    public void StartListening(View view) {
        this.mediaPlayer.start();

        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();

        ListeningStart.setVisibility(View.INVISIBLE);
        TvSoalListening.setText("listening to the instruction .. .");

        tvTime.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                startTime)))
        );

        myHandler.postDelayed(UpdateSongTime,100);
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            double timeRemaining = finalTime - startTime;
            tvTime.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
                    TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) timeRemaining)))
            );
            myHandler.postDelayed(this, 100);
        }
    };

    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();

        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        super.onDestroy();

    }

    public void opsi1ListeningKlik(View view) {
        //ambil Abjad Pilihan User
        jawabanUser = TvOpsi1Listening.getText().toString();
        setOpsi(TvOpsi1Listening, TvOpsi2Listening, TvOpsi3Listening, TvOpsi4Listening);
    }

    public void opsi2ListeningKlik(View view) {
        //ambil Abjad Pilihan User
        jawabanUser = TvOpsi2Listening.getText().toString();
        setOpsi(TvOpsi2Listening, TvOpsi1Listening, TvOpsi3Listening, TvOpsi4Listening);
    }

    public void opsi3ListeningKlik(View view) {
        //ambil Abjad Pilihan User
        jawabanUser = TvOpsi3Listening.getText().toString();
        setOpsi(TvOpsi3Listening, TvOpsi1Listening, TvOpsi2Listening, TvOpsi4Listening);
    }

    public void opsi4ListeningKlik(View view) {
        //ambil Abjad Pilihan User
        jawabanUser = TvOpsi4Listening.getText().toString();
        setOpsi(TvOpsi4Listening, TvOpsi1Listening, TvOpsi2Listening, TvOpsi3Listening);
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
}
