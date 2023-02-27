package com.example.kapp2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

@Dao
interface Kapp2Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBoton(boton: Boton)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPerfil(perfil: Perfil)
    @Delete
    fun delPerfil(perfil: Perfil)
    @Query("SELECT * FROM botones")
    fun getAllBoton(): LiveData<List<Boton>>
    @Query("SELECT * FROM perfiles")
    fun getAllPerfiles(): LiveData<List<Perfil>>
    @Query("SELECT * FROM botones WHERE tematica= :tema")
    fun getBotonesFiltroTematica(tema:Int): LiveData<List<Boton>>


}