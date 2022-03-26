package com.petit.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.petit.R
import com.petit.databinding.FragmentUpdateMascotaBinding
import com.petit.model.Mascotas
import com.petit.viewModel.MascotaViewModel


class UpdatePaseoFragment : Fragment() {


    private lateinit var mascotaViewModel: MascotaViewModel

    //private val args by navArgs<UpdateMascotaFragmentArgs>()

    private var _binding: FragmentUpdateMascotaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mascotaViewModel = ViewModelProvider(this)[MascotaViewModel::class.java]
        _binding = FragmentUpdateMascotaBinding.inflate(inflater,container,false)

        //binding.etNombre.setText(args.mascota.nombre)
        //binding.etEdad.setText(args.mascota.edad)
        //binding.etRaza.setText(args.mascota.raza)

        binding.btActualizar.setOnClickListener{updateMascota()}
        setHasOptionsMenu(true)
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteMascota()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updateMascota() {
        val nombre = binding.etNombre.text.toString()
        val edad = binding.etEdad.text.toString()
        val raza = binding.etRaza.text.toString()

        //val mascota = Mascotas(args.mascota.id,nombre,edad,raza,"","")
        //mascotaViewModel.updateMascota(mascota)
        Toast.makeText(requireContext(),getString(R.string.msg_actualizar),Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateMascotaFragment_to_nav_pets)

    }

    private fun deleteMascota(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) {_,_ ->
            //mascotaViewModel.deleteMascota(args.mascota)
            findNavController().navigate(R.id.action_updateMascotaFragment_to_nav_pets)
        }
        builder.setNegativeButton(getString(R.string.no)){_,_ ->}
        builder.setTitle(R.string.menu_delete)
        //builder.setMessage(getString(R.string.msg_segruro_brorrar)+" ${args.mascota.nombre}")
        builder.show()
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}