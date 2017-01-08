package com.example.djakaumbarawurung.persipantestcept.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Grammar;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.NarasiReading;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.Opsi;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.OpsiReading;
import com.example.djakaumbarawurung.persipantestcept.Model_Set_get.SoalReading;

import java.util.ArrayList;

/**
 * Created by Djaka Umbara Wurung on 8/12/2016.
 */
public class DataSource_PenghubungTabel extends AppCompatActivity {
    private SQLiteDatabase database;
    private SQLiteHelper databaseHelper;


    public DataSource_PenghubungTabel(Context context) {
        databaseHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }


    // ambil semua opsi grammar berdasarkan pertanyaan
    public ArrayList<Opsi> ambilOpsiGrammarSesuaiIdSoal(int idPertanyaan) {
        ArrayList<Opsi> alOpsi = new ArrayList<>();
        Cursor cursor = database.rawQuery(" select * from " + SQLiteHelper.TABLE_OPSI_GRAMMAR + " where "
                        + SQLiteHelper.COLUMN_ID_GRAMMAR + " = ? ",
                new String[]{String.valueOf(idPertanyaan)});
        cursor.moveToFirst();
        while ((!cursor.isAfterLast())) {
            Opsi opsi = new Opsi();
            opsi.setId_grammar(cursor.getInt(0));
            opsi.setOpsi(cursor.getString(1));
            opsi.setId_grammar(cursor.getInt(2));
            alOpsi.add(opsi);
            cursor.moveToNext();
        }
        return alOpsi;
    }


    public void insertDataKeGrammar(int id, String pertanyaan, String jawaban, String penjelasan) {
        database.execSQL("insert into " + databaseHelper.TABLE_GRAMMAR + " (" +
                databaseHelper.COLUMN_ID_GRAMMAR + "," +
                databaseHelper.COLUMN_PERTANYAAN_GRAMMAR + "," +
                databaseHelper.COLUMN_JAWABAN_GRAMMAR + "," +
                databaseHelper.COLUMN_PENJELASAN_GRAMMAR + ") values (" +
                id + ", '" +
                pertanyaan + "','" +
                jawaban + "','" +
                penjelasan + "')");
    }

    public void insertDataKeOpsi(int id, String opsi, int id_grammar) {
        database.execSQL("insert into " + databaseHelper.TABLE_OPSI_GRAMMAR + " (" +
                databaseHelper.COLUMN_ID_OPSI_GRAMMAR + "," +
                databaseHelper.COLUMN_OPSI_GRAMMAR + "," +
                databaseHelper.COLUMN_ID_GRAMMAR + ") values (" +
                id + ",'" +
                opsi + "'," +
                id_grammar + ")");
    }


    // baca aktivitas user itu log user grammar
    public String bacaAktivitasUser(int id) {
        Cursor cursor = database.rawQuery(" select * from " + SQLiteHelper.TABLE_LOG_GRAMMAR+ " where " + SQLiteHelper.COLUMN_ID_GRAMMAR
                + " = ? ", new String[]{String.valueOf(id)});
        String pilihanUser = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            pilihanUser = cursor.getString(2);
            cursor.moveToNext();
        }
        return pilihanUser;
    }

    // tapi ini log grammar

    //memasukkan jawban yang dipilih user ke tabel log
    public void insertLogUser(int idPertanyaan, String pilihanUser) {
        database.execSQL("insert into " + SQLiteHelper.TABLE_LOG_GRAMMAR + " (" +
                SQLiteHelper.COLUMN_JAWABAN_GRAMMAR + ", " +
                SQLiteHelper.COLUMN_ID_GRAMMAR + ") values ('" +
                pilihanUser + "', " +
                idPertanyaan + ");");

    }

    //mengosongkan tabel log

    public void KosongkanLog() {
        database.execSQL("delete from" + SQLiteHelper.TABLE_LOG_GRAMMAR);
    }


    // menghitung jumlah data di tabel pertanyaan
    public int hitungrecordGrammar() {
        int menampungrecord = 0;
        Cursor cursor = database.rawQuery("select count(*) from " + databaseHelper.TABLE_GRAMMAR, null);
        cursor.moveToFirst();
        menampungrecord = cursor.getInt(0);
        return menampungrecord;
    }

