package com.unidigital.demorestaurante

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.random.Random

class ElCorral(var enlace:IEnlace) : IRestaurante {
    var TAG = "ElCorral"
    var observaciones = ""
    private fun prepararCombo1() {
        Log.i(TAG,"Enlace combo 1")


        GlobalScope.async {
            Thread.sleep(2000)
            enlace.suOrdenEstaLista("Combo 1 Observacion: $observaciones")
        }

        enlace.preparandoSuOrden()
    }

    private fun prepararCombo2() {
        Log.i(TAG,"Enlace combo 2")

        enlace.suOrdenEstaLista("Combo 2 Observacion: $observaciones")
    }

    override fun iniciarTomadeOrden()  {
        var hora =  Random.nextInt(1, 24)

        Log.i(TAG,"Hora = $hora")
        if(hora > 17) {
            enlace.estoyCerrado()
        }
        else {
            enlace.presentarMenu(arrayListOf("Combo 1", "Combo 2"),
                {indiceCodigo, observacionesIn ->
                    observaciones = observacionesIn
                    if(indiceCodigo == 0){
                        prepararCombo1()
                    }
                    else if(indiceCodigo == 1){
                        prepararCombo2()
                    }
                }
            )
        }
    }

}