package br.com.tecnomotor.commonrail.device

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import br.com.tecnomotor.commonrail.device.CommonRailHexDefines.commonRailControleSource
import br.com.tecnomotor.commonrail.device.CommonRailHexDefines.commonRailControleTarget
import br.com.tecnomotor.commonrail.device.CommonRailHexDefines.commonRailMedicaoSource
import br.com.tecnomotor.commonrail.device.CommonRailHexDefines.commonRailMedicaoTarget
import br.com.tecnomotor.commonrail.device.commands.ControlCommands
import br.com.tecnomotor.commonrail.device.commands.MeasurementCommands
import br.com.tecnomotor.device.Device
import br.com.tecnomotor.device.DeviceFactory
import br.com.tecnomotor.kwptm.KWPTM
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import java.io.IOException
import java.util.concurrent.TimeoutException

/**Essa classe será responsável por criar os dispositivos medicao e controle
 *A ela cabe a responsabilidade de detectar a conexão, criar e retornar o dispositivo solicitado,
 *e lançar qualquer erro que possa ocorrer no processo
 */
@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CommonRailDeviceFactory(context: Context) : DeviceFactory(context) {

    enum class DeviceStatus { dsNone, dsFound, dsNotFound }
    private var controleDevice: CommonRailDevice? = null
    private var controleDeviceStatus: DeviceStatus = DeviceStatus.dsNone
    private var medicaoDevice: CommonRailDevice? = null
    private var medicaoDeviceStatus: DeviceStatus = DeviceStatus.dsNone

    init {
        helper.usingUSB()
        helper.refresh()
    }

    fun isControleFound(): Boolean {
        return controleDeviceStatus == DeviceStatus.dsFound
    }

    fun isMedicaoFount(): Boolean {
        return medicaoDeviceStatus == DeviceStatus.dsFound
    }

    @InternalCoroutinesApi
    private suspend fun deviceTest(device: CommonRailDevice): Boolean {
        var res = false
        try {
            res = device.startCommunication()
            if (!res) device.disconnect()
        } catch (e: Exception) {
            device.disconnect()
        }
        return res
    }

    /**
     * Verifica se o dispositivo é de medição e retorna o device
     * @param index para o device
     * @return Device já conectado
     */
    @InternalCoroutinesApi
    private suspend fun medicaoDeviceTest(index: Int): Device? {

        try {
            medicaoDevice = createMeasureDevice(index)
            val res = deviceTest(medicaoDevice!!)
            if (!res) {
                medicaoDevice = null
                medicaoDeviceStatus = DeviceStatus.dsNotFound
            } else {
                medicaoDevice!!.commands.kwp.setWriteTimeOut(10)
                medicaoDeviceStatus = DeviceStatus.dsFound
            }
        } catch (e: TimeoutException) {
            medicaoDevice = null
        }
        return medicaoDevice
    }

    /**
     * Verifica se o dispositivo é de controle e retorna o device
     * @param index para o device
     * @return Device já conectado
     */
    @InternalCoroutinesApi
    private suspend fun controleDeviceTest(index: Int): Device? {
        try {
            controleDevice = createControlDevice(index)
            val res = deviceTest(controleDevice!!)
            if (!res) {
                controleDevice = null
                controleDeviceStatus = DeviceStatus.dsNotFound
            } else {
                controleDevice!!.commands.kwp.setWriteTimeOut(10)
                controleDeviceStatus = DeviceStatus.dsFound
            }
        } catch (e: TimeoutException) {
            controleDevice = null
        }
        return controleDevice
    }

    @InternalCoroutinesApi
    private suspend fun deviceMaker(index: Int): Device {
        val device = when(index){
            0 -> createControlDevice(index)
            1 ->createMeasureDevice(index)
            else -> {
                throw Exception()
            }
        }
        device.startCommunication()

        return device
    }

    private suspend fun createControlDevice(index: Int): CommonRailDevice {
        val devKWPTM: KWPTM = helper.getDevice(index)
        devKWPTM.setWriteTimeOut(10)
        devKWPTM.setSource(commonRailControleSource).setTarget(commonRailControleTarget)
        return CommonRailDevice(CommonRailDeviceName.control, ControlCommands(devKWPTM))
    }
    private suspend fun createMeasureDevice(index: Int): CommonRailDevice {
        val devKWPTM: KWPTM = helper.getDevice(index)
        devKWPTM.setWriteTimeOut(10)
        devKWPTM.setSource(commonRailMedicaoSource).setTarget(commonRailMedicaoTarget)
        return CommonRailDevice(CommonRailDeviceName.measurement, MeasurementCommands(devKWPTM))
    }


    /**
     * Cria e retorna dispositivo relativo ao índice fornecido.
     * @param index Índice referente ao dispositivo
     */
    override suspend fun createDevice(index: Int): Device {
        var device: Device? = controleDeviceTest(index)
        if (device != null)
            return device
        device = medicaoDeviceTest(index)
        if (device != null)
            return device
        throw(IOException("Erro ao criar um dispositivo"))
    }

    override suspend fun createDevice(description: String): Device {
        val UARTPort = description.split("port")[1].toInt()
        println("uart = $UARTPort")
        return deviceMaker(UARTPort)
    }
}