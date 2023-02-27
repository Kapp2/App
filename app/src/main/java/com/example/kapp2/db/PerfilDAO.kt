package com.example.kapp2.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kapp2.model.Perfil

interface PerfilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPerfil(perfil: Perfil)
    @Delete
    fun delPerfil(perfil: Perfil)
    @Query("SELECT * FROM perfiles")
    fun getAllPerfiles(): LiveData<List<Perfil>>

}