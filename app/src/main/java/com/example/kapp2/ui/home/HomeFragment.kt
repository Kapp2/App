package com.example.kapp2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kapp2.databinding.FragmentHomeBinding
import com.example.kapp2.model.Perfil
import com.example.kapp2.viewModel.AppViewModel

class HomeFragment : Fragment() {

    companion object {
        lateinit var perfil: Perfil
    }

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

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}