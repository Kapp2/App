package com.example.kapp2.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

data class PerfilConBotones(
    @Embedded val perfil: Perfil,
    @Relation(
        parentColumn = "nickname",
        entityColumn = "boton_id",
        associateBy = Junction(BotonPerfilCrossRef::class)
    )
    val botones: List<Boton>
)