package br.com.tecnomotor.marvin.model.global

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class Client : Serializable {
    var id: Int = 0
    var name: String? = ""
    var address: String? = ""
    var number: String? = ""
    var district: String? = ""
    var cep: String? = ""
    var city: String? = ""
    var state: String? = ""
    var telephone: String? = ""
    var cell: String? = ""
    var fax: String? = ""
    var removed: Boolean? = false


    constructor(
        id: Int,
        name: String?,
        address: String?,
        number: String?,
        district: String?,
        cep: String?,
        city: String?,
        state: String?,
        telephone: String?,
        cell: String?,
        fax: String?,
        removed: Boolean?) {
        this.id = id
        this.name = name
        this.address = address
        this.number = number
        this.district = district
        this.cep = cep
        this.city = city
        this.state = state
        this.telephone = telephone
        this.cell = cell
        this.fax = fax
        this.removed = removed
    }

    constructor()
}
