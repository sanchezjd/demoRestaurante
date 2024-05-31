package com.unidigital.demorestaurante

import android.util.Log

class ElCorral(var enlace:IEnlace) : IRestaurante {
    var TAG = "ElCorral"
    private fun prepararCombo1() {
        Log.i(TAG,"Enlace combo 1")
        enlace.suOrdenEstaLista()
    }

    private fun prepararCombo2() {
        Log.i(TAG,"Enlace combo 2")
        enlace.suOrdenEstaLista()
    }

    override fun iniciarTomadeOrden()  {
        var hora = 4

        if(hora > 6) {
            enlace.estoyCerrado()
        }
        else {
            enlace .presentarMenu(arrayListOf("Combo 1", "Combo 2"),
                {indiceCodigo ->
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