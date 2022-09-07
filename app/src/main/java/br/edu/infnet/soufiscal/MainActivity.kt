package br.edu.infnet.soufiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var appAuth: FirebaseAuth
    private var appUser : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appAuth = FirebaseAuth.getInstance()

        val btLogin = findViewById<Button>(R.id.btLogin)
        val btCadastrar = findViewById<Button>(R.id.btCadastrar)

        btLogin.setOnClickListener {
            try{
                val etEmail = findViewById<EditText>(R.id.etEmail)
                val etSenha = findViewById<EditText>(R.id.etSenha)

                appAuth
                    .signInWithEmailAndPassword(etEmail.text.toString(),etSenha.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            appUser = appAuth.currentUser
                        }else{
                            appUser = null
                        }
                    }
            } catch (ex: IllegalArgumentException){
                Toast.makeText(this,"O campo de e-mail e senha não podem ser nulos.",Toast.LENGTH_LONG).show()
            }

        }

        btCadastrar.setOnClickListener {

            try {
                val etEmail = findViewById<EditText>(R.id.etEmail)
                val etSenha = findViewById<EditText>(R.id.etSenha)

                    appAuth.createUserWithEmailAndPassword(etEmail.text.toString(),etSenha.text.toString())
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                appUser = appAuth.currentUser
                            }
                        }

            } catch (ex: IllegalArgumentException){
                Toast.makeText(this,"O campo de e-mail e senha não podem ser nulos.",Toast.LENGTH_LONG).show()
            }

        }
    }
    override fun onStart() {
        super.onStart()
        appUser = appAuth.currentUser

    }

    override fun onStop() {
        super.onStop()
        appAuth.signOut()
    }

}