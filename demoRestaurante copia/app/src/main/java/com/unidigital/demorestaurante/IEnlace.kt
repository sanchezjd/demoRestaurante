package com.unidigital.demorestaurante

interface IEnlace {
    fun estoyCerrado()
    fun presentarMenu(listaCombos:ArrayList<String>, onClienteElija:(Int) -> Unit)
    fun preparandoSuOrden()
    fun suOrdenEstaLista()
    fun hayProblemasConsuOrden(problema:String)
}