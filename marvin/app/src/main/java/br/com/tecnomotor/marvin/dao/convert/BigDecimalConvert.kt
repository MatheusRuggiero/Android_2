package br.com.tecnomotor.marvin.dao.convert

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConvert {
    @TypeConverter
    fun fromString(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun amountToString(bigDecimal: BigDecimal?): Double? {
        return bigDecimal?.toDouble()
    }
}