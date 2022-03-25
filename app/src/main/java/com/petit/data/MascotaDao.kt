package com.petit.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.petit.model.Mascotas

class MascotaDao {
    private var codigoUsuario: String
    private var firestrore: FirebaseFirestore

    init {
        val usuario = Firebase.auth.currentUser?.email
        //val usuario = Firebase.auth.currentUser?.uid
        codigoUsuario="$usuario"
        firestrore = FirebaseFirestore.getInstance()
        firestrore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData() : MutableLiveData<List<Mascotas>> {
        val listaMascotas =  MutableLiveData<List<Mascotas>>()
        firestrore.collection("MascotasApp")
            .document(codigoUsuario)
            .collection("misMascotas")
            .addSnapshotListener{snapshot, e ->
                if(e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    val lista = ArrayList<Mascotas>()
                    val mascotas = snapshot.documents
                    mascotas.forEach{
                        val mascota = it.toObject(Mascotas::class.java)
                        if(mascota!= null){
                            lista.add(mascota)
                        }
                    }
                    listaMascotas.value = lista
                }
            }
        return listaMascotas
    }


    fun saveMascotas(mascota: Mascotas){
        val document: DocumentReference
        if(mascota.id.isEmpty()){
            document = firestrore
                .collection("MascotasApp")
                .document(codigoUsuario)
                .collection("misMascotas")
                .document()
            mascota.id = document.id
        }else{
            document = firestrore.collection("MascotasApp")
                .document(codigoUsuario)
                .collection("misMascotas")
                .document(mascota.id)
        }
        val set = document.set(mascota)
        set.addOnSuccessListener {
            Log.d("AddMascota","Mascota Agregada")
        }
            .addOnCanceledListener {
                Log.e("AddMascota","Mascota NO Agregada")
            }
    }


    suspend fun deleteMascota(mascota: Mascotas){
        if(mascota.id.isNotEmpty()){
            firestrore
                .collection("MascotasApp")
                .document(codigoUsuario)
                .collection("misMascotas")
                .document(mascota.id)
                .delete()
                .addOnSuccessListener {
                    Log.d("deleteMascota", "Mascota eliminada")
                }
                .addOnCanceledListener {
                    Log.e("deleteMascota", "Mascota NO eliminada")
                }
        }
    }

}