package com.example.trabajo3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                PantallaNotas()
            }
        }
    }
}

@Composable
fun PantallaNotas() {

    var nombre by remember { mutableStateOf("") }
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Calculadora de Promedio",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del alumno") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nota1,
            onValueChange = { nota1 = it },
            label = { Text("Nota 1 (0-10)") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nota2,
            onValueChange = { nota2 = it },
            label = { Text("Nota 2 (0-10)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            resultado = calcularPromedio(nombre, nota1, nota2)
        }) {
            Text("Calcular promedio")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            resultado = calcularConNotaAleatoria(nombre, nota1, nota2)
        }) {
            Text("Agregar nota de participación")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = resultado)
    }
}

// ------------------ LÓGICA ------------------

fun calcularPromedio(nombre: String, n1: String, n2: String): String {

    if (nombre.isBlank()) {
        return "Ingrese un nombre"
    }

    if (n1.isBlank() || n2.isBlank()) {
        return "Ingrese ambas notas"
    }

    val nota1 = n1.toDoubleOrNull()
    val nota2 = n2.toDoubleOrNull()

    if (nota1 == null || nota2 == null) {
        return "Las notas deben ser numéricas"
    }

    if (nota1 !in 0.0..10.0 || nota2 !in 0.0..10.0) {
        return "Las notas deben estar entre 0 y 10"
    }

    val promedio = (nota1 + nota2) / 2
    val estado = if (promedio >= 6) "Aprobado" else "Desaprobado"

    return "Alumno: $nombre\nPromedio: %.2f\nEstado: $estado".format(promedio)
}

fun calcularConNotaAleatoria(nombre: String, n1: String, n2: String): String {

    if (nombre.isBlank()) {
        return "Ingrese un nombre"
    }

    if (n1.isBlank() || n2.isBlank()) {
        return "Ingrese ambas notas"
    }

    val nota1 = n1.toDoubleOrNull()
    val nota2 = n2.toDoubleOrNull()

    if (nota1 == null || nota2 == null) {
        return "Las notas deben ser numéricas"
    }

    if (nota1 !in 0.0..10.0 || nota2 !in 0.0..10.0) {
        return "Las notas deben estar entre 0 y 10"
    }

    val notaRandom = Random.nextDouble(1.0, 10.0)
    val promedio = (nota1 + nota2 + notaRandom) / 3
    val estado = if (promedio >= 6) "Aprobado" else "Desaprobado"

    return "Alumno: $nombre\nPromedio con participación: %.2f\nEstado: $estado"
        .format(promedio)
}