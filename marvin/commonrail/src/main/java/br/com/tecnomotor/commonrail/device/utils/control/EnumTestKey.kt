package br.com.tecnomotor.commonrail.device.utils.control

enum class EnumTestKey(val value: Byte) {
    RIGHT(0x1.toByte()),    //0x1
    YES(0x2.toByte()),      //0x2
    NO(0x4.toByte()),       //0x4
    LEFT(0x8.toByte()),     //0x8
    RETURN(0x80.toByte()),  //0x80
    F4(0x10.toByte()),      //0x10
    F3(0x20.toByte()),      //0x20
    F2(0x40.toByte()),      //0x40
    F1(0x11.toByte()),       //0x11
    START(0x01.toByte()),
    STOP(0x02.toByte()),
    SKIP(0x03.toByte()),
    CANCEL(0x04.toByte())

}