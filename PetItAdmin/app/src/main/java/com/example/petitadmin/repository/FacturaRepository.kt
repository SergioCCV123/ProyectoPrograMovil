package com.example.petitadmin.repository

import androidx.lifecycle.MutableLiveData
import com.example.petitadmin.data.FacturaDao
import com.example.petitadmin.model.Factura

class FacturaRepository (private val facturaDao : FacturaDao) {

    val getAllData: MutableLiveData<List<Factura>> = facturaDao.getAllData()

    fun addfactura(factura: Factura){
        facturaDao.saveFacturas(factura)
    }
    fun updatefactura(factura: Factura){
        facturaDao.saveFacturas(factura)
    }
    fun deletefactura(factura: Factura){
        facturaDao.deleteFasctura(factura)
    }

}