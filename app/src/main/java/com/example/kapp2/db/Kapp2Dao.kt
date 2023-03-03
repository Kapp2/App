package com.example.kapp2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kapp2.db.relations.BotonEnPerfiles
import com.example.kapp2.db.relations.BotonPerfilCrossRef
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

@Dao
interface Kapp2Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBoton(boton: Boton)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerfil(perfil: Perfil)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBotonesFavoritos(crossRef: BotonPerfilCrossRef)

    @Delete
    suspend fun delPerfil(perfil: Perfil)

    @Delete
    suspend fun delBotonesFavoritos(crossRef: BotonPerfilCrossRef)

    @Transaction
    @Query("SELECT * FROM botones")
    fun getAllBotones(): LiveData<List<Boton>>

    @Transaction
    @Query("SELECT * FROM perfiles")
    fun getAllPerfiles(): LiveData<List<Perfil>>

    @Transaction
    @Query("SELECT * FROM botones WHERE tematica = :tematica")
    fun getBotonesFiltroTematica(tematica:Int): LiveData<List<Boton>>

    @Transaction
    @Query("SELECT * FROM botones")
    fun getBotonesFavoritos(): LiveData<List<BotonEnPerfiles>>

    @Transaction
    @Query("SELECT * FROM botones WHERE tematica = :tematica")
    fun getBotonesFavoritosFiltroTematica(tematica: Int): LiveData<List<BotonEnPerfiles>>

}