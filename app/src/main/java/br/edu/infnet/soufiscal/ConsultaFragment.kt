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
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao
import br.edu.infnet.soufiscal.recyclerView.AvaliacaoUsuarioAdapter
import br.edu.infnet.soufiscal.recyclerView.RecyclerViewItemListener
import com.google.firebase.auth.FirebaseAuth


class ConsultaFragment : Fragment(), RecyclerViewItemListener {
    private val avaliacaoDao = AvaliacaoDao()
    private lateinit var adapter: AvaliacaoUsuarioAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_consulta, container, false)

        val idAvaliador = FirebaseAuth.getInstance().currentUser?.email



        if (idAvaliador != null) {


            avaliacaoDao.avalicoesUsuario(idAvaliador).addOnSuccessListener {
                val avaliacoesUsuario = ArrayList<Avaliacao>()

                for (documento in it){
                    var avaliacao = documento.toObject(Avaliacao::class.java)
                    avaliacoesUsuario.add(avaliacao)
                    Log.i("DR3","${avaliacoesUsuario}")
                }

                val rvConsultaAvaliador = view.findViewById<RecyclerView>(R.id.rvConsultaAvaliador)
                rvConsultaAvaliador.layoutManager = LinearLayoutManager(context)
                adapter = AvaliacaoUsuarioAdapter()
                adapter.listaAvaliacao = avaliacoesUsuario
                adapter.setRecyclerViewItemListener(this)
                rvConsultaAvaliador.adapter = adapter

            }.addOnFailureListener {
                Toast.makeText(activity,"Falha", Toast.LENGTH_LONG).show()
            }


        }




        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        listar()
//
//    }

    private fun listar(){
        avaliacaoDao.listar().addOnSuccessListener {

        }
    }

    override fun recyclerViewItemClicked(view: View, id: String) {

    }

    override fun recyclerViewItemClickedLong(view: View, id: Int) {

    }

    override fun recyclerViewItemDeletarClicked(view: View, position: Int) {

    }


}