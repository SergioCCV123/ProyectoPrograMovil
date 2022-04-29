package com.example.petitadmin.repository

import androidx.lifecycle.MutableLiveData
import com.example.petitadmin.data.PaseosDao
import com.example.petitadmin.model.Paseos

class PaseosRepository (private val paseosDao : PaseosDao){

    val getAllData: MutableLiveData<List<Paseos>> = paseosDao.getAllData()

    fun addPaseos(paseo: Paseos){
        paseosDao.savePaseos(paseo)
    }
    fun updatePaseos(paseo: Paseos){
        paseosDao.savePaseos(paseo)
    }
    fun deletePaseos(paseo: Paseos){
        paseosDao.deletePaseos(paseo)
    }
}