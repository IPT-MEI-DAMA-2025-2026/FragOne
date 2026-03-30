package pt.ipt.dama2026.fragmentoum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
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

    // ViewBinding seguro
    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    // texto a mostrar na TextView do Fragmento
    private var txtTextView: String? = null

    // texto a mostrar no Botão do Fragmento
    private var txtButton: String? = null

    // var auxiliar com o número do Fragmento
    private var numFragmento: Int = 0

    // var para comunicar com a Activity, utilizando o ViewModel
    private val viewModel: SharedViewModel by activityViewModels()

    /**
     * Cria o fragmento com um texto e um botão     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            txtTextView = it.getString(ARG_TEXTO_TEXT_VIEW)
            txtButton = it.getString(ARG_TEXTO_BOTAO)
            numFragmento = it.getInt(ARG_NUM_FRAGMENTO)
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
        _binding = FragmentMyBinding.inflate(inflater, container, false)

        // atribuir o texto ao Botão
        binding.btFragmento.text = txtButton

        // ação do botão
        binding.btFragmento.setOnClickListener {

            binding.txtFragmento.text = txtTextView

            viewModel.selecionarFragmento(numFragmento)


            // Processar o nº do fragmento
            val isPar = numFragmento % 2 == 0
            if (isPar) {
                Snackbar.make(binding.root, getString(R.string.msgPar), Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireContext(), getString(R.string.msgImpar), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fragmentSelecionado.collect {evento ->
                    if (evento != null && evento.id != numFragmento) {
                        binding.txtFragmento.text = ""
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        fun novaInstancia(txtLabel: String, txtBotao: String, numFrag: Int) =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXTO_TEXT_VIEW, txtLabel)
                    putString(ARG_TEXTO_BOTAO, txtBotao)
                    putInt(ARG_NUM_FRAGMENTO, numFrag)
                }
            }
    }
}