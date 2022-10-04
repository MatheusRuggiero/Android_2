package br.com.tecnomotor.marvin.adapter.generic.injector

import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import java.io.Serializable

class PointInjectorGeneric : Serializable{
    var pointTestInjector: PointTestInjector? = PointTestInjector()
    var statusCheckBox: Boolean = true
}