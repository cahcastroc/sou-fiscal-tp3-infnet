package br.edu.infnet.soufiscal.recyclerView

import android.view.View

interface RecyclerViewItemListener {

    fun recyclerViewItemClicked(view: View, id: String)

    fun recyclerViewItemClickedLong(view: View, id: Int)

    fun recyclerViewItemDeletarClicked(view: View, position: Int)

}