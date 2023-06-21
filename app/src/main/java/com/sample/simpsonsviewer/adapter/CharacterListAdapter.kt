package com.sample.simpsonsviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.databinding.RowItemCharacterBinding
import com.sample.simpsonsviewer.models.Character

class CharacterListAdapter: ListAdapter<Character, CharacterListAdapter.ViewHolder>(DiffUtilCallback) {

    private var itemClickListener: ((character: Character) -> Unit)? = null

    fun setItemClickListener(listener: (character: Character) -> Unit) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    object DiffUtilCallback: DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.description == newItem.description
        }
    }

    inner class ViewHolder(
        private val binding: RowItemCharacterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            binding.title.text = item.title
            binding.container.setOnClickListener {
                itemClickListener?.invoke(item)
            }
        }

    }
}