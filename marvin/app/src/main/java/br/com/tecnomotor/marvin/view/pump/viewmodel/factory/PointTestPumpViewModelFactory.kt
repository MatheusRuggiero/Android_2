package br.com.tecnomotor.marvin.view.pump.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.view.pump.viewmodel.PointTestPumpViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@DelicateCoroutinesApi
class PointTestPumpViewModelFactory(
    private var pointTestPumpList: MutableList<PointTestPump>,
    private var setPointInView: (index: Int) -> Unit = {},
    private val pump: Pump
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PointTestPumpViewModel(pointTestPumpList, setPointInView, pump) as T
    }

}