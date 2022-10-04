package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.TypePointTestPump

class PontoDeTesteVisualCheck: PontoDeTeste {
    override fun obter(): PointTestPump {
        return PointTestPump(
            2,
            TypePointTestPump(23, "Visual Check", 0),
            "P2",
            currentExt1 = 1.0,
            typeRotation = 1,
            rotation = 2000,
            frequencyExt1 = 180,
            pressureFeed = 0.1,
            pressureTest = 300,
            timeWaitingMeasurement = 30,
            presetUser = true
        )
    }
}