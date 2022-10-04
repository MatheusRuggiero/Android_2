package br.com.tecnomotor.marvin.view.test.pump.data

import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.TypePointTestPump

class PontoDeTesteFill: PontoDeTeste {
    override fun obter(): PointTestPump {
        return PointTestPump(
            4,
            TypePointTestPump(6, "Fill", 0),
            "P4",
            currentExt1 = 1.4,
            frequencyExt1 = 180,
            typeRotation = 1,
            rotation = 500,
            pressureFeed = 0.1,
            timeWaitingMeasurement = 30,
            presetUser = true
        )
    }
}