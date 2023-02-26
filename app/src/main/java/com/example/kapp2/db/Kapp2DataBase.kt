package com.example.kapp2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

@Database(entities = [Boton::class], [Perfil::class], version = 1, exportSchema = false)
abstract class Kapp2DataBase : RoomDatabase(){

    abstract fun usuarioDAO(): PerfilDAO
}