package br.com.tecnomotor.marvin.view.injector.tests

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import br.com.tecnomotor.commonrail.device.commands.injector.EnumInjectorType
import br.com.tecnomotor.commonrail.device.commands.injector.result.ElectricalTestResult
import br.com.tecnomotor.commonrail.device.utils.control.ElectricalTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.controller.test.IControllerTest
import br.com.tecnomotor.marvin.controller.test.injector.demo.ElectricalTestInjectorControllerDemo
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorEntity
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.repository.injector.InjectorRepository
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.injector.InjectorActivity
import kotlinx.coroutines.*

@InternalCoroutinesApi
@DelicateCoroutinesApi
class ElectricalTestInjectorFragment : Fragment() {

    //private lateinit var deviceViewModel: DeviceViewModel
    private var codeInjector: String = ""
    private var injector: Injector? = null
    private var platform: Platform? = null
    private lateinit var listPoint: MutableList<PointTestInjector>
    private var root: View? = null
    private lateinit var repositoryInjector: InjectorRepository
    private lateinit var plan: String
    private lateinit var brand: String
    private val controlador by lazy {
        findNavController()
    }

    /* Componentes Layout */
    private lateinit var imageStatusTestElectricalOK: ImageView
    private lateinit var imageStatusTestElectricalFail: ImageView
    private lateinit var txtCondicao: TextView
    private lateinit var txtResistencia: TextView
    private lateinit var txtCondicaoB: TextView
    private lateinit var txtResistenciaB: TextView
    private lateinit var txtStatusTestEletric: TextView

    /* Componentes BUTTONS */
    private lateinit var visibilityButtonElectricNext: Group
    private lateinit var visibilityButtonElectricRepeat: Group
    private lateinit var visibilityButtonElectricCancel: Group
    private lateinit var buttonElectricNext: ImageButton
    private lateinit var buttonElectricRepeat: ImageButton
    private lateinit var buttonElectricCancel: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_electrical_test_injector, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startVariable(view)

