package br.com.tecnomotor.marvin.view.pump.tests

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
import br.com.tecnomotor.marvin.view.Extra
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@DelicateCoroutinesApi
class TestPumpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_pump)
        titleTestPump()
    }

    override fun onResume() {
        super.onResume()
        val args = Bundle()
        //args.putSerializable(Extra.deviceRepository, deviceRepository)
        args.putSerializable(Extra.pump, intent.getSerializableExtra(Extra.pump))
        args.putSerializable(Extra.points, intent.getSerializableExtra(Extra.points))
        args.putSerializable(Extra.plan, intent.getSerializableExtra(Extra.plan))
        args.putSerializable(Extra.typeReferencePump, intent.getSerializableExtra(Extra.typeReferencePump))
        args.putSerializable(Extra.platform, intent.getSerializableExtra(Extra.platform))

        val nav = NavHostFragment.create(R.navigation.nav_test_pump, args)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_nav_host_pump, nav)
            .setPrimaryNavigationFragment(nav)
            .commit()
    }

    /**
     * Título da view
     */
    private fun titleTestPump() {
        val intent = intent
        val pump = intent.getSerializableExtra(Extra.pump) as Pump
        val plan = intent.getSerializableExtra(Extra.plan) as PumpPlanTest


        title = "BOMBA  -  ${pump.code}  |  ${pump.brand.name}  |  Rev.Bomba ${pump.revision?.id}  |  ${plan.typePlanTest.description}  " +
                "|  Rev.Plan. ${plan.revision.id}  |  ${applicationContext.getString(R.string.title_pump_test)} "
    }

    /**
     * Esse evento deve ser tratado como cancelar teste
     */
    override fun onBackPressed() {
//        //super.onBackPressed()
    }

}