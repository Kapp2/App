package com.example.kapp2.ui.botones

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kapp2.R
import com.example.kapp2.adapter.BotonesAdapter
import com.example.kapp2.databinding.FragmentBotonesBinding
import com.example.kapp2.model.Boton
import com.example.kapp2.viewModel.AppViewModel

class BotonesFragment : Fragment() {

    private var _binding: FragmentBotonesBinding? = null
    private var mp: MediaPlayer?=null
    private val viewModel: AppViewModel //by activityViewModels()
        get() {
            TODO()
        }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var botonesAdapter: BotonesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBotonesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        val textView: TextView = binding.textDashboard
        botonesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciaRecyclerView()
        iniciaCRUD()

        viewModel.botonLiveData.observe(viewLifecycleOwner, Observer<List<Boton>> { lista ->
            //actualizaLista(lista)
            botonesAdapter.setLista(lista)
        })
    }
    /*
    fun CuantaActitud(view: View?) {
        val mp: MediaPlayer = MediaPlayer.create(activity, R.raw.cuenta_actitud)
        mp.start()
    }

     */
    private fun iniciaCRUD(){
        botonesAdapter.onBotonClickListener = object :
            BotonesAdapter.OnBotonClickListener {

            override fun onBotonClik(boton: Boton?) {
                val sound = boton?.sonido
                mp = sound?.let { MediaPlayer.create(activity, it) }
                mp?.start()
            }
        }
    }
    private fun iniciaRecyclerView() {
        //creamos el adaptador
        botonesAdapter = BotonesAdapter()
        with(binding.rvBotones) {
            //Creamos el layoutManager
            layoutManager = LinearLayoutManager(activity)
            //le asignamos el adaptador
            adapter = botonesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}