package com.syan.bvi
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val usuario: String,
    val contrasena: String
)
