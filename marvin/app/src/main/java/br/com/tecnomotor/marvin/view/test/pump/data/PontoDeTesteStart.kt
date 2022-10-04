package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.TypePointTestPump

class PontoDeTesteStart: PontoDeTeste {
    override fun obter(): PointTestPump {
        return PointTestPump(
            7,
            TypePointTestPump(18, "Start", 0),
            "P7",
            currentExt1 = 0.4,
            frequencyExt1 = 180,
            typeRotation = 1,
            rotation = 180,
            pressureFeed = 0.1,
            flowMain = 10.2,
            toleranceFlowMain = 1.5,
            pressureTest = 0,
            timeWaitingMeasurement = 1800,
            presetUser = true,
            measureMain = true
        )
    }
}