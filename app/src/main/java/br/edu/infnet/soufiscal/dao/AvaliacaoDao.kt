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

    fun setUpSnapShotListener(
        listener: (
            QuerySnapshot?,
            FirebaseFirestoreException?
        ) -> Unit
    ) = db
        .collection(collection)
        .addSnapshotListener(listener)


    fun listar(): Task<QuerySnapshot> {
        return db.collection(collection).get()
    }


    fun avalicoesUsuario(avaliador: String): Task<QuerySnapshot> {
        return db.collection(collection).whereEqualTo("avaliador", avaliador).get()

    }

    fun del(id: String): Task<Void> {
        return db.collection(collection).document(id).delete()
    }


    fun inserir(avaliacao: Avaliacao): Task<Void>? {
        var task: Task<Void>? = null
        if (avaliacao.id == null) {
            val ref: DocumentReference = db.collection(collection).document()
            avaliacao.id = ref.id
            task = ref.set(avaliacao)
        }
        return task
    }

    fun buscaPorBairro(): Task<QuerySnapshot> {
        return db.collection(collection).orderBy("bairro").get()
    }

    fun buscaPorBairroExato(bairro: String): Task<QuerySnapshot> {

        return db.collection(collection).whereEqualTo("bairro", bairro).get()
    }


    fun alterar(id: String): Task<Void> {
        return db.collection(collection).document(id)
            .update("documentacao", "Alerta!")
    }
}

