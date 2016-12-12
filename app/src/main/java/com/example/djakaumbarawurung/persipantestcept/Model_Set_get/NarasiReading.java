package com.example.djakaumbarawurung.persipantestcept.Model_Set_get;

import java.util.ArrayList;

/**
 * Created by Djaka Umbara Wurung on 10/19/2016.
 */
public class NarasiReading {

    private int idNarasi;
    private String narasi;

    public ArrayList<SoalReading> getSoalReadingArrayList() {
        return soalReadingArrayList;
    }

    public void setSoalReadingArrayList(ArrayList<SoalReading> soalReadingArrayList) {
        this.soalReadingArrayList = soalReadingArrayList;
    }

    private ArrayList<SoalReading> soalReadingArrayList;

    public int getIdNarasi() {
        return idNarasi;
    }

    public void setIdNarasi(int idNarasi) {
        this.idNarasi = idNarasi;
    }

    public String getNarasi() {
        return narasi;
    }

    public void setNarasi(String narasi) {
        this.narasi = narasi;
    }
}
