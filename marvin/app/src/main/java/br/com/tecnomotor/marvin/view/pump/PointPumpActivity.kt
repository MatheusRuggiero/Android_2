package br.com.tecnomotor.marvin.view.pump

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.pump.PointPumpGeneric
import br.com.tecnomotor.marvin.adapter.pump.PumpListTestPointAdapter
import br.com.tecnomotor.marvin.dao.entities.pump.PumpPointTestEntity
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.pump.*
import br.com.tecnomotor.marvin.model.pump.pk.PointTestPumpPK
import br.com.tecnomotor.marvin.repository.global.TypePlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.PointTestPumpRepository
import br.com.tecnomotor.marvin.repository.pump.PumpPlanTestRepository
import br.com.tecnomotor.marvin.repository.pump.TypePointTestPumpRepository
import br.com.tecnomotor.marvin.repository.pump.TypeReferencePumpRepository
import br.com.tecnomotor.marvin.utils.global.GlobalUtil
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.pump.tests.TestPumpActivity
import br.com.tecnomotor.marvin.view.viewmodel.factory.pump.module.PumpPointTestViewModelFactory
import br.com.tecnomotor.marvin.view.viewmodel.pump.module.PumpPointTestViewModel
import kotlinx.coroutines.*

@DelicateCoroutinesApi
@InternalCoroutinesApi
//@SuppressLint("UseSwitchCompatOrMaterialCode")
class PointPumpActivity : AppCompatActivity() {

    private val tagLog: String = this::class.java.simpleName
    private val PONTOS_BOMBA = "Pontos de Teste"
    var typeReferencePump = TypeReferencePump()
    private lateinit var pump: Pump
    private lateinit var plan: PumpPlanTest
    private lateinit var platform: Platform
    private var listPointTestPumpGeneric: MutableList<PointPumpGeneric> = ArrayList()
    private lateinit var loadingSpinner: ProgressBar
    private lateinit var button: Button
    private lateinit var txtRotation: TextView
//    private lateinit var switchCheckRotation: Switch

    private val adapter by lazy {
        PumpListTestPointAdapter(context = this, pump = pump)
    }

    private val viewModel by lazy {
        val pumpPlanTestRepository = PumpPlanTestRepository(application)
        val typePlanTestRepository = TypePlanTestRepository(application)
        val pointTestPumpRepository = PointTestPumpRepository(application)
        val typePointTestPumpRepository = TypePointTestPumpRepository(application)
        val typeReferencePumpRepository = TypeReferencePumpRepository(application)
        val factory =
            PumpPointTestViewModelFactory(
                pumpPlanTestRepository,
                typePlanTestRepository,
                pointTestPumpRepository,
                typePointTestPumpRepository,
                typeReferencePumpRepository
            )
        ViewModelProvider(this, factory)[PumpPointTestViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pump_activity_points)

        overridePendingTransition(0, 0)

        button = findViewById(R.id.btn_test_ponto_bomba)
        button.visibility = View.INVISIBLE
        txtRotation = findViewById(R.id.txtRotationPointTestPump)
        txtRotation.visibility = View.INVISIBLE
//        switchCheckRotation = findViewById(R.id.op_check_rotation)
//        switchCheckRotation.visibility = View.INVISIBLE
        loadingSpinner = findViewById(R.id.loading_spinner)
        loadingSpinner.visibility = View.VISIBLE

        pump = intent.getSerializableExtra(Extra.pump) as Pump
        plan = intent.getSerializableExtra(Extra.plan) as PumpPlanTest
        platform = intent.getSerializableExtra(Extra.platform) as Platform

        redirectExecuteTestPump(plan, pump)
        confRecyclerView()
        findAllListPoint()
        titleActivity()

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_voltar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun titleActivity() {
        title = "BOMBA  -  ${pump.code}  |  ${pump.brand.name}  |  Rev.Bomba ${pump.revision?.id}  |  ${plan.typePlanTest.description}  " +
                "|  Rev.Plan. ${plan.revision.id}  |  $PONTOS_BOMBA "
    }

    /**
     * Configurações da listagem de pontos de teste bombas (RecyclerView e CardView)
     */
    private fun confRecyclerView() {
        val listPointTestPumpRecyclerView = findViewById<RecyclerView>(R.id.recyclerview_list_point_pump)
        val recyclerLayoutManager = LinearLayoutManager(applicationContext)
        listPointTestPumpRecyclerView.layoutManager = recyclerLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            listPointTestPumpRecyclerView.context,
            recyclerLayoutManager.orientation
        )
        listPointTestPumpRecyclerView.addItemDecoration(dividerItemDecoration)
        listPointTestPumpRecyclerView.adapter = adapter
        configureAdapter()
    }

