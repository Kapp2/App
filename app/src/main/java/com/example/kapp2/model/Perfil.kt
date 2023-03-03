package com.example.kapp2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfiles")
data class Perfil(
    @PrimaryKey(autoGenerate = false)
    val nickname: String,
    val password:String
)
