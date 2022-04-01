package com.petit.ui.gallery

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
import com.petit.R
import com.petit.databinding.FragmentUpdatePaseoBinding
import com.petit.model.Paseos
import com.petit.viewModel.PaseosViewModel


class UpdatePaseoFragment : Fragment() {


    private lateinit var paseoViewModel: PaseosViewModel

    private val args by navArgs<UpdatePaseoFragmentArgs>()

    private var _binding: FragmentUpdatePaseoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        paseoViewModel = ViewModelProvider(this)[PaseosViewModel::class.java]
        _binding = FragmentUpdatePaseoBinding.inflate(inflater,container,false)

        binding.etNombre2.setText(args.paseos.nombreMascota)
        binding.etHFinal.setText(args.paseos.horaLlegada)
        binding.etHInicial.setText(args.paseos.horaSalida)
        binding.etTotal2.setText(args.paseos.Costo)

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
        val total = binding.etTotal2.text.toString()

        val paseo = Paseos(args.paseos.id,nombre,0.0,0.0,0.0,hInicial,hFinal,total)
        paseoViewModel.updatePaseo(paseo)
        Toast.makeText(requireContext(),getString(R.string.msg_actualizarP),Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updatePaseoFragment_to_nav_gallery)

    }

    private fun deletePaseo(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) {_,_ ->
            paseoViewModel.deletePaseo(args.paseos)
            findNavController().navigate(R.id.action_updatePaseoFragment_to_nav_gallery)
        }
        builder.setNegativeButton(getString(R.string.no)){_,_ ->}
        builder.setTitle(R.string.menu_delete)
        builder.setMessage(getString(R.string.msg_segruro_brorrar)+" ${args.paseos.nombreMascota}")
        builder.show()
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}