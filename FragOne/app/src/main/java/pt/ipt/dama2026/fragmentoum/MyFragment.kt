package pt.ipt.dama2026.fragmentoum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import pt.ipt.dama2026.fragmentoum.databinding.FragmentMyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TEXTO_TEXT_VIEW = "param1"
private const val ARG_TEXTO_BOTAO = "param2"
private const val ARG_NUM_FRAGMENTO = "0"


/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.novaInstancia] factory method to
 * create an instance of this fragment.
 */
class MyFragment : Fragment() {
    // TODO: Rename and change types of parameters

    // var para aceder ao layout do fragmento
    private lateinit var binding: FragmentMyBinding

    // texto a mostrar na TextView do Fragmento
    private var txtTextView: String? = null

    // texto a mostrar no Botão do Fragmento
    private var txtButton: String? = null

    // var auxiliar com o número do Fragmento
    private var numFragmento: Byte = 0


    /**
     * Cria o fragmento com um texto e um botão     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            txtTextView = it.getString(ARG_TEXTO_TEXT_VIEW)
            txtButton = it.getString(ARG_TEXTO_BOTAO)
            numFragmento = it.getByte(ARG_NUM_FRAGMENTO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //val view: View = inflater.inflate(R.layout.fragment_my, container, false)
        binding = FragmentMyBinding.inflate(inflater, container, false)

        // atribuir o texto ao Botão
        binding.btFragmento.text = txtButton

        // ação do botão
        binding.btFragmento.setOnClickListener {

            // atribuir o texto à Text View
            binding.txtFragmento.text = txtTextView

            // Processar o nº do fragmento
            if (numFragmento % 2 == 0) {
                Snackbar.make(binding.root, getString(R.string.msgPar), Snackbar.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.context, getString(R.string.msgImpar), Toast.LENGTH_SHORT)
                    .show()
            }
        }

//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_my, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param txtLabel texto a mostrar na TextView do Fragmento.
         * @param txtBotao texto a mostrar no Botão do Fragmento.
         * @numFrag número do fragmento
         * @return devolve uma nova instância do fragmento MyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun novaInstancia(txtLabel: String, txtBotao: String, numFrag: Byte) =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXTO_TEXT_VIEW, txtLabel)
                    putString(ARG_TEXTO_BOTAO, txtBotao)
                    putByte(ARG_NUM_FRAGMENTO, numFrag)
                }
            }
    }
}