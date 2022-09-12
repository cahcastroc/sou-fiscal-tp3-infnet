package br.edu.infnet.soufiscal.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.soufiscal.R
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao


class DadosSintetizadosAdapter(itemListener: RecyclerViewItemListener) :
    RecyclerView.Adapter<DadosSintetizadosAdapter.ViewHolder>() {


    var listaBairros = ArrayList<Avaliacao>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var itemListener: RecyclerViewItemListener = itemListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.dados_sintetizados_linha, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listaBairros[position], itemListener, position)
    }

    override fun getItemCount(): Int {
        return listaBairros.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(avaliacao: Avaliacao, itemListener: RecyclerViewItemListener, position: Int) {

            val tvNomeBairro = itemView.findViewById<TextView>(R.id.tvNomeBairro)
            val tvQtdLimpeza = itemView.findViewById<TextView>(R.id.tvQtdLimpeza)
            val tvQtdOrganizacao = itemView.findViewById<TextView>(R.id.tvQtdOrganizacao)
            val tvQtdValidade = itemView.findViewById<TextView>(R.id.tvQtdValidade)
            val tvQtdDocumentacao = itemView.findViewById<TextView>(R.id.tvQtdDocumentacao)
            val tvQtdRefrigeracao = itemView.findViewById<TextView>(R.id.tvQtdRefrigeracao)
            val tvQtdControle = itemView.findViewById<TextView>(R.id.tvQtdControle)



            tvNomeBairro.text = avaliacao.bairro
            tvQtdLimpeza.text = avaliacao.limpeza
            tvQtdOrganizacao.text = avaliacao.organizacao
            tvQtdValidade.text = avaliacao.validadeInsumos
            tvQtdRefrigeracao.text = avaliacao.refrigeracao
            tvQtdControle.text = avaliacao.controlePragas
            tvQtdDocumentacao.text = avaliacao.documentacao


        }


    }


}

