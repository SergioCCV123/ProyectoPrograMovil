package com.petit.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petit.data.MascotaDao
import com.petit.model.Mascotas
import com.petit.repository.MascotaRepository

class MascotaViewModel(application: Application) : AndroidViewModel(application)  {

    val getAllData: MutableLiveData<List<Mascotas>>

    private val repository : MascotaRepository = MascotaRepository(MascotaDao())

    init {
        getAllData = repository.getAllData
    }

    fun addMascota(mascota : Mascotas){
        repository.addMascota((mascota))
    }

    fun updateMascota(mascota : Mascotas){
        repository.updateMascota((mascota))
    }

    fun deleteMascota(mascota : Mascotas){
        repository.deleteMascota((mascota))
    }
}