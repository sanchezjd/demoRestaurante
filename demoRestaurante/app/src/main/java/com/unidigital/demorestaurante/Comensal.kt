package com.unidigital.demorestaurante

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Comensal : IEnlace, ViewModel() {
    var TAG = "Comensal"
    lateinit var restaurante:IRestaurante
    var listMenu  =  mutableStateListOf<Pair<Int,String>>()
    var mensajesDelRestaurante by mutableStateOf("Elija su Restaurante")
    var showDialogMenu by mutableStateOf(false)



    override fun estoyCerrado() {
        Log.i(TAG, "Restaurante Cerrado")
        mensajesDelRestaurante = "Restaurante Cerrado"
    }

    var onClicMenu : (Int) -> Unit by mutableStateOf({})
    override fun presentarMenu(listaCombos: ArrayList<String>, onClienteElija: (Int) -> Unit) {
        onClicMenu = onClienteElija
        listMenu.clear()
        var i = 0
        for(combos in listaCombos){
            listMenu.add(Pair(i,combos))
            Log.i(TAG, combos)
            i++
        }
        showDialogMenu = true

    }

    override fun suOrdenEstaLista() {
        Log.i(TAG, "Voy a comer")
        mensajesDelRestaurante = "Voy a comer"
    }

    override fun hayProblemasConsuOrden(problema: String) {
        Log.i(TAG, "Hay un problema: $problema")
        mensajesDelRestaurante = "Hay un problema: $problema"
    }

    fun llegarARestaurante(tipoRest:Int) {
        if(tipoRest == 1)
            restaurante = ElCorral(this)
        else
            restaurante = PapaJhons(this)

        restaurante.iniciarTomadeOrden()
    }

}