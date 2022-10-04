package br.com.tecnomotor.marvin.model.valve


import br.com.tecnomotor.marvin.model.global.TypePlanTest
import br.com.tecnomotor.marvin.model.valve.pk.TranslateTypePlanTestValvePK
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
class TranslateTypePlanTestValve : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var language: String?= ""

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var typePlanTest: TypePlanTest = TypePlanTest()

    @SerializedName("traducaoTipoPlanoTesteSensorPK")
    @JsonProperty("traducaoTipoPlanoTesteSensorPK")
    var translatePlanTypePlanTestPK : TranslateTypePlanTestValvePK? = TranslateTypePlanTestValvePK()

    @SerializedName("traducao")
    @JsonProperty("traducao")
    var translate: String?= ""

    constructor()

    constructor(language: String?, typePlanTest: TypePlanTest, translate: String?) {
        this.language = language
        this.typePlanTest = typePlanTest
        this.translate = translate
    }

    constructor(id: Int, language: String?, typePlanTest: TypePlanTest, translate: String?) {
        this.id = id
        this.language = language
        this.typePlanTest = typePlanTest
        this.translate = translate
    }

    constructor(translatePlanTypePlanTestPK: TranslateTypePlanTestValvePK?, translate: String?) {
        this.translatePlanTypePlanTestPK = translatePlanTypePlanTestPK
        this.translate = translate
    }


}
