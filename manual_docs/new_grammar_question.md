# Memasukkan data soal kuis grammar

Ada dua tabel yang harus diisi ketika memasukkan soal tipe grammar baru, yaitu TB_GRAMMAR dan TB_OPSI_GRAMMAR.

<pre>
TB_GRAMMAR

id_grammar  INTEGER PRIMARY KEY AUTOINCREMENT
pertanyaan  TEXT
jawaban     TEXT
penjelasan  TEXT
</pre>


<pre>
TB_OPSI_GRAMMAR

id_opsi     INTEGER PRIMARY KEY AUTOINCREMENT
pilihan     TEXT
id_grammar  TEXT
</pre>

Buat Activity baru, misal TambahSoalGrammarActivity

layout activity_tambah_soal_grammar.xml

![activity_tambah_soal_grammar.xml](https://github.com/ardhiesta/ToeflLikeApp/blob/master/manual_docs/img/add_new_question.jpg)

code lengkap dapat dilihat
[DI SINI](https://github.com/ardhiesta/ToeflLikeApp/blob/master/app/src/main/res/layout/activity_tambah_soal_grammar.xml)

Tambahkan method untuk memasukkan data ke tabel grammar dan tabel opsi grammar di [DataSource_PenghubungTabel.java](https://github.com/ardhiesta/ToeflLikeApp/blob/master/app/src/main/java/com/example/djakaumbarawurung/persipantestcept/database/DataSource_PenghubungTabel.java)

<pre>
// insert data ke tabel grammar (TB_GRAMMAR), id digenerate otomatis
    public long insertDataKeGrammarTanpaId(String pertanyaan, String jawaban, String penjelasan) {
        ContentValues values = new ContentValues();
        values.put(databaseHelper.COLUMN_PERTANYAAN_GRAMMAR, pertanyaan);
        values.put(databaseHelper.COLUMN_JAWABAN_GRAMMAR, jawaban);
        values.put(databaseHelper.COLUMN_PENJELASAN_GRAMMAR, penjelasan);

        // selain pakai .execSQL() untuk memasukkan data ke tabel bisa juga pakai .insert()
        // bedanya kalau pakai .insert akan dapat return value berupa nilai ID grammar terakhir yang tercreate
        // nilai ID grammar akan dibuat otomatis, tidak perlu ditulis
        // nilai ID grammar akan digunakan untuk menginsert opsi baru di TB_OPSI_GRAMMAR
        long insertedId = database.insert(databaseHelper.TABLE_GRAMMAR, null, values);
        return insertedId;
    }

    // insert data ke tabel opsi grammar (TB_OPSI_GRAMMAR)
    // opsiGrammar didapat dari data yang diinput lewat form, idGrammar didapat dari hasil method insertDataKeGrammarTanpaId
    public long insertOpsiGrammar(String opsiGrammar, long idGrammar){
        ContentValues values = new ContentValues();
        values.put(databaseHelper.COLUMN_OPSI_GRAMMAR, opsiGrammar);
        values.put(databaseHelper.COLUMN_ID_GRAMMAR, idGrammar);
        long newOpsiId = database.insert(databaseHelper.TABLE_OPSI_GRAMMAR, null, values);
        return newOpsiId;
    }
</pre>

Buat method simpanPertanyaanGrammarBaru di TambahSoalGrammarActivity.java, panggil di dalam onClick tombol simpan yang ada di layout [activity_tambah_soal_grammar.xml](https://github.com/ardhiesta/ToeflLikeApp/blob/master/app/src/main/res/layout/activity_tambah_soal_grammar.xml)

<pre>
public void simpanPertanyaanGrammarBaru(View view){
        String pertanyaan = etQuestion.getText().toString();
        String jawaban = etAnswer.getText().toString();
        String penjelasan = etExplanation.getText().toString();
        String opsi1 = etOpsi1.getText().toString();
        String opsi2 = etOpsi2.getText().toString();
        String opsi3 = etOpsi3.getText().toString();
        String opsi4 = etOpsi4.getText().toString();

        long newGrammarId = 0;

        if (!pertanyaan.equalsIgnoreCase("") && !jawaban.equalsIgnoreCase("")
                && !penjelasan.equalsIgnoreCase("")){
            newGrammarId = dataSource_penghubungTabel.insertDataKeGrammarTanpaId(pertanyaan, jawaban, penjelasan);
            if (newGrammarId > 0){
                dataSource_penghubungTabel.insertOpsiGrammar(opsi1, newGrammarId);
                dataSource_penghubungTabel.insertOpsiGrammar(opsi2, newGrammarId);
                dataSource_penghubungTabel.insertOpsiGrammar(opsi3, newGrammarId);
                dataSource_penghubungTabel.insertOpsiGrammar(opsi4, newGrammarId);
            }
        }
    }
</pre>
