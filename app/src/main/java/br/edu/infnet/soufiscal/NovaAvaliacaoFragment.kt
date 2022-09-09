package br.edu.infnet.soufiscal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao
import com.google.firebase.auth.FirebaseAuth


class NovaAvaliacaoFragment : Fragment() {

    private val avaliacaoDao = AvaliacaoDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_nova_avaliacao, container, false)



        val btSalvar = view.findViewById<Button>(R.id.btSalvar)
        val etNovaNome = view.findViewById<EditText>(R.id.etNovaNome)
        val etNovaBairro = view.findViewById<EditText>(R.id.etNovaBairro)
        val idFiscal = FirebaseAuth.getInstance().currentUser?.email


        Toast.makeText(activity,"Olha isso $idFiscal", Toast.LENGTH_LONG).show()



        btSalvar.setOnClickListener {

            val avaliacao = Avaliacao(idFiscal,etNovaNome.text.toString(),etNovaBairro.text.toString(),true,false,true,false,true,false)
            avaliacaoDao.add(avaliacao).addOnCompleteListener {
                Toast.makeText(activity,"Salvo",Toast.LENGTH_LONG).show()


            }.addOnFailureListener{
                Toast.makeText(activity,"Deu ruim",Toast.LENGTH_LONG).show()
            }

        }

        return view
    }

}