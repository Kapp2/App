package com.example.kapp2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kapp2.databinding.FragmentHomeBinding
import com.example.kapp2.model.Perfil
import com.example.kapp2.viewModel.AppViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    companion object {
        lateinit var perfil: Perfil

        fun perfilInit(): Boolean {
            return this::perfil.isInitialized
        }
    }

    private lateinit var list: List<Perfil>
    private var _binding: FragmentHomeBinding? = null
    private val viewModel: AppViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.perfilesLiveData.observe(viewLifecycleOwner) { lista ->
            list = lista
        }

        binding.btLogin.setOnClickListener {
            val nombre = binding.etName.text.toString()
            val passwd = binding.etPassword.text.toString()
            if (nombre.isEmpty() || passwd.isEmpty()) {
                Toast.makeText(activity, "Completa todos los campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val listPerfil: MutableMap<String, String> = HashMap()
            if (this::list.isInitialized) {
                list.forEach {
                    listPerfil[it.nickname] = it.password
                }
            }
            if (listPerfil.contains(nombre)) {
                if (passwd == listPerfil[nombre]) {
                    perfil = Perfil(nombre, passwd)
                    Toast.makeText(
                        activity, "Login Correcto. Disfruta!",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.setTematica(1)
                    viewModel.setTematica(0)
                } else {
                    binding.etPassword.setText("")
                    Toast.makeText(
                        activity, "Login Incorrecto. Contraseña no Válida.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "El perfil introducido no existe. Deseas crear uno nuevo?",
                    Snackbar.LENGTH_LONG
                )
                    .setAction("AÑADIR") { viewModel.addPerfil(Perfil(nombre, passwd)) }.show()
            }
        }

        binding.btcreateUser.setOnClickListener {
            val nombre = binding.etName.text.toString()
            val passwd = binding.etPassword.text.toString()
            viewModel.addPerfil(Perfil(nombre, passwd))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}