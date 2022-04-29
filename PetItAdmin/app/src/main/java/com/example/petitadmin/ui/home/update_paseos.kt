package com.example.petitadmin.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.petitadmin.R
import com.example.petitadmin.databinding.FragmentUpdatePaseosBinding
import com.example.petitadmin.model.Paseos
import com.example.petitadmin.ui.home.update_paseosArgs
import com.example.petitadmin.viewModel.PaseosViewModel


class update_paseos : Fragment() {


    private lateinit var paseoViewModel: PaseosViewModel

    private val args by navArgs<update_paseosArgs>()

    private var _binding: FragmentUpdatePaseosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        paseoViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]
        _binding = FragmentUpdatePaseosBinding.inflate(inflater,container,false)

        binding.etNombre2.setText(args.paseos.nombreMascota)
        binding.etHFinal.setText(args.paseos.horaLlegada)
        binding.etHInicial.setText(args.paseos.horaSalida)
        binding.etTotal2.setText(args.paseos.Costo.toString())

        binding.btActualizar.setOnClickListener{updatePaseos()}
        binding.btPhone.setOnClickListener{llamar()}
        binding.btWhatsapp.setOnClickListener{enviarWhatsapp()}
        binding.btLocation.setOnClickListener{verMapa()}
        setHasOptionsMenu(true)
        return binding.root
    }


    private fun verMapa() {
        val latitud = 0.0//binding.tvLatitud.text.toString().toDouble()
        val longitud = 0.0//binding.tvLongitud.text.toString().toDouble()
        if(latitud.isFinite() && longitud.isFinite()){
            val location = Uri.parse("geo:$latitud,$longitud?z18")
            val intent = Intent(Intent.ACTION_VIEW,location)

            startActivity(intent)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
    }

    private fun enviarWhatsapp() {
        val telefono = "71223417"
        if(telefono.isNotEmpty()){
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = "whatsapp://send?phone=506$telefono&text_"+
                    getString(R.string.msg_saludos)
            intent.setPackage("com.whatsapp")
            intent.data = Uri.parse(uri)
            startActivity(intent)
        }else{
            Toast.makeText(requireContext(),getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
    }

    private fun llamar() {
        val telefono = "71223417"
        if(telefono.isNotEmpty()){
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$telefono")
            if(requireActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
                requireActivity().requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),105)
            }else{
                requireActivity().startActivity(intent)
            }
        }else{
            Toast.makeText(requireContext(),
                getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deletePaseo()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updatePaseos() {
        val nombre = binding.etNombre2.text.toString()
        val hInicial = binding.etHInicial.text.toString()
        val hFinal = binding.etHFinal.text.toString()
        val total = binding.etTotal2.text.toString().toDouble()

        val paseo = Paseos(args.paseos.id,nombre,0.0,0.0,0.0,hInicial,hFinal,total, false, "")
        paseoViewModel.updatePaseo(paseo)
        Toast.makeText(requireContext(),getString(R.string.msg_actualizarP),Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_update_paseos_to_nav_home)

    }

    private fun deletePaseo(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) {_,_ ->
            paseoViewModel.deletePaseo(args.paseos)
            findNavController().navigate(R.id.action_update_paseos_to_nav_home)
        }
        builder.setNegativeButton(getString(R.string.no)){_,_ ->}
        builder.setTitle("Eliminar Paseo")
        builder.setMessage("Seguro que deseas eliminar el paseo de: "+" ${args.paseos.nombreMascota}")
        builder.show()
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}