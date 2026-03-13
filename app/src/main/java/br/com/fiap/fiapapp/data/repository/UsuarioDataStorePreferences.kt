package br.com.fiap.fiapapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import br.com.fiap.fiapapp.domain.Usuario
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "usuario_prefs")


class UsuarioDataStorePreferences(private val context: Context) {
    companion object {
        val NOME = stringPreferencesKey("nome")
        val EMAIL = stringPreferencesKey("email")
        val IDADE = intPreferencesKey("idade")
        val TEMA_ESCURO = booleanPreferencesKey("tema_escuro")
    }

    val usuarioFlow: Flow<Usuario> = context.dataStore.data.map { preferences ->
        Usuario(
            nome = preferences[NOME] ?: "",
            email = preferences[EMAIL] ?: "",
            idade = preferences[IDADE] ?: 0,
            temaEscuro = preferences[TEMA_ESCURO] ?: false
        )
    }
}