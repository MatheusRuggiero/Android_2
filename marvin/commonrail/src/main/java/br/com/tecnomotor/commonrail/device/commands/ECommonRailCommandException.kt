package br.com.tecnomotor.commonrail.device.commands

import android.content.Context
import br.com.tecnomotor.commonrail.R


class ECommonRailCommandException(private val commandError: Int): Exception() {

    companion object {

        /**
         * ERROS PLACA CONTROLE
         */
// Defines for Critical Errors < 85 (Locks the Machine)
        const val ERROR_NOT_ERROR                       = 0
        const val ERROR_PROBLEMA_BAIXAR_PRESSAO         = 1 // 0X01
        const val ERROR_BAIXA_PRESSAO_RAIL              = 2 // 0X02
        const val ERROR_TANQUE_ABAIXO_NIVEL             = 3 // 0X03
        const val ERROR_FALHA_VALVULA_DRV_RAIL          = 4 // 0X04
        const val ERROR_FALHA_SENSOR_PRESSAO_RAIL       = 5
        const val ERROR_FAIL_INIT_METER                 = 6
        const val ERROR_TEMPERATURA_ALTA                = 7
        const val ERROR_TEMPERATURA_BAIXA               = 8

        const val ERROR_VALV_EXT1_CORRENTE_MUITO_ALTA   = 16 // 0X10
        const val ERROR_VALV_EXT1_CORRENTE_ABAIXO       = 17 // 0X11
        const val ERROR_VALV_EXT1_CORRENTE_ACIMA        = 18 // 0X12
        const val ERROR_VALV_EXT1_CORRENTE_DESLIGAR     = 19 // 0X13

        const val ERROR_VALV_EXT2_CORRENTE_MUITO_ALTA   = 32 // 0X20
        const val ERROR_VALV_EXT2_CORRENTE_ABAIXO       = 33 // 0X21
        const val ERROR_VALV_EXT2_CORRENTE_ACIMA        = 34 // 0X22
        const val ERROR_VALV_EXT2_CORRENTE_DESLIGAR     = 35 // 0X23

        const val ERROR_VALV_DRV1_AUSENTE               = 48 // 0X30
        const val ERROR_VALV_DRV2_AUSENTE               = 49 // 0X31
        const val ERROR_VALV_EXT1_AUSENTE               = 50 // 0X32
        const val ERROR_VALV_EXT2_AUSENTE               = 51 // 0X33

        const val ERROR_ROTACAO_MUITO_ALTA              = 64 // 0x40
        const val ERROR_ROTACAO_ZERADA                  = 65 // 0x41
        const val ERROR_ROTACAO_ABAIXO                  = 66 // 0x42
        const val ERROR_ROTACAO_ACIMA                   = 67 // 0x43

        const val ERROR_PRESSAO_MUITO_ALTA              = 92 // 0x5C
        const val ERROR_PRESSAO_ABAIXO                  = 93 // 0x5D
        const val ERROR_PRESSAO_ABAIXAR                 = 94 // 0x5E
        const val ERROR_PRESSAO_ACIMA                   = 95 // 0x5F

        const val ERROR_CRITICO                         = 133 // 0X85 mascara para erros Criticos (a maquina vai parar ou já parou)

// Defines for Emergencial Errors >= 86 (Doesn't lock the machine, but reset)
        const val ERROR_TAMPA_ABERTA                    = 134 // 0x86
        const val ERROR_PARADA_EMERGENCIA               = 135 // 0x87
        const val ERROR_NAO_IDENTIFICADO                = 136 // 0x88
        const val ERROR_NAO_DISPONIVEL                  = 137 // 0x89
        const val ERROR_PARAMETRIZATION                 = 138 // 0x8A
        const val ERROR_FALHA_PROCESSO_AQUECIMENTO      = 144 // 0x90
        const val ERROR_ERROR_PRESSAO                   = 145 // 0x91
        const val ERROR_USO_INDEVIDO_ACESSORIO          = 146 // 0x92

// Defines for Non Critical Errors >= 171  (Doesn't locks the machine and the tests keeps running)
        const val ERROR_NAO_CRITICO                     = 171 // 0xAB   // mascara para erros (somente envia aviso ao usr que est� ocorrendo)
        const val ERROR_LIMITE_HORIMETRO_FILTRO         = 171 // 0xAB
        const val ERROR_LIMITE_HORIMETRO_FLUIDO         = 172 // 0xAC
        const val ERROR_INJETOR_NAO_PRESENTE            = 173 // 0xAD
        const val ERROR_GRAVA_FLASH                     = 174 // 0xAE
        const val ERROR_PROCESSO_TIMEOUT                = 175 // 0xAF
        const val ERROR_FALHA_VALVULA_DRV_TESTE         = 176 // 0xB0

// Defines for warning messages > 220 - warning
        const val ERROR_MESSAGEM_WARNING                = 220 // 0xDC	   // mascara para mensagens de aviso
        const val ERROR_TANQUE_NIVEL_INTERMITENTE       = 221 // 0xDD
        const val ERROR_LIMITE_CHECK_IN_FILTRO_LINHA    = 222 // 0xDE
        const val ERROR_LIMITE_CHANGE_IN_FILTRO_LINHA   = 223 // 0xDF

        /**
         * ERROS PLACA SOM
         */
        const val REMOTE_ERROR_SETUP_FREQUENCY          = 163 // 0xA3
        const val REMOTE_ERROR_SETUP_TIMEOUT            = 164 // 0xA4
        const val REMOTE_ERROR_SETUP_CM_CH1             = 166 // 0xA6
        const val REMOTE_ERROR_SETUP_CM_CH2             = 167 // 0xA7
        const val REMOTE_ERROR_SETUP_DOUBLE_CH          = 168 // 0xA8

        const val REMOTE_ERROR_CALIBRATION_CH1          = 177 // 0xB1
        const val REMOTE_ERROR_CALIBRATION_CH2          = 178 // 0xB2
        const val REMOTE_ERROR_UNCONNECTED_MODULE_CH1   = 179 // 0xB3
        const val REMOTE_ERROR_UNCONNECTED_MODULE_CH2   = 180 // 0xB4
        const val REMOTE_ERROR_UNCONNECTED_PWM_CH1      = 181 // 0xB5
        const val REMOTE_ERROR_UNCONNECTED_PWM_CH2      = 182 // 0xB6

        const val REMOTE_ERROR_LEAK_INJECTION_FAIL      = 192 // 0xC0
        const val REMOTE_ERROR_MEASURE_TIMEOUT_CH1      = 193 // 0xC1
        const val REMOTE_ERROR_MEASURE_TIMEOUT_CH2      = 194 // 0xC2
        const val REMOTE_ERROR_EMPTYING_TIMEOUT_CH1     = 195 // 0xC3
        const val REMOTE_ERROR_EMPTYING_TIMEOUT_CH2     = 196 // 0xC4
        const val REMOTE_ERROR_LEAK_BACK_FLOW_FAIL      = 197 // 0xC5
        const val REMOTE_ERROR_LOW_COEF_DTRMTN_CH1      = 198 // 0xC6
        const val REMOTE_ERROR_LOW_COEF_DTRMTN_CH2      = 199 // 0xC7
        const val REMOTE_ERROR_DEAD_VOLUME_TIMEOUT_CH1  = 200 // 0xC8
        const val REMOTE_ERROR_DEAD_VOLUME_TIMEOUT_CH2  = 201 // 0xC9

        const val REMOTE_ERROR_EMPTY_FLASH              = 208 // 0xD0
        const val REMOTE_ERROR_LEAKAGE_DRAIN_CH1        = 209 // 0xD1
        const val REMOTE_ERROR_LEAKAGE_DRAIN_CH2        = 210 // 0xD2
        const val REMOTE_ERROR_EXTRAPOLATED_VECTORS_CH1 = 211 // 0xD3
        const val REMOTE_ERROR_EXTRAPOLATED_VECTORS_CH2 = 212 // 0xD4
        const val REMOTE_ERROR_CONTAMINATED_OIL         = 213 // 0xD5
        const val REMOTE_ERROR_TOP_LEAK                 = 214 // 0xD6
        const val REMOTE_ERROR_OVER_TEMPERATURE         = 215 // 0xD7

        /**
         * ERROS GERAIS
         */
        const val ERROR_COMUNICACAO_EXCED_TENT        = 2000
        const val ERROR_LIGARBOMBAS_EXCED_TENT        = 2001
        const val ERROR_COMUNICACAO_USB               = 5000
        const val ERROR_MODULO_CONTROLE_NAO_CONECTADO = 5001
        const val ERROR_MODULO_MEDICAO_NAO_CONECTADO  = 5002

    }

