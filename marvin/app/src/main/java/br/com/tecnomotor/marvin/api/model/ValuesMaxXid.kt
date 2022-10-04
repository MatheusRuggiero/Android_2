package br.com.tecnomotor.marvin.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.codehaus.jackson.annotate.JsonIgnoreProperties
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class ValuesMaxXid : Serializable {
    var xid : String? = ""
}