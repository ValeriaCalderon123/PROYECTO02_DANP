package com.example.lab05_room

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab05_room.data.entity.Animals
import com.example.proyecto02_danp.R

class AnimalsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val commonNameCard: TextView = view.findViewById(R.id.nameAnimalCard)
    private val phylumNameCard: TextView = view.findViewById(R.id.phylumName)
    private val kingdomNameCard: TextView = view.findViewById(R.id.kindomName)
    private val scientificNameCard: TextView = view.findViewById(R.id.nameScience)
    private val speciesNameCard: TextView = view.findViewById(R.id.nameSpecies)

    fun bind(animal: Animals){
        with(animal) {
            commonNameCard.text = main_common_name
            phylumNameCard.text = phylum_name
            kingdomNameCard.text = kingdom_name
            scientificNameCard.text = scientific_name
            speciesNameCard.text = class_name
        }
    }

}
