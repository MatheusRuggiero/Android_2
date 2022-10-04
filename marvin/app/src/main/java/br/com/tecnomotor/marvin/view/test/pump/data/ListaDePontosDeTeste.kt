package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump

class ListaDePontosDeTeste(): ArrayList<PointTestPump>() {
    fun add(pontoDeTeste: PontoDeTeste):ListaDePontosDeTeste {
        this.add(pontoDeTeste.obter())
        return this
    }
}