package br.edu.infnet.soufiscal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth


class OpcoesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_opcoes, container, false)

        val navController = this.findNavController()



        val btNovaAvaliacao = view.findViewById<Button>(R.id.btNovaAvaliacao)
        btNovaAvaliacao.setOnClickListener {

            navController.navigate(R.id.action_opcoesFragment_to_novaAvaliacaoFragment)
        }

        val btConsulta = view.findViewById<Button>(R.id.btConsulta)
        btConsulta.setOnClickListener {

        }

        val btDados = view.findViewById<Button>(R.id.btDados)
        btDados.setOnClickListener {

        }


        val btLogout = view.findViewById<Button>(R.id.btLogout)
        btLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }



        return view
    }


}