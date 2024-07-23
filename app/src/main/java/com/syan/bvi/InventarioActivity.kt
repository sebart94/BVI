package com.syan.bvi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syan.bvi.databinding.InventarioBinding

class InventarioActivity : AppCompatActivity() {

    private lateinit var binding: InventarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = InventarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGeneral.setOnClickListener {
            val intent = Intent(this, InvGeneralActivity::class.java)
            startActivity(intent)
        }
    }
}
