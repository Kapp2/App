package com.example.kapp2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "botones")
@Parcelize
data class Boton(
    @PrimaryKey(autoGenerate = true)
    val boton_id:Long?=null,
    val titulo:String,
    val sonido:Int,
    val tematica:Int
): Parcelable {
    constructor(
        titulo: String,
        sonido: Int,
        tematica: Int
    ) : this(null, titulo, sonido, tematica)

    override fun equals(other: Any?): Boolean {
        return (other is Boton) && (this.boton_id == other.boton_id)
    }

    override fun hashCode(): Int {
        var result = boton_id?.hashCode() ?: 0
        result = 31 * result + titulo.hashCode()
        result = 31 * result + sonido.hashCode()
        result = 31 * result + tematica
        return result
    }
}