    /**
     * Inicializa o adapter
     */
    private fun configureAdapter() {
        adapter.whenItemClicked = this::checkListPointTestPump
    }

    /**
     * Atualiza lista de pontos de teste selecionados
     */
    private fun checkListPointTestPump(pointPumpGeneric: PointPumpGeneric) {
        var position = 0
        for (index in 0..listPointTestPumpGeneric.size) {
            if (listPointTestPumpGeneric[index].pointTestPump!!.planId == pointPumpGeneric.pointTestPump!!.planId
                && listPointTestPumpGeneric[index].pointTestPump?.pointTestPumpPK?.planTestPump?.typePlanTest?.description == pointPumpGeneric.pointTestPump?.pointTestPumpPK?.planTestPump?.typePlanTest?.description
                && listPointTestPumpGeneric[index].pointTestPump?.sequence == pointPumpGeneric.pointTestPump?.sequence
            ) {
                listPointTestPumpGeneric[index].statusCheckBox = !listPointTestPumpGeneric[index].statusCheckBox
                position = index
                break
            }
        }
        adapter.update(position) // necessário para conseguir clicar no nome dos itens
        updateVisibilityButton(listPointTestPumpGeneric)
    }

    /**
     * Atualiza a visibilidade do botão de acordo com a lista. Esconde o botão quando lista vazia
     */
    private fun updateVisibilityButton(list: MutableList<PointPumpGeneric>) {
        list.forEach { point ->
            if (point.statusCheckBox) {
                button.visibility = View.VISIBLE
                txtRotation.visibility = View.VISIBLE
//                switchCheckRotation.visibility = View.VISIBLE
                return // pelo menos 1 checkado já pode mostrar o botão
            }
        }
        // nenhum checkado, esconde o botão
        button.visibility = View.INVISIBLE
    }

