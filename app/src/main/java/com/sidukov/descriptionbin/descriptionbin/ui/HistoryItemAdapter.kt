package com.sidukov.descriptionbin.descriptionbin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sidukov.descriptionbin.R
import com.sidukov.descriptionbin.descriptionbin.data.local.EntityHistoryBin

class HistoryItemAdapter(private var list: List<EntityHistoryBin>) :
    RecyclerView.Adapter<HistoryItemAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.request_history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.card.text = list[position].bin
        holder.country.text = list[position].country
        holder.bank.text = list[position].bank
        holder.time.text = list[position].time
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<EntityHistoryBin>) {
        list = newList
        notifyDataSetChanged()
    }


    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: TextView = itemView.findViewById(R.id.item_card)
        val country: TextView = itemView.findViewById(R.id.item_country)
        val bank: TextView = itemView.findViewById(R.id.item_bank)
        val time: TextView = itemView.findViewById(R.id.item_time)
    }
}
