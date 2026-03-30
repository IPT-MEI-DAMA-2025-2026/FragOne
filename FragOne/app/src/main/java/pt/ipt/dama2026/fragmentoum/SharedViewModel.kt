package pt.ipt.dama2026.fragmentoum

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Classe para comunicar entre os fragmentos
 */
class SharedViewModel : ViewModel() {

    // estado interno mutável
    private val _fragmentSelecionado = MutableStateFlow<FragmentEvent?>(null)
    val fragmentSelecionado = _fragmentSelecionado.asStateFlow()

    fun selecionarFragmento(num: Int) {
        _fragmentSelecionado.value = null
        _fragmentSelecionado.value = FragmentEvent(num)
    }
}