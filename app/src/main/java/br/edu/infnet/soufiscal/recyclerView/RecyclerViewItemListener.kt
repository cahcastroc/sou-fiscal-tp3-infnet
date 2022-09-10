package br.edu.infnet.soufiscal.recyclerView

import android.view.View
import java.text.FieldPosition

interface RecyclerViewItemListener {

    fun recyclerViewItemClicked(view: View, id: String)


    fun recyclerViewItemLongClicked(view: View, id: String)



}