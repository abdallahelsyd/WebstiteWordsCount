package com.instabug.instabugwordscount.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.instabug.instabugwordscount.databinding.ListItemBinding
import com.instabug.instabugwordscount.domain.models.WordItem

class WordsListAdapter() : ListAdapter<WordItem, WordsListAdapter.WordsViewHolder>(ItemDiffUtil) {
    private object ItemDiffUtil : DiffUtil.ItemCallback<WordItem>() {
        override fun areItemsTheSame(oldItem: WordItem, newItem: WordItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: WordItem, newItem: WordItem) = oldItem.word == newItem.word
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        return WordsViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WordsViewHolder(private val binder: ListItemBinding) :
        RecyclerView.ViewHolder(binder.root) {

        fun bind(item: WordItem) = with(binder) {
            binder.tvWord.text=item.word
            binder.tvCount.text=item.count.toString()
        }
    }

}