package br.com.tecnomotor.marvin.view.pump.tests

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.fromHtml
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.model.global.Platform
import br.com.tecnomotor.marvin.model.pump.PointTestPump
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.model.pump.PumpPlanTest
import br.com.tecnomotor.marvin.model.pump.TypeReferencePump
import br.com.tecnomotor.marvin.view.Extra
import br.com.tecnomotor.marvin.view.pump.PumpActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@DelicateCoroutinesApi
class InstructionsConnectorsPumpFragment : Fragment() {

    private lateinit var buttonCancel: ImageButton
    private lateinit var buttonOk: ImageButton
    private lateinit var txtInfoConexaoBomba01: TextView
    private lateinit var txtInfoConexaoBomba02: TextView
    private lateinit var txtInfoConexaoBomba03: TextView

    private lateinit var listPoint: MutableList<PointTestPump>
    private var root: View? = null
    private lateinit var plan: PumpPlanTest
    private lateinit var pump: Pump
    private lateinit var platform: Platform
    private lateinit var typeReferencePump: TypeReferencePump
    private val navController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        root = inflater.inflate(R.layout.fragment_instructions_connectors_pump, container, false)
        return root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startVariable(view)

        super.onViewCreated(view, savedInstanceState)
        println("Argumentos: ${navController.graph.arguments.size}")
        navController.graph.arguments.forEach {
            println("Argumento[${it.key}]")
        }
        println("FragmentArgs: ${this.arguments?.size()}")
        this.arguments.let {
            println(it.toString())
        }
        val args = this.arguments
        if ((args != null) && (args.size() > 0)) {

            pump = args[Extra.pump] as Pump
            plan = args[Extra.plan] as PumpPlanTest
            platform = args[Extra.platform] as Platform
            typeReferencePump = args[Extra.typeReferencePump] as TypeReferencePump
            listPoint = args[Extra.points] as MutableList<PointTestPump>
        }

        setVariable()
        redirectExecuteTestPump()
        redirectCancelInstructions()
    }

    /**
     * Inicializa componentes visuais
     */
    private fun startVariable(view: View) {
        buttonCancel = view.findViewById(R.id.btn_instructions_cancel)
        buttonOk = view.findViewById(R.id.btn_ok_instructions)
        txtInfoConexaoBomba01 = view.findViewById(R.id.txt_info_conexao_pump_01)
        txtInfoConexaoBomba02 = view.findViewById(R.id.txt_info_conexao_pump_02)
        txtInfoConexaoBomba03 = view.findViewById(R.id.txt_info_conexao_pump_03)

        txtInfoConexaoBomba02.visibility = View.GONE
        txtInfoConexaoBomba03.visibility = View.GONE
    }

    /**
     * Preenche instruções com valores provenientes do banco
     */
    private fun setVariable() {
        var valueActuatorEXT1 = ""
        var valueActuatorEXT2 = ""

        if (pump.referenceActuator == 5){ // EXT1
            txtInfoConexaoBomba02.visibility = View.VISIBLE
            txtInfoConexaoBomba03.visibility = View.GONE
            valueActuatorEXT1 = pump.nameActuator!!.replace("/ -", "")
        }
        if (pump.referenceActuator == 6){ // EXT1 / EXT2
            txtInfoConexaoBomba02.visibility = View.VISIBLE
            txtInfoConexaoBomba03.visibility = View.VISIBLE
            val listValueActuator = pump.nameActuator!!.split(" / ")
            valueActuatorEXT1 = listValueActuator[0]
            valueActuatorEXT2 = listValueActuator[1]
        }
        if (pump.referenceActuator == 14){ // EXT2
            txtInfoConexaoBomba02.visibility = View.GONE
            txtInfoConexaoBomba03.visibility = View.VISIBLE
            valueActuatorEXT2 = pump.nameActuator!!.replace("- /", "")
        }

        val text01: String = getString(R.string.txt_info_conexao_pump_01, "DRV1", "DRV2")
        val text01Formatted = fromHtml(text01, FROM_HTML_MODE_COMPACT)
        val text02: String = getString(R.string.txt_info_conexao_pump_02, "EXT-1", "---", valueActuatorEXT1)
        val text02Formatted = fromHtml(text02, FROM_HTML_MODE_COMPACT)
        val text03: String = getString(R.string.txt_info_conexao_pump_03, "EXT-2", "---", valueActuatorEXT2)
        val text03Formatted = fromHtml(text03, FROM_HTML_MODE_COMPACT)

        txtInfoConexaoBomba01.text = text01Formatted
        txtInfoConexaoBomba02.text = text02Formatted
        txtInfoConexaoBomba03.text = text03Formatted
    }

    /**
     * Redireciona para a view de Executar Test Bomba
     */
    private fun redirectExecuteTestPump() {

        buttonOk.setOnClickListener {
            val args = Bundle()
            args.putSerializable(Extra.pump, pump)
            args.putSerializable(Extra.plan, plan)
            args.putSerializable(Extra.platform, platform)
            args.putSerializable(Extra.points, ArrayList<PointTestPump>(listPoint))
            args.putSerializable(Extra.typeReferencePump, typeReferencePump)
            Navigation.findNavController(requireView()).navigate(R.id.action_instructionsConnectorsPump_to_electricalTestPumppointTestPumpFragment, args)
        }
    }

    /**
     * Cancelar tela de instruções
     */
    @InternalCoroutinesApi
    private fun redirectCancelInstructions() {
        buttonCancel.setOnClickListener {
            val intent = Intent(activity, PumpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra(Extra.platform, platform)
            startActivity(intent)
            activity?.finish()
        }
    }

}