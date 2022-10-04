package br.com.tecnomotor.marvin.api.v1.interfaces.lotXid.global

import br.com.tecnomotor.marvin.api.v1.model.global.VersionSynchronize
import br.com.tecnomotor.marvin.utils.constant.URL.ANDROID
import br.com.tecnomotor.marvin.utils.constant.URL.API
import br.com.tecnomotor.marvin.utils.constant.URL.CRMANAGER
import br.com.tecnomotor.marvin.utils.constant.URL.LOT_XID
import br.com.tecnomotor.marvin.utils.constant.URL.SEPARATOR
import br.com.tecnomotor.marvin.utils.constant.URL.VERSION
import br.com.tecnomotor.marvin.utils.constant.URL.V_1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface VersionLotXidInterface {
    @Headers("Content-Type:application/octet-stream")
    @GET(CRMANAGER + SEPARATOR + API + SEPARATOR + ANDROID + SEPARATOR + V_1 + SEPARATOR + VERSION + SEPARATOR + LOT_XID)
    fun getLotXid(@QueryMap params: Map<String, String>): Call<List<VersionSynchronize>>
}