package com.example.mechacare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.razorpay.Checkout
import org.json.JSONObject


class BillingAdressActivity : AppCompatActivity() {
    // Deklarasikan variabel untuk widget
    private lateinit var namaLengkap: EditText
    private lateinit var alamat: EditText
    private lateinit var provinsi: EditText
    private lateinit var posKode: EditText
    private lateinit var nomorTelepon: EditText
    private lateinit var opsiPengiriman: EditText
    private lateinit var btnPembayaran: Button
    private val db = FirebaseFirestore.getInstance()

    // Function to set the SDK language


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_billing_adress)

        Checkout.preload(applicationContext)
        val co = Checkout()
        co.setKeyID("rzp_test_sg_Zorepa2oWYuMcr")

        val userId = "5g4Tf3YqlJob5cMrWHC9" // Ganti dengan ID pengguna dinamis jika diperlukan
        val transaksiId = "transaksi_sparepart" // Nama dokumen atau gunakan `add` untuk ID otomatis

        // Inisialisasi widget dari layout XML
        namaLengkap = findViewById(R.id.nama_lengkap)
        alamat = findViewById(R.id.et_alamat)
        provinsi = findViewById(R.id.et_provinsi)
        posKode = findViewById(R.id.et_poskode)
        nomorTelepon = findViewById(R.id.et_nomorTelepon)
        opsiPengiriman = findViewById(R.id.et_opsipengiriman)
        btnPembayaran = findViewById(R.id.btn_pembayaran)

        // Tambahkan aksi ke tombol pembayaran
        btnPembayaran.setOnClickListener {
            val nama = namaLengkap.text.toString().trim()
            val alamatLengkap = alamat.text.toString().trim()
            val provinsiUser = provinsi.text.toString().trim()
            val kodePos = posKode.text.toString().trim()
            val nomorTelepon =
                "08123456789" // Ganti dengan nomor telepon penggunanegaraUser = negara.text.toString().trim()
            val opsiKirim = opsiPengiriman.text.toString().trim()
            val namaBarang = intent.getStringExtra("nama") ?: ""
            val hargaBarang = intent.getIntExtra("harga", 0)
            val deskripsiBarang = intent.getStringExtra("deskripsi") ?: ""
            val status = 0

            if (nama.isEmpty() || alamatLengkap.isEmpty() || provinsiUser.isEmpty() ||
                kodePos.isEmpty() || nomorTelepon.isEmpty() || opsiKirim.isEmpty()
            ) {
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
            } else {

                val data = mapOf(
                    "nama" to nama,
                    "alamat" to alamatLengkap,
                    "provinsi" to provinsiUser,
                    "posKode" to kodePos,
                    "nomorTelepon" to nomorTelepon,
                    "opsiPengiriman" to opsiKirim,
                    "namaBarang" to namaBarang,
                    "hargaBarang" to hargaBarang,
                    "deskripsiBarang" to deskripsiBarang,
                    "status" to status
                )

                startPayment()


                db.collection("users")
                    .document(userId)
                    .collection("uid")
                    .add(data) // Ganti dengan `add` jika ingin ID otomatis
                    .addOnSuccessListener {
                        Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Gagal menyimpan data: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

    private fun startPayment() {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_sg_Zorepa2oWYuMcr") // Gunakan API Key yang valid

        try {
            val totalAmount = 50000 // Jumlah dalam satuan utama (Rp50.000)
            val amountInPaise = totalAmount * 100 // Konversi ke paise (sen)

            val options = JSONObject()
            options.put("name", "Nama Bisnis Anda")
            options.put("description", "Deskripsi Pembayaran")
            options.put("image", "https://example.com/logo.png")
            options.put("theme.color", "#3399cc")
            options.put("currency", "IDR")
            options.put("amount", amountInPaise) // Pastikan ini dalam paise

            val prefill = JSONObject()
            prefill.put("email", "user@example.com") // Email pengguna
            prefill.put("contact", "08123456789") // Nomor telepon pengguna

            options.put("prefill", prefill)
            checkout.open(this, options)

        } catch (e: Exception) {
            Toast.makeText(this, "Error in Razorpay Checkout: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }


    fun onPaymentSuccess(razorpayPaymentID: String?) {
        Toast.makeText(this, "Payment successful: $razorpayPaymentID", Toast.LENGTH_SHORT).show()
    }

    fun onPaymentError(code: Int, response: String?) {
        Toast.makeText(this, "Payment failed: $response", Toast.LENGTH_SHORT).show()
    }

}



