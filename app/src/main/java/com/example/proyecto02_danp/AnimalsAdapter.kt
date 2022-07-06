package com.example.proyecto02_danp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.lab05_room.AnimalsViewHolder
import com.example.lab05_room.data.entity.Animals


class AnimalsAdapter : PagingDataAdapter<Animals, AnimalsViewHolder>(AnimalsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_row, parent, false)
        return AnimalsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    object AnimalsComparator : DiffUtil.ItemCallback<Animals>() {
        override fun areItemsTheSame(oldItem: Animals, newItem: Animals): Boolean {
            return oldItem.taxonid == newItem.taxonid
        }

        override fun areContentsTheSame(oldItem: Animals, newItem: Animals): Boolean {
            return oldItem == newItem
        }
    }

}
