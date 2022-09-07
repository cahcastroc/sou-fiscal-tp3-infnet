package br.edu.infnet.soufiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        tvEmail.text = intent.getStringExtra("Email")
        tvLogin.text = intent.getStringExtra("UltimoLogin")


    }
}