package br.com.tecnomotor.commonrail.device.commands.injector

/**
 * id = id de resposta da comunicação
 * idDB = id do banco de dados
 */
enum class EnumInjectorType(val id: Int): ITypeOfInjector {
    NONE(0) {
        override fun getIdDB(): Int = -1
    },
    PIEZO(1) {
        override fun getIdDB(): Int = 1
    },
    INDUTIVO(2) {
        override fun getIdDB(): Int = 0
    },
    INDUTIVO_DS(3) {
        override fun getIdDB(): Int = 2
    };

    companion object {
        fun valueOf(id: Int): EnumInjectorType {
            var res = EnumInjectorType.NONE
            EnumInjectorType.values().forEach {
                if (it.id == id)
                    res = it
            }
            return res
        }
        fun valueOfDB(id: Int): EnumInjectorType {
            var res = EnumInjectorType.NONE
            EnumInjectorType.values().forEach {
                if (it.getIdDB() == id)
                    res = it
            }
            return res
        }
    }
}