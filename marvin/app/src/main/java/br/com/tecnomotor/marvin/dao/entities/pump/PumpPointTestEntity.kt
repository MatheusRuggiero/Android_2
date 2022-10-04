package br.com.tecnomotor.marvin.dao.entities.pump

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import javax.xml.bind.annotation.XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("serial")
@XmlRootElement
@Entity(
    tableName = "ptb_pontos_testes_bombas",
    primaryKeys = ["ptb_plb_id", "ptb_sequencia"]
)
data class PumpPointTestEntity(
    @ColumnInfo(name = "ptb_plb_id")
    var planId: Int = 0,

    @ColumnInfo(name = "ptb_sequencia")
    var sequence: Int = 0,

    @ColumnInfo(name = "ptb_tptb_id")
    var typePointId: Int = 0,

    @ColumnInfo(name = "ptb_nome_generico")
    var nameGeneric: String? = "",

    @ColumnInfo(name = "ptb_corrente_ext1")
    var currentExt1: Double = 0.0,

    @ColumnInfo(name = "ptb_corrente_ext2")
    var currentExt2: Double = 0.0,

    @ColumnInfo(name = "ptb_frequencia_ext1")
    var frequencyExt1: Int = 0,

    @ColumnInfo(name = "ptb_frequencia_ext2")
    var frequencyExt2: Int = 0,

    @ColumnInfo(name = "ptb_tipo_rotacao")
    var typeRotation: Int = 0,

    @ColumnInfo(name = "ptb_rotacao")
    var rotation: Int = 0,

    @ColumnInfo(name = "ptb_variacao_rotacao")
    var rotationVariation: Int = 0,

    @ColumnInfo(name = "ptb_pressao_alimentacao_bomba")
    var pressureFeed: Double = 0.0,

    @ColumnInfo(name = "ptb_tolerancia_pressao_alimentacao_bomba")
    var tolerancePressureFeed: Double = 0.0,

    @ColumnInfo(name = "ptb_temperatura_alimentacao_bomba")
    var powerTemperature: Int = 0,

    @ColumnInfo(name = "ptb_tolerancia_temperatura_alimentacao_bomba")
    var toleranceTemperatureSupply: Int = 0,

    @ColumnInfo(name = "ptb_temperatura_retorno")
    var temperatureReturn: Int = 0,

    @ColumnInfo(name = "ptb_tolerancia_temperatura_retorno")
    var toleranceTemperatureReturn: Int = 0,

    @ColumnInfo(name = "ptb_pressao_transferencia")
    var pressureTransference: Int = 0,

    @ColumnInfo(name = "ptb_tolerancia_pressao_transferencia")
    var tolerancePressureTransfer: Int = 0,

    @ColumnInfo(name = "ptb_vazao_principal")
    var flowMain: Double = 0.0,

    @ColumnInfo(name = "ptb_tolerancia_vazao_principal")
    var toleranceFlowMain: Double = 0.0,

    @ColumnInfo(name = "ptb_vazao_retorno")
    var flowReturn: Double = 0.0,

    @ColumnInfo(name = "ptb_tolerancia_vazao_retorno")
    var toleranceFlowReturn: Double = 0.0,

    @ColumnInfo(name = "ptb_pressao_teste")
    var pressureTest: Int = 0,

    @ColumnInfo(name = "ptb_tolerancia_pressao_teste")
    var tolerancePressureTest: Int = 0,

    @ColumnInfo(name = "ptb_atuador_1_tipo")
    var actuator11Type: Int = 0,

    @ColumnInfo(name = "ptb_atuador_1_valor")
    var actuator1Values: Double = 0.0,

    @ColumnInfo(name = "ptb_atuador_1_tolerancia_valor")
    var actuator1ValueTolerance: Double = 0.0,

    @ColumnInfo(name = "ptb_atuador_1_acionamento")
    var actuator1Activation: Int = 0,

    @ColumnInfo(name = "ptb_atuador_1_valor_acionamento")
    var actuator1ValuesActivation: Int = 0,

    @ColumnInfo(name = "ptb_atuador_1_tolerancia_valor_acionamento")
    var actuator1ValueToleranceActivation: Int = 0,

    @ColumnInfo(name = "ptb_atuador_2_tipo")
    var actuator2Type: Int = 0,

    @ColumnInfo(name = "ptb_atuador_2_valor")
    var actuator2Values: Double = 0.0,

    @ColumnInfo(name = "ptb_atuador_2_tolerancia_valor")
    var actuator2ToleranceValue: Double = 0.0,

    @ColumnInfo(name = "ptb_atuador_2_acionamento")
    var actuator2Activation: Int = 0,

    @ColumnInfo(name = "ptb_atuador_2_valor_acionamento")
    var actuator2ValuesActivation: Int = 0,

    @ColumnInfo(name = "ptb_atuador_2_tolerancia_valor_acionamento")
    var actuator2ToleranceValorActivation: Int = 0,

    @ColumnInfo(name = "ptb_tempo_espera_medicao")
    var timeWaitingMeasurement: Int = 0,

    @ColumnInfo(name = "ptb_exec_preset_usuario")
    var presetUser: Boolean = false,

//	New column table reference point test pump 

    @ColumnInfo(name = "ptb_empty_0")
    var empty0: Boolean = false,

    @ColumnInfo(name = "ptb_empty_1")
    var empty1: Boolean = false,

    @ColumnInfo(name = "ptb_empty_2")
    var empty2: Boolean = false,

    @ColumnInfo(name = "ptb_medir_principal")
    var measureMain: Boolean = false,

    @ColumnInfo(name = "ptb_medir_retorno")
    var measureReturn: Boolean = false

    ) : Serializable