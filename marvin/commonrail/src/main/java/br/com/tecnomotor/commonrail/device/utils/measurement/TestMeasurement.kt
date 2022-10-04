package br.com.tecnomotor.commonrail.device.utils.measurement

import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

class TestMeasurement(
    var process: EnumTestProcessForMeasurement = EnumTestProcessForMeasurement.NONE,
    var status: EnumTestStatus = EnumTestStatus.NONE,
    var error: Int = 0,
    private var injecao: MeasurementChannel? = null,
    private var retorno: MeasurementChannel? = null,
    var flagInjecao: Boolean = false,
    var flagRetorno: Boolean = false,
    var medirRetorno: Boolean = false,
    var medirInjecao: Boolean = false,
    var canalUnico: Boolean = false,
    var canalAtual: MeasurementChannel? = null
) {

    private companion object {
        val MEDICAO_LIMIAR_SUP = 70
        val MEDICAO_LIMIAR_MED = 40
        val MEDICAO_LIMIAR_INF = 40
    }

    private fun setCanalUnico(mesmoCanal: Boolean, canalUtilizado: MeasurementChannel) {
        this.canalAtual = canalUtilizado
        this.canalUnico = mesmoCanal
    }

    /**
     * Muda entre o canal de injeção e o canal de retorno
     */
    fun mudarDeCanal() {
        if (canalUnico) {
            canalAtual = if (canalAtual == injecao)
                retorno
            else
                injecao
            flagInjecao = (canalAtual == injecao)
            flagRetorno = (canalAtual == retorno)
        }
    }

    fun loadParametrizacao(
        processo: EnumTestProcessForMeasurement,
        frequencia: Int,
        medirInjecao: Boolean,
        medirRetorno: Boolean,
        vazaoMax: Double,
        retornoMax: Double
    ) {
        this.process = processo
        this.medirInjecao = medirInjecao
        this.medirRetorno = medirRetorno
        this.flagInjecao = medirInjecao == false
        this.flagRetorno = (!medirInjecao && medirRetorno)
        val canalInjecao = if (vazaoMax >= MEDICAO_LIMIAR_MED) Channel.CH2 else Channel.CH1
        val limiarInfInj = if (canalInjecao == Channel.CH1) MEDICAO_LIMIAR_INF else MEDICAO_LIMIAR_MED
        val limiarSupInj = if (canalInjecao == Channel.CH1) MEDICAO_LIMIAR_MED else MEDICAO_LIMIAR_SUP
        val canalRetorno = if (retornoMax >= MEDICAO_LIMIAR_MED) Channel.CH2 else Channel.CH1
        val limiarInfRet = if (canalRetorno == Channel.CH1) MEDICAO_LIMIAR_INF else MEDICAO_LIMIAR_MED
        val limiarSupRet = if (canalRetorno == Channel.CH1) MEDICAO_LIMIAR_MED else MEDICAO_LIMIAR_SUP

        if (medirRetorno && medirInjecao) {
            this.injecao = MeasurementChannel(canalInjecao)
            this.retorno = MeasurementChannel(canalRetorno)
            this.setCanalUnico(true, injecao!!)
            injecao!!.loadParametrizacao(processo, frequencia, vazaoMax, limiarInfInj, limiarSupInj)
            retorno!!.loadParametrizacao(processo, frequencia, retornoMax, limiarInfRet, limiarSupRet)
        } else if (medirInjecao) {
            this.injecao = MeasurementChannel(canalInjecao)
            this.retorno = null
            this.setCanalUnico(false, injecao!!)
            injecao!!.loadParametrizacao(processo, frequencia, vazaoMax, limiarInfInj, limiarSupInj)
        } else if (medirRetorno) {
            this.flagInjecao = false
            this.flagRetorno = true
            this.retorno = MeasurementChannel(canalRetorno)
            this.injecao = null
            this.setCanalUnico(false, retorno!!)
            retorno!!.loadParametrizacao(processo, frequencia, retornoMax, limiarInfRet, limiarSupRet)
        }
    }

    fun getParametros(): ByteArray {
        val parametros = this.canalAtual!!.getParametros() +
                byteArrayOf(
                    canalAtual!!.channel.id.toByte(),
                    if (flagInjecao) 0x01.toByte() else 0x00.toByte(),
                    if (flagRetorno) 0x01.toByte() else 0x00.toByte()
                )
        return parametros
    }

    override fun toString(): String {
        return "Medicao(processo=$process, status=$status, erro=$error, injecao=$injecao, retorno=$retorno, flagInjecao=$flagInjecao, flagRetorno=$flagRetorno, medirRetorno=$medirRetorno, medirInjecao=$medirInjecao, canalUnico=$canalUnico, canalAtual=$canalAtual)"
    }


}