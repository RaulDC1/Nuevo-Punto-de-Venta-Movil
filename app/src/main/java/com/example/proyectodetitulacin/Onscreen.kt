package com.example.proyectodetitulacin

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class Onscreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscreen)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        /*Pantalla principal para agregar los articulos e iniciar el presupuesto*/

        /*Cuadro de dialogo donde se tendra que ingresar el codigo del articulo*/
        val btnadd_i : Button = findViewById(R.id.add_i)
        btnadd_i.setOnClickListener {
            val view = View.inflate(this@Onscreen, R.layout.activity_articulos, null)

            val builder= AlertDialog.Builder(this@Onscreen)
            builder.setView(view)

            /*Estilo del cuadro de dialogo*/
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

    }

}

