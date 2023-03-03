package com.example.kapp2.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil
import com.example.kapp2.repository.Repository

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio:Repository
    val botonesLiveData : LiveData<List<Boton>>
    val perfilesLiveData : LiveData<List<Perfil>>
    val favoritosLiveData : MutableList<Boton> = mutableListOf()
    private val tematicaLiveData = MutableLiveData(5)
    val PERFIL="PERFIL"
    val TEMATICA="TEMATICA"
    private val botonesFavoritosLiveData by lazy {
        val mutableMap = mutableMapOf<String, Any?>(
            PERFIL to null,
            TEMATICA to 0
        )
        MutableLiveData(mutableMap)
    }
    init {
        //inicia repositorio
        Repository(getApplication<Application>().applicationContext)
        repositorio = Repository
        perfilesLiveData = repositorio.getAllPerfiles()
        botonesLiveData = Transformations.switchMap(tematicaLiveData) { tematica ->
            if(tematica == 0)
                repositorio.getAllBotones()
            else
                repositorio.getBotonesFiltroTematica(tematica)
        }
/*
        favoritosLiveData = Transformations.switchMap(botonesFavoritosLiveData){ botones ->
            if (botones[TEMATICA] == 0) {
                repositorio.getBotonesOfPerfil(botones[PERFIL]).first().botones
            }

        }

 */


    }

    fun setTematica(tematica: Int) {
        tematicaLiveData.value = tematica
    }

    /*fun setFavoritos(perfil: Perfil) = viewModelScope.launch(Dispatchers.IO) {
        favoritosLiveData = Repository.getBotonesOfPerfil(perfil).first().botones
    }*/
}