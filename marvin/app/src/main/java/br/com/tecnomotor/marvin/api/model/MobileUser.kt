package br.com.tecnomotor.marvin.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class MobileUser : Serializable{
    var userName : String? = ""
    var password : String? = ""

    constructor(userName: String?, password: String?) {
        this.userName = userName
        this.password = password
    }

    constructor()


}