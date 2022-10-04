package br.com.tecnomotor.marvin.api.v1.interfaces.lotXid.injector

import br.com.tecnomotor.marvin.api.v1.model.injector.TypePointInjectorTestSynchronize
import br.com.tecnomotor.marvin.utils.constant.URL
import br.com.tecnomotor.marvin.utils.constant.URL.ANDROID
import br.com.tecnomotor.marvin.utils.constant.URL.API
import br.com.tecnomotor.marvin.utils.constant.URL.LOT_XID
import br.com.tecnomotor.marvin.utils.constant.URL.SEPARATOR
import br.com.tecnomotor.marvin.utils.constant.URL.TYPE_POINT_INJECTOR_TEST
import br.com.tecnomotor.marvin.utils.constant.URL.V_1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface TypePointInjectorTestLotXidInterface {
    @Headers("Content-Type:application/octet-stream")
    @GET(URL.CRMANAGER + SEPARATOR + API + SEPARATOR + ANDROID + SEPARATOR + V_1 + SEPARATOR + TYPE_POINT_INJECTOR_TEST + SEPARATOR + LOT_XID)
    fun getLotXid(@QueryMap params: Map<String, String>): Call<List<TypePointInjectorTestSynchronize>>
}