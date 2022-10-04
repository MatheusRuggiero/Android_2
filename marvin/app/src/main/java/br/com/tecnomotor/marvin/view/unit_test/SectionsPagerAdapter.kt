package br.com.tecnomotor.marvin.view.unit_test

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.view.unit_test.injector.UnitTestInjectorTestFragment
import br.com.tecnomotor.marvin.view.unit_test.pump.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@InternalCoroutinesApi
@OptIn(DelicateCoroutinesApi::class)
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private val TAB_TITLES = arrayOf(
        R.string.title_tab_0,
        R.string.title_tab_1,
        R.string.title_tab_3,
        R.string.title_tab_4,
        R.string.title_tab_5,
        R.string.title_tab_6,
        R.string.title_tab_7,
        R.string.title_tab_9,
        R.string.title_tab_8,
    )

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        return when (position) {
            0 -> HardwareTestControleFragment()
            1 -> UnitTestElectricalPumpFragment()
            2 -> UnitTestCheckRotationFragment()
            3 -> UnitTestPumpTestFragment()
            4 -> HardwareTestMedicaoFragment()
            5 -> UnitTestParamMedFragment()
            6 -> UnitTestReadingsMedFragment()
            7 -> UnitTestSynchronizedPumpTestFragment()
            8 -> UnitTestInjectorTestFragment()
            else -> HardwareTestControleFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    /**
     * Número de abas
     */
    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}