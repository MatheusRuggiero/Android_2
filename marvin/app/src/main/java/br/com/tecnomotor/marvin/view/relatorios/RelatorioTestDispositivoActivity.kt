package br.com.tecnomotor.marvin.view.relatorios

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.view.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

@InternalCoroutinesApi
class RelatorioTestDispositivoActivity : AppCompatActivity() {

    val RELATORIO = "Relatório Dispositivo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio_test_dispositivo)

        verificaInfoRelatorio()
        redirectMain()
    }

    /**
     * Redireciona para a activity Main
     */
    private fun redirectMain() {
        val button = findViewById<Button>(R.id.btn_rel_inicio)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Válida e Recupera Informações do Relatório
     */
    private fun verificaInfoRelatorio() {
        val intent = intent
        val cod = intent.getStringExtra(Extra.code)
        val plano = intent.getStringExtra(Extra.plan)
        val marca = intent.getStringExtra(Extra.brandName)
        val hora =
            if (intent.getStringExtra(Extra.hour) == null) verificaHora() else intent.getStringExtra(Extra.hour)
        val data =
            if (intent.getStringExtra(Extra.date) == null) verificaData() else intent.getStringExtra(Extra.date)

        title = "$RELATORIO  |  $cod"

        exibeInfoRelatorio(cod, plano, marca, data, hora)
    }

    /**
     * Exibi na View as Informações do Relatório
     */
    private fun exibeInfoRelatorio(
        cod: String?,
        plano: String?,
        marca: String?,
        data: String?,
        hora: String?) {
        val codDispositivo = findViewById<TextView>(R.id.txt_cod_rel)
        val planoDispositivo = findViewById<TextView>(R.id.txt_plano_rel)
        val marcaDispositivo = findViewById<TextView>(R.id.txt_marca_rel)
        val dataRel = findViewById<TextView>(R.id.txt_data_rel)
        val horaRel = findViewById<TextView>(R.id.txt_hora_rel)

        codDispositivo.text = "$cod"
        planoDispositivo.text = "$plano"
        marcaDispositivo.text = "$marca"
        dataRel.text = data
        horaRel.text = hora
    }

    /**
     * Verifica Hora Atual
     */
    private fun verificaHora(): String {
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("K:mm a", Locale.getDefault())
        return dateTimeFormat.format(date)
    }

    /**
     * Verifica Data Atual
     */
    private fun verificaData(): String {
        val date = Calendar.getInstance().time
        var dateTimeFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        return dateTimeFormat.format(date)
    }

}