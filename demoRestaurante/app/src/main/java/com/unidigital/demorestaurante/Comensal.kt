package com.unidigital.demorestaurante

import android.util.Log

class Comensal : IEnlace {
    var TAG = "Comensal"
    var restaurante:IRestaurante

    init {
        restaurante = ElCorral(this)
    }

    override fun estoyCerrado() {
        Log.i(TAG, "Restaurante Cerrado")
    }

    override fun presentarMenu(listaCombos: ArrayList<String>, onClienteElija: (Int) -> Unit) {

        for(combos in listaCombos){
            Log.i(TAG, combos)
        }
        onClienteElija(0)
    }

    override fun suOrdenEstaLista() {
        Log.i(TAG, "Voy a comer")
    }

    fun llegarARestaurante() {
        restaurante.iniciarTomadeOrden()
    }

}