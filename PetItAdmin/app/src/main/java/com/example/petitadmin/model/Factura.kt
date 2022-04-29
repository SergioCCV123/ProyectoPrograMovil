package com.example.petitadmin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Factura(
    var id:String,
    val cliente:String,
    val mascota:String?,
    val total:Double?,
    val descripcion:String?,
    val estado:Boolean?,
) : Parcelable {
    constructor() :
            this("", "", "",0.0,"",false)
}
