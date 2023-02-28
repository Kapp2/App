package com.example.kapp2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.kapp2.model.Boton
import com.example.kapp2.model.Perfil
import com.example.kapp2.repository.Repository

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val repositorio:Repository
    val botonLiveData : LiveData<List<Boton>>
    val perfilLiveData : LiveData<List<Perfil>>
    private val tematicaLiveData = MutableLiveData(5)

    init {
        //inicia repositorio
        Repository(getApplication<Application>().applicationContext)
        repositorio = Repository
        perfilLiveData = repositorio.getAllPerfiles()
        botonLiveData = Transformations.switchMap(tematicaLiveData) {tematica ->
            if(tematica == 0)
                repositorio.getAllBotones()
            else
                repositorio.getBotonesFiltroTematica(tematica)
        }
    }

    fun setTematica(tematica: Int) {
        tematicaLiveData.value = tematica
    }
}