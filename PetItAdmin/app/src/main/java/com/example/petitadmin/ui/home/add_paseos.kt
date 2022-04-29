package com.example.petitadmin.ui.home

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
import com.example.petitadmin.R
import com.example.petitadmin.databinding.FragmentAddPaseosBinding
import com.example.petitadmin.model.Paseos
import com.example.petitadmin.viewModel.PaseosViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class add_paseos : Fragment() {


    private lateinit var paseosViewModel: PaseosViewModel
    private var _binding: FragmentAddPaseosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        paseosViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]
        _binding = FragmentAddPaseosBinding.inflate(inflater,container,false)

        binding.btActualizar.setOnClickListener{
            insertarPaseo()
        }

        binding.etHoraF.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                GenerarTotal()
        }

        ubicaGPS()

        return binding.root

    }

    private fun GenerarTotal() {

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
        if(binding.etNombre.text.isNullOrBlank()
            or binding.etHoraI.text.isNullOrBlank()
            or binding.etMinI.text.isNullOrBlank()
            or binding.etHoraF.text.isNullOrBlank()
            or binding.etMinF.text.isNullOrBlank()
            or binding.etTotal.text.isNullOrBlank()
        ){
            Toast.makeText(requireContext(),getString(R.string.msg_errores), Toast.LENGTH_SHORT).show()
        }else{
            val nombre = binding.etNombre.text.toString()
            val hInicio = binding.etHoraI.text.toString() + ":" + binding.etMinI.text.toString()
            val hFinal = binding.etHoraF.text.toString() + ":" + binding.etMinI.text.toString()
            val total = 0.0

            val paseo = Paseos("",nombre,0.0,0.0,0.0,hInicio,hFinal,total,false, "")
            paseosViewModel.addPaseo(paseo)
            Toast.makeText(requireContext(),getString(R.string.msg_agregarP), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_add_paseos_to_nav_home)
        }


    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}