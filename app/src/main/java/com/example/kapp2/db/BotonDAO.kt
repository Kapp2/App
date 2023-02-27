package com.example.kapp2.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

interface BotonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBoton(boton: Boton)
    @Query("SELECT * FROM botones")
    fun getAllBoton(): LiveData<List<Boton>>
    @Query("SELECT * FROM botones WHERE tematica= :tema")
    fun getBotonesFiltroTematica(tema:Int): LiveData<List<Boton>>

}