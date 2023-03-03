package com.example.kapp2.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.kapp2.db.relations.BotonEnPerfiles
import com.example.kapp2.db.relations.BotonPerfilCrossRef
import com.example.kapp2.db.relations.PerfilConBotones
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil
import com.example.kapp2.repository.Repository
import com.example.kapp2.ui.HomeFragment.Companion.perfil
import com.example.kapp2.ui.HomeFragment.Companion.perfilInit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio:Repository
    val botonesLiveData : LiveData<List<Boton>>
    val perfilesLiveData : LiveData<List<Perfil>>
    val favoritosLiveData : MutableLiveData<LiveData<List<PerfilConBotones>>> = MutableLiveData()
    val favoritosTematicaLiveData : LiveData<List<BotonEnPerfiles>>
    private val tematicaLiveData = MutableLiveData(5)
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

        if(perfilInit()){
            favoritosLiveData.value = repositorio.getBotonesOfPerfil(perfil)
        }

        favoritosTematicaLiveData = Transformations.switchMap(tematicaLiveData) { tematica ->
            repositorio.getBotonesOfPerfilFiltroTematica(tematica)
        }
    }

    fun addPerfil(perfil: Perfil) = viewModelScope.launch (Dispatchers.IO){
        Repository.addPerfil(perfil)
    }
    fun addFavorito(crossRef: BotonPerfilCrossRef) = viewModelScope.launch (Dispatchers.IO){
        Repository.addFavorito(crossRef)
    }
    fun delPerfil(perfil: Perfil) = viewModelScope.launch (Dispatchers.IO){
        Repository.delPerfil(perfil)
    }
    fun delFavorito(crossRef: BotonPerfilCrossRef) = viewModelScope.launch (Dispatchers.IO){
        Repository.delFavorito(crossRef)
    }

    fun setTematica(tematica: Int) {
        tematicaLiveData.value = tematica
    }
}