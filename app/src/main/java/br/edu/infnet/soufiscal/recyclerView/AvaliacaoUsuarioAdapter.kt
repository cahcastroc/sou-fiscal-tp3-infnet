package br.edu.infnet.soufiscal.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.soufiscal.R
import br.edu.infnet.soufiscal.model.Avaliacao

class AvaliacaoUsuarioAdapter() : RecyclerView.Adapter<AvaliacaoUsuarioAdapter.ViewHolder>() {

    var listaAvaliacao = ArrayList<Avaliacao>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    lateinit var itemListener: RecyclerViewItemListener

    fun setRecyclerViewItemListener(listener: RecyclerViewItemListener){

        itemListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.avaliacao_usuario_linha,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listaAvaliacao[position],itemListener,position)
    }

    override fun getItemCount(): Int {
        return listaAvaliacao.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItem(avaliacao: Avaliacao, itemListener: RecyclerViewItemListener, position: Int){
            val tvRvNome = itemView.findViewById<TextView>(R.id.tvRvNome)
            val tvRvBairro = itemView.findViewById<TextView>(R.id.tvRvBairro)
            val tvRvLimpeza = itemView.findViewById<TextView>(R.id.tvRvLimpeza)
            val tvRvOrganizacao = itemView.findViewById<TextView>(R.id.tvRvOrganizacao)
            val tvRvValidade = itemView.findViewById<TextView>(R.id.tvRvValidade)
            val tvRvDocumentacao = itemView.findViewById<TextView>(R.id.tvRvDocumentacao)
            val tvRvControle = itemView.findViewById<TextView>(R.id.tvRvControle)
            val tvRvRefrigeracao = itemView.findViewById<TextView>(R.id.tvRvRefrigeracao)



            tvRvNome.text = avaliacao.nome.toString()
            tvRvBairro.text = avaliacao.bairro
            tvRvLimpeza.text = avaliacao.limpeza
            tvRvOrganizacao.text = avaliacao.organizacao
            tvRvValidade.text = avaliacao.validadeInsumos
            tvRvDocumentacao.text = avaliacao.documentacao
            tvRvControle.text = avaliacao.controlePragas
            tvRvRefrigeracao.text = avaliacao.refrigeracao


            itemView.setOnClickListener {
                itemListener.recyclerViewItemLongClicked(it,avaliacao.id!!)
            }

//            btDeletar.setOnClickListener {
//                 itemListener.recyclerViewItemDeletarClicked(it,avaliacao.id!!)
//
//                }




            }


        }
    }
