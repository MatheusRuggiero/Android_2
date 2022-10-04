package br.com.tecnomotor.marvin.model.pump

import br.com.tecnomotor.marvin.model.pump.pk.TranslateTypePointTestPumpPK
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class TranslateTypePointTestPump : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var language: String? = ""

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var typePoint: Int = 0

    @SerializedName("traducaoTipoPontoTesteBombaPK")
    @JsonProperty("traducaoTipoPontoTesteBombaPK")
    var translateTypePointTestPumpPK: TranslateTypePointTestPumpPK? = TranslateTypePointTestPumpPK()

    @SerializedName("traducao")
    @JsonProperty("traducao")
    var translate: String? = ""

    constructor()
    constructor(id: Int, language: String?, typePoint: Int, translate: String?) {
        this.id = id
        this.language = language
        this.typePoint = typePoint
        this.translate = translate
    }

    constructor(language: String?, typePoint: Int, translate: String?) {
        this.language = language
        this.typePoint = typePoint
        this.translate = translate
    }

    constructor(translateTypePointTestPumpPK: TranslateTypePointTestPumpPK?, translate: String?) {
        this.translateTypePointTestPumpPK = translateTypePointTestPumpPK
        this.translate = translate
    }


}