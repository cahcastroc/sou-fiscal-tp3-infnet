package br.edu.infnet.soufiscal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao
import com.google.firebase.auth.FirebaseAuth


class ConsultaFragment : Fragment() {
    private val avaliacaoDao = AvaliacaoDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_consulta, container, false)

        val idAvaliador = FirebaseAuth.getInstance().currentUser?.email

        if (idAvaliador != null) {
            avaliacaoDao.avalicoesUsuario(idAvaliador).addOnSuccessListener {
                for (documento in it){
                    var avaliacao = documento.toObject(Avaliacao::class.java)
                    Log.i("DR3","${avaliacao.idAvaliador} - ${avaliacao.nome}")
                }
            }
        }



        return view
    }


}