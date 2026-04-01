package pt.ipt.dama2026.fragmentoum.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import pt.ipt.dama2026.fragmentoum.model.FragmentState

/**
 * Classe para comunicar entre os fragmentos
 */
class SharedViewModel : ViewModel() {

    // estado interno mutável
    private val _uiState = MutableStateFlow(FragmentState())
    val uiState: StateFlow<FragmentState> = _uiState.asStateFlow()

    fun selecionarFragmento(num: Int) {
        _uiState.value = FragmentState(fragmentoSelecionado = num)
    }
}