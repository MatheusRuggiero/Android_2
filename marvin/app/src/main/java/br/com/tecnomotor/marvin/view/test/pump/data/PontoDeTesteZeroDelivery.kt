package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.TypePointTestPump

class PontoDeTesteZeroDelivery: PontoDeTeste {
    override fun obter(): PointTestPump {
        return PointTestPump(
            5,
            TypePointTestPump(26, "Zero Delivery", 0),
            "P5",
            currentExt1 = 1.62,
            frequencyExt1 = 180,
            typeRotation = 1,
            rotation = 3500,
            pressureFeed = 0.1,
            flowMain = 1.0,
            toleranceFlowMain = 1.0,
            pressureTest = 0,
            timeWaitingMeasurement = 1800,
            presetUser = true,
            measureMain = true
        )
    }
}