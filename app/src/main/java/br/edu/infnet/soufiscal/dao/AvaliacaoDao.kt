package br.edu.infnet.soufiscal.dao

import br.edu.infnet.soufiscal.model.Avaliacao
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AvaliacaoDao {

    private val collection = "avaliacoes"
    val db = Firebase.firestore

    fun setUpSnapShotListener (
        listener: (
            QuerySnapshot?,
            FirebaseFirestoreException?
        ) -> Unit
    ) = db
        .collection(collection)
        .addSnapshotListener(listener)

    fun add(avaliacao: Avaliacao): Task<DocumentReference>{
        return db.collection(collection).add(avaliacao)
    }



}