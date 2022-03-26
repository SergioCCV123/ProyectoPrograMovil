package com.petit.repository

import androidx.lifecycle.MutableLiveData
import com.petit.data.MascotaDao
import com.petit.model.Mascotas

class MascotaRepository (private val mascotaDao : MascotaDao) {

    val getAllData: MutableLiveData<List<Mascotas>> = mascotaDao.getAllData()

    fun addMascota(mascota: Mascotas){
        mascotaDao.saveMascotas(mascota)
    }
    fun updateMascota(mascota: Mascotas){
        mascotaDao.saveMascotas(mascota)
    }
    fun deleteMascota(mascota: Mascotas){
        mascotaDao.deleteMascota(mascota)
    }

}