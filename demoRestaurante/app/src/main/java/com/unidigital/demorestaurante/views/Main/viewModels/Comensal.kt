package com.unidigital.demorestaurante.views.Main.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unidigital.demorestaurante.ElCorral
import com.unidigital.demorestaurante.IEnlace
import com.unidigital.demorestaurante.IRestaurante
import com.unidigital.demorestaurante.PapaJhons

class Comensal : IEnlace, ViewModel() {
    var TAG = "Comensal"
    lateinit var restaurante: IRestaurante
    var listMenu  =  mutableStateListOf<Pair<Int,String>>()
    var mensajesDelRestaurante by mutableStateOf("Elija su Restaurante")
    var showDialogMenu by mutableStateOf(false)
    var showDialogOrdenLista by mutableStateOf(false)
    var showDialogObservaciones by mutableStateOf(false)
    var observaciones by mutableStateOf("")
    var indexMenu by mutableStateOf(0xFF)
    var showDialogWait by mutableStateOf(false)
    var montoOrden:Long by mutableStateOf(0)
    var paraLlevar by mutableStateOf(false)

    override fun estoyCerrado() {
        Log.i(TAG, "Restaurante Cerrado")
        mensajesDelRestaurante = "Restaurante Cerrado"
    }

    var onClicMenu : (Int,String,Boolean) -> Unit by mutableStateOf({ a,b,c -> })
    override fun presentarMenu(listaCombos: ArrayList<String>, onClienteElija: (Int, String, Boolean) -> Unit) {
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

    override fun suOrdenEstaLista(mensaje: String, monto: Long) {
        Log.i(TAG, "Voy a comer")
        montoOrden = monto
        mensajesDelRestaurante = mensaje
        showDialogOrdenLista = true
        showDialogWait = false
        //mensajesDelRestaurante = "Voy a comer"
    }

    override fun preparandoSuOrden() {
        mensajesDelRestaurante = "Esperando mi comida"
        showDialogWait = true

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