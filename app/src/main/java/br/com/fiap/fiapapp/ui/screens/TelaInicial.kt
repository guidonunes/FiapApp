package br.com.fiap.fiapapp.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.fiapapp.data.repository.UsuarioDataStorePreferences
import br.com.fiap.fiapapp.ui.theme.FiapAppTheme
import br.com.fiap.fiapapp.ui.viewmodel.TelaInicialViewModel

@Composable
fun TelaInicial(
    context: Context,
    viewModel: TelaInicialViewModel
) {
    val state by viewModel.uiState.collectAsState()


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "iData Storage",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = state.nome,
                onValueChange = viewModel::atualizarNome,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Qual o seu nome?")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.email,
                onValueChange = viewModel::atualizarEmail,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Qual o seu e-mail?")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = if(state.idade == 0) "" else state.idade.toString(),
                onValueChange = viewModel::atualizarIdade,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Qual a sua idade?")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.temaEscuro,
                    onCheckedChange = viewModel::atualizarTemaEscuro
                )
                Text(text = "Tema escuro?")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    viewModel.gravarDados()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Gravar dados")
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TelaInicialPreview() {
    FiapAppTheme {
        TelaInicial(
            context = LocalContext.current,
            viewModel = TelaInicialViewModel(
                UsuarioDataStorePreferences(LocalContext.current)
            )
        )
    }
}