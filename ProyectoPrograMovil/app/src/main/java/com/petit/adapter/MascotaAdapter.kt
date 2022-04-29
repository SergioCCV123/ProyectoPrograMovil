package com.petit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.petit.databinding.MascotaFileBinding
import com.petit.model.Mascotas
import com.petit.ui.Macota.MascotaFragment
import com.petit.ui.Macota.MascotaFragmentDirections

class MascotaAdapter : RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    //Lista para almacenar la info de las mascotas registradas en el app

    private var listaMascotas = emptyList<Mascotas>()

    inner class MascotaViewHolder(private val itemBinding: MascotaFileBinding):
        RecyclerView.ViewHolder(itemBinding.root){

        fun bind(mascota: Mascotas){
            itemBinding.tvNombre.text = mascota.nombre
            itemBinding.tvLlegada.text = mascota.edad
            itemBinding.tvSalida.text = mascota.raza

            Glide.with(itemBinding.root.context)
                .load(mascota.rutaImagen)
                .circleCrop()
                .into(itemBinding.imagen)

            itemBinding.vistaFila.setOnClickListener{
                val action = MascotaFragmentDirections
                    .actionNavPetsToUpdateMascotaFragment(mascota)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val itemBinding = MascotaFileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return MascotaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = listaMascotas[position]
        holder.bind(mascota)
    }


    override fun getItemCount(): Int {
        return listaMascotas.size
    }

    fun setData(mascota: List<Mascotas>){
        this.listaMascotas=mascota
        notifyDataSetChanged()
    }
}