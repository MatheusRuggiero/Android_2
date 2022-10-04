package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.TypePointTestPump

class PontoDeTesteEfficiency(): PontoDeTeste{
    override fun obter(): PointTestPump {
        return PointTestPump(
            6,
            TypePointTestPump(4, "Efficiency", 0),
            "P6",
            currentExt1 = 0.4,
            frequencyExt1 = 180,
            typeRotation = 1,
            rotation = 1000,
            pressureFeed = 0.1,
            flowMain = 58.8,
            toleranceFlowMain = 6.4,
            pressureTest = 1600,
            timeWaitingMeasurement = 1800,
            presetUser = true,
            measureMain = true
        )
    }
}