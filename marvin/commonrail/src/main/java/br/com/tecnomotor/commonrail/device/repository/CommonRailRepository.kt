package br.com.tecnomotor.commonrail.device.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import br.com.tecnomotor.commonrail.device.CommonRailDevice
import br.com.tecnomotor.commonrail.device.CommonRailDeviceFactory
import br.com.tecnomotor.commonrail.device.CommonRailDeviceName
import br.com.tecnomotor.commonrail.device.commands.ControlCommands
import br.com.tecnomotor.commonrail.device.commands.MeasurementCommands
import br.com.tecnomotor.commonrail.device.controller.DeviceControlController
import br.com.tecnomotor.commonrail.device.controller.DeviceMeasurementController
import br.com.tecnomotor.kwptm.KWPTMinfo
import kotlinx.coroutines.*

/**
 * Esta classe é uma intermediária entre o dispositivo (placa) e o viewModel
 */
@InternalCoroutinesApi
@DelicateCoroutinesApi
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CommonRailRepository(context: Context) {
    private var devicesIsReady = false

    //O repository não pode ser serializable, deve seguir o princípio single source of truth, para isso ele deve ser Singleton e, portantnto, não pode ser serializável
    private val tag = "CommonRailRepository"
    private var deviceFactory  = CommonRailDeviceFactory(context)
    private var initJob = GlobalScope.launch {  }
    private var connectingJob = GlobalScope.launch {  }
    val controlController: DeviceControlController by lazy {
        while (!devicesIsReady){
        }

        DeviceControlController(controlDevice.commands)
    }

    val measurementController: DeviceMeasurementController by lazy {
        while (!devicesIsReady){}

        DeviceMeasurementController(measurementDevice.commands)

    }

    enum class CommonRailRepositoryState{
                                            SerialConnecting,//procurando as portas seriais
                                            DeviceConnecting,//procurando os dispositivos
                                            Done             //criou os dispositivos, está esperando algum erro para tentar conectar novamente
                                        }

    var repositoryState = CommonRailRepositoryState.SerialConnecting

    companion object {
        private lateinit var instance: CommonRailRepository
        fun getInstance(context: Context? = null): CommonRailRepository {
            if (!isItInitialized())
                    if(context == null)
                        throw java.lang.Exception("O contexto deve ser enviado pelo menos na primeira vez que o método getInstance(contexto) for chamado")
                    else
                        instance = CommonRailRepository(context.applicationContext)
            return instance
        }

        private fun isItInitialized() = this::instance.isInitialized
    }
    init{
        if (isItInitialized())
            throw Exception("Repository é Singleton, deve ser inicializado a partir do método getInstance.")


        connectingJob = reconnectJob()
    }


    private var avaiableDeviceList: ArrayList<KWPTMinfo> = arrayListOf()

    lateinit var controlDevice: CommonRailDevice
    fun controlIsOk() = this::controlDevice.isInitialized

    lateinit var measurementDevice: CommonRailDevice
    fun measurementIsOk() = this::measurementDevice.isInitialized

    suspend fun createDevices():Boolean {
        deviceFactory.refreshDevicesList()
        avaiableDeviceList.clear()
        avaiableDeviceList.addAll(deviceFactory.devicesList)
        try {
                avaiableDeviceList.forEach { device ->
                val available = deviceFactory.createDevice(device.description) as CommonRailDevice
                when (available.name) {
                    CommonRailDeviceName.control -> controlDevice = available
                    CommonRailDeviceName.measurement -> measurementDevice = available
                    else -> {}
                }
            }
        }catch (e:Exception){
            return false
        }
        initControllers()
        return controlIsOk() && measurementIsOk()
    }
    
    private fun initControllers(){
        devicesIsReady = true
    }

    private fun reconnectJob(): Job{
        return GlobalScope.launch(context = Dispatchers.IO) {
            resetBoards()
            while (true) {
                connectStateMachine()
                delay(100)
            }
        }
    }

    private suspend fun resetBoards() {
        var controlJob = GlobalScope.launch {  }
        var measureJob = GlobalScope.launch {  }

        deviceFactory.refreshDevicesList()
        avaiableDeviceList.clear()
        avaiableDeviceList.addAll(deviceFactory.devicesList)
        try {
            avaiableDeviceList.forEach { device ->
                val available = deviceFactory.createDevice(device.description) as CommonRailDevice
                when (available.name) {
                    CommonRailDeviceName.control ->controlJob = resetBoardJob(available)
                    CommonRailDeviceName.measurement ->measureJob = resetBoardJob(available)
                    else -> {}
                }
            }
        }catch (e:Exception){
            println("falha resetando placas")
        }
        joinAll(controlJob,measureJob)
        avaiableDeviceList.clear()
    }
    private suspend fun resetBoardJob(board: CommonRailDevice): Job {
        return GlobalScope.launch(context = Dispatchers.IO) {
            board.reset()
            board.disconnect()
        }
    }
    private suspend fun connectStateMachine() {
        deviceFactory.refreshDevicesList()
        val deviceList = deviceFactory.devicesList
        when (repositoryState) {
            CommonRailRepositoryState.SerialConnecting -> {//Fica neste estado até encontrar as 2 portas seriais do FTDI
                println("buscando 2 portas seriais, encontradas ${deviceList.count()}")

                if (deviceList.count() == 2) {
                    repositoryState = CommonRailRepositoryState.DeviceConnecting
                }

            }
            CommonRailRepositoryState.DeviceConnecting -> {//Fica neste estado até conseguir criar o controle e o som
                println("buscando dispositivos")
                if (createDevices())
                    repositoryState = CommonRailRepositoryState.Done
            }
            CommonRailRepositoryState.Done -> {
                //Fica neste estado durante a execussão do app até identificar uma desconexão

                if (deviceList.count() == 0) {
                    repositoryState = CommonRailRepositoryState.SerialConnecting
                }
                delay(1000)
            }

        }
    }

    fun controlCommands(): ControlCommands =
        controlDevice.commands as ControlCommands

    fun measurementCommands(): MeasurementCommands =
        measurementDevice.commands as MeasurementCommands


}