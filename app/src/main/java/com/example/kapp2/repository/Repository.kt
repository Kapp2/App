package com.example.kapp2.repository

import android.app.Application
import android.content.Context
import com.example.kapp2.db.Kapp2Dao
import com.example.kapp2.db.Kapp2DataBase
import com.example.kapp2.db.realtions.BotonesFavoritosCrossRef
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil

object Repository {
    //private lateinit var modelTareas:ModelTempTareas
    private lateinit var modelApp: Kapp2Dao
    //el context suele ser necesario para recuperar datos
    private lateinit var application: Application
    //inicio del objeto singleton
    operator fun invoke(context: Context){
        this.application= context.applicationContext as Application
        //iniciamos el modelo
        //ModelTempTareas(application)
        //modelTareas = ModelTempTareas

        modelApp = Kapp2DataBase.getInstance(application).kapp2Dao
    }
    //Métodos que llaman a los otros métodos
    suspend fun addBoton(boton: Boton) = modelApp.addBoton(boton)
    suspend fun addPerfil(perfil: Perfil) = modelApp.addPerfil(perfil)
    suspend fun addFavorito(crossRef: BotonesFavoritosCrossRef) = modelApp.addBotonesFavoritosCrossRef(crossRef)
    suspend fun delPerfil(perfil: Perfil) = modelApp.delPerfil(perfil)
    fun getAllBotones() = modelApp.getAllBotones()
    fun getAllPerfiles() = modelApp.getAllPerfiles()
    suspend fun getBotonesOfPerfil(perfil: Perfil) = modelApp.getBotonesOfPerfil(perfil.perfil_id)
    fun getBotonesFiltroTematica(tematica: Int) = modelApp.getBotonesFiltroTematica(tematica)
}