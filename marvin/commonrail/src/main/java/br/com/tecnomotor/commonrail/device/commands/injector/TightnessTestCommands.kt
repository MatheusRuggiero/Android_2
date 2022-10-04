package br.com.tecnomotor.commonrail.device.commands.injector

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class TightnessTestCommands(
    repository: CommonRailRepository
) : InjectorCommands(repository) {

    private var configuredControls = false

    /**
     *  Parametrização do teste para injetor
     *
     * ParameterGroup,          [01]  => 01 - Injector;
     *      CurrInjector,       [02]  => 0 - inj1 .. 3 - inj4
     *      InjectionFlag,      [03]  => 0 - disable measurement; 1 - enable measurement
     *      InjectionMsrFlag,   [04]  => 0 - disable measurement; 1 - enable measurement
     *      ReturnMsrFlag,      [05]  => 0 - disable measurement; 1 - enable measurement
     *      Pressure,           [06 07]
     *      TempoTeste,         [08 09]
     *      Frequency,          [10 11]
     *      TimeInj,            [12 13]
     *      TimeOn,             [14]
     *      TimeOff,            [15]
     *      TimeHigh,           [16]
     *      TimeIHigh,          [17 18]
     *      InjFillCtl,         [19]
     *      lTimeHoldOff        [20]
     *      lVLow               [21]
     *      lVHigh              [22]
     *
     *      [0x01]
     *      [canal = 0x00],
     *      [injecaoAtiva?:0x01:0x00]
     *      [medirInjecao?:0x01:0x00]
     *      [medirRetorno?:0x01:0x00]
     *      [(pressaoStep shr(8) && 0xFF)]
     *      [(pressaoStep && 0xFF)]
     *      [(0x07,0x08)]
     *      [(frequencia shr(8) && 0xFF)]
     *      [(frequencia && 0xFF)]
     *      [(tempoInjecao shr(8) && 0xFF)]
     *      [(tempoInjecao && 0xFF)]
     *      [(tempoLigado && 0xFF]
     *      [(tempoDesligado && 0xFF)]
     *      [(tempoDoSinalEmAlta && 0xFF)]
     *      [(tempo12Volts shr(8) && 0xFF]
     *      [(tempo12Volts && 0xFF)]
     *      [preEncherMedidoInjecao?:0x01:0x00]
     *      [tempoDoSinalDesligado && 0xFF]
     *      [tensaoBaixa && 0xFF]
     *      [tensaoAlta && 0xFF]
     *
     *
     */
    @InternalCoroutinesApi
    suspend fun parameterizeTest(testPoint: HashMap<String, ByteArray>): Boolean {
        var res: Boolean = false
        if (testPoint.isNotEmpty()) {
            while (!repository.controlCommands()?.canParameterize()!!)
                delay(100)

            val parameters = byteArrayOf(0x01.toByte(), 0x00.toByte()) +
                    testPoint["injecaoAtiva"]!! +
                    testPoint["medirInjecao"]!! +
                    testPoint["medirRetorno"]!! +
                    testPoint["pressaoStep"]!! +
                    byteArrayOf(0x07.toByte(), 0x08.toByte()) +
                    testPoint["frequencia"]!! +
                    testPoint["tempoInjecao"]!! +
                    testPoint["tempoLigado"]!! +
                    testPoint["tempoDesligado"]!! +
                    testPoint["tempoDoSinalEmAlta"]!! +
                    testPoint["tempo12Volts"]!! +
                    testPoint["preEncherMedidoInjecao"]!! +
                    testPoint["tempoDoSinalDesligado"]!! +
                    testPoint["tensaoBaixa"]!! +
                    testPoint["tensaoAlta"]!!
            res = repository.controlCommands()?.setParameters(parameters) ?: false
            if (res)
                res = res && repository.measurementCommands()?.parameterizesTightnessTest() ?: false
        }
        return res
    }

    override suspend fun startTest() {
        if (!configuredControls)
            configuredControls = repository.controlCommands()?.configurePID() ?: false
        repository.controlCommands()?.startTest()
        repository.measurementCommands()?.tightness()
    }

}