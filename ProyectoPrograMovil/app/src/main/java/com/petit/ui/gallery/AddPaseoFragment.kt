package com.petit.ui.gallery

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.text.Editable
import android.util.Log
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
    var total:Int = 0
    var horas:Int = 0
    var min:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        paseosViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]
        _binding = FragmentAddPaseoBinding.inflate(inflater,container,false)

        binding.btActualizar.setOnClickListener{
            insertarPaseo()
        }

        binding.etHoraF.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                GenerarTotal()
            else{
                GenerarTotal()
            }
        }

        binding.etHoraF.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                GenerarTotal()
        }

        binding.etHoraI.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                GenerarTotal()
            else{
                GenerarTotal()
            }
        }

        binding.etMinF.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                GenerarTotal()
            else{
                GenerarTotal()
            }
        }

        binding.etMinI.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus)
                GenerarTotal()
            else{
                GenerarTotal()
            }
        }




        ubicaGPS()

        return binding.root

    }

    private fun GenerarTotal() {

        if(binding.etHoraI.text.isNullOrBlank() or binding.etHoraF.text.isNullOrBlank()){
        }else{
            horas = (binding.etHoraF.text.toString().toInt() - binding.etHoraI.text.toString().toInt()) * 1000
        }
        if(binding.etMinI.text.isNullOrBlank() or binding.etMinF.text.isNullOrBlank()){
        }else{
            min = binding.etMinF.text.toString().toInt() - binding.etMinI.text.toString().toInt()
            if(min >= 30){
                min = 500
            }else if(min <= -30){
                min = -500
            }else{
                min = 0
            }
        }

        total = horas + min;
        binding.etTotal.text = total.toString().toEditable()
    }



    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)


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
            val total = binding.etTotal.text.toString().toDouble()

            Log.d("TAG", ""+total)

            val paseo = Paseos("",nombre,0.0,0.0,0.0,hInicio,hFinal,total,false, "")
            paseosViewModel.addPaseo(paseo)
            Toast.makeText(requireContext(),getString(R.string.msg_agregarP), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addPaseoFragment_to_nav_gallery)
        }


    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}