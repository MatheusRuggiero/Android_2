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
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.com.tecnomotor.commonrail.device.commands.injector.result.TightnessTestResult
import br.com.tecnomotor.commonrail.device.repository.CommonRailRepository
import br.com.tecnomotor.commonrail.device.utils.EnumLeakTestCondition
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.controller.test.IControllerTest
import br.com.tecnomotor.marvin.controller.test.injector.demo.TightnessTestInjectorControllerDemo
import br.com.tecnomotor.marvin.dao.entities.injector.InjectorTestReportEntity
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.injector.Injector
import br.com.tecnomotor.marvin.model.injector.PointTestInjector
import br.com.tecnomotor.marvin.repository.injector.InjectorRepository
import br.com.tecnomotor.marvin.repository.injector.InjectorTestReportRepository
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.injector.InjectorActivity
import br.com.tecnomotor.marvin.view.viewmodel.factory.injector.module.TestTightnessViewModelFactory
import br.com.tecnomotor.marvin.view.viewmodel.injector.module.TestTightnessViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class TestTightnessInjectorFragment : Fragment() {

    private lateinit var deviceRepository: CommonRailRepository
    private var injector: Injector? = null
    private var root: View? = null
    private var platform: Platform? = null

    //    private lateinit var repositoryInjector: InjectorRepository
    private val viewModel by lazy {
        val injectorRepository = InjectorRepository(requireActivity().application)
        val injectorTestReportRepository = InjectorTestReportRepository(requireActivity().application)
        val factory = TestTightnessViewModelFactory(injectorRepository, injectorTestReportRepository)
        ViewModelProvider(this, factory).get(TestTightnessViewModel::class.java)
    }
    private lateinit var listPoint: MutableList<PointTestInjector>
    private val controller by lazy {
        findNavController()
    }

    /* Componentes Layout */
    private lateinit var imageStatusTestTightnessOK: ImageView
    private lateinit var imageStatusTestTightnessFail: ImageView
    private lateinit var txtCondicao: TextView
    private lateinit var txtStatusTestTightness: TextView
    private lateinit var processBar: ProgressBar

    /* Componentes BUTTONS */
    private lateinit var visibilityButtonTightnessNext: Group
    private lateinit var visibilityButtonTightnessSave: Group
    private lateinit var visibilityButtonTightnessFinish: Group
    private lateinit var visibilityButtonTightnessCancel: Group
    private lateinit var visibilityButtonTightnessSkip: Group
    private lateinit var buttonTightnessNext: ImageButton
    private lateinit var buttonTightnessSave: ImageButton
    private lateinit var buttonTightnessCancel: ImageButton
    private lateinit var buttonTightnessSkip: ImageButton
    private lateinit var buttonTightnessFinish: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_tightness_injector, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startVariable(view)

        super.onViewCreated(view, savedInstanceState)
        println("Argumentos: ${controller.graph.arguments.size}")
        controller.graph.arguments.forEach {
            println("Argumento[${it.key}]")
        }
        println("FragmentArgs: ${this.arguments?.size()}")
        println(this.arguments.toString())
        val args = this.arguments
        if ((args != null) && (args.size() > 0)) {
            injector = args[Extra.injector] as Injector
            listPoint = args[Extra.points] as MutableList<PointTestInjector>
            platform = args[Extra.platform] as Platform
//            while (true) {
//                if ((deviceViewModel.getDeviceControle() != null) && (deviceViewModel.getDeviceMedicao() != null))
//                    break
//            }
            startTestTightnessInjector()
        } else {
            imageStatusTestTightnessFail.visibility = View.VISIBLE
            txtStatusTestTightness.text = EnumTestStatus.TEST_FAIL.getLocalizedMessage(requireActivity().application)
            txtStatusTestTightness.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
        }
    }

    /**
     * Start Tightness Test Injector
     */
    @SuppressLint("ResourceAsColor", "NewApi")
    @OptIn(DelicateCoroutinesApi::class)
    private fun startTestTightnessInjector() {
        /***
         * Pegar os parâmetros default do banco de dados
         * Usar o viewModel agora para consultas
         */

        //Pegar os parâmetros default do banco de dados
//        val globalScopeSearchInjector = GlobalScope.launch(Dispatchers.IO) {
//            repositoryInjector = InjectorRepository(requireActivity().application)
//        }

        // ISSO AQUI TEM QUE VIR DO BANCO DE DADOS *ROGERIO
        /* PONTOS DE TESTE INJETORES */
        val pontoDeTeste: HashMap<String, ByteArray> =
            hashMapOf(
                "injecaoAtiva" to byteArrayOf(0x00.toByte()),               //1 byte
                "medirInjecao" to byteArrayOf(0x00.toByte()),               //1 byte
                "medirRetorno" to byteArrayOf(0x01.toByte()),               //1 byte
                "pressaoStep" to byteArrayOf(0x05.toByte(), 0x14.toByte()),  //2 byte
                "frequencia" to byteArrayOf(0x00.toByte(), 0x00.toByte()),  //2 byte
                "tempoInjecao" to byteArrayOf(0x00.toByte(), 0x00.toByte()), //2 byte
                "tempoLigado" to byteArrayOf(0x00.toByte()),                //1 byte
                "tempoDesligado" to byteArrayOf(0x00.toByte()),             //1 byte
                "tempoDoSinalEmAlta" to byteArrayOf(0x00.toByte()),         //1 byte
                "tempo12Volts" to byteArrayOf(0x00.toByte(), 0x00.toByte()), //2 byte
                "preEncherMedidoInjecao" to byteArrayOf(0x00.toByte()),     //1 byte
                "tempoDoSinalDesligado" to byteArrayOf(0x00.toByte()),      //1 byte
                "tensaoBaixa" to byteArrayOf(0x0C.toByte()),                //1 byte
                "tensaoAlta" to byteArrayOf(0x3C.toByte())                  //1 byte
            )

//        val tightnessTest = TightnessTestInjectorController(CommandsTesteEstanqueidade(deviceViewModel.repository), pontoDeTeste)
        val tightnessTest = TightnessTestInjectorControllerDemo()

//        globalScopeSearchInjector.cancel()

        /**
         * Chama os métodos para iniciar o teste estanqueidade
         */
        tightnessTest.startTest()

        /**
         * Mostra as informações do teste na view
         * Manipula elementos da view
         */
        tightnessTest.TightnessTestResult.observeForever {
            println("View::TESTE ESTANQUEIDADE::  + $it")

            if (it.status != EnumTestStatus.NONE) {

                /* Mostra o status do teste na tela */
                if (isAdded) { //Previne problema na hora de redirecionar de fragment para activity
                    txtStatusTestTightness.text = it.status.getLocalizedMessage(requireActivity().application)
                }

                if (it.status == EnumTestStatus.TEST_STARTING || it.status == EnumTestStatus.TEST_RUNNING || it.status == EnumTestStatus.TEST_WAITING) {
                    txtCondicao.setTextColor(resources.getColor(R.color.dark_gray, null))
                    txtStatusTestTightness.setTextColor(resources.getColor(R.color.dark_gray, null))
                    processBar.visibility = View.VISIBLE

                    /* Exibir botões que são usados durante a EXECUÇÃO do teste. */
                    releaseButtonsTestRunning(tightnessTest, it)
                }
                if (it.status == EnumTestStatus.TEST_WARNING) {
                    processBar.visibility = View.GONE
                    if (it.condition != EnumLeakTestCondition.OK) {
                        txtCondicao.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                        txtStatusTestTightness.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                        imageStatusTestTightnessFail.visibility = View.VISIBLE
                    } else {
                        txtCondicao.setTextColor(resources.getColor(R.color.dark_orange, null))
                        txtStatusTestTightness.setTextColor(resources.getColor(R.color.dark_orange, null))
                        imageStatusTestTightnessFail.visibility = View.VISIBLE
                    }

                    /* Exibir botões que são usados quando ocorre um ERRO no teste */
                    releaseButtonsTestError(tightnessTest, it)
                }
            }

            if (it.status == EnumTestStatus.TEST_FINISHED) {

                /* Mostra o condição do teste na tela */
                txtCondicao.text = it.condition.getLocalizedMessage(requireActivity().application)
                processBar.visibility = View.GONE

                if (it.condition != EnumLeakTestCondition.OK) {
                    txtCondicao.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                    txtStatusTestTightness.setTextColor(resources.getColor(R.color.vermelho_tecnomotor, null))
                    imageStatusTestTightnessFail.visibility = View.VISIBLE
                    imageStatusTestTightnessOK.visibility = View.INVISIBLE

                    /* Exibir botões que são usados quando ocorre um ERRO no teste */
                    releaseButtonsTestError(tightnessTest, it)
                } else {
                    txtCondicao.setTextColor(resources.getColor(R.color.green, null))
                    txtStatusTestTightness.setTextColor(resources.getColor(R.color.green, null))
                    imageStatusTestTightnessOK.visibility = View.VISIBLE
                    imageStatusTestTightnessFail.visibility = View.INVISIBLE


                    /* Exibir botões que são usados quando o teste estiver OK */
                    releaseButtonsTestOK()
                }

            }

        }

    }

    /**
     * Agrupar informações do teste
     */
    private fun bundleArgs(): Bundle {
        val args = Bundle()
        args.putSerializable(Extra.injector, injector)
        args.putSerializable(Extra.points, ArrayList<PointTestInjector>(listPoint))
        args.putSerializable(Extra.platform, platform)
        return args
    }

    /**
     * Avança para os Pontos de Teste Injetor
     */
    private fun nextTestInjector() {
        val args = bundleArgs()
        buttonTightnessNext.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_test_tightness_injector_fragment_to_point_test_injector_fragment, args)
        }
    }

    /**
     * Pular teste atual e ir para o próximo
     */
    private fun skipTestInjector(tightnessTestInjector: IControllerTest) {
        buttonTightnessSkip.setOnClickListener {
            val args = bundleArgs()
            tightnessTestInjector.skipTest()
            Navigation.findNavController(requireView()).navigate(R.id.action_test_tightness_injector_fragment_to_point_test_injector_fragment, args)
        }
    }

    /**
     * Cancelar Teste
     */
    private fun cancelTestInjector(tightnessTestInjector: IControllerTest, result: TightnessTestResult) {
        buttonTightnessCancel.setOnClickListener {
            if (result.status == EnumTestStatus.TEST_STARTING || result.status == EnumTestStatus.TEST_RUNNING) {
                tightnessTestInjector.cancelTest()
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
     * Finalizar Test
     * Status diferente de OK
     */
    private fun finishTestInjector(tightnessTestInjector: IControllerTest) {
        buttonTightnessFinish.setOnClickListener {
//            tightnessTestInjector.finishTest()
            val intent = Intent(activity, InjectorActivity::class.java)
            intent.putExtra(Extra.platform, platform)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            activity?.finish()
        }
    }

    /**
     * Gravar as informações do teste no Banco de Dados
     * Status diferente de OK
     */
    private fun saveTestInjector(tightnessTestInjector: IControllerTest, result: TightnessTestResult) {
        buttonTightnessSave.setOnClickListener {

            /* Precisa fazer a ação de Salvar(Gravar) */
            val injectorTestReportEntity = InjectorTestReportEntity()
            injectorTestReportEntity.injConditionTestTightness = result.condition.name
            injectorTestReportEntity.injStatusTestTightness = result.status.name
            viewModel.repository2.save(injectorTestReportEntity)

            releaseButtonsTestFinish(tightnessTestInjector) // Botões Teste Finalizado
        }
    }


    /**
     *  INICIALIZAR COMPONENTES DE LAYOUT
     *  E EXIBIR BOTÕES E PROGRESS BAR
     */


    /**
     * Inicializar variáveis componentes layouts e button
     */
    private fun startVariable(view: View) {
        imageStatusTestTightnessOK = view.findViewById(R.id.status_test_tightness_ok)
        imageStatusTestTightnessFail = view.findViewById(R.id.status_test_tightness_fail)
        txtCondicao = view.findViewById(R.id.txt_condition_tightness_injector_value)!!
        txtCondicao.text = "---"
        txtStatusTestTightness = view.findViewById(R.id.txt_status_test_tightness_injector_value)!!
        processBar = view.findViewById(R.id.processBarTestTightness)
        visibilityButtonTightnessNext = view.findViewById(R.id.groupButtonTightnessNext)
        visibilityButtonTightnessCancel = view.findViewById(R.id.groupButtonTightnessCancel)
        visibilityButtonTightnessFinish = view.findViewById(R.id.groupButtonTightnessFinish)
        visibilityButtonTightnessSave = view.findViewById(R.id.groupButtonTightnessSave)
        visibilityButtonTightnessSkip = view.findViewById(R.id.groupButtonTightnessSkip)
        buttonTightnessNext = view.findViewById(R.id.btn_tightness_test_next)
        buttonTightnessSave = view.findViewById(R.id.btn_tightness_test_save)
        buttonTightnessCancel = view.findViewById(R.id.btn_tightness_test_cancel)
        buttonTightnessSkip = view.findViewById(R.id.btn_tightness_test_skip)
        buttonTightnessFinish = view.findViewById(R.id.btn_tightness_test_finish)

        visibilityButtonTightnessNext.visibility = View.GONE
        visibilityButtonTightnessCancel.visibility = View.GONE
        visibilityButtonTightnessFinish.visibility = View.GONE
        visibilityButtonTightnessSave.visibility = View.GONE
        visibilityButtonTightnessSkip.visibility = View.GONE

    }

    /**
     * Exibir os botões quando o teste tiver o status "OK"
     */
    private fun releaseButtonsTestOK() {
        visibilityButtonTightnessNext.visibility = View.VISIBLE
        visibilityButtonTightnessCancel.visibility = View.VISIBLE
        visibilityButtonTightnessFinish.visibility = View.GONE
        visibilityButtonTightnessSave.visibility = View.GONE
        visibilityButtonTightnessSkip.visibility = View.GONE

        nextTestInjector()
    }

    /**
     * Exibir botões enquanto o teste estiver sendo "Executado"
     */
    private fun releaseButtonsTestRunning(tightnessTestInjector: IControllerTest, TightnessTestResult: TightnessTestResult) {
        visibilityButtonTightnessCancel.visibility = View.VISIBLE
        visibilityButtonTightnessSkip.visibility = View.VISIBLE

        skipTestInjector(tightnessTestInjector)
        cancelTestInjector(tightnessTestInjector, TightnessTestResult)
    }

    /**
     * Exibir botões quando o teste de algum ERRO
     */
    private fun releaseButtonsTestError(tightnessTestInjector: IControllerTest, TightnessTestResult: TightnessTestResult) {
        visibilityButtonTightnessSave.visibility = View.VISIBLE
        visibilityButtonTightnessSkip.visibility = View.GONE

        cancelTestInjector(tightnessTestInjector, TightnessTestResult)
        saveTestInjector(tightnessTestInjector, TightnessTestResult)
    }

    /**
     * Exibir botões quando o teste for FINALIZADO
     */
    private fun releaseButtonsTestFinish(tightnessTestInjector: IControllerTest) {
        visibilityButtonTightnessFinish.visibility = View.VISIBLE
        visibilityButtonTightnessSave.visibility = View.GONE

        finishTestInjector(tightnessTestInjector)
    }

}