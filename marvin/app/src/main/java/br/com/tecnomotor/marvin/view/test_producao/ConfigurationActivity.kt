package br.com.tecnomotor.marvin.view.test_producao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ConfigurationActivity : AppCompatActivity()  {

    val CONFIGURACAO = "CONFIGURAÇÕES"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuration_activity)

        title = CONFIGURACAO

        redirectButtons()
    }

    /**
     * Verifica qual botão foi selecionado
     */
    private fun redirectButtons() {
        redirectParameters()
        //redirectManFluido() //Talvez seja implementado no futuro.
        redirectEstanqueidade()
    }

    /**
     * Redireciona para a activity de Teste de Parâmetros
     */
    private fun redirectParameters(){
        val button = findViewById<Button>(R.id.btn_test_parametros)
        button.setOnClickListener{
            val intent = Intent(this, ParametersTestActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Redireciona para a activity de TESTE DE ESTANQUEIDADE
     */
    private fun redirectEstanqueidade(){
        val button = findViewById<Button>(R.id.btn_test_estanqueidade)
        button.setOnClickListener{
            val intent = Intent(this, EstanqueidadeTestActivity::class.java)
            startActivity(intent)
        }
    }
}