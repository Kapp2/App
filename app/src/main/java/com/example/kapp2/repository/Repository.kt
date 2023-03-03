package com.example.kapp2.repository

import android.app.Application
import android.content.Context
import com.example.kapp2.db.Kapp2Dao
import com.example.kapp2.db.Kapp2DataBase
import com.example.kapp2.db.relations.BotonEnPerfiles
import com.example.kapp2.db.relations.BotonPerfilCrossRef
import com.example.kapp2.model.Perfil

object Repository {

    private lateinit var modelApp: Kapp2Dao
    //el context suele ser necesario para recuperar datos
    private lateinit var application: Application

    operator fun invoke(context: Context){
        this.application= context.applicationContext as Application
        modelApp = Kapp2DataBase.getInstance(application).kapp2Dao
    }
    //Métodos que llaman a los otros métodos
    suspend fun addPerfil(perfil: Perfil) = modelApp.addPerfil(perfil)
    suspend fun addFavorito(crossRef: BotonPerfilCrossRef) = modelApp.addBotonesFavoritos(crossRef)
    suspend fun delPerfil(perfil: Perfil) = modelApp.delPerfil(perfil)
    suspend fun delFavorito(crossRef: BotonPerfilCrossRef) = modelApp.delBotonesFavoritos(crossRef)
    fun getAllBotones() = modelApp.getAllBotones()
    fun getAllPerfiles() = modelApp.getAllPerfiles()
    fun getBotonesFiltroTematica(tematica: Int) = modelApp.getBotonesFiltroTematica(tematica)
    fun getBotonesOfPerfil(perfil: Perfil) = modelApp.getBotonesOfPerfil(perfil.nickname)
    fun getBotonesOfPerfilFiltroTematica(tematica: Int) =
        modelApp.getBotonesOfPerfilFiltroTematica(tematica)
}