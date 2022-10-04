package br.com.tecnomotor.marvin.api.v1.interfaces.maxXid.injector

import br.com.tecnomotor.marvin.api.model.ValuesMaxXid
import br.com.tecnomotor.marvin.utils.constant.URL.ANDROID
import br.com.tecnomotor.marvin.utils.constant.URL.API
import br.com.tecnomotor.marvin.utils.constant.URL.CRMANAGER
import br.com.tecnomotor.marvin.utils.constant.URL.INJECTOR
import br.com.tecnomotor.marvin.utils.constant.URL.MAX_XID
import br.com.tecnomotor.marvin.utils.constant.URL.SEPARATOR
import br.com.tecnomotor.marvin.utils.constant.URL.V_1
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface InjectorMaxXidInterface {
    @Headers("Content-Type:application/octet-stream")
    @GET(CRMANAGER + SEPARATOR + API + SEPARATOR + ANDROID + SEPARATOR + V_1 + SEPARATOR + MAX_XID + SEPARATOR + INJECTOR)
    fun getMax(@QueryMap params: Map<String, String>): Call<ValuesMaxXid>
}