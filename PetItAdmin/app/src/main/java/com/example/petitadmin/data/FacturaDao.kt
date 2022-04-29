package com.example.petitadmin.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.example.petitadmin.model.Factura

class FacturaDao {
    private var codigoUsuario: String
    private var firestrore: FirebaseFirestore

    init {
        val usuario = Firebase.auth.currentUser?.email
        //val usuario = Firebase.auth.currentUser?.uid
        codigoUsuario="$usuario"
        firestrore = FirebaseFirestore.getInstance()
        firestrore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData() : MutableLiveData<List<Factura>> {
        val listaFacturas =  MutableLiveData<List<Factura>>()
        firestrore.collection("FacturaApp")
            .document(codigoUsuario)
            .collection("misFacturas")
            .addSnapshotListener{snapshot, e ->
                if(e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    val lista = ArrayList<Factura>()
                    val factura = snapshot.documents
                    factura.forEach{
                        val factura = it.toObject(Factura::class.java)
                        if(factura!= null){
                            lista.add(factura)
                        }
                    }
                    listaFacturas.value = lista
                }
            }
        return listaFacturas
    }


    fun saveFacturas(fasctura: Factura){
        val document: DocumentReference
        if(fasctura.id.isEmpty()){
            document = firestrore
                .collection("FacturasApp")
                .document(codigoUsuario)
                .collection("misFacturas")
                .document()
            fasctura.id = document.id
        }else{
            document = firestrore.collection("FacturasApp")
                .document(codigoUsuario)
                .collection("misFacturas")
                .document(fasctura.id)
        }
        val set = document.set(fasctura)
        set.addOnSuccessListener {
            Log.d("AddFasctura","Fasctura Agregada")
        }
            .addOnCanceledListener {
                Log.e("AddFasctura","Fasctura NO Agregada")
            }
    }


    fun deleteFasctura(fasctura: Factura){
        if(fasctura.id.isNotEmpty()){
            firestrore
                .collection("FacturasApp")
                .document(codigoUsuario)
                .collection("misFacturas")
                .document(fasctura.id)
                .delete()
                .addOnSuccessListener {
                    Log.d("deleteFasctura", "Fasctura eliminada")
                }
                .addOnCanceledListener {
                    Log.e("deleteFasctura", "Fasctura NO eliminada")
                }
        }
    }

}