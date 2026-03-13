package br.com.fiap.fiapapp.ui.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.fiapapp.data.repository.UsuarioDataStorePreferences
import br.com.fiap.fiapapp.domain.Usuario
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TelaInicialViewModel(
    private val usuarioDataStorePreferences: UsuarioDataStorePreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(Usuario())
    val uiState: StateFlow<Usuario> = _uiState.asStateFlow()

    init {
        carregarDados()
    }

    private fun carregarDados() {
        viewModelScope.launch {
            usuarioDataStorePreferences.usuarioFlow.collect {
                _uiState.value = it
            }
        }
    }

    fun atualizarNome(nome: String) {
        _uiState.update { it.copy(nome = nome) }
    }

    fun atualizarEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun atualizarIdade(idade: String) {
        _uiState.update { it.copy(idade = idade.toIntOrNull() ?: 0) }
    }

    fun atualizarTemaEscuro(temaEscuro: Boolean) {
        _uiState.update { it.copy(temaEscuro = temaEscuro) }
    }

    fun gravarDados() {
        viewModelScope.launch {
            usuarioDataStorePreferences.salvarUsuario(_uiState.value)
        }
    }
}
