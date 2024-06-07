package com.unidigital.demorestaurante

interface IEnlace {
    fun estoyCerrado()
    fun presentarMenu(listaCombos:ArrayList<String>, onClienteElija:(Int, String) -> Unit)
    fun suOrdenEstaLista(mensaje:String = "")
    fun preparandoSuOrden()
    fun hayProblemasConsuOrden(problema:String)
}