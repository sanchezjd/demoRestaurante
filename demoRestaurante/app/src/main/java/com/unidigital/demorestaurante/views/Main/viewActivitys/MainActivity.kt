package com.unidigital.demorestaurante.views.Main.viewActivitys

import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.unidigital.demorestaurante.*
import com.unidigital.demorestaurante.R
import com.unidigital.demorestaurante.views.Main.viewModels.Comensal
import com.unidigital.demorestaurante.views.Pago.viewActivitys.PagoActivity
import com.unidigital.demorestaurante.ui.theme.DemoRestauranteTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val comensal: Comensal by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                        MainView(comensal)
                    }

                }
            }
        }
    }
}



@Composable
fun MainView(comensal: Comensal) {

    var context = LocalContext.current

    val medioPagoLauncher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                var tipoPAgo = result.data!!.getStringExtra("TIPO")
                comensal.mensajesDelRestaurante = "Su pago fue con $tipoPAgo"
            }
            RESULT_CANCELED -> {

            }
        }
    }

    if(comensal.showDialogWait) {
        DialogWait(textoMensaje = "Preparando Comida")
    }

    if(comensal.showDialogObservaciones) {
       viewObservaciones(comensal)
    }

    if(comensal.showDialogMenu) {
            Dialog(onDismissRequest = { /*TODO*/ }) {
                Surface(
                    modifier = Modifier,
                    shape = MaterialTheme.shapes.small
                ) {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Text(text = "MENU")


                        LazyColumn(modifier = Modifier
                            .padding(10.dp)
                            .height(150.dp)) {
                            items(comensal.listMenu) { menuItem ->
                                customBoton(
                                    etiqueta = menuItem.second,
                                    onClickIn = {
                                        comensal.showDialogObservaciones = true
                                        comensal.indexMenu = menuItem.first
                                        comensal.showDialogMenu = false
                                    })
                                Spacer(modifier = Modifier.size(5.dp))
                            }
                        }

                        /* for(itemMenu in comensal.listMenu) {
                        customBoton(
                            etiqueta = itemMenu.second,
                            onClickIn = {
                            comensal.onClicMenu (itemMenu.first)
                            comensal.showDialogMenu = false
                        })
                        Spacer(modifier = Modifier.size(5.dp))
                    }*/
                    }
                }
            }
        }

    if(comensal.showDialogOrdenLista) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Surface(
                modifier = Modifier,
                shape = MaterialTheme.shapes.large
            ) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Text(text = "SU ORDEN ESTA LISTA")

                    Button(onClick = {
                        comensal.showDialogOrdenLista = false
                        val intent = Intent(context, PagoActivity::class.java)
                        intent.putExtra("MONTO", comensal.montoOrden)
                        medioPagoLauncher.launch(intent)

                        //comensal.mensajesDelRestaurante = "Voy comer"


                    }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), horizontalAlignment = CenterHorizontally) {


        Text(text = "DEMO")
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            customBoton(
                etiqueta = "Ordenar El Corral",
                onClickIn = { comensal.llegarARestaurante(1) }
            )


            Spacer(modifier = Modifier.size(10.dp))

            customBoton(
                etiqueta = "Ordenar Papa Jhons",
                onClickIn = { comensal.llegarARestaurante(2)}
            )

        }

        Text(text = comensal.mensajesDelRestaurante, fontSize = if(comensal.paraLlevar) 24.sp else 16.sp)

        if(comensal.paraLlevar)
            Text(text = "PARA LLEVAR")
    }
}

@Composable
fun viewObservaciones(comensal: Comensal) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            modifier = Modifier,
            shape = MaterialTheme.shapes.small
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Text(text = "OBSERVACIONES")

                OutlinedTextField(
                    value = comensal.observaciones,
                    onValueChange = {
                        if (it.length <= 20)
                            comensal.observaciones = it
                    },
                    label = { Text("Observaciones") },

                    )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Para llevar")
                    Switch(
                        modifier = Modifier.weight(0.17F),
                        checked = comensal.paraLlevar,
                        onCheckedChange = {
                            comensal.paraLlevar = it
                        }
                    )
                }

                customBoton(
                    etiqueta = "Aceptar",
                    onClickIn = {
                        comensal.showDialogObservaciones = false
                        comensal.onClicMenu(comensal.indexMenu, comensal.observaciones, comensal.paraLlevar)

                    })
            }
        }
    }
}

