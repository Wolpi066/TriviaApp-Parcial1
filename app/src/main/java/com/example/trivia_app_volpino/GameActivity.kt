package com.example.trivia_app_volpino

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {
    private var correctAnswerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val rootLayout = findViewById<LinearLayout>(R.id.rootGameLayout)
        val imgIcon = findViewById<ImageView>(R.id.imgCategoryIcon)
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val rgOptions = findViewById<RadioGroup>(R.id.rgOptions)
        val rbOption1 = findViewById<RadioButton>(R.id.rbOption1)
        val rbOption2 = findViewById<RadioButton>(R.id.rbOption2)
        val rbOption3 = findViewById<RadioButton>(R.id.rbOption3)
        val btnVerify = findViewById<Button>(R.id.btnVerify)

        val userName = intent.getStringExtra("USER_NAME")
        val category = intent.getStringExtra("USER_CATEGORY")

        // Validacion
        if (userName != null && category != null) {

            tvGreeting.text = getString(R.string.greeting, userName, category)

            // Lógica
            when (category) {
                "Historia" -> {
                    rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_history))
                    imgIcon.setImageResource(R.drawable.ic_history)
                    tvQuestion.text = "¿En qué año llegó Cristóbal Colón a América?"
                    rbOption1.text = "1490"
                    rbOption2.text = "1492"
                    rbOption3.text = "1500"
                    correctAnswerId = rbOption2.id
                }
                "Programación" -> {
                    rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_programming))
                    imgIcon.setImageResource(R.drawable.ic_programming)
                    tvQuestion.text = "¿Qué significa POO?"
                    rbOption1.text = "Programación Orientada a Objetos"
                    rbOption2.text = "Procesamiento Operativo Ordenado"
                    rbOption3.text = "Programación Ofuscada y Oculta"
                    correctAnswerId = rbOption1.id
                }
                "Cine" -> {
                    rootLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.bg_cinema))
                    imgIcon.setImageResource(R.drawable.ic_cinema)
                    tvQuestion.text = "¿Quién dirigió la película 'El Padrino' (1972)?"
                    rbOption1.text = "Steven Spielberg"
                    rbOption2.text = "Quentin Tarantino"
                    rbOption3.text = "Francis Ford Coppola"
                    correctAnswerId = rbOption3.id
                }
            }
        }

        // Verificación
        btnVerify.setOnClickListener {
            val selectedOptionId = rgOptions.checkedRadioButtonId

            if (selectedOptionId == -1) {
                Toast.makeText(this, "Por favor, seleccioná una opción", Toast.LENGTH_SHORT).show()
            } else {
                if (selectedOptionId == correctAnswerId) {
                    // Respuesta Correcta
                    rootLayout.setBackgroundColor(android.graphics.Color.parseColor("#A5D6A7"))
                    Toast.makeText(this, "¡Respuesta Correcta!", Toast.LENGTH_LONG).show()
                } else {
                    // Respuesta Incorrecta
                    rootLayout.setBackgroundColor(android.graphics.Color.parseColor("#EF9A9A"))
                    Toast.makeText(this, "Respuesta Incorrecta", Toast.LENGTH_LONG).show()
                }
                // Deshabilitar botón para que no se pueda responder de nuevo
                btnVerify.isEnabled = false
            }
        }
    }
}