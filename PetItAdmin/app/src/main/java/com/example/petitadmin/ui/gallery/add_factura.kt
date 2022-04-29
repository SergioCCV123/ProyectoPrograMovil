package com.example.petitadmin.ui.gallery

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.petitadmin.R
import com.example.petitadmin.databinding.FragmentAddFacturaBinding
import com.example.petitadmin.model.Factura
import com.example.petitadmin.viewModel.GalleryViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class add_factura : Fragment() {
    private lateinit var tomarFotoActivity: ActivityResultLauncher<Intent>

    private lateinit var facturViewModel: GalleryViewModel
    private var _binding: FragmentAddFacturaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        facturViewModel = ViewModelProvider(this)[facturViewModel::class.java]
        _binding = FragmentAddFacturaBinding.inflate(inflater,container,false)

        binding.btActualizar3.setOnClickListener{
            insertarFactura()
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

    private fun insertarFactura() {
        val nombre = binding.etCliente2.text.toString()
        val estado = false
        val mascota = binding.etMascota2.text.toString()
        val detalle = binding.etdetalle2.text.toString()
        val total = binding.ettotal2.text.toString().toDouble()

        val factura = Factura("",nombre,mascota,total,detalle,estado)
        facturViewModel.addFactura(factura)
        Toast.makeText(requireContext(),getString(R.string.msg_agregar), Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_add_factura_to_nav_gallery)

    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}