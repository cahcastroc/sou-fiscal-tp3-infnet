package br.edu.infnet.soufiscal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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

        val idFiscal = FirebaseAuth.getInstance().currentUser?.email


        val etNovaNome = view.findViewById<EditText>(R.id.etNovaNome)
        val etNovaBairro = view.findViewById<EditText>(R.id.etNovaBairro)


        val btSalvar = view.findViewById<Button>(R.id.btSalvar)
        btSalvar.setOnClickListener {

            val avaliacao = Avaliacao(idFiscal,etNovaNome.text.toString(),etNovaBairro.text.toString(),true,false,true,false,true,false)
            avaliacaoDao.add(avaliacao).addOnCompleteListener {
                Toast.makeText(activity,"Registro salvo com sucesso",Toast.LENGTH_LONG).show()
                val navController = this.findNavController()
                navController.navigate(R.id.action_novaAvaliacaoFragment_to_opcoesFragment)


            }.addOnFailureListener{
                Toast.makeText(activity,"Erro inesperado",Toast.LENGTH_LONG).show()
            }

        }

        return view
    }

}