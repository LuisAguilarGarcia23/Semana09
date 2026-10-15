package com.example.semana09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.semana09.ui.theme.Semana09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana09Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ControlAcceso(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Semana09Theme {
        Greeting("Android")
    }
}

@Composable
fun ControlAcceso(modifier: Modifier = Modifier) {
    var contadorPersonas by remember { mutableIntStateOf(0) }
    var entradaTexto by remember { mutableStateOf("") }
    var ultimoNombre by remember { mutableStateOf("Ninguno") }

    Column(modifier = modifier) {
        Text(text = "Control de Aforo")
        Text(text = "Personas adentro: $contadorPersonas")

        val usuarioAMostrar = if (contadorPersonas == 0) "Ninguno" else ultimoNombre
        Text(text = "Usuario registrado: $usuarioAMostrar")

        TextField(value = entradaTexto, onValueChange = { entradaTexto = it })
        Button(onClick = {
            ultimoNombre = entradaTexto
            contadorPersonas += 1
            entradaTexto = ""
        }) {
            Text(text = "Registrar e Ingresar")
        }

        Row {
            Button(onClick = {
                if (contadorPersonas > 0) {
                    contadorPersonas -= 1
                }
            }) {
                Text(text = "Salida (-1)")
            }

            Button(onClick = {
                contadorPersonas = 0
                ultimoNombre = "Ninguno"
            }) {
                Text(text = "Reiniciar")
            }
        }
    }
}
