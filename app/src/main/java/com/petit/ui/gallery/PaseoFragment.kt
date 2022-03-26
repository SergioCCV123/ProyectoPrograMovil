package com.petit.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.petit.R
import com.petit.adapter.PaseosAdapter
import com.petit.databinding.FragmentPaseosBinding
import com.petit.viewModel.PaseosViewModel

class PaseoFragment : Fragment() {

    private lateinit var paseoViewModel: PaseosViewModel
    private var _binding: FragmentPaseosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        paseoViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]
        _binding = FragmentPaseosBinding.inflate(inflater,container,false)

        binding.addPaseoFavBotton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_gallery_to_addPaseoFragment)
        }
        //Activar el RecycleView
        val paseoAdapter = PaseosAdapter()
        val recicdlador = binding.reciclador
        recicdlador.adapter = paseoAdapter
        recicdlador.layoutManager= LinearLayoutManager(requireContext())

        paseoViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]

        paseoViewModel.getAllData.observe(viewLifecycleOwner){ paseos->
            paseoAdapter.setData(paseos)
        }

        return binding.root

    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}