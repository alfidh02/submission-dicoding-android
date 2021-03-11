package com.project.submissiondicoding

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        val nameCoffee = intent.getStringExtra("nama")
        val detailCoffee = intent.getStringExtra("detail")
        val picCoffee = intent.getIntExtra("gambar",0)
        val locCoffee = intent.getStringExtra("lokasi")

        val builder = AlertDialog.Builder(this)

        toolbarTitle.title = nameCoffee
        tvDetail.text = detailCoffee
        ivCoffee.setImageResource(picCoffee)

        btnDirect.setOnClickListener {
            with(builder) {
                setTitle("Aktifkan GPS")
                setMessage("Harap aktifkan GPS terlebih dahulu. Jika sudah, silahkan klik OK")
                setPositiveButton("OK") { dialogInterface, i ->
                    val gmmIntentUri = Uri.parse("google.navigation:q=$locCoffee")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")

                    if (mapIntent.resolveActivity(it.context.packageManager)!=null) {
                        startActivity(mapIntent)
                    } else {
                        Toast.makeText(this@DetailActivity, "Google Maps belum terinstall. Install terlebih dahulu", Toast.LENGTH_SHORT).show()
                    }
                }
                setNegativeButton("Batal") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
            }
        }

        ibShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, "Hai kawan. Daripada bingung mau kemana, mending ngopi dululah kita di ${toolbarTitle.title} ☺️")
            startActivity(Intent.createChooser(intent, "Ajak teman ngopi"))
        }
    }

    fun backDetail(view: View) {
        finish()
    }
}