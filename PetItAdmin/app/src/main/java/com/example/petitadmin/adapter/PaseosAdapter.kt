package com.example.petitadmin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.petitadmin.databinding.PaseoFileBinding
import com.example.petitadmin.model.Paseos
import com.example.petitadmin.ui.home.HomeFragmentDirections

class PaseosAdapter : RecyclerView.Adapter<PaseosAdapter.PaseosViewHolder>(){

    private var listaPaseos = emptyList<Paseos>()

    inner class PaseosViewHolder(private val itemBinding: PaseoFileBinding):
        RecyclerView.ViewHolder(itemBinding.root){

        fun bind(paseo: Paseos){
            itemBinding.tvNombre.text = paseo.nombreMascota
            itemBinding.tvId.text = paseo.horaSalida
            itemBinding.tvMascota.text = paseo.horaLlegada
            itemBinding.tvEstado.text = paseo.Costo.toString()
            itemBinding.vistaFila.setOnClickListener{
                val action = HomeFragmentDirections.actionNavHomeToUpdatePaseos(paseo)
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