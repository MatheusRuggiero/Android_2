package br.com.tecnomotor.marvin.model.injector

import br.com.tecnomotor.marvin.model.injector.pk.TranslateTypePointTestInjectorPK
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
class TranslateTypePointTestInjector : Serializable {

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var id: Int = 0

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var language: String? = ""

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    var typePointTestInjector : TypePointTestInjector? = TypePointTestInjector()

    @SerializedName("traducaoTipoPontoTesteInjetorPK")
    @JsonProperty("traducaoTipoPontoTesteInjetorPK")
    var translateTypePointInjectorTestPK: TranslateTypePointTestInjectorPK? = TranslateTypePointTestInjectorPK()

    @SerializedName("traducao")
    @JsonProperty("traducao")
    var translate: String? = ""

    constructor()
    constructor(id: Int, language: String?, typePointTestInjector: TypePointTestInjector?, translate: String?) {
        this.id = id
        this.language = language
        this.typePointTestInjector = typePointTestInjector
        this.translate = translate
    }

    constructor(language: String?, typePointTestInjector: TypePointTestInjector?, translate: String?) {
        this.language = language
        this.typePointTestInjector = typePointTestInjector
        this.translate = translate
    }

    constructor(translateTypePointInjectorTestPK: TranslateTypePointTestInjectorPK?) {
        this.translateTypePointInjectorTestPK = translateTypePointInjectorTestPK
    }


}
