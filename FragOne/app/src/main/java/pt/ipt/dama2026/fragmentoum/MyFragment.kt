package pt.ipt.dama2026.fragmentoum

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    // var para recolher a ação feita sobre o fragmento
    private var listener: OnFragmentClickListener? = null

    // texto a mostrar na TextView do Fragmento
    private var txtTextView: String? = null

    // texto a mostrar no Botão do Fragmento
    private var txtButton: String? = null

    // var auxiliar com o número do Fragmento
    private var numFragmento: Byte = 0

    // var para comunicar com a Activity, utilizando o ViewModel
    private val viewModel: SharedViewModel by activityViewModels()


    /**
     * interface para comunicar com a Activity
     */
    interface OnFragmentClickListener {
        fun onFragmentClicked(fragment: MyFragment)
    }

    /**
     * verifica se a Activity implementa a interface
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnFragmentClickListener
    }

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

    /**
     * Tarefas executadas quando o fragmento é instanciado
     */
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

            binding.txtFragmento.text = txtTextView

            viewModel.selecionarFragmento(numFragmento.toInt())


            // Processar o nº do fragmento
            if (numFragmento % 2 == 0) {
                Snackbar.make(binding.root, getString(R.string.msgPar), Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this.context, getString(R.string.msgImpar), Toast.LENGTH_SHORT)
                    .show()
            }
        }
        //        // Inflate the layout for this fragment
        //        return inflater.inflate(R.layout.fragment_my, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fragmentSelecionado.observe(viewLifecycleOwner) { selecionado ->

            // se NÃO for este fragmento → limpa
            if (selecionado != numFragmento.toInt()) {
                binding.txtFragmento.text = ""
            }
        }
    }


    /**
     * limpar o conteúdo do texto da TextView
     */
    fun limparTexto() {
        binding.txtFragmento.text = ""
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