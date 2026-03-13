package br.com.fiap.fiapapp.ui.viewmodel.factory


import br.com.fiap.fiapapp.data.repository.UsuarioDataStorePreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.fiapapp.ui.viewmodel.TelaInicialViewModel

class TelaInicialViewModelFactory(
    private val usuarioDataStorePreferences: UsuarioDataStorePreferences
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TelaInicialViewModel::class.java)) {
            return TelaInicialViewModel(usuarioDataStorePreferences) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido")
    }
}