    /**
     * Listar Pontos de teste do Bomba
     */
    @DelicateCoroutinesApi
    private fun findAllListPoint() {
        var listLoadDataBase: ArrayList<PumpPointTestEntity>?
        viewModel.findPointTestByIdPlan(plan.id).observe(this) { resource ->
            listLoadDataBase = resource.data as ArrayList<PumpPointTestEntity>?
            if (!listLoadDataBase.isNullOrEmpty()) {
                listLoadDataBase?.forEach { pumpPointTestEntity ->
                    var existRegister = false
                    val pointPumpTest = PointTestPump()
                    val pointTestPumpPk = PointTestPumpPK()

                    pointPumpTest.planId = pumpPointTestEntity.planId
                    pointPumpTest.sequence = pumpPointTestEntity.sequence

                    viewModel.findPlanTestById(pumpPointTestEntity.planId)
                        .observe(this) { planTest ->
                            pointTestPumpPk.planTestPump.id = pumpPointTestEntity.planId
                            if (planTest != null) {
                                pointTestPumpPk.planTestPump.typePlanId = planTest.typePlanId
                                viewModel.findTypePlanTestById(planTest.typePlanId).observe(this) {
                                    pointTestPumpPk.planTestPump.typePlanTest.id = it.id
                                    pointTestPumpPk.planTestPump.typePlanTest.description =
                                        it.description
                                }
                                pointTestPumpPk.planTestPump.pressure = planTest.pressure
                                pointTestPumpPk.planTestPump.rotation = planTest.rotation
                                pointTestPumpPk.planTestPump.minimumFlow = planTest.minimumFlow
                                pointTestPumpPk.planTestPump.timeCourse = planTest.timeCourse
                                pointTestPumpPk.planTestPump.prescaler = planTest.prescaler
                                pointTestPumpPk.planTestPump.token = planTest.token
                                pointTestPumpPk.planTestPump.deleted = planTest.deleted
                                pointTestPumpPk.planTestPump.deletePermanent =
                                    planTest.deletePermanent
                            }
                            pointTestPumpPk.sequence = pumpPointTestEntity.sequence
                            pointPumpTest.pointTestPumpPK = pointTestPumpPk
                        }

                    viewModel.findByTypePointPumpID(pumpPointTestEntity.typePointId)
                        ?.observe(this) { typePointTest ->
                            Log.d(tagLog, "TypePointTestPump: ${typePointTest.description}")
                            pointPumpTest.typePointTest = TypePointTestPump(
                                typePointTest.id,
                                typePointTest.description,
                                typePointTest.type
                            )
                        }

                    pointPumpTest.nameGeneric = pumpPointTestEntity.nameGeneric
                    pointPumpTest.currentExt1 = pumpPointTestEntity.currentExt1
                    pointPumpTest.currentExt2 = pumpPointTestEntity.currentExt2
                    pointPumpTest.frequencyExt1 = pumpPointTestEntity.frequencyExt1
                    pointPumpTest.frequencyExt2 = pumpPointTestEntity.frequencyExt2
                    pointPumpTest.typeRotation = pumpPointTestEntity.typeRotation
                    pointPumpTest.rotation = pumpPointTestEntity.rotation
                    pointPumpTest.rotationVariation = pumpPointTestEntity.rotationVariation
                    pointPumpTest.pressureFeed = pumpPointTestEntity.pressureFeed
                    pointPumpTest.tolerancePressureFeed = pumpPointTestEntity.tolerancePressureFeed
                    pointPumpTest.powerTemperature = pumpPointTestEntity.powerTemperature
                    pointPumpTest.toleranceTemperatureSupply = pumpPointTestEntity.toleranceTemperatureSupply
                    pointPumpTest.temperatureReturn = pumpPointTestEntity.temperatureReturn
                    pointPumpTest.toleranceTemperatureReturn = pumpPointTestEntity.toleranceTemperatureReturn
                    pointPumpTest.pressureTransference = pumpPointTestEntity.pressureTransference
                    pointPumpTest.tolerancePressureTransfer = pumpPointTestEntity.tolerancePressureTransfer
                    pointPumpTest.flowMain = pumpPointTestEntity.flowMain
                    pointPumpTest.toleranceFlowMain = pumpPointTestEntity.toleranceFlowMain
                    pointPumpTest.flowReturn = pumpPointTestEntity.flowReturn
                    pointPumpTest.toleranceFlowReturn = pumpPointTestEntity.toleranceFlowReturn
                    pointPumpTest.pressureTest = pumpPointTestEntity.pressureTest
                    pointPumpTest.tolerancePressureTest = pumpPointTestEntity.tolerancePressureTest
                    pointPumpTest.actuator1Type = pumpPointTestEntity.actuator11Type
                    pointPumpTest.actuator1Values = pumpPointTestEntity.actuator1Values
                    pointPumpTest.actuator1ValueTolerance = pumpPointTestEntity.actuator1ValueTolerance
                    pointPumpTest.actuator1Activation = pumpPointTestEntity.actuator1Activation
                    pointPumpTest.actuator1ValuesActivation = pumpPointTestEntity.actuator1ValuesActivation
                    pointPumpTest.actuator1ValueToleranceActivation = pumpPointTestEntity.actuator1ValueToleranceActivation
                    pointPumpTest.actuator2Type = pumpPointTestEntity.actuator2Type
                    pointPumpTest.actuator2Values = pumpPointTestEntity.actuator2Values
                    pointPumpTest.actuator2ToleranceValue = pumpPointTestEntity.actuator2ToleranceValue
                    pointPumpTest.actuator2Activation = pumpPointTestEntity.actuator2Activation
                    pointPumpTest.actuator2ValuesActivation = pumpPointTestEntity.actuator2ValuesActivation
                    pointPumpTest.actuator2ToleranceValorActivation = pumpPointTestEntity.actuator2ToleranceValorActivation
                    pointPumpTest.timeWaitingMeasurement = pumpPointTestEntity.timeWaitingMeasurement
                    pointPumpTest.presetUser = pumpPointTestEntity.presetUser
                    pointPumpTest.measureMain = pumpPointTestEntity.measureMain
                    pointPumpTest.measureReturn = pumpPointTestEntity.measureReturn

//                    while (true) {
//                        if ((pointTestPumpPk.planTestPump.id > 0) &&
//                            (pointPumpTest.typePointTest!!.id > 0) &&
//                            (pointTestPumpPk.planTestPump.typePlanTest.id > 0))
//                            return@forEach
//                    }

                    val pumpPointGeneric = PointPumpGeneric()
                    pumpPointGeneric.pointTestPump = pointPumpTest
                    pumpPointGeneric.statusCheckBox = pointPumpTest.presetUser
                    listPointTestPumpGeneric.add(pumpPointGeneric)
                }
                GlobalScope.launch(Dispatchers.Main) {
                    delay(1000)
                    while (true) {
                        if (listPointTestPumpGeneric.size > 0) {
                            Log.d(tagLog, "Update adapter: ${listPointTestPumpGeneric.size}")
                            adapter.update(listPointTestPumpGeneric)
                            button.visibility = View.VISIBLE
                            txtRotation.visibility = View.VISIBLE
//                            switchCheckRotation.visibility = View.VISIBLE
                            loadingSpinner.visibility = View.INVISIBLE
                            return@launch
                        }
                    }
                }

            } else {
                loadingSpinner.visibility = View.INVISIBLE
                Toast.makeText(this, "Nenhuma informação encontrada", Toast.LENGTH_LONG).show()
                GlobalUtil.method.shortTimeMessageAlert(
                    context = applicationContext,
                    msg = applicationContext.getString(R.string.error_load_list)
                )
            }
        }
        /**
         * Aqui deve receber o valor do campo "pump.referenceActuator"
         * Atualmente está com valores fixos
         */
        viewModel.findByTypeReferencePumpId(pump.referenceActuator)
            .observe(this@PointPumpActivity) { resTypeReference ->
                resTypeReference.let {
                    typeReferencePump = TypeReferencePump(
                        it.id, it.drv1, it.drv2, it.ext1, it.ext2, it.inj1, it.inj2
                    )
                }
            }
    }

