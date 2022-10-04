package br.com.tecnomotor.marvin.api.v1.interfaces.license

import br.com.tecnomotor.marvin.api.model.DeviceAuthenticationParameter
import br.com.tecnomotor.marvin.utils.constant.URL.ANDROID
import br.com.tecnomotor.marvin.utils.constant.URL.API
import br.com.tecnomotor.marvin.utils.constant.URL.CRMANAGER
import br.com.tecnomotor.marvin.utils.constant.URL.LICENSE
import br.com.tecnomotor.marvin.utils.constant.URL.NEW_LICENSE
import br.com.tecnomotor.marvin.utils.constant.URL.SEPARATOR
import br.com.tecnomotor.marvin.utils.constant.URL.V_1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface NewLicenseInterface {
    @Headers("Content-Type:application/octet-stream")
    @GET(CRMANAGER + SEPARATOR + API + SEPARATOR + ANDROID + SEPARATOR + V_1 + SEPARATOR + LICENSE + SEPARATOR + NEW_LICENSE)
    fun getNewLicense(@QueryMap params: Map<String, String>): Call<DeviceAuthenticationParameter>
}