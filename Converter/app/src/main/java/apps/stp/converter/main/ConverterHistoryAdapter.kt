package apps.stp.converter.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import apps.stp.converter.R
import apps.stp.converter.data.HistoryItem
import java.util.*

class ConverterHistoryAdapter  : RecyclerView.Adapter<ConverterHistoryAdapter.ItemViewHolder>() {
    private var mHistoryItemsList = ArrayList<HistoryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false))

    override fun getItemCount() = mHistoryItemsList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(mHistoryItemsList[position])

    fun addItem(item: HistoryItem) {
        mHistoryItemsList.add(item)
        notifyItemInserted(itemCount - 1)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val inputNumber: TextView = itemView.findViewById(R.id.inputNumber)
        private val resultNumber: TextView = itemView.findViewById(R.id.resultNumber)
        private val inputNumberSystem: TextView = itemView.findViewById(R.id.inputNumberSystem)
        private val resultNumberSystem: TextView = itemView.findViewById(R.id.resultNumberSystem)

        fun bind(item: HistoryItem) {
            inputNumber.text = item.inputNumber
            resultNumber.text = item.resultNumber
            inputNumberSystem.text = item.inputNumberSystem
            resultNumberSystem.text = item.resultNumberSystem
        }
    }
}