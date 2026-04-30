package com.example.trivia_app_volpino

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val spinnerCategory = findViewById<Spinner>(R.id.spinnerCategory)
        val btnStart = findViewById<Button>(R.id.btnStart)

        // Configuracion del Spinner
        val adapterSpinner = ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        )

        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapterSpinner

        // Lógica del botón de inicio
        btnStart.setOnClickListener {
            val name = etName.text.toString().trim()
            val selectedPosition = spinnerCategory.selectedItemPosition

            if (name.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresá tu nombre", Toast.LENGTH_SHORT).show()
                etName.error = "Campo obligatorio"
                return@setOnClickListener // Cortamos la ejecución si hay error
            }

            val category = spinnerCategory.selectedItem.toString()

            // Creacion del intent
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("USER_NAME", name)
            intent.putExtra("USER_CATEGORY", category)

            startActivity(intent)
        }
    }
}