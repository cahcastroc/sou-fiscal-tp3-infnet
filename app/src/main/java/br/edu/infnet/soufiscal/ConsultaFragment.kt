package br.edu.infnet.soufiscal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.soufiscal.criptografia.Criptografador
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao
import br.edu.infnet.soufiscal.recyclerView.AvaliacaoUsuarioAdapter
import br.edu.infnet.soufiscal.recyclerView.RecyclerViewItemListener
import com.google.firebase.auth.FirebaseAuth


class ConsultaFragment : Fragment(), RecyclerViewItemListener {
    private val avaliacaoDao = AvaliacaoDao()
    private lateinit var adapter: AvaliacaoUsuarioAdapter
//    private val criptografador = Criptografador()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_consulta, container, false)

        atualizar()


        return view
    }

    fun atualizar() {
        val avaliador = FirebaseAuth.getInstance().currentUser?.email
        if (avaliador != null) {

            avaliacaoDao.avalicoesUsuario(avaliador).addOnSuccessListener {
                val avaliacoesUsuario = ArrayList<Avaliacao>()

                for (documento in it) {
                    var avaliacao = documento.toObject(Avaliacao::class.java)//
                    avaliacoesUsuario.add(avaliacao)

                    //--- Não consegui desencriptar depois que enviei para o banco. Não encontrei soluções válidas
                    //--- para isso com o Firestore, somente com o Room/Sql (através do conteúdo ministrado pelo Prof. com o uso do TypeConverter e
                    //--- a criação de um tipo específico). Logo não realizei a encriptação do nome e do bairro devido a necessidade deles para o relatório sintético.


                    avaliacoesUsuario.forEach {
                        it.nome = it.nome?.substring(0, 5)
                    }

                }

                val rvConsultaAvaliador = view?.findViewById<RecyclerView>(R.id.rvConsultaAvaliador)
                rvConsultaAvaliador?.layoutManager = LinearLayoutManager(context)
                adapter = AvaliacaoUsuarioAdapter()
                adapter.listaAvaliacao = avaliacoesUsuario
                adapter.setRecyclerViewItemListener(this)
                rvConsultaAvaliador?.adapter = adapter

            }.addOnFailureListener {
                Toast.makeText(activity, "Falha", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun recyclerViewItemClicked(view: View, id: String) {
        avaliacaoDao.alterar(id).addOnSuccessListener {
            Toast.makeText(activity, "Criado alerta", Toast.LENGTH_LONG).show()
        }
        atualizar()
    }


    override fun recyclerViewItemLongClicked(view: View, id: String) {
        avaliacaoDao.del(id).addOnSuccessListener {

            Toast.makeText(activity, "Item deletado", Toast.LENGTH_LONG).show()
        }
        atualizar()
    }


}