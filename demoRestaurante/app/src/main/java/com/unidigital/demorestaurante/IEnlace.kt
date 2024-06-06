package com.unidigital.demorestaurante

interface IEnlace {
    fun estoyCerrado()
    fun presentarMenu(listaCombos:ArrayList<String>, onClienteElija:(Int) -> Unit)
    fun suOrdenEstaLista()
    fun preparandoSuOrden()
    fun hayProblemasConsuOrden(problema:String)
}