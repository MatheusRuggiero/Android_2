package br.com.tecnomotor.marvin.view.injector.tests

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PlanTestInjector
import br.com.tecnomotor.marvin.view.Extra
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class TestInjectorActivity : AppCompatActivity() {

    private val TITLE_TEST_INJECTOR = "Executando Testes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_injector)
        titleTestInjector()
    }

    override fun onResume() {
        super.onResume()
        val args = Bundle()
        args.putSerializable(Extra.code, intent.getStringExtra(Extra.code))
        args.putSerializable(Extra.points, intent.getSerializableExtra(Extra.points))
        args.putSerializable(Extra.injector, intent.getSerializableExtra(Extra.injector))
        args.putSerializable(Extra.platform, intent.getSerializableExtra(Extra.platform))

        val nav = NavHostFragment.create(R.navigation.nav_test_injector, args)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_nav_host_injector, nav)
            .setPrimaryNavigationFragment(nav)
            .commit()
    }

    /**
     * Título da view
     */
    private fun titleTestInjector() {
        val intent = intent
        val inj = intent.getSerializableExtra(Extra.injector) as? Injector
        val plano = intent.getSerializableExtra(Extra.plan) as? PlanTestInjector

        title = "INJETOR - ${inj?.code}  |  ${inj?.brand?.name}  |  ${inj?.type}  |  Rev.Inj. ${inj?.revisionNumber} " +
                " |  ${plano?.typePlanTest?.description}  |  Rev.Plan. ${plano?.revision?.id}  |  $TITLE_TEST_INJECTOR "
    }

    /**
     * Esse evento deve ser tratado como cancelar teste
     */
    override fun onBackPressed() {
        //super.onBackPressed()
    }

}