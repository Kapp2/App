package com.example.kapp2.ui.botones

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kapp2.R
import com.example.kapp2.adapter.Kapp2Adapter
import com.example.kapp2.databinding.FragmentBotonesBinding
import com.example.kapp2.model.Boton

class BotonesFragment : Fragment() {

    private var _binding: FragmentBotonesBinding? = null
    private lateinit var mp: MediaPlayer
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var kapp2Adapter: Kapp2Adapter


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

    }
    /*
    fun CuantaActitud(view: View?) {
        val mp: MediaPlayer = MediaPlayer.create(activity, R.raw.cuenta_actitud)
        mp.start()
    }

     */
    private fun iniciaCRUD(){
        kapp2Adapter.onBotonClickListener = object :
            Kapp2Adapter.OnBotonClickListener {


            override fun onBotonClik(boton: Boton?) {
                val sound = boton?.tematica
                val action: MediaPlayer =MediaPlayer.create(activity, R.raw.cuenta_actitud)
                mp.start()
            }
        }
    }
    private fun iniciaRecyclerView() {
        //creamos el adaptador
        kapp2Adapter = Kapp2Adapter()

        with(binding.rvBotones) {
            //Creamos el layoutManager
            layoutManager = LinearLayoutManager(activity)
            //le asignamos el adaptador
            adapter = kapp2Adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}