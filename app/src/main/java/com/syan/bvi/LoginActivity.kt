package com.syan.bvi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.syan.bvi.databinding.ActivityLoginBinding
import java.util.concurrent.Executors

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: AppDatabase
    private lateinit var usuarioDao: UsuarioDao
    private val apiService = RetrofitClient.apiService
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar la base de datos
        db = AppDatabase.getDatabase(applicationContext)
        usuarioDao = db.usuarioDao()

        // Obtener datos de la API e insertar en la base de datos
        fetchAndInsertUsuarios()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Verificar el usuario en la base de datos
            verifyLogin(username, password)
        }
    }

    private fun fetchAndInsertUsuarios() {
        executor.execute {
            try {
                val response = apiService.getUsuarios().execute()
                val usuarios = response.body()
                if (usuarios != null) {
                    usuarioDao.insertAll(usuarios)
                } else {
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Error al obtener usuarios de la API", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Error al obtener usuarios de la API", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verifyLogin(username: String, password: String) {
        executor.execute {
            val user = usuarioDao.getUsuario(username, password)
            runOnUiThread {
                if (user != null && user.contrasena == password) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Omitir si no deseas cerrar LoginActivity
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
