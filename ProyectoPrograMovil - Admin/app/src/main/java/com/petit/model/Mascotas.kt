package com.petit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Mascotas(
    var id:String,
    val nombre:String,
    val edad:String?,
    val raza:String?,
    val rutaAudio:String?,
    val rutaImagen:String?,
) : Parcelable {
    constructor() :
            this("", "", "", "", "",  "")
}
