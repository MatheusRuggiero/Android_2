package br.com.tecnomotor.commonrail.device.utils.measurement

enum class Channel(val id: Int) {
    CH1(1),
    CH2(2),
    DGT(3);

    companion object {
        fun valueOf(id: Int): Channel {
            var res = CH1
            Channel.values().forEach {
                if (it.id == id)
                    res = it
            }
            return res
        }
    }
}