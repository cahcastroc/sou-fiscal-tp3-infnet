package br.edu.infnet.soufiscal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import br.edu.infnet.soufiscal.criptografia.Criptografador
import br.edu.infnet.soufiscal.dao.AvaliacaoDao
import br.edu.infnet.soufiscal.model.Avaliacao
import com.google.firebase.auth.FirebaseAuth


class NovaAvaliacaoFragment : Fragment() {

    private val avaliacaoDao = AvaliacaoDao()

    private lateinit var avaliacao: Avaliacao

    private val criptografador = Criptografador()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_nova_avaliacao, container, false)

        val idFiscal = FirebaseAuth.getInstance().currentUser?.email

        val btSalvar = view.findViewById<Button>(R.id.btSalvar)
        val etNovaNome = view.findViewById<EditText>(R.id.etNovaNome)
        val etNovaBairro = view.findViewById<EditText>(R.id.etNovaBairro)

        //---Checkbox e auxiliares
        var limpeza = "Não"
        val cb1 = view.findViewById<CheckBox>(R.id.cB1)
        cb1.setOnCheckedChangeListener { checkBox, b ->
            if (checkBox.isChecked) {
                limpeza = "Sim"
            }
        }

        var organizacao = "Não"
        val cb2 = view.findViewById<CheckBox>(R.id.cB2)
        cb2.setOnCheckedChangeListener { checkBox, b ->
            if (checkBox.isChecked) {
                organizacao = "Sim"
            }
        }

        var validade = "Não"
        val cb3 = view.findViewById<CheckBox>(R.id.cB3)
        cb3.setOnCheckedChangeListener { checkBox, b ->
            if (checkBox.isChecked) {
                validade = "Sim"
            }
        }

        var documentacao = "Não"
        val cb4 = view.findViewById<CheckBox>(R.id.cB4)
        cb4.setOnCheckedChangeListener { checkBox, b ->
            if (checkBox.isChecked) {
                documentacao = "Sim"
            }
        }

        var controle = "Não"
        val cb5 = view.findViewById<CheckBox>(R.id.cB5)
        cb5.setOnCheckedChangeListener { checkBox, b ->
            if (checkBox.isChecked) {
                controle = "Sim"
            }
        }

        var refrigeracao = "Não"
        val cb6 = view.findViewById<CheckBox>(R.id.cB6)
        cb6.setOnCheckedChangeListener { checkBox, b ->
            if (checkBox.isChecked) {
                organizacao = "Sim"
            }
        }


        btSalvar.setOnClickListener {


            val nome = criptografador.criptografar(etNovaNome.text.toString())


            val avaliacao = Avaliacao(
                null,
                idFiscal,
                nome,
                etNovaBairro.text.toString(),
                limpeza, organizacao, validade, documentacao, controle, refrigeracao
            )

            avaliacaoDao.inserir(avaliacao)?.addOnSuccessListener {
                Toast.makeText(activity, "Registro salvo com sucesso", Toast.LENGTH_LONG).show()
                val navController = this.findNavController()
                navController.navigate(R.id.action_novaAvaliacaoFragment_to_opcoesFragment)


            }?.addOnFailureListener {
                Toast.makeText(activity, "Erro inesperado", Toast.LENGTH_LONG).show()
            }
        }


        return view
    }


}