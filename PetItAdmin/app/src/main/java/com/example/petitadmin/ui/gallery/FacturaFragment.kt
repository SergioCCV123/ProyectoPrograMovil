package com.example.petitadmin.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petitadmin.R
import com.example.petitadmin.adapter.FacturaAdapter
import com.example.petitadmin.databinding.FragmentFacturaBinding
import com.example.petitadmin.viewModel.GalleryViewModel

class FacturaFragment : Fragment() {

    private lateinit var facturaViewModel: GalleryViewModel
    private var _binding: FragmentFacturaBinding  ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        facturaViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        _binding = FragmentFacturaBinding.inflate(inflater,container,false)

        binding.addFacturas.setOnClickListener{
            findNavController().navigate(R.id.action_nav_gallery_to_add_factura)
        }
        //Activar el RecycleView
        val facturaAdapter = FacturaAdapter()
        val recicdlador = binding.reciclador
        recicdlador.adapter = facturaAdapter
        recicdlador.layoutManager= LinearLayoutManager(requireContext())

        facturaViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]

        facturaViewModel.getAllData.observe(viewLifecycleOwner){ facturas->
            facturaAdapter.setData(facturas)
        }

        return binding.root

    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}