package com.example.kapp2.db.relations

import androidx.room.Entity

@Entity(primaryKeys = ["boton_id", "nickname"])
data class BotonPerfilCrossRef(
    val boton_id: Long,
    val nickname: String
)