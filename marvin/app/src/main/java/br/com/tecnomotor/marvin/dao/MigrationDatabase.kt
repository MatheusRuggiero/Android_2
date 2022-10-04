package br.com.tecnomotor.marvin.dao

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MigrationDatabase {
    companion object {
        @JvmField
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                DBUtil.alterTable(
//                    database,
//                    tableName = "rev_revisoes",
//                    columns = mapOf(
//                        "rev_id INTEGER".toExisting(),
//                        "rev_motivo TEXT".toExisting(),
//                        "rev_data_hora INTEGER".toExisting()
//                    ),
//                    primaryKeys = listOf("rev_id")
//                )
            }
        }

        @JvmField
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}