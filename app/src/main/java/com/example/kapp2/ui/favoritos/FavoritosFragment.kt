package com.example.kapp2.ui.favoritos

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kapp2.R
import com.example.kapp2.adapters.BotonesAdapter
import com.example.kapp2.databinding.FragmentBotonesBinding
import com.example.kapp2.model.Boton
import com.example.kapp2.viewModel.AppViewModel
class FavoritosFragment : Fragment() {

    private var _binding: FragmentBotonesBinding? = null
    private val viewModel: AppViewModel by activityViewModels()
    private var mp: MediaPlayer?=null

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
        binding.tvBotones.setText(R.string.favoritos)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciaRecyclerView()
        iniciaCRUD()
        iniciaSpTematica()

        viewModel.botonesLiveData.observe(viewLifecycleOwner) { lista ->
            //botonesAdapter.setLista(lista)
        }
    }

    private fun iniciaCRUD(){
        botonesAdapter.onBotonClickListener = object :
            BotonesAdapter.OnBotonClickListener {

            override fun onBotonClick(boton: Boton?, view: ToggleButton) {
                if(!view.isChecked){
                    mp?.stop()
                    return
                }
                val sound = boton?.sonido
                mp = sound?.let { MediaPlayer.create(activity, it) }
                mp?.start()
                Handler(Looper.getMainLooper()).postDelayed({
                    view.isChecked = false
                }, (mp?.duration?:1000).toLong())
            }
        }
    }
    private fun iniciaRecyclerView() {
        //creamos el adaptador
        botonesAdapter = BotonesAdapter()
        with(binding.rvBotones) {
            //le asignamos el adaptador
            adapter = botonesAdapter
        }
    }
    private fun iniciaSpTematica() {
        ArrayAdapter.createFromResource(requireContext(), R.array.tematicas,
            android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spTematica.adapter = adapter
        }

        binding.spTematica.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, v: View?, posicion: Int, id: Long) {
                viewModel.setTematica(posicion)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}