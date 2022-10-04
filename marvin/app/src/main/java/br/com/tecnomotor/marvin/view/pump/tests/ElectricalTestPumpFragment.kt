package br.com.tecnomotor.marvin.view.pump.tests

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.com.tecnomotor.commonrail.device.commands.pump.PumpCommands
import br.com.tecnomotor.commonrail.device.commands.pump.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.controller.test.IControllerTest
import br.com.tecnomotor.marvin.controller.test.pump.ElectricalTestPumpController
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
import br.com.tecnomotor.marvin.model.pump.TypeReferencePump
import br.com.tecnomotor.marvin.utils.objects.FormatTestResults
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.pump.PumpActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@DelicateCoroutinesApi
class ElectricalTestPumpFragment : Fragment() {

    private val tagLog = this.javaClass.simpleName
    private lateinit var electricTestController: ElectricalTestPumpController
    private lateinit var listPoint: MutableList<PointTestPump>
    private var root: View? = null
    private lateinit var plan: PumpPlanTest
    private lateinit var pump: Pump
    private lateinit var platform: Platform
    private lateinit var typeReferencePump: TypeReferencePump
    private val tableActuator = ArrayList<ColumnTableActuator>()
    private val resultActuator = ResultActuator()
    private val navController by lazy {
        findNavController()
    }

    /* Componentes View */
    private lateinit var imageStatusTestElectricalOK: ImageView
    private lateinit var imageStatusTestElectricalFail: ImageView
    private lateinit var txtStatusTestEletric: TextView
    private lateinit var visibilityButtonElectricNext: Group
    private lateinit var visibilityButtonElectricRepeat: Group
    private lateinit var visibilityButtonElectricCancel: Group
    private lateinit var buttonElectricNext: ImageButton
    private lateinit var buttonElectricRepeat: ImageButton
    private lateinit var buttonElectricCancel: ImageButton

    inner class ColumnTableActuator(
        val groupValues: Group,
        val txtTitle: TextView,
        val txtResistance: TextView,
        val txtCondition: TextView,
    )

    inner class RowTableActuator(
        var condition: ElectricalTestCondition,
        var resistance: String,
    )

    inner class ResultActuator : ArrayList<RowTableActuator>() {
        fun conditionIsOk(): Boolean {
            this.forEach {
                if ((it.condition != ElectricalTestCondition.OK) && (it.condition != ElectricalTestCondition.NONE))
                    return false
            }
            return true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        root = inflater.inflate(R.layout.fragment_electrical_test_pump, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startVariable(view)
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        if ((args != null) && (args.size() > 0)) {
            pump = args[Extra.pump] as Pump
            plan = args[Extra.plan] as PumpPlanTest
            platform = args[Extra.platform] as Platform
            typeReferencePump = args[Extra.typeReferencePump] as TypeReferencePump
            listPoint = args[Extra.points] as MutableList<PointTestPump>

            startVariableTable(view)

            electricTestController = ElectricalTestPumpController(typeReferencePump)

            /**
             * Mostra as informações do teste na view
             * Manipula elementos da view
             */
            electricTestController.electricalResultLiveData.observe(viewLifecycleOwner) { electricalResult ->
                when (electricalResult.status) {
                    EnumTestStatus.TEST_FINISHED -> {
                        Log.i(tagLog, electricalResult.toString())

                        //Se a resistência for igual a 50.00 Ohm, não é para exibir o valor
                        //É para exibir uns Traços no lugar (Ideai Waldir e Celso)
                        val resultResistance = if (electricalResult.resistance == 50.00) {
                            "- - . - -"
                        } else {
                            FormatTestResults.FORMAT_OHMS.format(electricalResult.resistance)
                        }

                        resultActuator.add(
                            RowTableActuator(
                                electricalResult.condition,
                                resultResistance
                            )
                        )
                        tableActuator.forEachIndexed { index, columnTableActuator ->
                            if (index < resultActuator.size) {
                                columnTableActuator.txtCondition.text = resultActuator[index].condition.getLocalizedMessage(requireActivity().application)
                                columnTableActuator.txtResistance.text = resultActuator[index].resistance
                                if (resultActuator[index].condition == ElectricalTestCondition.OK) {
                                    columnTableActuator.groupValues.visibility = View.VISIBLE
                                    columnTableActuator.txtCondition.setTextColor(
                                        resources.getColor(
                                            R.color.green,
                                            null
                                        )
                                    )
                                    columnTableActuator.txtResistance.setTextColor(
                                        resources.getColor(
                                            R.color.green,
                                            null
                                        )
                                    )
                                } else if (resultActuator[index].condition != ElectricalTestCondition.NONE) {
                                    columnTableActuator.groupValues.visibility = View.VISIBLE
                                    columnTableActuator.txtCondition.setTextColor(
                                        resources.getColor(
                                            R.color.vermelho_tecnomotor,
                                            null
                                        )
                                    )
                                    columnTableActuator.txtResistance.setTextColor(
                                        resources.getColor(
                                            R.color.vermelho_tecnomotor,
                                            null
                                        )
                                    )
                                }
                            } else return@forEachIndexed
                        }
                    }
                }
            }
            electricTestController.electricalTestResultLiveData.observe(viewLifecycleOwner) { electricalTestResult ->
                when (electricalTestResult.status) {
                    EnumTestStatus.TEST_ALL_TESTS_FINISHED -> {
                        txtStatusTestEletric.text = electricalTestResult.status.getLocalizedMessage(requireActivity().application)
                        /* Mostra informação final do teste */
                        if (resultActuator.conditionIsOk()) {
                            setResultStatusImage(true)
                            releaseAllButtons(electricalTestResult, electricTestController)
                        } else {
                            setResultStatusImage(false)
                            releaseErrorButtons(electricalTestResult, electricTestController)
                        }
                    }
                    EnumTestStatus.TEST_FINISHED -> { }
                    else -> {
                        txtStatusTestEletric.text = electricalTestResult.status.getLocalizedMessage(requireActivity().application)
                    }
                }
            }
            startTestElectricalPump()
        } else {
            imageStatusTestElectricalFail.visibility = View.VISIBLE
            txtStatusTestEletric.text = EnumTestStatus.TEST_FAIL.getLocalizedMessage(requireActivity().application)
            txtStatusTestEletric.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
        }
    }

    /**
     * Start Electrical Test Pump
     */
    @SuppressLint("ResourceAsColor", "NewApi")
    private fun startTestElectricalPump() {
        resultActuator.clear()
        electricTestController.startTest()
    }

    /**
     * Avançar para o próximo teste (Pontos de Teste)
     */
    @SuppressLint("ResourceType")
    private fun nextTestPump(resultElectrical: ElectricalTestResult) {
        val args = Bundle()
        args.putSerializable(Extra.pump, pump)
        args.putSerializable(Extra.plan, plan)
        args.putSerializable(Extra.typeReferencePump, typeReferencePump)
        args.putSerializable(Extra.platform, platform)
        args.putSerializable(Extra.points, ArrayList<PointTestPump>(listPoint))
        args.putSerializable(Extra.resultElectrical, resultElectrical)

        buttonElectricNext.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_electricalTestPumpFragment_to_rotationPumpFragment, args)
        }
    }

