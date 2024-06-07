package com.unidigital.demorestaurante.views.Pago.viewActivitys

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.unidigital.demorestaurante.miBarraBaja
import com.unidigital.demorestaurante.miTopBar
import com.unidigital.demorestaurante.ui.theme.DemoRestauranteTheme

class PagoActivity : ComponentActivity() {
    var monto = ""

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        if (extras != null) {
            monto = extras.getLong("MONTO").toString()
        }
        setContent {
            DemoRestauranteTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        topBar = {
                            miTopBar()
                        },
                        bottomBar = {
                            miBarraBaja()
                        }
                    )
                    {
                        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("SELECCIONE SU TIPO DE PAGO")
                            Text("MONTO A PAGAR: $monto")
                            Button(onClick = {
                                var intent = Intent()
                                intent.putExtra("TIPO", "EFECTIVO")
                                setResult(RESULT_OK, intent)
                                finish()
                            }) {
                                Text("EFECTIVO")

                            }
                            Button(onClick = {
                                var intent = Intent()
                                intent.putExtra("TIPO", "TARJETA")
                                setResult(RESULT_OK, intent)
                                finish()
                            }) {
                                Text("TARJETA")

                            }
                        }
                    }
                }
            }
        }
    }
}



