package com.example.kapp2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kapp2.db.realtions.BotonesFavoritosCrossRef
import com.example.kapp2.db.realtions.PerfilConBotones
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

@Dao
interface Kapp2Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBoton(boton: Boton)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerfil(perfil: Perfil)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBotonesFavoritosCrossRef(crossRef: BotonesFavoritosCrossRef)

    @Delete
    suspend fun delPerfil(perfil: Perfil)

    @Transaction
    @Query("SELECT * FROM botones")
    fun getAllBotones(): LiveData<List<Boton>>

    @Transaction
    @Query("SELECT * FROM perfiles")
    fun getAllPerfiles(): LiveData<List<Perfil>>

    @Transaction
    @Query("SELECT * FROM perfiles WHERE perfil_id = :perfilId")
    suspend fun getBotonesOfPerfil(perfilId: Long?): List<PerfilConBotones>

    @Query("SELECT * FROM botones WHERE tematica = :tematica")
    fun getBotonesFiltroTematica(tematica:Int): LiveData<List<Boton>>


}