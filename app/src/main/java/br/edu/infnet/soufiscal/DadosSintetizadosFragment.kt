package br.edu.infnet.soufiscal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao
import br.edu.infnet.soufiscal.recyclerView.AvaliacaoUsuarioAdapter
import br.edu.infnet.soufiscal.recyclerView.DadosSintetizadosAdapter
import br.edu.infnet.soufiscal.recyclerView.RecyclerViewItemListener
import com.google.firebase.firestore.ktx.toObject
import java.util.*
import kotlin.collections.ArrayList


class DadosSintetizadosFragment : Fragment(), RecyclerViewItemListener,AdapterView.OnItemSelectedListener {

    private val avaliacaoDao = AvaliacaoDao()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dados_sintetizados, container, false)

        //------Spinner
        val spBairros = view.findViewById<Spinner>(R.id.spBairros)
            spBairros.onItemSelectedListener = this

        avaliacaoDao.buscaPorBairro().addOnSuccessListener {
            val bairros = LinkedHashSet<String>()

            for(documento in it){
                var bairro = documento.toObject(Avaliacao::class.java)

                if(bairro.bairro!=null){
                    bairros.add(bairro.bairro!!)
                }
            }

            val spBairros = view.findViewById<Spinner>(R.id.spBairros)
            val adapter = activity?.let { it1 ->
                ArrayAdapter<String>(
                    it1,
                    android.R.layout.simple_list_item_1,
                    ArrayList<String>(bairros)
                )
            }
            spBairros.adapter = adapter
        }


        //--------------RV

        val rvListaBairro = view?.findViewById<RecyclerView>(R.id.rvListaBairro)
        rvListaBairro?.layoutManager = LinearLayoutManager(context)
        val rvAdapter = DadosSintetizadosAdapter(this)
        rvListaBairro?.adapter = rvAdapter
//            rvAdapter.listaBairros = bairros
//        rvAdapter.setRecyclerViewItemListener(this)






//        avaliacaoDao.buscaPorBairro().addOnSuccessListener {
//            val bairros = ArrayList<Avaliacao>()
//
//            for (documento in it) {
//                var avaliacao = documento.toObject(Avaliacao::class.java)//
//                bairros.add(avaliacao)
//
//            }
//
//            val rvListaBairro = view?.findViewById<RecyclerView>(R.id.rvListaBairro)
//            rvListaBairro?.layoutManager = LinearLayoutManager(context)
//            adapter = DadosSintetizadosAdapter()
//            adapter.listaBairros = bairros
//            adapter.setRecyclerViewItemListener(this)
//
//            rvListaBairro?.adapter = adapter
//        }



        return view
    }


    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val bairro = adapterView?.getItemAtPosition(position).toString()

        avaliacaoDao.buscaPorBairroLimpeza(bairro).addOnSuccessListener {
                val bairros = ArrayList<Avaliacao>()

            for (documento in it){
                bairros.add(documento.toObject(Avaliacao::class.java))
            }

            val rvListaBairro = requireView().findViewById<RecyclerView>(R.id.rvListaBairro)
            val adapter = rvListaBairro!!.adapter as DadosSintetizadosAdapter

            adapter.listaBairros = bairros



        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun recyclerViewItemClicked(view: View, id: String) {

    }

    override fun recyclerViewItemDeletarClicked(view: View, id: String) {

    }


}



