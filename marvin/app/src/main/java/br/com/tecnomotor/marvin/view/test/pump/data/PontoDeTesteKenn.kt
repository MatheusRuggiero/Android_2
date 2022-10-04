package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.TypePointTestPump

class PontoDeTesteKenn: PontoDeTeste {
    override fun obter(): PointTestPump {
        return PointTestPump(
            3,
            TypePointTestPump(11, "Kenn", 0),
            "P3",
            currentExt1 = 0.4,
            frequencyExt1 = 180,
            typeRotation = 1,
            rotation = 3500,
            pressureFeed = 0.1,
            flowMain = 205.6,
            toleranceFlowMain = 22.7,
            flowReturn = 40.0,
            toleranceFlowReturn = 16.0,
            pressureTest = 500,
            timeWaitingMeasurement = 30,
            presetUser = true,
            measureMain = true,
            measureReturn = true
        )
    }
}