package com.petit.repository

import androidx.lifecycle.MutableLiveData
import com.petit.data.MascotaDao
import com.petit.data.PaseosDao
import com.petit.model.Mascotas
import com.petit.model.Paseos

class PaseosRepository (private val paseosDao : PaseosDao) {

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