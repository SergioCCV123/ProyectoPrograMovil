package com.petit.ui.Macota

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.petit.R
import com.petit.databinding.FragmentMascotaBinding
import com.petit.viewModel.MascotaViewModel

class MascotaFragment : Fragment() {

    private lateinit var mascotaViewModel: MascotaViewModel
    private var _binding: FragmentMascotaBinding  ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mascotaViewModel = ViewModelProvider(this)[MascotaViewModel::class.java]
        _binding = FragmentMascotaBinding.inflate(inflater,container,false)

        binding.addMascotaFavBotton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_pets_to_addMascotaFragment)
        }
        //Activar el RecycleView
        val mascotaAdapter = MascotaAdapter()
        val recicdlador = binding.reciclador
        recicdlador.adapter = mascotaAdapter
        recicdlador.layoutManager= LinearLayoutManager(requireContext())

        mascotaViewModel = ViewModelProvider(this)[MascotaViewModel::class.java]

        mascotaViewModel.getAllData.observe(viewLifecycleOwner){ mascotas->
            mascotaAdapter.setData(mascotas)
        }

        return binding.root

    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}