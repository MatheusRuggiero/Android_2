package br.com.tecnomotor.marvin.adapter.pump

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.tecnomotor.marvin.R
import br.com.tecnomotor.marvin.adapter.generic.pump.PointPumpGeneric
import br.com.tecnomotor.marvin.model.pump.Pump
import br.com.tecnomotor.marvin.view.Extra.pump

class PumpListTestPointAdapter(
    private val context: Context,
    private val pump: Pump,
    private val list: MutableList<PointPumpGeneric> = mutableListOf(),
    var whenItemClicked: (obj: PointPumpGeneric) -> Unit = {}
) : RecyclerView.Adapter<PumpListTestPointAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PumpListTestPointAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pump_recyclerview_points, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = list[position]
        holder.bind(pumpPointTest = obj, obj.statusCheckBox)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var pumpPointTest: PointPumpGeneric

        init {
            itemView.setOnClickListener {
                if (::pumpPointTest.isInitialized) {
                    whenItemClicked(pumpPointTest)
                }
            }

            itemView.findViewById<CheckBox>(R.id.checkBox_pump).setOnClickListener {
                if (::pumpPointTest.isInitialized) {
                    whenItemClicked(pumpPointTest)
                }
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(pumpPointTest: PointPumpGeneric, checkbox: Boolean) {
            this.pumpPointTest = pumpPointTest
            var descriptionPump = ""
            var rotacaoPointTeste = ""

            if (!pumpPointTest.pointTestPump?.nameGeneric.isNullOrEmpty())
                descriptionPump = "${pumpPointTest.pointTestPump?.nameGeneric.toString()} -"

            descriptionPump = "$descriptionPump ${pumpPointTest.pointTestPump?.typePointTest?.description}"

//            TODO apenas para teste, descomentar esta linha e comentar linhas abaixo
//            rotacaoPointTeste = "${pumpPointTest.pointTestPump?.rotation}"
            val pressao = pumpPointTest.pointTestPump?.pressureTest // debug
            val principal = if(pumpPointTest.pointTestPump?.measureMain == true) "principal" else "" // debug
            val retorno = if(pumpPointTest.pointTestPump?.measureReturn == true) "retorno" else "" // debug
            val ext1corrente = pumpPointTest.pointTestPump?.actuator1Values ?: 0 // corrente ext1
            val ext1freq = pumpPointTest.pointTestPump?.actuator1ValuesActivation ?: 0 // frequência ext1
            val ext2corrente = pumpPointTest.pointTestPump?.actuator2Values ?: 0 // corrente ext2
            val ext2freq = pumpPointTest.pointTestPump?.actuator2ValuesActivation ?: 0 // frequência ext2
            val refAct = pump.referenceActuator
            //rotacaoPointTeste = "Ext1: $ext1corrente/$ext1freq | Ext2: $ext2corrente/$ext2freq | Ref. Act. ${refAct.toString().padStart(2)} | Pressão: ${pressao.toString().padStart(4)} | ${principal.padStart(9)} | ${retorno.padStart(9)} | Rotação: ${pumpPointTest.pointTestPump?.rotation.toString().padStart(4)}" // debug
            rotacaoPointTeste = pumpPointTest.pointTestPump?.rotation.toString()

            itemView.findViewById<TextView>(R.id.description_point_pump).text = descriptionPump
            itemView.findViewById<TextView>(R.id.txtValueExt1).text = "Ext1: $ext1corrente A: $ext1freq Hz | "
            itemView.findViewById<TextView>(R.id.txtValueExt2).text = "Ext2: $ext2corrente A: $ext2freq Hz | "
            itemView.findViewById<TextView>(R.id.txtValueAtuador).text = "Atuador: $refAct | "
            itemView.findViewById<TextView>(R.id.txtValuePressure).text = "$pressao bar | "
            itemView.findViewById<TextView>(R.id.txtValueMedPrincipal).text = "$principal | "
            itemView.findViewById<TextView>(R.id.txtValueMedRetorno).text = "$retorno | "
            itemView.findViewById<TextView>(R.id.txtValueRotation).text = rotacaoPointTeste
            itemView.findViewById<CheckBox>(R.id.checkBox_pump).isChecked = checkbox
        }
    }


    fun update(list: List<PointPumpGeneric>) {
        notifyItemRangeRemoved(0, this.list.size)
        this.list.clear()
        this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
    }

    /**
     * Atualiza apenas um item
     */
    fun update(position: Int) {
        notifyItemChanged(position)
    }

}
