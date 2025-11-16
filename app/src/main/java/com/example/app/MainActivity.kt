package com.example.app // ajuste para o pacote do projeto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign

data class Aluno(
    var nome: String = "",
    val notas: MutableList<Double> = mutableListOf()
) {
    fun adicionarNota(nota: Double) {
        if (notas.size < 3) notas.add(nota)
    }
    fun calcularMedia(): Double {
        if (notas.isEmpty()) return 0.0
        return notas.sum() / notas.size
    }
    fun status(): String {
        val media = calcularMedia()
        return when {
            media < 6.0 -> "Reprovado"
            media <= 9.0 -> "Aprovado"
            else -> "Ótimo Aproveitamento"
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NotasApp()
                }
            }
        }
    }
}

@Composable
fun NotasApp() {
    var aluno by remember { mutableStateOf(Aluno()) }
    var nomeInput by remember { mutableStateOf("") }
    var notaInputs by remember { mutableStateOf(listOf("", "", "")) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Cadastro de Aluno e Lançamento de Notas", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = nomeInput,
            onValueChange = { nomeInput = it },
            label = { Text("Nome completo") },
            modifier = Modifier.fillMaxWidth()
        )

        for (i in 0..2) {
            OutlinedTextField(
                value = notaInputs[i],
                onValueChange = { new -> notaInputs = notaInputs.toMutableList().also { it[i] = new } },
                label = { Text("Nota TP${i + 1}") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                val nomeTrim = nomeInput.trim()
                val parsedNotas = notaInputs.map { it.replace(",", ".").toDoubleOrNull() ?: 0.0 }
                val novo = Aluno(nome = nomeTrim)
                parsedNotas.forEach { novo.adicionarNota(it) }
                aluno = novo
                focusManager.clearFocus()
            }) { Text("Salvar") }

            OutlinedButton(onClick = {
                nomeInput = ""
                notaInputs = listOf("", "", "")
                aluno = Aluno()
            }) { Text("Limpar") }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Aluno:", style = MaterialTheme.typography.titleMedium)
                Text(text = aluno.nome.ifBlank { "(não cadastrado)" })
                Spacer(modifier = Modifier.height(8.dp))
                Text("Notas:", style = MaterialTheme.typography.titleMedium)
                if (aluno.notas.isEmpty()) Text("(nenhuma nota lançada)")
                else aluno.notas.forEachIndexed { idx, n -> Text("TP${idx + 1}: ${"%.2f".format(n)}") }
                Spacer(modifier = Modifier.height(8.dp))
                val media = aluno.calcularMedia()
                Text("Média Geral: ${"%.2f".format(media)}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Status: ${aluno.status()}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}
