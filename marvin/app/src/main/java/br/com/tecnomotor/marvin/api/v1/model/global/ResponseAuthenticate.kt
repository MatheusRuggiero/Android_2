package br.com.tecnomotor.marvin.api.v1.model.global

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import javax.xml.bind.annotation.XmlRootElement


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class ResponseAuthenticate {
    var id: Int = 0
    var subject: String? = ""
    var text: String? = ""
}