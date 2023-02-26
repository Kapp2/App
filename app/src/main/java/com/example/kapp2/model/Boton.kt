package com.example.kapp2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "botones")
@Parcelize
data class Boton(
    @PrimaryKey(autoGenerate = true)
    val id:Long?=null,
    val titulo:String,
    val sonido:String,
    val tematica:Int
): Parcelable {
    constructor(
        titulo: String,
        sonido: String,
        tematica: Int
    ) : this(null, titulo, sonido, tematica)

    override fun equals(other: Any?): Boolean {
        return (other is Boton) && (this.id == other.id)
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + titulo.hashCode()
        result = 31 * result + sonido.hashCode()
        result = 31 * result + tematica
        return result
    }
}