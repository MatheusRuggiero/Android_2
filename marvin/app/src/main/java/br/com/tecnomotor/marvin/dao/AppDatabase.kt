package br.com.tecnomotor.marvin.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.tecnomotor.marvin.api.model.DeviceAuthentication
import br.com.tecnomotor.marvin.api.model.ProductLicenseDevice
import br.com.tecnomotor.marvin.api.model.XidDeviceAuthentication
import br.com.tecnomotor.marvin.api.model.XidProductLicenseDevice
import br.com.tecnomotor.marvin.dao.convert.DateConverter
import br.com.tecnomotor.marvin.dao.entities.configuration.XidParameterRemoteEntity
import br.com.tecnomotor.marvin.dao.entities.configuration.XidPermissionParameterRemoteEntity
import br.com.tecnomotor.marvin.dao.entities.configuration.XidTranslateParameterRemoteEntity
import br.com.tecnomotor.marvin.dao.entities.global.*
import br.com.tecnomotor.marvin.dao.entities.injector.*
import br.com.tecnomotor.marvin.dao.entities.pump.*
import br.com.tecnomotor.marvin.dao.entities.sensor.*
import br.com.tecnomotor.marvin.dao.entities.valve.*
import br.com.tecnomotor.marvin.dao.impl.configuration.*
import br.com.tecnomotor.marvin.dao.impl.global.*
import br.com.tecnomotor.marvin.dao.impl.injector.*
import br.com.tecnomotor.marvin.dao.impl.pump.*
import br.com.tecnomotor.marvin.dao.impl.sensor.*
import br.com.tecnomotor.marvin.dao.impl.valve.*
import br.com.tecnomotor.marvin.dao.entities.configuration.ParameterRemoteEntity
import br.com.tecnomotor.marvin.dao.entities.configuration.PermissionParameterRemoteEntity
import br.com.tecnomotor.marvin.model.configuration.TranslateParameterRemoteEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(
    entities = [
        DeviceAuthentication::class,
        XidDeviceAuthentication::class,
        ProductLicenseDevice::class,
        XidProductLicenseDevice::class,
        InjectorPlanTestEntity::class,
        XidPlanTestInjectorEntity::class,
        TypePointInjectorTestEntity::class,
        XidTypePointInjectorTestEntity::class,
        BrandEntity::class,
        XidBrandEntity::class,
        VersionEntity::class,
        XidVersionEntity::class,
        TypePlanTestEntity::class,
        XidTypePlanTestEntity::class,
        PlatformEntity::class,
        XidPlatformEntity::class,
        RevisionEntity::class,
        XidRevisionEntity::class,
        InjectorEntity::class,
        XidInjectorEntity::class,
        RevisionInjectorEntity::class,
        XidRevisionInjectorEntity::class,
        InjectorPlatformEntity::class,
        XidInjectorPlatformEntity::class,
        InjectorPlatformPlanTestEntity::class,
        XidInjectorPlatformPlanTestEntity::class,
        PointInjectorTestEntity::class,
        XidPointTestInjectorEntity::class,
        TranslateTypePlanTestInjectorEntity::class,
        XidTranslateTypePlanTestInjectorEntity::class,
        TranslateTypePointTestInjectorEntity::class,
        XidTranslateTypePointTestInjectorEntity::class,
//    Module report injector
        InjectorTestReportEntity::class,
        InjectorPointTestChannelEntity::class,
        InjectorPointTestReportEntity::class,
        InjectorReportEntity::class,

//    Module pump
        PumpEntity::class,
        RevisionPumpEntity::class,
        TypePumpEntity::class,
        PumpPlanTestEntity::class,
        RevisionPumpPlanEntity::class,
        PumpPlatformEntity::class,
        PumpPlatformPlanTestEntity::class,
        PumpPointTestEntity::class,
        TypePointTestPumpEntity::class,
        TranslateTypePointTestPumpEntity::class,
        TranslateTypePlanTestPumpEntity::class,
        TypeReferencePumpEntity::class,
        XidPumpEntity::class,
        XidRevisionPumpEntity::class,
        XidTypePumpEntity::class,
        XidPlanTestPumpEntity::class,
        XidPointTestPumpEntity::class,
        XidRevisionPlanPumpEntity::class,
        XidPumpPlatformPlanEntity::class,
        XidTypePointTestPumpEntity::class,
        XidPumpPlatformEntity::class,
        XidTranslateTypePlanTestPumpEntity::class,
        XidTranslateTypePointTestPumpEntity::class,
        XidTypeReferencePumpEntity::class,
//    Module sensor
        SensorEntity::class,
        RevisionSensorEntity::class,
        SensorTestPlanEntity::class,
        RevisionPlanSensorEntity::class,
        SensorPlatformEntity::class,
        SensorPlatformPlanTestEntity::class,
        TranslateTypePlanTestSensorEntity::class,
        XidSensorEntity::class,
        XidRevisionSensorEntity::class,
        XidPlanTestSensorEntity::class,
        XidRevisionPlanSensorEntity::class,
        XidSensorPlatformEntity::class,
        XidSensorPlatformPlanEntity::class,
        XidTranslateTypePlanTestSensorEntity::class,
//    Module valve
        RevisionPlanValveEntity::class,
        RevisionValveEntity::class,
        TranslateTypePlanTestValveEntity::class,
        ValveEntity::class,
        ValvePlanTestEntity::class,
        ValvePlatformEntity::class,
        ValvePlatformPlanEntity::class,
        XidPlanTestValveEntity::class,
        XidRevisionValveEntity::class,
        XidRevisionPlanValveEntity::class,
        XidTranslateTypePlanTestValveEntity::class,
        XidValveEntity::class,
        XidValvePlatformEntity::class,
        XidValvePlatformPlanEntity::class,
//    Module parameter remote
        ParameterRemoteEntity::class,
        PermissionParameterRemoteEntity::class,
        TranslateParameterRemoteEntity::class,
        XidParameterRemoteEntity::class,
        XidPermissionParameterRemoteEntity::class,
        XidTranslateParameterRemoteEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Tables modules authentication and register device
     */
    abstract fun deviceAuthenticationDao(): DeviceAuthenticationDao?
    abstract fun xidDeviceAuthenticationDao(): XidDeviceAuthenticationDao?
    abstract fun productLicenseDeviceDao(): ProductLicenseDeviceDao?
    abstract fun xidProductLicenseDeviceDao(): XidProductLicenseDeviceDao?

    /**
     * Tables modules global
     */
    abstract fun brandDao(): BrandDao?
    abstract fun platformDao(): PlatformDao?
    abstract fun revisionDao(): RevisionDao?
    abstract fun typePlanTestDao(): TypePlanTestDao?
    abstract fun versionDao(): VersionDao?
    abstract fun xidBrandDao(): XidBrandDao?
    abstract fun xidPlatformDao(): XidPlatformDao?
    abstract fun xidRevisionDao(): XidRevisionDao?
    abstract fun xidTypePlanTestDao(): XidTypePlanTestDao?
    abstract fun xidVersionDao(): XidVersionDao?

    /**
     * Tables modules injector
     */
    abstract fun injectorDao(): InjectorDao?
    abstract fun injectorPlanTestDao(): InjectorPlanTestDao?
    abstract fun injectorPlatformDao(): InjectorPlatformDao?
    abstract fun injectorPlatformPlanTestDao(): InjectorPlatformPlanTestDao?
    abstract fun pointTestInjectorDao(): PointTestInjectorDao?
    abstract fun revisionInjectorDao(): RevisionInjectorDao?
    abstract fun translateTypePlanTestInjectorDao(): TranslateTypePlanTestInjectorDao?
    abstract fun translateTypePointTestInjectorDao(): TranslateTypePointTestInjectorDao?
    abstract fun typePointInjectorTestDao(): TypePointInjectorTestDao?
    abstract fun xidInjectorDao(): XidInjectorDao?
    abstract fun xidInjectorPlanTestDao(): XidInjectorPlanTestDao?
    abstract fun xidInjectorPlatformDao(): XidInjectorPlatformDao?
    abstract fun xIdInjectorPlatformPlanTestDao(): XidInjectorPlatformTestPlanDao?
    abstract fun xidPointTestInjectorDao(): XidPointTestInjectorDao?
    abstract fun xidRevisionInjectorDao(): XidRevisionInjectorDao?
    abstract fun xidTranslateTypePlanTestInjectorDao(): XidTranslateTypePlanTestInjectorTestDao?
    abstract fun xidTranslateTypePointTestInjectorDao(): XidTranslateTypePointTestInjectorDao?
    abstract fun xidTypePointInjectorTestDao(): XidTypePointInjectorTestDao?

    abstract fun injectorTestReportDao(): InjectorTestReportDao?
    abstract fun injectorPointTestChannelDao(): InjectorPointTestChannelDao?
    abstract fun injectorPointTestReportDao(): InjectorPointTestReportDao?
    abstract fun injectorReportDao(): InjectorReportDao?


    /**
     * Tables modules pump
     */
    abstract fun pumpDao(): PumpDao?
    abstract fun pumpPlanTestDao(): PumpPlanTestDao?
    abstract fun pumpPlatformDao(): PumpPlatformDao?
    abstract fun pumpPlatformPlanTestDao(): PumpPlatformPlanTestDao?
    abstract fun pointTestPumpDao(): PointTestPumpDao?
    abstract fun revisionPumpDao(): RevisionPumpDao?
    abstract fun revisionPumpPlanDao(): RevisionPumpPlanDao?
    abstract fun translateTypePlanTestPumpTestDao(): TranslateTypePlanTestPumpTestDao?
    abstract fun translateTypePointTestPumpDao(): TranslateTypePointTestPumpTestDao?
    abstract fun typePointTestPumpDao(): TypePointTestPumpDao?
    abstract fun typePumpDao(): TypePumpDao?
    abstract fun typeReferencePumpDao(): TypeReferencePumpDao?
    abstract fun xidPumpDao(): XidPumpDao?
    abstract fun xidPointTestPumpDao(): XidPointTestPumpDao?
    abstract fun xidPumpPlanTestDao(): XidPlanTestPumpDao?
    abstract fun xidPumpPlatformDao(): XidPumpPlatformDao?
    abstract fun xidPumpPlatformPlanTestDao(): XidPumpPlatformPlanTestDao?
    abstract fun xidRevisionPlanPumpDao(): XidRevisionPlanPumpDao?
    abstract fun xidRevisionPumpDao(): XidRevisionPumpDao?
    abstract fun xidTranslateTypePlanTestPumpTestDao(): XidTranslateTypePlanTestPumpTestDao?
    abstract fun xidTranslateTypePointTestPumpDao(): XidTranslateTypePointTestPumpTestDao?
    abstract fun xidTypePointTestPumpDao(): XidTypePointTestPumpDao?
    abstract fun xidTypePumpDao(): XidTypePumpDao?
    abstract fun xidTypeReferencePumpDao(): XidTypeReferencePumpDao?

    /**
     * Tables modules sensor
     */
    abstract fun sensorDao(): SensorDao?
    abstract fun revisionSensorDao(): RevisionSensorDao?
    abstract fun sensorPlanTestDao(): SensorPlanTestDao?
    abstract fun revisionPlanSensorDao(): RevisionSensorPlanDao?
    abstract fun sensorPlatformDao(): SensorPlatformDao?
    abstract fun sensorPlatformPlanTestDao(): SensorPlatformPlanTestDao?
    abstract fun translateTypePlanTestSensorTestDao(): TranslateTypePlanTestSensorTestDao?
    abstract fun xidSensorDao(): XidSensorDao?
    abstract fun xidRevisionSensorDao(): XidRevisionSensorDao?
    abstract fun xidPlanTestSensorDao(): XidPlanTestSensorDao?
    abstract fun xidRevisionPlanSensorDao(): XidRevisionPlanSensorDao?
    abstract fun xidSensorPlatformDao(): XidSensorPlatformDao?
    abstract fun xidSensorPlatformPlanTestDao(): XidSensorPlatformPlanTestDao?
    abstract fun xidTranslateTypePlanTestSensorTestDao(): XidTranslateTypePlanTestSensorDao?

    /**
     * Tables modules valve
     */

    abstract fun revisionPlanValveDao(): RevisionValvePlanDao?
    abstract fun revisionValveDao(): RevisionValveDao?
    abstract fun translateTypePlanTestValveDao(): TranslateTypePlanTestValveDao?
    abstract fun valveDao(): ValveDao?
    abstract fun valvePlanTestDao(): ValvePlanTestDao?
    abstract fun valvePlatformDao(): ValvePlatformDao?
    abstract fun valvePlatformPlanDao(): ValvePlatformPlanDao?
    abstract fun xidPlanTestValveDao(): XidPlanTestValveDao?
    abstract fun xidRevisionValveDao(): XidRevisionValveDao?
    abstract fun xidRevisionPlanValveDao(): XidRevisionPlanValveDao?
    abstract fun xidTranslateTypePlanTestValveDao(): XidTranslateTypePlanTestValveDao?
    abstract fun xidValveDao(): XidValveDao?
    abstract fun xidValvePlatformDao(): XidValvePlatformDao?
    abstract fun xidValvePlatformPlanDao(): XidValvePlatformPlanDao?

    /**
     * Tables modules parameter remote
     */
    abstract fun parameterRemoteDao(): ParameterRemoteDao?
    abstract fun permissionParameterRemoteDao(): PermissionParameterRemoteDao?
    abstract fun translateParameterRemoteDao(): TranslateParameterRemoteDao?
    abstract fun xidParameterRemoteDao(): XidParameterRemoteDao?
    abstract fun xidPermissionParameterRemoteDao(): XidPermissionParameterRemoteDao?
    abstract fun xidTranslateParameterRemoteDao(): XidTranslateParameterRemoteDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "DBPadrao-Param.db"
                        )
                            .createFromAsset("database/DBPadrao-Param.db")
                            .addMigrations(MigrationDatabase.MIGRATION_1_2)
                            .addMigrations(MigrationDatabase.MIGRATION_2_3)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}