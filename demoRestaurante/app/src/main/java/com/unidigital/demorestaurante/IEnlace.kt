package com.unidigital.demorestaurante

interface IEnlace {
    fun estoyCerrado()
    fun presentarMenu(listaCombos:ArrayList<String>, onClienteElija:(Int, String, Boolean) -> Unit)
    fun suOrdenEstaLista(mensaje:String = "", monto:Long)
    fun preparandoSuOrden()
    fun hayProblemasConsuOrden(problema:String)
}