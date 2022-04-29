package com.example.petitadmin.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.petitadmin.data.PaseosDao
import com.example.petitadmin.model.Paseos
import com.example.petitadmin.repository.PaseosRepository

class PaseosViewModel (application: Application) : AndroidViewModel(application){

    val getAllData: MutableLiveData<List<Paseos>>

    private val repository : PaseosRepository = PaseosRepository(PaseosDao())

    init {
        getAllData = repository.getAllData
    }

    fun addPaseo(paseo : Paseos){
        repository.addPaseos((paseo))
    }

    fun updatePaseo(paseo : Paseos){
        repository.updatePaseos((paseo))
    }

    fun deletePaseo(paseo : Paseos){
        repository.deletePaseos((paseo))
    }
}