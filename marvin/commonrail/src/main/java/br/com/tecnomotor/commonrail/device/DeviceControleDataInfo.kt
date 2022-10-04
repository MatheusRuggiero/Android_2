package br.com.tecnomotor.commonrail.device

class DeviceControleDataInfo(
    var platformId: Int,
    version: String,
    revision: String
): DeviceDataInfo(version, revision) {
    override fun toString(): String {
        return "p${platformId} ${super.toString()}"
    }
}