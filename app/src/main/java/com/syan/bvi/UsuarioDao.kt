package com.syan.bvi

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(usuario: Usuario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(usuarios: List<Usuario>)

    @Query("SELECT * FROM usuarios WHERE usuario = :usuario AND contrasena = :contrasena LIMIT 1")
    fun getUsuario(usuario: String, contrasena: String): Usuario?
}
