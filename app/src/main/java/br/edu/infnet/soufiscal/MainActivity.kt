package br.edu.infnet.soufiscal

import android.content.Intent
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var appAuth: FirebaseAuth
    private var appUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        appAuth = FirebaseAuth.getInstance()

        val btLogin = findViewById<Button>(R.id.btLogin)
        val btCadastrar = findViewById<Button>(R.id.btCadastrar)

        btLogin.setOnClickListener {
            try {
                val etEmail = findViewById<EditText>(R.id.etEmail)
                val etSenha = findViewById<EditText>(R.id.etSenha)

                appAuth
                    .signInWithEmailAndPassword(etEmail.text.toString(), etSenha.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            appUser = appAuth.currentUser

                            val emailUsuario = "${appUser!!.email}"
                            val ultimoLogin = "${Date(appUser!!.metadata!!.lastSignInTimestamp)}"
                            intentAppActivity(emailUsuario, ultimoLogin)

                        } else {
                            appUser = null
                            Toast.makeText(
                                this,
                                "Dados de credenciais inválidos ou Usuário não cadastrado.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            } catch (ex: IllegalArgumentException) {
                Toast.makeText(
                    this,
                    "O campo de e-mail e senha não podem ser nulos.",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

        btCadastrar.setOnClickListener {

            val etEmail = findViewById<EditText>(R.id.etEmail)
            val etSenha = findViewById<EditText>(R.id.etSenha)

            if (validaFormatoEmail(etEmail) && validaFormatoSenha(etSenha)) {

                appAuth.createUserWithEmailAndPassword(
                    etEmail.text.toString(),
                    etSenha.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            appUser = appAuth.currentUser
                            Toast.makeText(
                                this,
                                "Cadastro realizado com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()

                            val emailUsuario = "${appUser!!.email}"
                            val ultimoLogin = "Esse é o seu primeiro login!"
                            intentAppActivity(emailUsuario, ultimoLogin)
                        }
                    }.addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Processo inválido. Caso seja cadastrado clique no botão de login.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        appUser = appAuth.currentUser

    }


    fun validaFormatoEmail(etEmail: EditText): Boolean {
        if (EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
            return true
        } else {
            etEmail.error = "E-mail inválido"
            return false
        }
    }


    fun validaFormatoSenha(etSenha: EditText): Boolean {
        if (etSenha.text.toString().length >= 6) {
            return true
        } else {
            etSenha.error = "Mínimo de 6 caracteres"
            return false
        }
    }

    fun intentAppActivity(emailUsuario: String, ultimoLogin: String) {

        val intent = Intent(this, AplicationActivity::class.java)
        intent.putExtra("Email", emailUsuario)
        intent.putExtra("UltimoLogin", ultimoLogin)
        startActivity(intent)
    }
}




