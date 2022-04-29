package com.petit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.petit.databinding.PaseoFileBinding
import com.petit.model.Paseos
import com.petit.ui.Macota.MascotaFragmentDirections
import com.petit.ui.gallery.PaseoFragmentDirections

class PaseosAdapter : RecyclerView.Adapter<PaseosAdapter.PaseosViewHolder>() {

    //Lista para almacenar la info de las mascotas registradas en el app

    private var listaPaseos = emptyList<Paseos>()

    inner class PaseosViewHolder(private val itemBinding: PaseoFileBinding):
        RecyclerView.ViewHolder(itemBinding.root){

        fun bind(paseo: Paseos){
            itemBinding.tvNombre.text = paseo.nombreMascota
            itemBinding.tvSalida.text = paseo.horaSalida
            itemBinding.tvLlegada.text = paseo.horaLlegada
            itemBinding.tvCosto.text = paseo.Costo.toString()
            itemBinding.vistaFila.setOnClickListener{
                val action = PaseoFragmentDirections
                    .actionNavGalleryToUpdatePaseoFragment(paseo)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaseosViewHolder {
        val itemBinding = PaseoFileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return PaseosViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PaseosViewHolder, position: Int) {
        val paseo = listaPaseos[position]
        holder.bind(paseo)
    }


    override fun getItemCount(): Int {
        return listaPaseos.size
    }

    fun setData(paseo: List<Paseos>){
        this.listaPaseos=paseo
        notifyDataSetChanged()
    }
}