package br.com.tecnomotor.commonrail.device.utils.control

/**
 * ********************************************************************************
 * defines for External tests
 * ********************************************************************************
 * #define   IDLE_PROCESS                                 0x00
 * #define   PROCESSO_VERIFICAR_PERIFERICOS               0x01
 * #define   PROCESSO_VERIFICAR_INJETORES                 0x02
 * #define   PROCESSO_LIGAR_BOMBA_TANQUE                  0x03
 * #define   PROCESSO_LIGAR_BOMBA_ALTA                    0x04
 * #define   PROCESSO_DESLIGA_PRESSAO_INJ                 0x05
 * #define   PROCESSO_CONDICIONAR_FLUIDO                  0x06
 * #define   PROCESSO_ABAIXAR_PRESSAO                     0x07
 * #define   PROCESSO_DESLIGAR_SISTEMA_PRESSAO            0x08
 * #define   PROCESSO_DESLIGAR_SISTEMA                    0x09
 * #define   PROCESSO_AGUARDAR_COMANDO                    0x0A
 * #define   PROCESSO_TESTAR_INJETORES                    0x0D
 * #define   PROCESSO_TESTAR_SENSOR                       0x10
 * #define   PROCESSO_VERIFICAR_VALVULA                   0x11
 * #define   PROCESSO_TESTAR_VALVULA                      0x13
 * #define   PROCESSO_TESTAR_BOMBA                        0x14
 * #define   PROCESSO_TESTAR_ESTANQUE_BOMBA               0x15
 * #define   PROCESSO_CONDICIONAR_FLUIDO_EXTERN           0x16
 * #define   PROCESSO_VERIFICAR_VALVULA_DRV_1             0x17
 * #define   PROCESSO_VERIFICAR_VALVULA_DRV_2             0x18
 * #define   PROCESSO_VERIFICAR_VALVULA_EXT_1             0x19
 * #define   PROCESSO_VERIFICAR_VALVULA_EXT_2             0x1A
 * #define   PROCESSO_VERIFICAR_ROTACAO_PADRAO            0x20
 * #define   PROCESSO_VERIFICAR_ROTACAO_TESTE             0x21
 */
enum class EnumTestProcessForControl(val value: Byte) {
    NONE(0x00.toByte()),
    CheckPeripherals(0x01.toByte()),
    CheckInjectors(0x02.toByte()),
    TurnOnTankPump(0x03.toByte()),
    TurnOnHighPump(0x04.toByte()),
    TurnOffInjectionPressure(0x05.toByte()),
    ConditionFluid(0x06.toByte()),
    LowerThePressure(0x07.toByte()),
    TurnOffPressureSystem(0x08.toByte()),
    WaitForTheCommand(0x0A.toByte()),
    TestInjectores(0x0D.toByte()),
    TestSensors(0x10.toByte()),
    CheckValves(0x11.toByte()),
    TestValves(0x13.toByte()),
    TestPumps(0x14.toByte()),
    TestPumpTightness(0x15.toByte()),
    ConditionExternalFluid(0x16.toByte()),
    CheckValveDRV1(0x17.toByte()),
    CheckValveDRV2(0x18.toByte()),
    CheckValveEXT1(0x19.toByte()),
    CheckValveExt2(0x1A.toByte()),
    CheckTestRotation(0x21.toByte());

    companion object {
        fun valueOf(value: Byte): EnumTestProcessForControl {
            var res = NONE
            values().forEach {
                if (it.value == value)
                    res = it
            }
            return res
        }
    }
}