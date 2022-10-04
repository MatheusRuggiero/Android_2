package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.TypePointTestPump

class PontoDeTesteWarmUp: PontoDeTeste {
    override fun obter(): PointTestPump {
        return PointTestPump(
            1,
            TypePointTestPump(24, "Warm Up", 0),
            "P1",
            currentExt1 = 0.4,
            typeRotation = 1,
            rotation = 2000,
            frequencyExt1 = 180,
            pressureFeed = 0.1,
            pressureTest = 250,
            timeWaitingMeasurement = 30,
            presetUser = true
        )
    }
}