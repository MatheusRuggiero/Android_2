package br.com.tecnomotor.marvin.view.test_producao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnomotor.marvin.R
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ParametersTestActivity : AppCompatActivity()  {

    val PARAMETERS = "PARÂMETROS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parameters_activity)

        title = PARAMETERS
    }
}