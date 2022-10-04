package br.com.tecnomotor.marvin.api.v1.model.pump

import br.com.tecnomotor.marvin.model.pump.TypePointTestPump
import br.com.tecnomotor.marvin.model.xId.pump.XidTypePointTestPump
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

class TypePointTestPumpSynchronize {

    @SerializedName("tipoPontoTesteBomba")
    @JsonProperty("tipoPontoTesteBomba")
    var typePointTestPump: TypePointTestPump = TypePointTestPump()

    @SerializedName("xidTypePointTestPump")
    @JsonProperty("xidTypePointTestPump")
    var xidTypePointTestPump: XidTypePointTestPump = XidTypePointTestPump()
}