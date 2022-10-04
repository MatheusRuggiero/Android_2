package br.com.tecnomotor.marvin.model.pump

import android.util.Log
import br.com.tecnomotor.commonrail.device.utils.measurement.EnumTypeOfMeasurement
import br.com.tecnomotor.marvin.model.pump.pk.PointTestPumpPK
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toByteArray16
import br.com.tecnomotor.marvin.utils.ExtentionFunctions.toHex
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class PointTestPump : Serializable {
    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var planId: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var sequence: Int = 0

    @SerializedName("tipoTeste")
    @JsonProperty("tipoTeste")
    var typePointTest: TypePointTestPump? = TypePointTestPump()

    @SerializedName("pontoTesteBombaPK")
    @JsonProperty("pontoTesteBombaPK")
    var pointTestPumpPK: PointTestPumpPK? = PointTestPumpPK()

    @SerializedName("nomeGenerico")
    @JsonProperty("nomeGenerico")
    var nameGeneric: String? = ""

    @SerializedName("correnteExt1")
    @JsonProperty("correnteExt1")
    var currentExt1: Double = 0.0

    @SerializedName("correnteExt2")
    @JsonProperty("correnteExt2")
    var currentExt2: Double = 0.0

    @SerializedName("frequenciaExt1")
    @JsonProperty("frequenciaExt1")
    var frequencyExt1: Int = 0

    @SerializedName("frequenciaExt2")
    @JsonProperty("frequenciaExt2")
    var frequencyExt2: Int = 0

    @SerializedName("tipoRotacao")
    @JsonProperty("tipoRotacao")
    var typeRotation: Int = 0

    @SerializedName("rotacao")
    @JsonProperty("rotacao")
    var rotation: Int = 0

    @SerializedName("variacaoRotacao")
    @JsonProperty("variacaoRotacao")
    var rotationVariation: Int = 0

    @SerializedName("pressaoAlimenBomba")
    @JsonProperty("pressaoAlimenBomba")
    var pressureFeed: Double = 0.0

    @SerializedName("toleranciaPressaoAlimenBomba")
    @JsonProperty("toleranciaPressaoAlimenBomba")
    var tolerancePressureFeed: Double = 0.0

    @SerializedName("tempAlimenBomba")
    @JsonProperty("tempAlimenBomba")
    var powerTemperature: Int = 0

    @SerializedName("toleranciaTempAlimenBomba")
    @JsonProperty("toleranciaTempAlimenBomba")
    var toleranceTemperatureSupply: Int = 0

    @SerializedName("tempRetorno")
    @JsonProperty("tempRetorno")
    var temperatureReturn: Int = 0

    @SerializedName("toleranciaTempRetorno")
    @JsonProperty("toleranciaTempRetorno")
    var toleranceTemperatureReturn: Int = 0

    @SerializedName("pressaoTransferencia")
    @JsonProperty("pressaoTransferencia")
    var pressureTransference: Int = 0

    @SerializedName("toleranciaPressaoTrans")
    @JsonProperty("toleranciaPressaoTrans")
    var tolerancePressureTransfer: Int = 0

    @SerializedName("vazaoPrincipal")
    @JsonProperty("vazaoPrincipal")
    var flowMain: Double = 0.0

    @SerializedName("toleranciaVazaoPrincipal")
    @JsonProperty("toleranciaVazaoPrincipal")
    var toleranceFlowMain: Double = 0.0

    @SerializedName("vazaoRetorno")
    @JsonProperty("vazaoRetorno")
    var flowReturn: Double = 0.0

    @SerializedName("toleranciaVazaoRetorno")
    @JsonProperty("toleranciaVazaoRetorno")
    var toleranceFlowReturn: Double = 0.0

    @SerializedName("pressaoTeste")
    @JsonProperty("pressaoTeste")
    var pressureTest: Int = 0

    @SerializedName("toleranciaPressaoTeste")
    @JsonProperty("toleranciaPressaoTeste")
    var tolerancePressureTest: Int = 0

    @SerializedName("atuadorTipo1")
    @JsonProperty("atuadorTipo1")
    var actuator1Type: Int = 0

    @SerializedName("atuadorValor1")
    @JsonProperty("atuadorValor1")
    var actuator1Values: Double = 0.0

    @SerializedName("atuadorToleranciaValor1")
    @JsonProperty("atuadorToleranciaValor1")
    var actuator1ValueTolerance: Double = 0.0

    @SerializedName("atuadorAcionamento1")
    @JsonProperty("atuadorAcionamento1")
    var actuator1Activation: Int = 0

    @SerializedName("atuadorValorAcionamento1")
    @JsonProperty("atuadorValorAcionamento1")
    var actuator1ValuesActivation: Int = 0

    @SerializedName("atuadorToleranciaValorAcionamento1")
    @JsonProperty("atuadorToleranciaValorAcionamento1")
    var actuator1ValueToleranceActivation: Int = 0

    @SerializedName("atuadorTipo2")
    @JsonProperty("atuadorTipo2")
    var actuator2Type: Int = 0

    @SerializedName("atuadorValor2")
    @JsonProperty("atuadorValor2")
    var actuator2Values: Double = 0.0

    @SerializedName("atuadorToleranciaValor2")
    @JsonProperty("atuadorToleranciaValor2")
    var actuator2ToleranceValue: Double = 0.0

    @SerializedName("atuadorAcionamento2")
    @JsonProperty("atuadorAcionamento2")
    var actuator2Activation: Int = 0

    @SerializedName("atuadorValorAcionamento2")
    @JsonProperty("atuadorValorAcionamento2")
    var actuator2ValuesActivation: Int = 0

    @SerializedName("atuadorToleranciaValorAcionamento2")
    @JsonProperty("atuadorToleranciaValorAcionamento2")
    var actuator2ToleranceValorActivation: Int = 0

    @SerializedName("tempoEsperaMedicao")
    @JsonProperty("tempoEsperaMedicao")
    var timeWaitingMeasurement: Int = 0

    @SerializedName("execPresetUsuario")
    @JsonProperty("execPresetUsuario")
    var presetUser: Boolean = false

    //	New column table reference point test pump
    @SerializedName("empty0")
    @JsonProperty("empty0")
    var empty0: Boolean = false

    @SerializedName("empty1")
    @JsonProperty("empty1")
    var empty1: Boolean = false

    @SerializedName("empty2")
    @JsonProperty("empty2")
    var empty2: Boolean = false

    @SerializedName("medirPrincipal")
    @JsonProperty("medirPrincipal")
    var measureMain: Boolean = false

    @SerializedName("medirRetorno")
    @JsonProperty("medirRetorno")
    var measureReturn: Boolean = false

    fun isMeasure():Boolean = (measureMain || measureReturn)

    val device: Byte = 0x07
    private val barycenterCH1: Int = 0x0000
    private val barycenterCH2: Int = 0x0000
    val frequency: Int = 0x0000
    //val timeout: Int = 0x0014 // vai usar do banco
    private val measurementChannel: Byte = 0x03


    constructor()

    /**
     * Para debyg (Rogério)
     */
    constructor(
        sequence: Int,
        typePointTest: TypePointTestPump?,
        nameGeneric: String?,
        currentExt1: Double = 0.0,
        currentExt2: Double = 0.0,
        frequencyExt1: Int = 0,
        frequencyExt2: Int = 0,
        typeRotation: Int = 0,
        rotation: Int = 0,
        rotationVariation: Int = 0,
        pressureFeed: Double = 0.0,
        tolerancePressureFeed: Double = 0.0,
        powerTemperature: Int = 0,
        toleranceTemperatureSupply: Int = 0,
        temperatureReturn: Int = 0,
        toleranceTemperatureReturn: Int = 0,
        pressureTransference: Int = 0,
        tolerancePressureTransfer: Int = 0,
        flowMain: Double = 0.0,
        toleranceFlowMain: Double = 0.0,
        flowReturn: Double = 0.0,
        toleranceFlowReturn: Double = 0.0,
        pressureTest: Int = 0,
        tolerancePressureTest: Int = 0,
        actuator1Type: Int = 0,
        actuator1Values: Double = 0.0,
        actuator1ValueTolerance: Double = 0.0,
        actuator1Activation: Int = 0,
        actuator1ValuesActivation: Int = 0,
        actuator1ValueToleranceActivation: Int = 0,
        actuator2Type: Int = 0,
        actuator2Values: Double = 0.0,
        actuator2ToleranceValue: Double = 0.0,
        actuator2Activation: Int = 0,
        actuator2ValuesActivation: Int = 0,
        actuator2ToleranceValorActivation: Int = 0,
        timeWaitingMeasurement: Int = 0,
        presetUser: Boolean = false,
        empty0: Boolean = false,
        empty1: Boolean = false,
        empty2: Boolean = false,
        measureMain: Boolean = false,
        measureReturn: Boolean = false
    ) {
        this.sequence = sequence
        this.typePointTest = typePointTest
        this.nameGeneric = nameGeneric
        this.currentExt1 = currentExt1
        this.currentExt2 = currentExt2
        this.frequencyExt1 = frequencyExt1
        this.frequencyExt2 = frequencyExt2
        this.typeRotation = typeRotation
        this.rotation = rotation
        this.rotationVariation = rotationVariation
        this.pressureFeed = pressureFeed
        this.tolerancePressureFeed = tolerancePressureFeed
        this.powerTemperature = powerTemperature
        this.toleranceTemperatureSupply = toleranceTemperatureSupply
        this.temperatureReturn = temperatureReturn
        this.toleranceTemperatureReturn = toleranceTemperatureReturn
        this.pressureTransference = pressureTransference
        this.tolerancePressureTransfer = tolerancePressureTransfer
        this.flowMain = flowMain
        this.toleranceFlowMain = toleranceFlowMain
        this.flowReturn = flowReturn
        this.toleranceFlowReturn = toleranceFlowReturn
        this.pressureTest = pressureTest
        this.tolerancePressureTest = tolerancePressureTest
        this.actuator1Type = actuator1Type
        this.actuator1Values - actuator1Values
        this.actuator1ValueTolerance = actuator1ValueTolerance
        this.actuator1Activation = actuator1Activation
        this.actuator1ValuesActivation = actuator1ValuesActivation
        this.actuator1ValueToleranceActivation = actuator1ValueToleranceActivation
        this.actuator2Type = actuator2Type
        this.actuator2Values = actuator2Values
        this.actuator2ToleranceValue = actuator2ToleranceValue
        this.actuator2Activation = actuator2Activation
        this.actuator2ValuesActivation = actuator2ValuesActivation
        this.actuator2ToleranceValorActivation = actuator2ToleranceValorActivation
        this.timeWaitingMeasurement = timeWaitingMeasurement
        this.presetUser = presetUser
        this.empty0 = empty0
        this.empty1 = empty1
        this.empty2 = empty2
        this.measureMain = measureMain
        this.measureReturn = measureReturn
    }

    fun getMinFlowMain(): Float {
        return flowMain.minus(toleranceFlowMain).toFloat()
    }

    fun getMaxFlowMain(): Float {
        return flowMain.plus(toleranceFlowMain).toFloat()
    }

    fun getMinFlowReturn(): Float {
        return flowReturn.minus(toleranceFlowReturn).toFloat()
    }

    fun getMaxFlowReturn(): Float {
        return flowReturn.plus(toleranceFlowReturn).toFloat()
    }

    fun getFlowMainMinimumGraph(): Float {
        var res = 0f
        if (!measureMain)
            return res
        val flow = getMinFlowMain()
        res = flow - 10f
        if ((flow == 0f) || (res < 0f)) return 0f
        return res
    }

    fun getFlowMainMaximumGraph(): Float {
        var res = 0f
        if (!measureMain)
            return res
        val flow = getMaxFlowMain()
        if (flow == 0f) return flow
        res = flow + 10f
        return res
    }

    fun getFlowReturnMinimumGraph(): Float {
        var res = 0f
        if (!measureReturn)
            return res
        val flow = getMinFlowReturn()
        res = flow - 10f
        if ((flow == 0f) || (res < 0f)) return 0f
        return res
    }

    fun getFlowReturnMaximumGraph(): Float {
        var res = 0f
        if (!measureReturn)
            return res
        val flow = getMaxFlowReturn()
        if (flow == 0f) return flow
        res = flow + 10f
        return res
    }

    fun getControlParameters(timeout: Int, pump: Pump): ByteArray {
        return byteArrayOf(
            0x06.toByte(),  //Dispositivo
            //0x01.toByte()   //Tipo de controle TODO: remover depois
            pump.referenceActuator.toByte()
        ) +
                (actuator1Values * 100).toInt().toByteArray16() + // deveria ser currentExt1
                (actuator2Values * 100).toInt().toByteArray16() + // deveria ser currentExt2
                actuator1ValuesActivation.toByteArray16() + // deveria ser frequencyExt1
                actuator2ValuesActivation.toByteArray16() + // deveria ser frequencyExt2
                pressureTest.toByteArray16() +
                rotation.toByteArray16() +
                timeout.toByteArray16()
    }

    fun getTimeout(): Int = timeWaitingMeasurement

    fun getControlParameters(pump: Pump): ByteArray {
        val parametros = getControlParameters(timeWaitingMeasurement, pump)
        return parametros
    }

    /**
     * Devolve os parâmetros para a placa de medição. Alguns valores estão fixos, outros vem do banco
     * Como nunca mede ambos os canais principal e retorno então de acordo com o argumento typeOfMeasurement e a informação do banco então
     * escolhe apenas um dos dois para deixar setado com 1: measureMain ou measureReturn
     * @param device indica qual dispositivo será usado na parametrização
     * @param typeOfMeasurement indica qual vazão será usada na medição: principal ou retorno. Caso deixado em branco nenhuma será selecionada.
     */
    fun getMeasurementParameters(device: Byte, typeOfMeasurement: EnumTypeOfMeasurement = EnumTypeOfMeasurement.None): ByteArray {
        var measureMainInt   = 0
        var measureReturnInt = 0
        when (typeOfMeasurement) {
            EnumTypeOfMeasurement.FlowMeasurement   -> measureMainInt   = if(measureMain)   1 else 0
            EnumTypeOfMeasurement.ReturnMeasurement -> measureReturnInt = if(measureReturn) 1 else 0
            else -> {}
        }

        val parameters =
            byteArrayOf(device) +                    //dispositivo
                barycenterCH1.toByteArray16() +        //centro de massa ch1
                barycenterCH2.toByteArray16() +        //centro de massa ch2
                frequency.toByteArray16() +            //frequencia
                timeWaitingMeasurement.toByteArray16() + //timeout
        byteArrayOf(
            measurementChannel,         //canal de medição
            measureMainInt.toByte(),  //flag de injeção
            measureReturnInt.toByte() //flag retorno
        )
        Log.w("PointTestPump", "Measurement parameters: ${parameters.toHex()}")
        return parameters
    }


    override fun toString(): String {
        return "PointTestPump(planId=$planId, sequence=$sequence, typePointTest=${typePointTest?.description}, measureMain=$measureMain, measureReturn=$measureReturn, nameGeneric=$nameGeneric, currentExt1=$currentExt1, currentExt2=$currentExt2, frequencyExt1=$frequencyExt1, frequencyExt2=$frequencyExt2, typeRotation=$typeRotation, rotation=$rotation, rotationVariation=$rotationVariation, pressureFeed=$pressureFeed, tolerancePressureFeed=$tolerancePressureFeed, powerTemperature=$powerTemperature, toleranceTemperatureSupply=$toleranceTemperatureSupply, temperatureReturn=$temperatureReturn, toleranceTemperatureReturn=$toleranceTemperatureReturn, pressureTransference=$pressureTransference, tolerancePressureTransfer=$tolerancePressureTransfer, flowMain=$flowMain, toleranceFlowMain=$toleranceFlowMain, flowReturn=$flowReturn, toleranceFlowReturn=$toleranceFlowReturn, pressureTest=$pressureTest, tolerancePressureTest=$tolerancePressureTest, actuator11Type=$actuator1Type, actuator1Values=$actuator1Values, actuator1ValueTolerance=$actuator1ValueTolerance, actuator1Activation=$actuator1Activation, actuator1ValuesActivation=$actuator1ValuesActivation, actuator1ValueToleranceActivation=$actuator1ValueToleranceActivation, actuator2Type=$actuator2Type, actuator2Values=$actuator2Values, actuator2ToleranceValue=$actuator2ToleranceValue, actuator2Activation=$actuator2Activation, actuator2ValuesActivation=$actuator2ValuesActivation, actuator2ToleranceValorActivation=$actuator2ToleranceValorActivation, timeWaitingMeasurement=$timeWaitingMeasurement, presetUser=$presetUser, empty0=$empty0, empty1=$empty1, empty2=$empty2)"
    }

}
