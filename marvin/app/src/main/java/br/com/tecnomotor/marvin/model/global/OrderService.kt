package br.com.tecnomotor.marvin.model.global

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class OrderService : Serializable {

    var id: Int = 0
    var code: String?= ""
    var plate: String?= ""
    var dateCreation: Date = Date()
    var hourCreation: String?= ""
    var removed: Boolean? = false
    var client: Client = Client()

    constructor()
    constructor(id: Int, code: String?, plate: String?, dateCreation: Date, hourCreation: String?, removed: Boolean?, client: Client) {
        this.id = id
        this.code = code
        this.plate = plate
        this.dateCreation = dateCreation
        this.hourCreation = hourCreation
        this.removed = removed
        this.client = client
    }
}

