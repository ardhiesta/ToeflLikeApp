# Halaman Tutorial

Misal ingin memberi petunjuk mengenai penggunaan aplikasi, dapat dilakukan dengan cara membuat halaman untuk menampilkan gambar yang berisi petunjuk penggunaan program yang disertai penjelasan.

Pada contoh ini, di Home terdapat tombol untuk membuka tutorial.

![home](https://github.com/ardhiesta/ToeflLikeApp/blob/master/manual_docs/img/home.jpg)

Kemudian user diarahkan ke activity yang memuat dua halaman yang berisi screenshot program.

![tutor1](https://github.com/ardhiesta/ToeflLikeApp/blob/master/manual_docs/img/tutor1.jpg)

![tutor1](https://github.com/ardhiesta/ToeflLikeApp/blob/master/manual_docs/img/tutor2.jpg)

Untuk membuat screen tutorial tersebut, langkah-langkahnya adalah sebagai berikut:

1. Masukkan gambar-gambar yang berisi screenshot program untuk tutorial ke folder <b>res/drawable</b>

2. Tambahkan code berikut ke [build.gradle](https://github.com/ardhiesta/ToeflLikeApp/blob/master/app/build.gradle)

    <pre>//viewPager indicator
    compile 'me.relex:circleindicator:1.2.2@aar'</pre>

3. Buat <b>Fragment</b>, caranya dengan klik kanan pada folder package di project Android Studio kemudian pilih <b>New --> Fragment --> Fargment (Blank)</b>

4. Setiap gambar akan ditampilkan pada sebuah Fragment, dalam contoh ini saya membuat dua buah Fragmant yaitu [FragmentTutor1](https://github.com/ardhiesta/ToeflLikeApp/blob/master/app/src/main/java/com/example/djakaumbarawurung/persipantestcept/tutor_screen/FragmentTutor1.java) dan [FragmentTutor2](https://github.com/ardhiesta/ToeflLikeApp/blob/master/app/src/main/java/com/example/djakaumbarawurung/persipantestcept/tutor_screen/FragmentTutor1.java).
