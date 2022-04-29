package com.example.petitadmin.ui.gallery

import android.app.AlertDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.petitadmin.R
import com.example.petitadmin.databinding.FragmentUpdateFacturaBinding
import com.example.petitadmin.model.Factura
import com.example.petitadmin.update_facturaArgs
import com.example.petitadmin.viewModel.GalleryViewModel


class update_factura : Fragment() {
    private lateinit var mascotaViewModel: GalleryViewModel

    private val args by navArgs<update_facturaArgs>()
    private lateinit var mediaplayer: MediaPlayer

    private var _binding: FragmentUpdateFacturaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mascotaViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]
        _binding = FragmentUpdateFacturaBinding.inflate(inflater,container,false)

        binding.etCliente.setText(args.factura.cliente)
        binding.etMascota.setText(args.factura.mascota)
        binding.etdetalle.setText(args.factura.descripcion)
        binding.ettotal.setText(args.factura.total.toString())


        binding.btActualizar2.setOnClickListener{updateFactura()}
        setHasOptionsMenu(true)
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteFactura()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updateFactura() {
        val nombre = binding.etCliente.text.toString()
        val estado = true
        val mascota = binding.etMascota.text.toString()
        val detalle = binding.etdetalle.text.toString()
        val total = binding.ettotal.text.toString().toDouble()

        val factura = Factura(args.factura.id,nombre,mascota,total,detalle,estado)
        mascotaViewModel.updateFactura(factura)
        Toast.makeText(requireContext(),getString(R.string.msg_actualizar), Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_add_factura_to_nav_gallery)

    }

    private fun deleteFactura(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)) {_,_ ->
            mascotaViewModel.deleteFactura(args.factura)
            findNavController().navigate(R.id.action_add_factura_to_nav_gallery)
        }
        builder.setNegativeButton(getString(R.string.no)){_,_ ->}
        builder.setTitle("Eliminar Factura")
        builder.setMessage("Seguro que desea borrar la factura de: "+" ${args.factura.cliente}")
        builder.show()
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}