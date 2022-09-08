package br.edu.infnet.soufiscal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao
import com.google.firebase.auth.FirebaseAuth

class AppActivity : AppCompatActivity() {

    private val avaliacaoDao = AvaliacaoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val btLogout = findViewById<Button>(R.id.btDeslogar)

        btLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

//        val tvEmail = findViewById<TextView>(R.id.tvEmail)
//        val tvLogin = findViewById<TextView>(R.id.tvLogin)
//
//        tvEmail.text = intent.getStringExtra("Email")
//        tvLogin.text = intent.getStringExtra("UltimoLogin")

        var avaliacao = Avaliacao(null,"McDonalds","São Mateus",true,false,true,true,true,false)
        avaliacaoDao.add(avaliacao)
//        avaliacaoDao.setUpSnapShotListener{ querySnapshot, firebaseFirestoreException ->
//
//        }

    }
}