    /**
     * Repetir o Teste Elétrico
     */
    private fun repeatTestElectricalPump() {
        buttonElectricRepeat.setOnClickListener {
            releaseRepeatElements()
            startTestElectricalPump()
        }
    }

    /**
     * Cancelar teste elétrico
     */
    @DelicateCoroutinesApi
    private fun cancelTestElectricPump(
        result: ElectricalTestResult,
        electricTestInjector: IControllerTest,
    ) {
        buttonElectricCancel.setOnClickListener {
            if (result.status == EnumTestStatus.TEST_STARTING || result.status == EnumTestStatus.TEST_RUNNING)
                electricTestInjector.cancelTest()

            val intent = Intent(activity, PumpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra(Extra.platform, platform)
            startActivity(intent)
            activity?.finish()
        }
    }


    /**
     *  INICIALIZAR COMPONENTES DE LAYOUT
     *  E EXIBIR BOTÕES
     */


    /**
     * Inicializar variáveis componentes layouts e button
     */
    @SuppressLint("ResourceAsColor", "NewApi")
    private fun startVariable(view: View) {
        imageStatusTestElectricalOK = view.findViewById(R.id.status_test_electrical_ok)
        imageStatusTestElectricalFail = view.findViewById(R.id.status_test_electrical_fail)
        buttonElectricNext = view.findViewById(R.id.btn_electric_test_next)
        buttonElectricRepeat = view.findViewById(R.id.btn_repeat_electric_test)
        buttonElectricCancel = view.findViewById(R.id.btn_cancel_electric_test)
        txtStatusTestEletric = view.findViewById(R.id.txt_status_eletric_pump_value)!!

        visibilityButtonElectricNext = view.findViewById(R.id.btn_eletric_test_next_group)
        visibilityButtonElectricRepeat = view.findViewById(R.id.btn_eletric_test_repeat_group)
        visibilityButtonElectricCancel = view.findViewById(R.id.btn_eletric_test_cancel_group)
        visibilityButtonElectricRepeat.visibility = View.GONE
        visibilityButtonElectricCancel.visibility = View.GONE
        visibilityButtonElectricNext.visibility = View.GONE
    }

    private fun startVariableTable(view: View) {
        tableActuator.add(
            ColumnTableActuator(
                view.findViewById(R.id.group_column_a),
                view.findViewById(R.id.column_a),
                view.findViewById(R.id.txt_resistencia_value_a),
                view.findViewById(R.id.txt_condition_value_a)
            )
        )
        tableActuator.add(
            ColumnTableActuator(
                view.findViewById(R.id.group_column_b),
                view.findViewById(R.id.column_b),
                view.findViewById(R.id.txt_resistencia_value_b),
                view.findViewById(R.id.txt_condition_value_b)
            )
        )
        tableActuator.add(
            ColumnTableActuator(
                view.findViewById(R.id.group_column_c),
                view.findViewById(R.id.column_c),
                view.findViewById(R.id.txt_resistencia_value_c),
                view.findViewById(R.id.txt_condition_value_c)
            )
        )
        tableActuator.add(
            ColumnTableActuator(
                view.findViewById(R.id.group_column_d),
                view.findViewById(R.id.column_d),
                view.findViewById(R.id.txt_resistencia_value_d),
                view.findViewById(R.id.txt_condition_value_d)
            )
        )
        tableActuator.forEach {
            it.groupValues.visibility = View.GONE
        }

        var indexColumnTable = 0
        if (typeReferencePump.drv1) {
            tableActuator[indexColumnTable].let {
                it.txtTitle.text = context?.getText(R.string.drv_1)
            }
            indexColumnTable++
        }
        if (typeReferencePump.drv2) {
            tableActuator[indexColumnTable].let {
                it.txtTitle.text = context?.getText(R.string.drv_2)
            }
            indexColumnTable++
        }
        if (typeReferencePump.ext1) {
            tableActuator[indexColumnTable].let {
                it.txtTitle.text = context?.getText(R.string.ext_1)
            }
            indexColumnTable++
        }
        if (typeReferencePump.ext2) {
            tableActuator[indexColumnTable].let {
                it.txtTitle.text = context?.getText(R.string.ext_2)
            }
            indexColumnTable++
        }
        if ((typeReferencePump.inj1) && (indexColumnTable < tableActuator.size)) {
            tableActuator[indexColumnTable].let {
                it.txtTitle.text = context?.getText(R.string.inj_1)
            }
            indexColumnTable++
        }
        if ((typeReferencePump.inj2) && (indexColumnTable < tableActuator.size)) {
            tableActuator[indexColumnTable].let {
                it.txtTitle.text = context?.getText(R.string.inj_2)
            }
        }
    }


    /**
     * Exibir botões quando a condição do teste for "OK"
     */
    @DelicateCoroutinesApi
    private fun releaseAllButtons(
        resultadoTesteEletrico: ElectricalTestResult,
        electricTestInjector: IControllerTest,
    ) {
        visibilityButtonElectricRepeat.visibility = View.VISIBLE
        visibilityButtonElectricCancel.visibility = View.VISIBLE
        visibilityButtonElectricNext.visibility = View.VISIBLE

        repeatTestElectricalPump()
        nextTestPump(resultadoTesteEletrico)
        cancelTestElectricPump(resultadoTesteEletrico, electricTestInjector)
    }

    /**
     * Exibir botões quando a condição do teste for "ERROR"
     */
    @DelicateCoroutinesApi
    private fun releaseErrorButtons(
        resultadoTesteEletrico: ElectricalTestResult,
        electricTestInjector: IControllerTest,
    ) {
        visibilityButtonElectricRepeat.visibility = View.VISIBLE
        visibilityButtonElectricCancel.visibility = View.VISIBLE
        visibilityButtonElectricNext.visibility = View.GONE

        repeatTestElectricalPump()
        cancelTestElectricPump(resultadoTesteEletrico, electricTestInjector)
    }

    /**
     * Ocultar os botões quando for repetir o teste
     */
    @SuppressLint("ResourceAsColor", "NewApi")
    private fun releaseRepeatElements() {
        visibilityButtonElectricRepeat.visibility = View.GONE
        visibilityButtonElectricNext.visibility = View.GONE
        visibilityButtonElectricCancel.visibility = View.GONE
        imageStatusTestElectricalOK.visibility = View.INVISIBLE
        imageStatusTestElectricalFail.visibility = View.INVISIBLE

        tableActuator.forEach {
            it.groupValues.visibility = View.GONE
            it.txtCondition.setTextColor(resources.getColor(R.color.dark_gray, null))
            it.txtResistance.setTextColor(resources.getColor(R.color.dark_gray, null))
        }

    }

    /**
     * Seta resultado da imagem final do teste
     */
    private fun setResultStatusImage(statusSuccess: Boolean) {
        if (statusSuccess) {
            imageStatusTestElectricalOK.visibility = View.VISIBLE
            imageStatusTestElectricalFail.visibility = View.INVISIBLE
        } else {
            imageStatusTestElectricalOK.visibility = View.INVISIBLE
            imageStatusTestElectricalFail.visibility = View.VISIBLE
        }
    }

}