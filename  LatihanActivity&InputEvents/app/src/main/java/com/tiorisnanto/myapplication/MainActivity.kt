package com.tiorisnanto.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtPanjang: EditText
    private lateinit var edtLebar: EditText
    private lateinit var edtTinggi: EditText
    private lateinit var txtHasil: TextView
    private lateinit var btnHitung: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPanjang = findViewById(R.id.edtPanjang)
        edtLebar = findViewById(R.id.edtLebar)
        edtTinggi = findViewById(R.id.edtTinggi)
        txtHasil = findViewById(R.id.tvHasil)
        btnHitung = findViewById(R.id.btnHitung)

        btnHitung.setOnClickListener(this)

        if (savedInstanceState != null) {
            val hasil = savedInstanceState.getString(STATE_RESULT)
            txtHasil.text = hasil
        }
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btnHitung) {

            /**
             * Sintaks .isEmpty() berfungsi untuk mengecek apakah inputan dari edittext itu masih kosong.
             * Jika iya, maka kita akan menampilkan pesan error
             * dan mengganti variabel boolean isEmptyField menjadi true supaya bisa lanjut ke proses selanjutnya.
             */

            /**
             * Sintaks .text.toString() di atas berfungsi untuk mengambil
             * isi dari sebuah edittext kemudian menyimpannya dalam sebuah variabel.
             * Tambahan .trim() berfungsi untuk menghiraukan spasi jika ada, sehingga nilai yang didapat hanya berupa angka.
             */

            var isEmpty = false

            val inputPanjang = edtPanjang.text.toString().trim()
            val inputLebar = edtLebar.text.toString().trim()
            val inputTinggi = edtTinggi.text.toString().trim()

            if (inputPanjang.isEmpty()) {
                isEmpty = true
                edtPanjang.error = "Panjang tidak boleh kosong"
            }

            if (inputLebar.isEmpty()) {
                isEmpty = true
                edtLebar.error = "Lebar tidak boleh kosong"
            }

            if (inputTinggi.isEmpty()) {
                isEmpty = true
                edtTinggi.error = "Tinggi tidak boleh kosong"
            }

            if (!isEmpty) {
                val panjang = inputPanjang.toDouble()
                val lebar = inputLebar.toDouble()
                val tinggi = inputTinggi.toDouble()

                val volume = panjang * lebar * tinggi
                txtHasil.text = volume.toString()
            }
        }
    }

    /**
     *  tambahkan metode onSaveInstanceState() pada MainActivity
     *  Catatan:
        Pada Kotlin kita tidak bisa membuat konstanta secara langsung di dalam Class.
        Ia hanya bisa dibuat di dalam Object karena konstanta bersifat static.
        Oleh karena itulah dibutuhkan companion object untuk membuat komponen yang static dan bisa diakses dari luar kelas.
     */

    /**
     * tambahkan juga beberapa baris berikut pada baris terakhir metode onCreate()
     *
     */

    /**
     *  Fungsi onSaveInstanceState akan dipanggil secara otomatis sebelum sebuah Activity hancur.
     *  Di sini kita perlu menambahkan onSaveInstanceState karena ketika orientasi berubah,
     *  Activity tersebut akan di-destroy dan memanggil fungsi onCreate lagi,
     *  sehingga kita perlu menyimpan nilai hasil perhitungan tersebut supaya data tetap terjaga dan
     *  tidak hilang ketika orientasi berubah.
     */

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, txtHasil.text.toString())
    }

    companion object {
        const val STATE_RESULT = "state_result"
    }
}