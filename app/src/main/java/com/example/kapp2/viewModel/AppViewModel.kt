package com.example.kapp2.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.kapp2.model.Boton
import com.example.kapp2.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel {
    private val repositorio:Repository
    //liveData de lista de tareas
    val botonLiveData : LiveData<List<Boton>>
    private val estado = MutableLiveData(3)
    //LiveData que cuando se modifique un filtro cambia el tareasLiveData
    val SOLO_SIN_PAGAR="SOLO_SIN_PAGAR"
    val ESTADO="ESTADO"

    private val filtrosLiveData by lazy {//inicio tardío
        val mutableMap = mutableMapOf<String, Any?>(
            SOLO_SIN_PAGAR to false,
            ESTADO to 3
        )
        MutableLiveData(mutableMap)
    }
    //creamos el LiveData de tipo Booleano. Repesenta nuestro filtro
    private val soloSinPagarLiveData= MutableLiveData(false)
    //inicio ViewModel
    init {
        //inicia repositorio
        Repository(getApplication<Application>() as Context)
       // Linea original  --------- Repository(getApplication<Application>().applicationContext)
        repositorio=Repository
        //tareasLiveData =repositorio.getAllTareas()
        /*tareasLiveData= Transformations.switchMap(soloSinPagarLiveData)
        {soloSinPagar->Repository.getTareasFiltroSinPagar(soloSinPagar)}*/
        /*tareasLiveData= Transformations.switchMap(estado)
        {estado->Repository.getTareasFiltroEstado(estado)}*/
        botonLiveData= Transformations.switchMap(filtrosLiveData)
        { mapFiltro ->
            val aplicarSinPagar = mapFiltro!![SOLO_SIN_PAGAR] as Boolean
            val estado = mapFiltro!![ESTADO] as Int
            //Devuelve el resultado del when
            when {//trae toda la lista de tareas
                (!aplicarSinPagar && (estado == 3)) ->
                    repositorio.getAllBotones()
                //Sólo filtra por ESTADO
                (!aplicarSinPagar && (estado != 3)) ->
                    repositorio.getBotonesFiltroTematica(estado)
                //Sólo filtra SINPAGAR
                (aplicarSinPagar && (estado == 3)) ->
                    repositorio.getBotonesFiltroTematica(2)
                //Filtra por ambos
                else ->
                    repositorio.getAllBotones()
            }
        }
    }

    private fun <T> getApplication(): Any {
        return 2
    }


    /**
     * Modifica el Map filtrosLiveData el elemento "SOLO_SIN_PAGAR"
     * que activará el Transformations de TareasLiveData
     */
    fun setSoloSinPagar(soloSinPagar: Boolean) {
        //recuperamos el map
        val mapa = filtrosLiveData.value
        //modificamos el filtro
        mapa!![SOLO_SIN_PAGAR] = soloSinPagar
        //activamos el LiveData
        filtrosLiveData.value = mapa
    }
    /**
     * Modifica el Map filtrosLiveData el elemento "ESTADO"
     * que activará el Transformations de TareasLiveData lo
     *llamamos cuando cambia el RadioButton
     */
    fun setEstado(estado: Int) {
        //recuperamos el map
        val mapa = filtrosLiveData.value
        //modificamos el filtro
        mapa!![ESTADO] = estado
        //activamos el LiveData
        filtrosLiveData.value = mapa
    }

}