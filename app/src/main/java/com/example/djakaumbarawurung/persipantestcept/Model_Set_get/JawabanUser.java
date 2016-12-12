package com.example.djakaumbarawurung.persipantestcept.Model_Set_get;

/**
 * Created by linuxluv on 11/15/16.
 */

public class JawabanUser {
    //menyimpan id pertanyaan
    int idPertanyaan;
    //menyimpan jawaban soal yang dipilih user
    String jawaban;

    public int getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(int idPertanyaan) {
        this.idPertanyaan = idPertanyaan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}
