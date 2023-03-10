package com.example.kapp2.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

data class BotonEnPerfiles(
    @Embedded val boton: Boton,
    @Relation(
        parentColumn = "boton_id",
        entityColumn = "nickname",
        associateBy = Junction(BotonPerfilCrossRef::class)
    )
    val perfiles: List<Perfil>
)