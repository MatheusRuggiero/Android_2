package br.com.tecnomotor.marvin.view.test_producao

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.marvin.R
import com.github.mikephil.charting.charts.LineChart
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class EstanqueidadeTestActivity : AppCompatActivity() {

    val ESTANQUEIDADE = "ESTANQUEIDADE"

    lateinit var mChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.estanqueidade_activity)

        title = ESTANQUEIDADE

        buttonStartEstanqueidade()
        buttonCancelEstanqueidade()
    }

    /**
     * Redireciona os valores (Pressão e Tempo) para o método para iniciar o teste.
     */
    private fun buttonStartEstanqueidade() {
        val button = findViewById<Button>(R.id.btn_est_start)
        button.setOnClickListener {

            val pressure = findViewById<EditText>(R.id.editTextEstPressure)
            val time = findViewById<EditText>(R.id.editTextEstTime)

            startTestEstanqueidade(pressure, time)
        }
    }

    /**
     * Redireciona para o método que vai cancelar o teste.
     */
    private fun buttonCancelEstanqueidade() {
        val button = findViewById<Button>(R.id.btn_est_cancel)
        button.setOnClickListener {
            cancelTestEstanqueidade()
        }
    }

    /**
     * Enviar os comandos e valores (Pressão e Tempo) para a máquina iniciar os testes
     */
    private fun startTestEstanqueidade(pressure: EditText, time: EditText) {

        val toast = Toast.makeText(applicationContext, pressure.text.toString(), Toast.LENGTH_SHORT)
        toast.show()

    }

    /**
     * Cancelar o teste de Estanqueidade
     */
    private fun cancelTestEstanqueidade() {

    }
}