//    public int hitungrecordReading() {
//        int menampungrecord = 0;
//        Cursor cursor = database.rawQuery("select count(*) from " + databaseHelper.TABLE_READING, null);
//        cursor.moveToFirst();
//        menampungrecord = cursor.getInt(0);
//        return menampungrecord;
//    }


    //
    public int hitungrecordOpsi() {
        int menampungrecord = 0;
        Cursor cursor = database.rawQuery("select count(*) from " + databaseHelper.TABLE_OPSI_GRAMMAR, null);
        cursor.moveToFirst();
        menampungrecord = cursor.getInt(0);
        return menampungrecord;
    }


    public int hitungrecordOpsiReading() {
        int menampungrecord = 0;
        Cursor cursor = database.rawQuery("select count(*) from " + databaseHelper.TABLE_OPSI_READING, null);
        cursor.moveToFirst();
        menampungrecord = cursor.getInt(0);
        return menampungrecord;
    }

//    public int hitungrecordNarasiReading() {
//        int menampungrecord = 0;
//        Cursor cursor = database.rawQuery("select count(*) from " + databaseHelper.TABLE_NARASI_READING, null);
//        cursor.moveToFirst();
//        menampungrecord = cursor.getInt(0);
//        return menampungrecord;
//    }

    //untuk menghitung jumlah record di dalam suatu tabel
    public int hitungJumlahRecordTabel(String namaTabel){
        int jmlRecord = 0;
        Cursor cursor = database.rawQuery("select count(*) from " + namaTabel, null);
        cursor.moveToFirst();
        jmlRecord = cursor.getInt(0);
        return jmlRecord;
    }


    // mengambil id grammar yang sesuai kondisi
    public Grammar ambilGrammarSesuaiId(int Id_Grammar) {
        Grammar grammar2 = new Grammar();
        Cursor cursor = database.rawQuery("select * from " + SQLiteHelper.TABLE_GRAMMAR + " where " + SQLiteHelper.COLUMN_ID_GRAMMAR + " = ? "
                , new String[]{String.valueOf(Id_Grammar)}
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            grammar2.setId_soal(cursor.getInt(0));
            grammar2.setPertanyaan(cursor.getString(1));
            grammar2.setJawaban(cursor.getString(2));
            grammar2.setPenjelasan(cursor.getString(3));
            grammar2.setOpsiArrayList(ambilOpsiGrammarSesuaiIdSoal(Id_Grammar));
            cursor.moveToNext();
        }

        return grammar2;
    }

    // Ambil pertanyaan sesuai id ( semua data )
    public ArrayList<Integer> mengambilsemuaIdGrammar() {
        ArrayList<Integer> alId = new ArrayList<>();
        Cursor cursor = database.rawQuery("select " + SQLiteHelper.COLUMN_ID_GRAMMAR +
                " from " + SQLiteHelper.TABLE_GRAMMAR, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            alId.add(cursor.getInt(0));
            cursor.moveToNext();
        }
        return alId;
    }

    // mengambil penjelasan sesuai id ( nanti diubah)
    public String ambilPenjelasanSesuaiId(int pertanyaan) {
        String penjelasan = "";
        Cursor cursor = database.rawQuery(" select " + SQLiteHelper.COLUMN_PENJELASAN_GRAMMAR + " from " +
                        SQLiteHelper.TABLE_GRAMMAR + " where " + SQLiteHelper.COLUMN_ID_GRAMMAR + " = ?",
                new String[]{String.valueOf(pertanyaan)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            penjelasan = cursor.getString(0);
            cursor.moveToNext();
        }
        return penjelasan;
    }




// masuk ke bagian reading

    // memasukkan data pertanyaan ke tabel pertanyaan reading
    public void insertDataSoalReading(int id, String pertanyaan, String jawaban, String penjelasan, int id_narasi) {
        database.execSQL("insert into " + databaseHelper.TABLE_SOAL_READING + " (" +
                databaseHelper.COLUMN_ID_READING + "," +
                databaseHelper.COLUMN_JAWABAN_READING + "," +
                databaseHelper.COLUMN_PENJELASAN_READING + "," +
                databaseHelper.COLUMN_PERTANYAAN_READING + "," +
                databaseHelper.COLUMN_ID_NARASI + ") values (" +
                id + ", '" +
                jawaban + "','" +
                penjelasan + "','" +
                pertanyaan + "','" +
                id_narasi + "')");
    }

    // unutk membuat data narasi
    public void buatDataBacaanReading(int id, String bacaan) {
        database.execSQL("insert into " + SQLiteHelper.TABLE_NARASI_READING + " (" +
                SQLiteHelper.COLUMN_ID_NARASI + ", " +
                SQLiteHelper.COLUMN_NARASI_READING +
                ") values (" +
                id + ", '" +
                bacaan + "');");
    }



    // mengambil data naskah narasi untuk setiap soal tipe reading

    public ArrayList<NarasiReading> ambilNarasiReading() {
        String queryNaskah = " select * from " + SQLiteHelper.TABLE_NARASI_READING;
        Cursor cursor = database.rawQuery(queryNaskah,null);
        cursor.moveToFirst();


        ArrayList<NarasiReading>narasiReadingArrayList = new ArrayList<>();
        while(!cursor.isAfterLast()){
            NarasiReading narasiReading= new NarasiReading();
            int idNarasi = cursor.getInt(0);
            narasiReading.setIdNarasi(idNarasi);
            narasiReading.setNarasi(cursor.getString(1));
            narasiReading.setSoalReadingArrayList(ambilSoalReading(idNarasi));
            narasiReadingArrayList.add(narasiReading);
            cursor.moveToNext();
        }
        return narasiReadingArrayList;
    }


    // mengambil data pertanyaan untuk soal tipe reading
    public ArrayList<SoalReading> ambilSoalReading(int idNaskahReading) {
        String queryPertanyaanReading = "select * from " + SQLiteHelper.TABLE_SOAL_READING +
                " where " + SQLiteHelper.COLUMN_ID_NARASI + " = ?";
        Cursor cursor = database.rawQuery(queryPertanyaanReading, new String[]
                {String.valueOf(idNaskahReading)});
        cursor.moveToFirst();

        ArrayList<SoalReading> soalReadingArrayList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            SoalReading soalReading = new SoalReading();
            int idPertanyaan = cursor.getInt(0);
            soalReading.setId(idPertanyaan);
            soalReading.setPertanyaan(cursor.getString(1));
            soalReading.setJawaban(cursor.getString(2));
            soalReading.setPenjelasan(cursor.getString(4));
            soalReading.setOpsiReadingArrayList(ambilOpsiReading(idPertanyaan));
            soalReadingArrayList.add(soalReading);
            cursor.moveToNext();
        }
        return soalReadingArrayList;
    }

    // mengambil data opsi pertanyaan untuk soal tipe reading
    public ArrayList<OpsiReading> ambilOpsiReading(int idOpsiReading){
            String queryOpsiReading = "Select * from " + SQLiteHelper.TABLE_OPSI_READING + " Where " +
                    SQLiteHelper.COLUMN_ID_READING + " = ? ";
            Cursor cursor = database.rawQuery(queryOpsiReading, new String[]{String.valueOf(idOpsiReading)});
            cursor.moveToFirst();

            ArrayList<OpsiReading> opsiReadingArrayList = new ArrayList<>();
            while (!cursor.isAfterLast()) {
                OpsiReading opsiReading = new OpsiReading();
                opsiReading.setId(cursor.getInt(0));
                opsiReading.setOpsi(cursor.getString(1));
                opsiReadingArrayList.add(opsiReading);
                cursor.moveToNext();
            }
            return opsiReadingArrayList;
        }



