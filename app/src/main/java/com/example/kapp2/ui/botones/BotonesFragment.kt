package com.example.kapp2.ui.botones

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kapp2.R
import com.example.kapp2.databinding.FragmentBotonesBinding

class BotonesFragment : Fragment() {

    private var _binding: FragmentBotonesBinding? = null
    private lateinit var mp: MediaPlayer
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        val botonesViewModel =
            ViewModelProvider(this)[BotonesViewModel::class.java]

        binding.toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                mp  = MediaPlayer.create(activity, R.raw.cuenta_actitud)
                mp.start()
            }
            else
                mp.pause()
        }
        binding.toggleButton.setOnClickListener {
            while (mp.isPlaying){ }
            (it as ToggleButton).isChecked = false
        }

    }
    /*
    fun CuantaActitud(view: View?) {
        val mp: MediaPlayer = MediaPlayer.create(activity, R.raw.cuenta_actitud)
        mp.start()
    }

     */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}