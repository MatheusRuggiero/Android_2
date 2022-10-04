package br.com.tecnomotor.marvin.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
class DeviceAuthenticationParameter : Serializable {
    val deviceAuthentication: DeviceAuthentication = DeviceAuthentication()
    val productLicenseDevice: ProductLicenseDevice = ProductLicenseDevice()
    val xidDeviceAuthentication: XidDeviceAuthentication = XidDeviceAuthentication()
    val xidProductLicenseDevice: XidProductLicenseDevice = XidProductLicenseDevice()
}