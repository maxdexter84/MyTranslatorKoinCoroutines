package ru.maxdexter.mytranslatorkoincoroutines.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import ru.maxdexter.mytranslatorkoincoroutines.db.HistoryModel
import ru.maxdexter.translatorcoincoroutine.databinding.ListHistoryItemBinding

class HistoryAdapter : ListAdapter<HistoryModel, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.from(parent)
    }
    class HistoryViewHolder private constructor(private val binding: ListHistoryItemBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(historyModel: HistoryModel){
            binding.historyItem = historyModel
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): HistoryViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListHistoryItemBinding.inflate(layoutInflater,parent,false)
                return HistoryViewHolder(binding)
            }
        }

    }

}

class HistoryDiffCallback : DiffUtil.ItemCallback<HistoryModel>(){
    override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
        return oldItem.query == newItem.query
    }

    override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
        return oldItem == newItem
    }

}

