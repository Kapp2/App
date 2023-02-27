package com.example.kapp2.db.realtions

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

data class PerfilConBotones(
    @Embedded val perfil: Perfil,
    @Relation(
        parentColumn = "perfil_id",
        entityColumn = "boton_id",
        associateBy = Junction(BotonesFavoritosCrossRef::class)
    )
    val botones: List<Boton>
)