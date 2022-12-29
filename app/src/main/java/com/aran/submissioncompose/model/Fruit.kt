package com.aran.submissioncompose.model

data class Fruit(
    val id: Long,
    val name: String,
    val desc: String,
    val rating: String,
    val mineral: String,
    val vitC: String,
    val vitA: String,
    val image: Int,
    val price: Int
)