// memasukkan data opsi ke tabel opsi reading
    public void buatDataOpsiReading(int id, String OpsiReading, int tanya) {
        database.execSQL("insert into " + SQLiteHelper.TABLE_OPSI_READING + " (" +
                SQLiteHelper.COLUMN_ID_OPSI_READING + ", " +
                SQLiteHelper.COLUMN_OPSI_READING+ ", " +
                SQLiteHelper.COLUMN_ID_READING +
                ") values (" +
                id + ", '" +
                OpsiReading + "', " +
                tanya + ");");
    }



    // memasukkan opsi ke opsi
    public void insertDataOpsiReading(String opsi, int id_soal_reading) {
        database.execSQL("insert into " + databaseHelper.TABLE_OPSI_READING + " (" +
                databaseHelper.COLUMN_OPSI_READING + ", " +
                databaseHelper.COLUMN_ID_READING + ") values ('" +
                opsi + "', " +
                id_soal_reading + ")");
    }


    // insert  masukan data opsi ke table baca reading

    public void insertDataNarasiReading(int id, String NarasiReading) {
        database.execSQL(" insert into " + databaseHelper.TABLE_NARASI_READING + " (" +
                databaseHelper.COLUMN_ID_NARASI + "," +
                databaseHelper.COLUMN_NARASI + ") values (" +
                id + ",'" +
                NarasiReading + "')");
    }


}



