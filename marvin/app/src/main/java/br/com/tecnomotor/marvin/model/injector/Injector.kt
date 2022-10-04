package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.commonrail.device.commands.injector.EnumInjectorType
import br.com.tecnomotor.marvin.model.global.Brand
import br.com.tecnomotor.marvin.model.global.Revision
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
class Injector : Serializable {
    @SerializedName("id")
    @JsonProperty("id")
    var id: Int = 0

    @SerializedName("codigo")
    @JsonProperty("codigo")
    var code: String? = ""

    @SerializedName("padrao")
    @JsonProperty("padrao")
    var standard: Boolean? = false

    @SerializedName("marca")
    @JsonProperty("marca")
    var brand: Brand? = Brand()

    @SerializedName("aplicacao")
    @JsonProperty("aplicacao")
    var application: String? = ""

    @SerializedName("tipo")
    @JsonProperty("tipo")
    var type = ""

    @SerializedName("tipoId")
    @JsonProperty("tipoId")
    var typeId = 0

    @SerializedName("adaptadorPressao")
    @JsonProperty("adaptadorPressao")
    var adaptPressure: String? = ""

    @SerializedName("adaptadorRetorno")
    @JsonProperty("adaptadorRetorno")
    var adaptReturn: String? = ""

    @SerializedName("adaptadorConector")
    @JsonProperty("adaptadorConector")
    var adaptConnector: String? = ""

    @SerializedName("resistenciaMinima")
    @JsonProperty("resistenciaMinima")
    var resistanceMinimum: Double? = 0.0

    @SerializedName("resistenciaMaxima")
    @JsonProperty("resistenciaMaxima")
    var resistanceMaximum: Double? = 0.0

    @SerializedName("resistenciabMinima")
    @JsonProperty("resistenciabMinima")
    var resistanceMinimumB: Double? = 0.0

    @SerializedName("resistenciabMaxima")
    @JsonProperty("resistenciabMaxima")
    var resistanceMaximumB: Double? = 0.0

    @SerializedName("token")
    @JsonProperty("token")
    var token: String? = ""

    var revisionNumber: Int = 0

    @SerializedName("revisao")
    @JsonProperty("revisao")
    var revision: Revision? = Revision()

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var revisionInjector: RevisionInjector = RevisionInjector()

    @SerializedName("deleted")
    @JsonProperty("deleted")
    var deleted: Boolean? = false

    constructor()

    constructor(
        id: Int,
        code: String?,
        standard: Boolean?,
        brand: Brand?,
        application: String?,
        typeId: Int,
        adaptPressure: String?,
        adaptReturn: String?,
        adaptConnector: String?,
        resistanceMinimum: Double?,
        resistanceMaximum: Double?,
        resistanceMinimumB: Double?,
        resistanceMaximumB: Double?,
        token: String?,
        revision: Revision?,
        revisionInjector: RevisionInjector,
        deleted: Boolean?
    ) {
        this.id = id
        this.code = code
        this.standard = standard
        this.brand = brand
        this.application = application
        this.type = EnumInjectorType.valueOfDB(typeId).toString()
        this.typeId = typeId
        this.adaptPressure = adaptPressure
        this.adaptReturn = adaptReturn
        this.adaptConnector = adaptConnector
        this.resistanceMinimum = resistanceMinimum
        this.resistanceMaximum = resistanceMaximum
        this.resistanceMinimumB = resistanceMinimumB
        this.resistanceMaximumB = resistanceMaximumB
        this.token = token
        this.revision = revision
        this.revisionInjector = revisionInjector
        this.deleted = deleted
    }


}