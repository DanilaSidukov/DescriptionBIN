package com.sidukov.descriptionbin.descriptionbin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sidukov.descriptionbin.R
import com.sidukov.descriptionbin.descriptionbin.data.History

class HistoryItemAdapter(private var list: List<History>) :
    RecyclerView.Adapter<HistoryItemAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.request_history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.card.text = list[position].cardName
        holder.country.text = list[position].countryName
        holder.bank.text = list[position].bankName
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<History>) {
        list = newList
        notifyDataSetChanged()
    }


    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: TextView = itemView.findViewById(R.id.item_card)
        val country: TextView = itemView.findViewById(R.id.item_country)
        val bank: TextView = itemView.findViewById(R.id.item_bank)
    }


}