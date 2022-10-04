package br.com.tecnomotor.marvin.api.v1.model.pump

import br.com.tecnomotor.marvin.model.pump.TypePump
import br.com.tecnomotor.marvin.model.xId.pump.XidTypePump
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

class TypePumpSynchronize {
    @SerializedName("tipoBomba")
    @JsonProperty("tipoBomba")
    var typePump: TypePump = TypePump()

    @SerializedName("xidTypePump")
    @JsonProperty("xidTypePump")
    var xidTypePump: XidTypePump = XidTypePump()
}