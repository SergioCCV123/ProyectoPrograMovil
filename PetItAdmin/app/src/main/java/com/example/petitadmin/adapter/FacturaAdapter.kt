package com.example.petitadmin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petitadmin.databinding.FacturaFileBinding
import com.example.petitadmin.model.Factura

class FacturaAdapter : RecyclerView.Adapter<FacturaAdapter.FacturaViewHolder>() {

    //Lista para almacenar la info de las Factura registradas en el app

    private var listaFactura = emptyList<Factura>()

    var estado = ""
    
    inner class FacturaViewHolder(private val itemBinding: FacturaFileBinding):
        RecyclerView.ViewHolder(itemBinding.root){

        fun bind(factura: Factura){
            itemBinding.tvId.text = factura.id
            itemBinding.tvNombre.text = factura.cliente
            itemBinding.tvMascota.text = factura.mascota
            if(factura.estado == false){
                estado = "Pendiente"
            }else{
                estado = "Pago"
            }
            itemBinding.tvEstado.text = estado
            itemBinding.tvTotal.text = estado
            itemBinding.tvDescripcion.text = factura.descripcion

            
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val itemBinding = FacturaFileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return FacturaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        val factura = listaFactura[position]
        holder.bind(factura)
    }


    override fun getItemCount(): Int {
        return listaFactura.size
    }

    fun setData(factura: List<Factura>){
        this.listaFactura=factura
        notifyDataSetChanged()
    }
}