package com.example.kapp2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "perfiles")
@Parcelize
data class Perfil(
    @PrimaryKey(autoGenerate = true)
    val perfil_id:Long?=null,
    val nickname:String,
    val password:String
):Parcelable {
    constructor(
        nickname: String,
        password: String
    ):this(null, nickname, password)

    override fun equals(other: Any?): Boolean {
        return (other is Perfil)&&(this.perfil_id == other.perfil_id)
    }

    override fun hashCode(): Int {
        var result = perfil_id?.hashCode() ?: 0
        result = 31 * result + nickname.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}