        super.onViewCreated(view, savedInstanceState)
        println("Argumentos: ${controlador.graph.arguments.size}")
        controlador.graph.arguments.forEach {
            println("Argumento[${it.key}]")
        }
        println("FragmentArgs: ${this.arguments?.size()}")
        this.arguments.let {
            println(it.toString())
        }
        val args = this.arguments
        if ((args != null) && (args.size() > 0)) {
            listPoint = args[Extra.points] as MutableList<PointTestInjector>
            injector = args[Extra.injector] as Injector
            platform = args[Extra.platform] as Platform
            //plan = args[Extra.plain] as String
//            while (true) {
//                if ((deviceViewModel.getDeviceControle() != null) && (deviceViewModel.getDeviceMedicao() != null))
//                    break
//            }
            startTestElectricalInjector()
        } else {
            imageStatusTestElectricalFail.visibility = View.VISIBLE
            txtStatusTestEletric.text = EnumTestStatus.TEST_FAIL.getLocalizedMessage(requireActivity().application)
            txtStatusTestEletric.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
        }
    }

    /**
     * Start Electrical Test Injector
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun startTestElectricalInjector() {

        //Pegar os parâmetros default do banco de dados
        var injectorEntity: InjectorEntity? = InjectorEntity()
        val globalScopeSearchInjector = GlobalScope.launch(Dispatchers.IO) {
            repositoryInjector = InjectorRepository(requireActivity().application)
            injectorEntity = repositoryInjector.findByInjectorUsingCode(injector?.code.toString())
        }

//        val electricTest = ElectricalTestInjectorController(CommandsInjetor(deviceViewModel.repository))
        val electricTest = ElectricalTestInjectorControllerDemo()

        /**
         * Verifica se o tipo do injetor é INDUTIVO_DS
         * Para exibir os demais campos
         */
        val tipoInjetor = EnumInjectorType.valueOf(injectorEntity!!.tipo)
        if (tipoInjetor == EnumInjectorType.INDUTIVO_DS) {
            txtCondicaoB.visibility = View.VISIBLE
            txtResistenciaB.visibility = View.VISIBLE
        }

        globalScopeSearchInjector.cancel()

        /**
         * Mostra as informações do teste na view
         * Manipula elementos da view
         */
        electricTest.resultTestElectric.observeForever {
            println("View::TESTE ELÉTRICO::  + $it")

            if (it.status != EnumTestStatus.NONE) {
                txtStatusTestEletric.text = it.status.getLocalizedMessage(requireActivity().application)
            }

            if (it.status == EnumTestStatus.TEST_FINISHED) {

                txtCondicao.visibility = View.VISIBLE
                txtResistencia.visibility = View.VISIBLE

                txtCondicao.text = it.conditionA.getLocalizedMessage(requireActivity().application)
                txtResistencia.text = it.getTextResistanceA()
                txtCondicaoB.text = it.conditionB.getLocalizedMessage(requireActivity().application)
                txtResistenciaB.text = it.getTextResistanceB()

                if (it.conditionA == ElectricalTestCondition.OK) {
                    txtCondicao.setTextColor(resources.getColor(R.color.green, null))
                    txtResistencia.setTextColor(resources.getColor(R.color.green, null))
                }

                /* Mostra informações para injetores DIFERENTES de INDUTIVO_DS */
                if (it.conditionA != ElectricalTestCondition.OK) {
                    txtCondicao.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                    txtResistencia.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                    txtResistencia.text = "---"
                    imageStatusTestElectricalFail.visibility = View.VISIBLE
                    releaseErrorButtons(it, electricTest)
                } else {
                    imageStatusTestElectricalOK.visibility = View.VISIBLE
                    releaseAllButtons(it, electricTest)
                }

                /* Mostra informações para injetores IGUAIS a INDUTIVO_DS */
                if (it.conditionB != ElectricalTestCondition.OK || it.conditionA != ElectricalTestCondition.OK) {
                    if (tipoInjetor == EnumInjectorType.INDUTIVO_DS) {
                        txtCondicaoB.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                        txtResistenciaB.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                        txtResistencia.text = "---"
                        txtResistenciaB.text = "---"
                        imageStatusTestElectricalFail.visibility = View.VISIBLE
                        releaseErrorButtons(it, electricTest)
                    }
                } else {
                    imageStatusTestElectricalOK.visibility = View.VISIBLE
                    txtCondicao.setTextColor(resources.getColor(R.color.green, null))
                    txtResistencia.setTextColor(resources.getColor(R.color.green, null))
                    releaseAllButtons(it, electricTest)
                }
            }
        }

        /**
         * Chama os métodos para iniciar o teste elétrico
         */
        electricTest.startTest()

    }

    /**
     * Avançar para o próximo teste (Estanqueidade)
     */
    @SuppressLint("ResourceType")
    private fun nextTestInjector(resultElectrical: ElectricalTestResult) {
        val args = Bundle()
        args.putSerializable(Extra.code, codeInjector)
        args.putSerializable(Extra.injector, injector)
        args.putSerializable(Extra.points, ArrayList<PointTestInjector>(listPoint))
        args.putSerializable(Extra.resultElectrical, resultElectrical)
        args.putSerializable(Extra.platform, platform)

        buttonElectricNext.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_electricalTestInjectorFragment_to_testRotationInjector, args)
        }
    }

    /**
     * Repetir o Teste Elétrico
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun repeatTestElectricalInjector() {
        buttonElectricRepeat.setOnClickListener {
            releaseRepeatElements()
            startTestElectricalInjector()
        }
    }

    /**
     * Cancelar teste elétrico
     */
    private fun cancelTestElectricInjector(result: ElectricalTestResult, electricTestInjector: IControllerTest) {
        buttonElectricCancel.setOnClickListener {
            if (result.status == EnumTestStatus.TEST_STARTING || result.status == EnumTestStatus.TEST_RUNNING) {
                electricTestInjector.cancelTest()

                val intent = Intent(activity, InjectorActivity::class.java)
                intent.putExtra(Extra.platform, platform)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                activity?.finish()
            } else {
                val intent = Intent(activity, InjectorActivity::class.java)
                intent.putExtra(Extra.platform, platform)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                activity?.finish()
            }
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
        visibilityButtonElectricNext = view.findViewById(R.id.groupButtonElectricalNext)
        visibilityButtonElectricRepeat = view.findViewById(R.id.groupButtonElectricalRepeat)
        visibilityButtonElectricCancel = view.findViewById(R.id.groupButtonElectricalCancel)
        buttonElectricNext = view.findViewById(R.id.btn_electric_test_next)
        buttonElectricRepeat = view.findViewById(R.id.btn_electric_test_repeat)
        buttonElectricCancel = view.findViewById(R.id.btn_electric_test_cancel)
        txtCondicao = view.findViewById(R.id.txt_condicao_value)!!
        txtResistencia = view.findViewById(R.id.txt_resistencia_value)!!
        txtCondicaoB = view.findViewById(R.id.txt_condicao_b_value)!!
        txtResistenciaB = view.findViewById(R.id.txt_resistencia_b_value)!!
        txtStatusTestEletric = view.findViewById(R.id.txt_status_eletric_inj_value)!!


        //txtCondicao.visibility = View.GONE
        //txtResistencia.visibility = View.GONE
        txtCondicaoB.visibility = View.GONE
        txtResistenciaB.visibility = View.GONE
        visibilityButtonElectricRepeat.visibility = View.GONE
        visibilityButtonElectricCancel.visibility = View.GONE
        visibilityButtonElectricNext.visibility = View.GONE
    }


    /**
     * Exibir todos os botões quando a condição do teste for "OK"
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun releaseAllButtons(electricalTestResult: ElectricalTestResult, electricTestInjector: IControllerTest) {
        visibilityButtonElectricRepeat.visibility = View.VISIBLE
        visibilityButtonElectricCancel.visibility = View.VISIBLE
        visibilityButtonElectricNext.visibility = View.VISIBLE

        repeatTestElectricalInjector()
        nextTestInjector(electricalTestResult)
        cancelTestElectricInjector(electricalTestResult, electricTestInjector)
    }

    /**
     * Exibir os botões quando o teste der "ERRO"
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun releaseErrorButtons(electricalTestResult: ElectricalTestResult, electricTestInjector: IControllerTest) {
        visibilityButtonElectricRepeat.visibility = View.VISIBLE
        visibilityButtonElectricCancel.visibility = View.VISIBLE
        visibilityButtonElectricNext.visibility = View.GONE

        repeatTestElectricalInjector()
        cancelTestElectricInjector(electricalTestResult, electricTestInjector)
    }

    /**
     * Ocultar os botões quando for repetir o teste
     */
    @SuppressLint("ResourceAsColor", "NewApi")
    private fun releaseRepeatElements() {
        visibilityButtonElectricRepeat.visibility = View.GONE
        visibilityButtonElectricNext.visibility = View.GONE
        visibilityButtonElectricCancel.visibility = View.GONE
        imageStatusTestElectricalOK.visibility = View.GONE
        imageStatusTestElectricalFail.visibility = View.GONE
        //txtCondicao.visibility = View.GONE
        //txtResistencia.visibility = View.GONE
        txtCondicaoB.visibility = View.GONE
        txtResistenciaB.visibility = View.GONE

        txtCondicao.text = "---"
        txtResistencia.text = "---"
        txtCondicaoB.text = "---"
        txtResistenciaB.text = "---"

        txtCondicao.setTextColor(resources.getColor(R.color.dark_gray, null))
        txtResistencia.setTextColor(resources.getColor(R.color.dark_gray, null))
        txtCondicaoB.setTextColor(resources.getColor(R.color.dark_gray, null))
        txtResistenciaB.setTextColor(resources.getColor(R.color.dark_gray, null))
    }

}