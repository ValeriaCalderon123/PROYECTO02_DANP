package com.example.lab05_room.data.entity

data class Animals(
    val taxonid: Int,
    val kingdom_name: String?,
    val phylum_name: String?,
    val class_name: String?,
    val order_name: String?,
    val family_name: String?,
    val genus_name: String?,
    val scientific_name: String?,
    val taxonomic_authority: String?,
    val infra_rank: String?,
    val infra_name: String?,
    val population: String?,
    val category: String?,
    val main_common_name: String?
)
