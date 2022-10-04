package br.com.tecnomotor.marvin.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import br.com.tecnomotor.marvin.BuildConfig
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.controller.VersionController
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.utils.constant.ParameterSharedPreferences
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.view.injector.InjectorActivity
import br.com.tecnomotor.marvin.view.pump.PumpActivity
import br.com.tecnomotor.marvin.view.test.pump.TestControllerActivity
import br.com.tecnomotor.marvin.view.test.pump.TestPointTestViewModelActivity
import br.com.tecnomotor.marvin.view.test.pump.TestRotationViewModelActivity
import br.com.tecnomotor.marvin.view.unit_test.pump.UnitTestActivity
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private var getVersionJob = GlobalScope.launch { }
    private val tagLog = this::class.java.simpleName

    private lateinit var versionController: VersionController

    private lateinit var textVersion: TextView
    private lateinit var textAppVersion: TextView
    private lateinit var loadingVersion: ProgressBar
    private lateinit var loadingSpinner: ProgressBar

    private var platform = Platform(10, "TM581", 2000) //Pegar do banco de dados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()
        redirectButtons()
        textAppVersion = findViewById(R.id.text_version_app)
        // na versão oficial não deverá aparecer Debug Version
        if (BuildConfig.DEBUG) {
            textAppVersion.visibility = View.VISIBLE
            textAppVersion.text = "Debug Version: " + BuildConfig.VERSION_NAME
        } else {
            textAppVersion.visibility = View.GONE
        }
        //requestPermissionApplication()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()

        /*
            Seria interessante colocar no SplashScreem e salvar esses valores em SharePreferences
         */
        loadingSpinner = findViewById(R.id.loading_spinner_main)
        loadingSpinner.visibility = View.INVISIBLE
        loadingVersion = findViewById(R.id.loading_version)
        textVersion = findViewById(R.id.text_version)

        getVersion()

    }

    /**
     * Simples efeito de clique no logo
     */
    private fun clickLogo() {
        val button = findViewById<ImageView>(R.id.logo_tecnomotor)
        button.setOnClickListener {
            loadingSpinner.visibility = View.VISIBLE
            val intent = Intent(this, UnitTestActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Verifica qual botão foi selecionado
     */
    private fun redirectButtons() {
        clickLogo()
        redirectListPumps()
        redirectListInjector()
        redirectListRelatorios()
        redirectConfiguration()
        redirectSynchronization()
        redirectSensors()
        redirectValves()
    }

    /**
     * Redireciona para a activity de Lista de Bombas (View)
     */
    private fun redirectListPumps() {
        val button = findViewById<ImageButton>(R.id.btn_main_pump)
        button.setOnClickListener {
            loadingSpinner.visibility = View.VISIBLE
            val intent = Intent(this, PumpActivity::class.java)
            intent.putExtra(Extra.platform, platform)
            startActivity(intent)
        }
    }

    /**
     * Redireciona para a activity de Lista de Injetores (View)
     */
    private fun redirectListInjector() {
        val button = findViewById<ImageButton>(R.id.btn_main_injector)
        button.setOnClickListener {
//            Toast.makeText(this, "Funcionalidade ainda não disponível.", Toast.LENGTH_SHORT).show()
            loadingSpinner.visibility = View.VISIBLE
            val intent = Intent(this, InjectorActivity::class.java)
            intent.putExtra(Extra.platform, platform)
            startActivity(intent)
        }
    }

    /**
     * Redireciona para a activity de Lista de Relatórios (View)
     */
    private fun redirectListRelatorios() {
        val button = findViewById<LinearLayout>(R.id.btn_main_report)
        button.setOnClickListener {
//            Toast.makeText(this, "Funcionalidade ainda não disponível.", Toast.LENGTH_SHORT).show()
            loadingSpinner.visibility = View.VISIBLE
//            val intent = Intent(this, ListRelatoriosActivity::class.java)
            val intent = Intent(this, TestPointTestViewModelActivity::class.java)
//            intent.putExtra(Extra.platform, platform)
            startActivity(intent)
        }
    }

    /**
     * Redireciona para a activity de Configuração - TESTE PRODUÇÃO (View)
     */
    private fun redirectConfiguration() {
        val button = findViewById<LinearLayout>(R.id.btn_main_configuration)
        button.setOnClickListener {
//            Toast.makeText(this, "Funcionalidade ainda não disponível.", Toast.LENGTH_SHORT).show()
            loadingSpinner.visibility = View.VISIBLE
//            val intent = Intent(this, ConfigurationActivity::class.java)
            val intent = Intent(this, TestControllerActivity::class.java)
//            intent.putExtra(Extra.platform, platform)
            startActivity(intent)
        }
    }

    /**
     * Redireciona para a activity de Sincronização
     */
    private fun redirectSynchronization() {
        val btnSynchronized = findViewById<LinearLayout>(R.id.btn_main_synchronization_main)
        btnSynchronized.setOnClickListener {
//            Toast.makeText(this, "Funcionalidade ainda não disponível.", Toast.LENGTH_SHORT).show()
            loadingSpinner.visibility = View.VISIBLE
            val intent = Intent(this, TestRotationViewModelActivity::class.java)
//            val intent = Intent(this, SynchronizationDatabase::class.java)
            startActivity(intent)
        }
    }

    /**
     * Redireciona para a activity de Sensores
     */
    private fun redirectSensors() {
        val btnSensors = findViewById<ImageButton>(R.id.btn_main_sensor)
        btnSensors.setOnClickListener {
            Toast.makeText(this, "Funcionalidade ainda não disponível.", Toast.LENGTH_SHORT).show()
//            loading_spinner.visibility = View.VISIBLE
//            val intent = Intent(this, SensorsActivity::class.java)
//            startActivity(intent)
        }
    }

    /**
     * Redireciona para a activity de Válvulas
     */
    private fun redirectValves() {
        val btnVales = findViewById<ImageButton>(R.id.btn_main_valve)
        btnVales.setOnClickListener {
            Toast.makeText(this, "Funcionalidade ainda não disponível.", Toast.LENGTH_SHORT).show()
//            loading_spinner.visibility = View.VISIBLE
//            val intent = Intent(this, ValvesActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        GlobalUtil.method.resetParametersTryAgain(
            applicationContext.getSharedPreferences(
                ParameterSharedPreferences.SHARED_PREFERENCES_CONFIGURATION_SYNCHRONIZATION,
                Context.MODE_PRIVATE
            )
        )
    }

    override fun onStop() {
        loadingSpinner.visibility = View.INVISIBLE
        super.onStop()
    }

    private fun requestPermissionApplication() {
        val ALL_PERMISSIONS = 101
        val permissions = listOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA
        )
        ActivityCompat.requestPermissions(
            this@MainActivity,
            permissions.toTypedArray(),
            ALL_PERMISSIONS
        )
    }

    fun showVersion(versionControl: String?, versionMeasurement: String?) {
        runOnUiThread {
            Log.i(tagLog, "loadingVersion set GONE")
            loadingVersion.visibility = View.GONE
            if ((versionControl != null) && (versionMeasurement != null)) {
                textVersion.text = "$versionControl / $versionMeasurement"
                textVersion.visibility = View.VISIBLE
            }
        }
    }

    @DelicateCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getVersion() {
        loadingVersion.visibility = View.VISIBLE
        textVersion.visibility = View.GONE
        var versionControl: String? = null
        var versionMeasurement: String? = null

        versionController = VersionController(applicationContext)

        versionController.dataInfoControlLiveData.observe(this) {
            if (it != null) {
                Log.d(tagLog, "Control: $it")
                versionControl = it.getVersionString()
            }
        }
        versionController.dataInfoMeasurementLiveData.observe(this) {
            if (it != null) {
                Log.d(tagLog, "Meassurement: $it")
                versionMeasurement = it.getVersionString()
            }
        }
        versionController.start()

        if (!getVersionJob.isActive)
            getVersionJob = GlobalScope.launch(Dispatchers.IO) {

                var countExit = 0
                while (isActive) { //permite que o job seja cancelável
                    if ((versionControl != null) && (versionMeasurement != null)) {
                        showVersion(versionControl, versionMeasurement)
                        return@launch
                    }
                    delay(1000)
                    countExit++
                    Log.i(tagLog, "getVersion count: $countExit")
                    if (countExit > 10) {
                        versionController.stop()
                        showVersion(versionControl, versionMeasurement)
                        return@launch
                    }
                }
            }

    }

}