package ru.maxdexter.mytranslatorkoincoroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import ru.maxdexter.mytranslatorkoincoroutines.model.SearchResult
import ru.maxdexter.translatorcoincoroutine.databinding.ListItemTranslatorBinding


class MainAdapter(): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var list: List<SearchResult>? = null
    inner class ViewHolder(private var binding: ListItemTranslatorBinding): RecyclerView.ViewHolder(binding.root) {


        fun bind(data: SearchResult){
            binding.apply {
                tvHeader.text = data.text
                tvDescription.text = data.meanings?.get(0)?.translation?.translation
            }
            setItemClickListener {
                onItemClickListener?.invoke(data)
            }
        }


    }

    fun setData(list: List<SearchResult>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemTranslatorBinding.inflate(layoutInflater,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = list?.get(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    private var onItemClickListener: ((SearchResult)->Unit)? = null

    fun setItemClickListener(listener: ((SearchResult) -> Unit)){
            onItemClickListener = listener
    }

}
