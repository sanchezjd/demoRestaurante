package com.unidigital.demorestaurante

import android.util.Log
import kotlin.random.Random

class PapaJhons(var enlace:IEnlace) : IRestaurante {
    var TAG = "PapaJhons"

    private fun preparePizzas(tipoPizza:Int) {
        if(tipoPizza==1){
            enlace.hayProblemasConsuOrden("AGOTADA")
        }
        else
            enlace.suOrdenEstaLista()
    }

    override fun iniciarTomadeOrden() {
        var hora =  Random.nextInt(1, 24)

        Log.i(TAG,"Hora = $hora")


        if(hora > 10 &&  hora < 22) {
            enlace.presentarMenu(arrayListOf("Pizza 1", "Pizza 2", "Pizza 3", "Pizza 4"),
                {indiceCodigo ->
                    preparePizzas(indiceCodigo)
                }
            )
        }
        else
            enlace.estoyCerrado()
    }

}