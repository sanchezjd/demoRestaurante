package com.unidigital.demorestaurante

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize

@Composable
fun customBoton(etiqueta:String, onClickIn:() -> Unit, sizeFuente:TextUnit = 16.sp) {
    Button(onClick = onClickIn,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
            .height(60.dp)
            .width(180.dp)
            .border(
                    2.dp,
                    Color.Blue,
                    shape = RoundedCornerShape(15.dp)
                ) // Borde azul
                .background( Color.Blue, shape = RoundedCornerShape(15.dp))


    )
    {
        Text(text = etiqueta, fontSize = sizeFuente, color = Color.White)
    }
}


@Composable
fun DialogWait(textoMensaje: String,  textoMensaje2:String? = null, textoBoton1:String? = null, onClickBoton1:() -> Unit = {}, withGif:Boolean = true, textosModo1:Boolean = false) {

    var fontSize1 = 12.sp

    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(shape = MaterialTheme.shapes.medium, modifier = Modifier.width(250.dp)) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        textoMensaje,
                        fontSize = fontSize1,
                        textAlign = TextAlign.Center,
                    )
                }
                if(withGif) {
                    Spacer(modifier = Modifier.size(10.dp))
                    GifImage(
                        Modifier.size(30.dp),
                        imageID = R.drawable.loading
                    )
                }
                if (textoMensaje2 != null) {
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        textoMensaje2,

                        fontSize = fontSize1,
                        textAlign = TextAlign.Center,

                    )
                }
                if (textoBoton1 != null) {
                    Spacer(modifier = Modifier.size(10.dp))
                    customBoton(
                        textoBoton1,

                        onClickIn = onClickBoton1
                    )
                }
            }
        }
    }
}



@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    imageID: Int
){
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberImagePainter(
            imageLoader = imageLoader,
            data = imageID,
            builder = {
                size(OriginalSize)
            }
        ),
        contentDescription = null,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun miTopBar( ) {
    CenterAlignedTopAppBar(
        modifier = Modifier.height(50.dp),
        title = {Text("DEMO RESTAURANTE", color = Color.White)},
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )

            }
        }
    )
}

@Composable
fun miBarraBaja( ) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logodemo), contentDescription = "")
    }
}