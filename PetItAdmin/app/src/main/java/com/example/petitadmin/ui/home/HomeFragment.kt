package com.example.petitadmin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petitadmin.R
import com.example.petitadmin.adapter.PaseosAdapter
import com.example.petitadmin.databinding.FragmentPaseosBinding
import com.example.petitadmin.viewModel.PaseosViewModel

class HomeFragment : Fragment() {

    private lateinit var paseoViewModel: PaseosViewModel
    private var _binding: FragmentPaseosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        paseoViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]
        _binding = FragmentPaseosBinding.inflate(inflater,container,false)

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