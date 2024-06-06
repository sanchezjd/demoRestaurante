package com.unidigital.demorestaurante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import com.unidigital.demorestaurante.ui.theme.DemoRestauranteTheme

class MainActivity : ComponentActivity() {
    private val comensal: Comensal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            DemoRestauranteTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    if(comensal.showDialogMenu) {
                        Dialog(onDismissRequest = { /*TODO*/ }) {
                            Surface( modifier = Modifier,
                                shape = MaterialTheme.shapes.small) {
                                Column(horizontalAlignment = CenterHorizontally) {
                                    Text(text = "MENU")


                                    for(itemMenu in comensal.listMenu) {
                                        Button(onClick = {
                                            comensal.onClicMenu(itemMenu.first)
                                            comensal.showDialogMenu = false
                                        }) {
                                            Text(text = itemMenu.second)
                                        }

                                    }
                                }
                            }
                        }
                    }

                    if(comensal.showDialogOrdenLista) {
                        Dialog(onDismissRequest = { /*TODO*/ }) {
                            Surface(
                                modifier = Modifier,
                                shape = MaterialTheme.shapes.small
                            ) {
                                Column(horizontalAlignment = CenterHorizontally) {
                                    Text(text = "SU ORDEN ESTA LISTA")
                                    Button(onClick = {
                                        comensal.showDialogOrdenLista = false
                                        comensal.cambiarEstadoVoyAComer()
                                    }) {
                                        Text(text = "OK")
                                    }
                                }

                            }
                        }
                    }

                    Column (modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                        horizontalAlignment = CenterHorizontally) {


                        Text(text = "DEMO")
                        Spacer(modifier = Modifier.height(30.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Button(modifier = Modifier
                                .height(60.dp)
                                .width(180.dp),
                                onClick = {
                                    comensal.llegarARestaurante(1)
                                })
                            {
                                Column (modifier = Modifier
                                    .fillMaxSize(),
                                    horizontalAlignment = CenterHorizontally) {
                                    Text(text = "Ordenar")
                                    Text(text = "El Corral", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.size(10.dp))
                            Button(modifier = Modifier
                                .height(60.dp)
                                .width(180.dp), onClick = { comensal.llegarARestaurante(2) }) {
                                Text(text = "Ordenar Papa Jhons")
                            }
                        }

                        Text(text = comensal.mensajesDelRestaurante)
                    }
                }
            }
        }
    }
}

