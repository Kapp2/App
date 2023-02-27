package com.example.kapp2.db.relations

import androidx.room.Entity

@Entity(primaryKeys = ["boton_id", "perfil_id"])
data class BotonesFavoritosCrossRef(
    val boton_id: Long,
    val perfil_id: Long
)