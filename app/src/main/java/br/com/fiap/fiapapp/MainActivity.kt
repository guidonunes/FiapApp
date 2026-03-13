package br.com.fiap.fiapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.fiapapp.data.repository.UsuarioDataStorePreferences
import br.com.fiap.fiapapp.ui.screens.TelaInicial
import br.com.fiap.fiapapp.ui.theme.FiapAppTheme
import br.com.fiap.fiapapp.ui.viewmodel.TelaInicialViewModel
import br.com.fiap.fiapapp.ui.viewmodel.factory.TelaInicialViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: TelaInicialViewModel by viewModels {
        TelaInicialViewModelFactory(
            UsuarioDataStorePreferences(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state = viewModel.uiState.collectAsState()
            FiapAppTheme(
                darkTheme = state.value.temaEscuro
            ) {

                TelaInicial(this, viewModel)
            }
        }
    }
}

