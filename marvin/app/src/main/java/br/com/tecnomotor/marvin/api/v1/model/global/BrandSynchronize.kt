package br.com.tecnomotor.marvin.api.v1.model.global

import br.com.tecnomotor.marvin.dao.entities.global.XidBrandEntity
import br.com.tecnomotor.marvin.model.global.Brand
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
class BrandSynchronize : Serializable {

    @SerializedName("marca")
    @JsonProperty("marca")
    var brand = Brand()

    @SerializedName("xidBrand")
    @JsonProperty("xidBrand")
    var xidBrand = XidBrandEntity()
}