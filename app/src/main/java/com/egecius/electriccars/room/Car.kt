package com.egecius.electriccars.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey
    val name: String,
    val img: String
) : Serializable
