package com.petit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Paseos(
    var id:String,
    val nombreMascota:String,
    val longitud:Double?,
    val latitud:Double?,
    val altura:Double?,
    val horaSalida:String?,
    val horaLlegada:String?,
    val Costo:String?,
) : Parcelable {
    constructor() :
            this("", "", 0.0,0.0,0.0,"", "", "")
}
