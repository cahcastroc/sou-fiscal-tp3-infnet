package br.edu.infnet.soufiscal.criptografia

import android.util.Base64

class CriptoString {


    companion object{
        @JvmStatic
        val criptoGrafador = Criptografador()
    }

    private var cifra: String? = null


    fun getCriptoBase64(): String?{
        return cifra
    }
    fun setCriptoBase64(value: String?){
        cifra = value
    }

    // Criptografia e decriptografia
    fun getClearText(): String?{
        return criptoGrafador.descriptografar(cifra!!)
    }
    fun setClearText(value: String?){
        cifra = criptoGrafador.criptografar(value!!)
    }
}