    override val message: String
        get() = commandError.toString()

    fun getCommandError(): Int = this.commandError

    fun getLocalizedMessage(context: Context): String {
        when(commandError) {
            ERROR_NOT_ERROR                      -> return ""

            /**
             * ERROS PLACA CONTROLE
             */
            ERROR_PROBLEMA_BAIXAR_PRESSAO        -> return context.getString(R.string.MSG_ERROR_01_PROBLEMA_BAIXAR_PRESSAO)
            ERROR_BAIXA_PRESSAO_RAIL             -> return context.getString(R.string.MSG_ERROR_02_BAIXA_PRESSAO_RAIL)
            ERROR_TANQUE_ABAIXO_NIVEL            -> return context.getString(R.string.MSG_ERROR_03_TANQUE_ABERTO_NIVEL)
            ERROR_FALHA_VALVULA_DRV_RAIL         -> return context.getString(R.string.MSG_ERROR_04_FALHA_VALVULA_DRV)
            ERROR_FALHA_SENSOR_PRESSAO_RAIL      -> return context.getString(R.string.MSG_ERROR_05_FALHA_SENSOR_PRESSAO_RAIL)
            ERROR_FAIL_INIT_METER                -> return context.getString(R.string.MSG_ERROR_06_FAIL_INIT_METER)
            ERROR_TEMPERATURA_ALTA               -> return context.getString(R.string.MSG_ERROR_07_TEMPERATURA_ALTA)
            ERROR_TEMPERATURA_BAIXA              -> return context.getString(R.string.MSG_ERROR_08_TEMPERATURA_BAIXA)

            ERROR_VALV_EXT1_CORRENTE_MUITO_ALTA  -> return context.getString(R.string.MSG_ERROR_16_VALV_EXT1_CORRENTE_MUITO_ALTA)
            ERROR_VALV_EXT1_CORRENTE_ABAIXO      -> return context.getString(R.string.MSG_ERROR_17_VALV_EXT1_CORRENTE_ABAIXO)
            ERROR_VALV_EXT1_CORRENTE_ACIMA       -> return context.getString(R.string.MSG_ERROR_18_VALV_EXT1_CORRENTE_ACIMA)
            ERROR_VALV_EXT1_CORRENTE_DESLIGAR    -> return context.getString(R.string.MSG_ERROR_19_VALV_EXT1_CORRENTE_DESLIGAR)

            ERROR_VALV_EXT2_CORRENTE_MUITO_ALTA  -> return context.getString(R.string.MSG_ERROR_32_VALV_EXT2_CORRENTE_MUITO_ALTA)
            ERROR_VALV_EXT2_CORRENTE_ABAIXO      -> return context.getString(R.string.MSG_ERROR_33_VALV_EXT2_CORRENTE_ABAIXO)
            ERROR_VALV_EXT2_CORRENTE_ACIMA       -> return context.getString(R.string.MSG_ERROR_34_VALV_EXT2_CORRENTE_ACIMA)
            ERROR_VALV_EXT2_CORRENTE_DESLIGAR    -> return context.getString(R.string.MSG_ERROR_35_VALV_EXT2_CORRENTE_DESLIGAR)

            ERROR_VALV_DRV1_AUSENTE              -> return context.getString(R.string.MSG_ERROR_48_VALV_DRV1_AUSENTE)
            ERROR_VALV_DRV2_AUSENTE              -> return context.getString(R.string.MSG_ERROR_49_VALV_DRV2_AUSENTE)
            ERROR_VALV_EXT1_AUSENTE              -> return context.getString(R.string.MSG_ERROR_50_VALV_EXT1_AUSENTE)
            ERROR_VALV_EXT2_AUSENTE              -> return context.getString(R.string.MSG_ERROR_51_VALV_EXT2_AUSENTE)

            ERROR_ROTACAO_MUITO_ALTA             -> return context.getString(R.string.MSG_ERROR_64_ROTACAO_MUITO_ALTA)
            ERROR_ROTACAO_ZERADA                 -> return context.getString(R.string.MSG_ERROR_65_ROTACAO_ZERADA)
            ERROR_ROTACAO_ABAIXO                 -> return context.getString(R.string.MSG_ERROR_66_ROTACAO_ABAIXO)
            ERROR_ROTACAO_ACIMA                  -> return context.getString(R.string.MSG_ERROR_67_ROTACAO_ACIMA)

            ERROR_PRESSAO_MUITO_ALTA             -> return context.getString(R.string.MSG_ERROR_92_PRESSAO_MUITO_ALTA)
            ERROR_PRESSAO_ABAIXO                 -> return context.getString(R.string.MSG_ERROR_93_PRESSAO_ABAIXO)
            ERROR_PRESSAO_ABAIXAR                -> return context.getString(R.string.MSG_ERROR_94_PRESSAO_ABAIXAR)
            ERROR_PRESSAO_ACIMA                  -> return context.getString(R.string.MSG_ERROR_95_PRESSAO_ACIMA)

            ERROR_CRITICO                        -> return context.getString(R.string.MSG_ERROR_133_CRITICO)

            ERROR_TAMPA_ABERTA                   -> return context.getString(R.string.MSG_ERROR_134_TAMPA_ABERTA)
            ERROR_PARADA_EMERGENCIA              -> return context.getString(R.string.MSG_ERROR_135_PARADA_EMERGENCIA)
            ERROR_NAO_IDENTIFICADO               -> return context.getString(R.string.MSG_ERROR_136_NAO_IDENTIFICADO)
            ERROR_NAO_DISPONIVEL                 -> return context.getString(R.string.MSG_ERROR_137_NAO_DISPONIVEL)
            ERROR_PARAMETRIZATION                -> return context.getString(R.string.MSG_ERROR_138_PARAMETRIZATION)
            ERROR_FALHA_PROCESSO_AQUECIMENTO     -> return context.getString(R.string.MSG_ERROR_144_FALHA_PROCESSO_AQUECIMENTO)
            ERROR_ERROR_PRESSAO                  -> return context.getString(R.string.MSG_ERROR_145_ERROR_PRESSAO)
            ERROR_USO_INDEVIDO_ACESSORIO         -> return context.getString(R.string.MSG_ERROR_146_ERROR_USO_INDEVIDO_ACESSORIO)

            ERROR_LIMITE_HORIMETRO_FILTRO        -> return context.getString(R.string.MSG_ERROR_171_LIMITE_HORIMETRO_FILTRO)
            ERROR_LIMITE_HORIMETRO_FLUIDO        -> return context.getString(R.string.MSG_ERROR_172_LIMITE_HORIMETRO_FLUIDO)
            ERROR_INJETOR_NAO_PRESENTE           -> return context.getString(R.string.MSG_ERROR_173_INJETOR_NAO_PRESENTE)
            ERROR_GRAVA_FLASH                    -> return context.getString(R.string.MSG_ERROR_174_GRAVA_FLASH)
            ERROR_PROCESSO_TIMEOUT               -> return context.getString(R.string.MSG_ERROR_175_PROCESSO_TIMEOUT)
            ERROR_FALHA_VALVULA_DRV_TESTE        -> return context.getString(R.string.MSG_ERROR_176_FALHA_VALVULA_DRV_TESTE)

            ERROR_MESSAGEM_WARNING               -> return context.getString(R.string.MSG_ERROR_220_ERROR_MESSAGEM_WARNING)
            ERROR_TANQUE_NIVEL_INTERMITENTE      -> return context.getString(R.string.MSG_ERROR_221_ERROR_TANQUE_NIVEL_INTERMITENTE)
            ERROR_LIMITE_CHECK_IN_FILTRO_LINHA   -> return context.getString(R.string.MSG_ERROR_222_ERROR_LIMITE_CHECK_IN_FILTRO_LINHA)
            ERROR_LIMITE_CHANGE_IN_FILTRO_LINHA  -> return context.getString(R.string.MSG_ERROR_223_ERROR_LIMITE_CHANGE_IN_FILTRO_LINHA)

            /**
             * ERROS PLACA SOM
             */
            REMOTE_ERROR_SETUP_FREQUENCY         -> return context.getString(R.string.MSG_ERROR_163_SETUP_FREQUENCY)
            REMOTE_ERROR_SETUP_TIMEOUT           -> return context.getString(R.string.MSG_ERROR_164_SETUP_TIMEOUT)
            REMOTE_ERROR_SETUP_CM_CH1            -> return context.getString(R.string.MSG_ERROR_166_SETUP_CM_CH1)
            REMOTE_ERROR_SETUP_CM_CH2            -> return context.getString(R.string.MSG_ERROR_167_SETUP_CM_CH2)
            REMOTE_ERROR_SETUP_DOUBLE_CH         -> return context.getString(R.string.MSG_ERROR_168_SETUP_DOUBLE_CH)

            REMOTE_ERROR_CALIBRATION_CH1         -> return context.getString(R.string.MSG_ERROR_177_CALIBRATION_CH1)
            REMOTE_ERROR_CALIBRATION_CH2         -> return context.getString(R.string.MSG_ERROR_178_CALIBRATION_CH2)
            REMOTE_ERROR_UNCONNECTED_MODULE_CH1  -> return context.getString(R.string.MSG_ERROR_179_UNCONNECTED_MODULE_CH1)
            REMOTE_ERROR_UNCONNECTED_MODULE_CH2  -> return context.getString(R.string.MSG_ERROR_180_UNCONNECTED_MODULE_CH2)
            REMOTE_ERROR_UNCONNECTED_PWM_CH1     -> return context.getString(R.string.MSG_ERROR_181_UNCONNECTED_PWM_CH1)
            REMOTE_ERROR_UNCONNECTED_PWM_CH2     -> return context.getString(R.string.MSG_ERROR_182_UNCONNECTED_PWM_CH2)

            REMOTE_ERROR_LEAK_INJECTION_FAIL     -> return context.getString(R.string.MSG_ERROR_192_LEAK_INJECTION_FAIL)
            REMOTE_ERROR_MEASURE_TIMEOUT_CH1     -> return context.getString(R.string.MSG_ERROR_193_MEASURE_TIMEOUT_CH1)
            REMOTE_ERROR_MEASURE_TIMEOUT_CH2     -> return context.getString(R.string.MSG_ERROR_194_MEASURE_TIMEOUT_CH2)
            REMOTE_ERROR_EMPTYING_TIMEOUT_CH1    -> return context.getString(R.string.MSG_ERROR_195_EMPTYING_TIMEOUT_CH1)
            REMOTE_ERROR_EMPTYING_TIMEOUT_CH2    -> return context.getString(R.string.MSG_ERROR_196_EMPTYING_TIMEOUT_CH2)
            REMOTE_ERROR_LEAK_BACK_FLOW_FAIL     -> return context.getString(R.string.MSG_ERROR_197_LEAK_FAIL)
            REMOTE_ERROR_LOW_COEF_DTRMTN_CH1     -> return context.getString(R.string.MSG_ERROR_198_LOW_COEF_DTRMTN_CH1)
            REMOTE_ERROR_LOW_COEF_DTRMTN_CH2     -> return context.getString(R.string.MSG_ERROR_199_LOW_COEF_DTRMTN_CH2)
            REMOTE_ERROR_DEAD_VOLUME_TIMEOUT_CH1 -> return context.getString(R.string.MSG_ERROR_200_DEAD_VOLUME_TIMEOUT_CH1)
            REMOTE_ERROR_DEAD_VOLUME_TIMEOUT_CH2 -> return context.getString(R.string.MSG_ERROR_201_DEAD_VOLUME_TIMEOUT_CH2)

            REMOTE_ERROR_EMPTY_FLASH             -> return context.getString(R.string.MSG_ERROR_208_EMPTY_FLASH)
            REMOTE_ERROR_LEAKAGE_DRAIN_CH1       -> return context.getString(R.string.MSG_ERROR_209_LEAKAGE_CH1)
            REMOTE_ERROR_LEAKAGE_DRAIN_CH2       -> return context.getString(R.string.MSG_ERROR_210_LEAKAGE_CH2)
            REMOTE_ERROR_EXTRAPOLATED_VECTORS_CH1-> return context.getString(R.string.MSG_ERROR_211_EXTRAPOLATED_VECTORS_CH1)
            REMOTE_ERROR_EXTRAPOLATED_VECTORS_CH2-> return context.getString(R.string.MSG_ERROR_212_EXTRAPOLATED_VECTORS_CH2)
            REMOTE_ERROR_CONTAMINATED_OIL        -> return context.getString(R.string.MSG_ERROR_213_CONTAMINATED_OIL)
            REMOTE_ERROR_TOP_LEAK                -> return context.getString(R.string.MSG_ERROR_214_REMOTE_ERROR_TOP_LEAK)
            REMOTE_ERROR_OVER_TEMPERATURE        -> return context.getString(R.string.MSG_ERROR_215_REMOTE_ERROR_OVER_TEMPERATURE)

            /**
             * ERROS GERAIS
             */
            ERROR_COMUNICACAO_EXCED_TENT         -> return context.getString(R.string.MSG_ERROR_2000_COMUNICACAO_EXCED_TENT)
            ERROR_LIGARBOMBAS_EXCED_TENT         -> return context.getString(R.string.MSG_ERROR_2001_LIGARBOMBAS_EXCED_TENT)
            ERROR_COMUNICACAO_USB                -> return context.getString(R.string.MSG_ERROR_5000_COMUNICACAO)
            ERROR_MODULO_CONTROLE_NAO_CONECTADO  -> return context.getString(R.string.MSG_ERROR_5001_CONTROLE_NC)
            ERROR_MODULO_MEDICAO_NAO_CONECTADO   -> return context.getString(R.string.MSG_ERROR_5002_MEDICAO_NC)

            else -> return context.getString(R.string.MSG_ERROR_136_NAO_IDENTIFICADO)
        }
    }

}