package br.com.tecnomotor.marvin.controller.test.injector

enum class InjectorTestPointType(val id: Int) {
    NORMAL(0),
    LEAK(1),
    START(2),
    HEATING(3);

    companion object {
        fun valueOf(id: Int): InjectorTestPointType {
            var res = NORMAL
            InjectorTestPointType.values().forEach {
                if (it.id == id)
                    res = it
            }
            return res
        }
    }
}