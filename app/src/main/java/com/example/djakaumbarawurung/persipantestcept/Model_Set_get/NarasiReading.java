package com.example.djakaumbarawurung.persipantestcept.Model_Set_get;

import java.util.ArrayList;

/**
 * Created by Djaka Umbara Wurung on 10/19/2016.
 */
public class NarasiReading {

    private int id_narasi;
    private String narasi;

    public ArrayList<PertanyaanReading> getPertanyaanReadingArraylist() {
        return pertanyaanReadingArraylist;
    }

    public void setPertanyaanReadingArraylist(ArrayList<PertanyaanReading> pertanyaanReadingArraylist) {
        this.pertanyaanReadingArraylist = pertanyaanReadingArraylist;
    }

    private ArrayList<PertanyaanReading> pertanyaanReadingArraylist;

    public int getId_narasi() {
        return id_narasi;
    }

    public void setId_narasi(int id_narasi) {
        this.id_narasi = id_narasi;
    }

    public String getNarasi() {
        return narasi;
    }

    public void setNarasi(String narasi) {
        this.narasi = narasi;
    }
}
