package br.com.tecnomotor.marvin.model.pump.pk

import br.com.tecnomotor.marvin.model.pump.TypePointTestPump
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class TranslateTypePointTestPumpPK : Serializable {

    @SerializedName("tipoPontoTesteBomba")
    @JsonProperty("tipoPontoTesteBomba")
    var typePointTestPump = TypePointTestPump()

    @SerializedName("idioma")
    @JsonProperty("idioma")
    var language = ""
}