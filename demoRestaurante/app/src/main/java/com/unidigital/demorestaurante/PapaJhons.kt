package com.unidigital.demorestaurante

import android.util.Log
import kotlin.random.Random

class PapaJhons(var enlace:IEnlace) : IRestaurante {
    var TAG = "PapaJhons"
    var extras = ""
    var paraLlevar = false

    private fun preparePizzas(tipoPizza:Int) {
        if(tipoPizza==1){
            enlace.hayProblemasConsuOrden("AGOTADA")
        }
        else
            enlace.suOrdenEstaLista(monto = 3000)
    }

    override fun iniciarTomadeOrden() {
        var hora =  Random.nextInt(1, 24)

        Log.i(TAG,"Hora = $hora")


        if(hora > 10 &&  hora < 22) {
            enlace.presentarMenu(arrayListOf("Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4","Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4","Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4","Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4","Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4"),
                {indiceCodigo, extrasIn, paraLlevarIn ->
                    extras = extrasIn
                    paraLlevar = paraLlevarIn
                    preparePizzas(indiceCodigo)
                }
            )
        }
        else
            enlace.estoyCerrado()
    }

}