package com.example.petitadmin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petitadmin.data.FacturaDao
import com.example.petitadmin.model.Factura
import com.example.petitadmin.repository.FacturaRepository

class GalleryViewModel(application: Application) : AndroidViewModel(application)  {

    val getAllData: MutableLiveData<List<Factura>>

    private val repository : FacturaRepository = FacturaRepository(FacturaDao())

    init {
        getAllData = repository.getAllData
    }

    fun addFactura(factura : Factura){
        repository.addfactura((factura))
    }

    fun updateFactura(factura : Factura){
        repository.updatefactura((factura))
    }

    fun deleteFactura(factura : Factura){
        repository.deletefactura((factura))
    }
}