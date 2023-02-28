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
    var favoritosLiveData =  MutableLiveData(0)
    val perfilesLiveData : LiveData<List<Perfil>>
    private val tematicaLiveData = MutableLiveData(5)
    /*
    val PERFIL="Pedro"
    val TEMATICA="0"
    private val filtrosLiveData by lazy {//inicio tardío
        val mutableMap = mutableMapOf<String, Any?>(
            PERFIL to null,
            TEMATICA to 3
        )
        MutableLiveData(mutableMap)
    }*/
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
        botonesLiveData=Transformations.switchMap(filtrosLiveData)
        { mapFiltro ->
            val perfilSelected = mapFiltro!![PERFIL] as Perfil
            val tema = mapFiltro!![TEMATICA] as Int
            //Devuelve el resultado del when
            when {//trae toda la lista de tareas
                (perfilSelected == null && (tema == 0)) ->
                    repositorio.getAllBotones()
                //Sólo filtra por ESTADO
                (perfilSelected == null && (tema != 0)) ->
                    repositorio.getBotonesFiltroTematica(tema)
                //Sólo filtra SINPAGAR
                (perfilSelected != null) ->
                    repositorio.getBotonesOfPerfil(perfilSelected)
                //Filtra por ambos
                else ->
                    repositorio.getAllBotones()
            }
        }

         */



    }

    fun setTematica(tematica: Int) {
        tematicaLiveData.value = tematica
    }

    fun setFavoritos(perfil: Perfil) = viewModelScope.launch(Dispatchers.IO) {
        favoritosLiveData = Repository.getBotonesOfPerfil(perfil).first().botones
    }
}