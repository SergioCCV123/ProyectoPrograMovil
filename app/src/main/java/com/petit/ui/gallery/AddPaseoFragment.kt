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
import com.petit.databinding.FragmentAddMascotaBinding
import com.petit.model.Mascotas
import com.petit.viewModel.MascotaViewModel


class AddPaseoFragment : Fragment() {


    private lateinit var mascotaViewModel: MascotaViewModel
    private var _binding: FragmentAddMascotaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mascotaViewModel = ViewModelProvider(this)[MascotaViewModel::class.java]
        _binding = FragmentAddMascotaBinding.inflate(inflater,container,false)

        binding.btActualizar.setOnClickListener{
            insertarMascota()
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

    private fun insertarMascota() {
        val nombre = binding.etNombre.text.toString()
        val edad = binding.etEdad.text.toString()
        val raza = binding.etRaza.text.toString()

        val mascota = Mascotas("",nombre,edad,raza,"","")
        mascotaViewModel.addMascota(mascota)
        Toast.makeText(requireContext(),getString(R.string.msg_agregar), Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addMascotaFragment_to_nav_pets)

    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}