package com.example.djakaumbarawurung.persipantestcept.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Opsi;

import java.util.ArrayList;

public class Opsi_Grammar_Source {

    private SQLiteDatabase database; // untuk mengkases database
    private SQLiteHelper databaseHelper;

    public Opsi_Grammar_Source(Context context) {
        databaseHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    //memasukkan record

    public int hitungrecord (){
        int menampungrecord = 0;
        Cursor cursor = database.rawQuery("select count(*) from " + databaseHelper.TABLE_OPSI_READING, null);
        cursor.moveToFirst();
        menampungrecord=cursor.getInt(0);
        return menampungrecord;
    }
    public ArrayList<Opsi> getOpsiByPertanyaan(long idpertanyaan){
        ArrayList<Opsi> arrayListOpsi = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from "+SQLiteHelper.TABLE_OPSI_READING+ " where " + SQLiteHelper.COLUMN_ID_READING + " = ?",
                new String[]{String.valueOf(idpertanyaan)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Opsi opsi = new Opsi();
            opsi.setId_opsi(cursor.getInt(0));
            opsi.setOpsi(cursor.getString(1));
            opsi.setId_grammar(cursor.getInt(2));
            arrayListOpsi.add(opsi);
            cursor.moveToNext();
        }
        return arrayListOpsi;

    }
}