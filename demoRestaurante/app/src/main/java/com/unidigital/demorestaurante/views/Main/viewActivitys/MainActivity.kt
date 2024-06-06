package com.unidigital.demorestaurante.views.Main.viewActivitys

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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.unidigital.demorestaurante.views.Main.viewModels.Comensal
import com.unidigital.demorestaurante.views.Pago.viewActivitys.PagoActivity
import com.unidigital.demorestaurante.ui.theme.DemoRestauranteTheme

class MainActivity : ComponentActivity() {
    private val comensal: Comensal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            DemoRestauranteTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainView(comensal)
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

    if(comensal.showDialogMenu) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Surface( modifier = Modifier,
                shape = MaterialTheme.shapes.small) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Text(text = "MENU")

                    for(itemMenu in comensal.listMenu) {
                        Button(onClick = {
                            comensal.onClicMenu (itemMenu.first)
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
                shape = MaterialTheme.shapes.large
            ) {
                Column(horizontalAlignment = CenterHorizontally) {
                    Text(text = "SU ORDEN ESTA LISTA")

                    Button(onClick = {
                        comensal.showDialogOrdenLista = false
                        val intent = Intent(context, PagoActivity::class.java)
                        intent.putExtra("MONTO", "1000")
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
            Button(modifier = Modifier
                .height(60.dp)
                .width(180.dp), onClick = { comensal.llegarARestaurante(1) }) {
                Text(text = "Ordenar El Corral")
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

