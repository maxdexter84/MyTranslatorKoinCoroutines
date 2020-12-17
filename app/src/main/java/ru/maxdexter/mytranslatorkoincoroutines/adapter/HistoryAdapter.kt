package ru.maxdexter.mytranslatorkoincoroutines.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import ru.maxdexter.mytranslatorkoincoroutines.db.HistoryModel
import ru.maxdexter.mytranslatorkoincoroutines.model.DetailModel
import ru.maxdexter.translatorcoincoroutine.databinding.ListHistoryItemBinding

class HistoryAdapter(private val listener: OnListItemClickListener) : ListAdapter<DetailModel, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.from(parent)
    }

    interface OnListItemClickListener{
        fun onClick(detailModel: DetailModel)
    }
   class HistoryViewHolder private constructor(private val binding: ListHistoryItemBinding): RecyclerView.ViewHolder(binding.root){


        fun bind(detailModel: DetailModel,listener: OnListItemClickListener ){
            binding.tvHeaderHistoryItem.text = detailModel.word
            binding.tvDescriptionHistoryItem.text = detailModel.translate
            itemView.setOnClickListener {
                listener.onClick(detailModel)
            }
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

class HistoryDiffCallback : DiffUtil.ItemCallback<DetailModel>(){
    override fun areItemsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
        return oldItem.word == newItem.word
    }

    override fun areContentsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
        return oldItem == newItem
    }



}