    /**
     * Ação do Ícone Voltar (View)
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    /**
     * Redireciona para a view de Executar Test Bomba
     */
    private fun redirectExecuteTestPump(plan: PumpPlanTest, pump: Pump) {
        val button = findViewById<Button>(R.id.btn_test_ponto_bomba)
        button.setOnClickListener {

            /**
             * TODO - Essa função será utilizado no futuro.
             * TODO - Ainda é preciso fazer a tratativa de erros no firmware.
             */
            //Verifica se será feita a verificação de rotação
//            val config = RotationTestConfig.getInstance()
//            config.setCheckRotation(switchCheckRotation.isChecked)

            val pointsPump: MutableList<PointTestPump> = arrayListOf()
            checkSelectedPointTest().forEach {
                pointsPump.add(it.pointTestPump!!)
            }

            val intent = Intent(this, TestPumpActivity::class.java)
            intent.putExtra(Extra.platform, platform)
            intent.putExtra(Extra.pump, pump)
            intent.putExtra(Extra.plan, plan)
            intent.putExtra(Extra.points, ArrayList<PointTestPump>(pointsPump))
            intent.putExtra(Extra.typeReferencePump, typeReferencePump)
            startActivity(intent)
        }
    }

    /**
     * Check Selected Point Test Pump
     */
    private fun checkSelectedPointTest(): MutableList<PointPumpGeneric> {
        val list: MutableList<PointPumpGeneric> = ArrayList()
        listPointTestPumpGeneric.forEach { point ->
            if (point.statusCheckBox) {
                list.add(point)
            }
        }
        return list
    }
}