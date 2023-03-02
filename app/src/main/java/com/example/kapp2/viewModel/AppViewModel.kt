package com.example.kapp2.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil
import com.example.kapp2.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio:Repository
    val botonesLiveData : LiveData<List<Boton>>
    val perfilesLiveData : LiveData<List<Perfil>>
    private var favoritos : MutableList<Boton> = mutableListOf()
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
    }

    fun setTematica(tematica: Int) {
        tematicaLiveData.value = tematica
    }

    fun setFavoritos(perfil: Perfil) = viewModelScope.launch(Dispatchers.IO) {
        favoritos.addAll(Repository.getBotonesOfPerfil(perfil).first().botones)
    }

    fun getBotonesFavoritos(tematica: Int): List<Boton> {
        return if(tematica == 0)
            favoritos
        else {
            favoritos.forEach { boton ->
                if(boton.tematica != tematica)
                    favoritos.remove(boton)
            }
            favoritos
        }
    }
}