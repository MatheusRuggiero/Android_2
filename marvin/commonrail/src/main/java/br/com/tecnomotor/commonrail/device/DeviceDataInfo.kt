package br.com.tecnomotor.commonrail.device

open class DeviceDataInfo(
    var version: String,
    var revision: String
) {
    override fun toString(): String {
        return "v${version}.${revision}"
    }

    fun getVersionString() = "v${version}.${revision}"
}