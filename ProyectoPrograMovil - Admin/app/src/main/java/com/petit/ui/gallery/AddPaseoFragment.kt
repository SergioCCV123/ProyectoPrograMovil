package com.petit.ui.gallery

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.petit.R
import com.petit.databinding.FragmentAddPaseoBinding
import com.petit.model.Paseos
import com.petit.viewModel.PaseosViewModel


class AddPaseoFragment : Fragment() {


    private lateinit var paseosViewModel: PaseosViewModel
    private var _binding: FragmentAddPaseoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        paseosViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]
        _binding = FragmentAddPaseoBinding.inflate(inflater,container,false)

        binding.btActualizar.setOnClickListener{
            insertarPaseo()
        }

        ubicaGPS()

        return binding.root

    }

    // var para saber si tengo permisos
    private var conPermisos:Boolean=true
    private fun ubicaGPS() {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())

        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                105
            )


        }

        /*if(conPermisos){
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location?  ->
                if(location != null){
                    binding.tvLatitud.text = "${location.latitude}"
                    binding.tvLongitud.text = "${location.longitude}"
                    binding.tvAltura.text = "${location.altitude}"
                }else{
                    binding.tvLatitud.text = getString(R.string.error)
                    binding.tvLongitud.text = getString(R.string.error)
                    binding.tvAltura.text= getString(R.string.error)
                }

            }
        }*/
    }

    private fun insertarPaseo() {
        val nombre = binding.etNombre.text.toString()
        val hInicio = binding.etHInicial.text.toString()
        val hFinal = binding.etHFinal.text.toString()
        val Total = binding.etEdad.text.toString()

        val paseo = Paseos("",nombre,0.0,0.0,0.0,hInicio,hFinal,Total,false,"")
        paseosViewModel.addPaseo(paseo)
        Toast.makeText(requireContext(),getString(R.string.msg_agregarP), Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addPaseoFragment_to_nav_gallery)

    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}