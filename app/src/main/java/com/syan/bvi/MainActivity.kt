package com.syan.bvi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.syan.bvi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Aplicar ajuste de márgenes en respuesta a los insets (bordes)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBusqueda.setOnClickListener {
            val intent = Intent(this, BusquedaActivity::class.java)
            startActivity(intent)
        }
        binding.btnDespacho.setOnClickListener {
            val intent = Intent(this, DespachoActivity::class.java)
            startActivity(intent)
        }
        binding.btnIngreso.setOnClickListener {
            val intent = Intent(this, IngresoActivity::class.java)
            startActivity(intent)
        }
        binding.btnInventario.setOnClickListener {
            val intent = Intent(this, InventarioActivity::class.java)
            startActivity(intent)
        }
    }
}
