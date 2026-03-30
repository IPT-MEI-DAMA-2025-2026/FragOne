package pt.ipt.dama2026.fragmentoum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Classe para comunicar entre os fragmentos
 */
class SharedViewModel : ViewModel() {

    private val _fragmentSelecionado = MutableLiveData<Int?>()
    val fragmentSelecionado: LiveData<Int?> = _fragmentSelecionado

    fun selecionarFragmento(num: Int) {
        _fragmentSelecionado.value = null
        _fragmentSelecionado.value = num
    }
}