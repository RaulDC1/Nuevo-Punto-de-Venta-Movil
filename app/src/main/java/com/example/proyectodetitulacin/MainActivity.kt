package com.example.proyectodetitulacin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    /* Datos necesarios para la comprobacion de inicio de sesion*/

    private lateinit var loginbtn: Button
    private lateinit var usern: EditText
    private lateinit var pword: EditText
    private lateinit var login: Login


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        loginbtn = findViewById(R.id.btnLogin)
        usern = findViewById(R.id.Email)
        pword = findViewById(R.id.Password)
        login = Login(this)

        /*Realización de el inicio de sesion y comprobacion*/

        loginbtn.setOnClickListener {

            val useredtx = usern.text.toString()
            val passedtx = pword.text.toString()

            if (TextUtils.isEmpty(useredtx) || TextUtils.isEmpty(passedtx)){
                Toast.makeText(this,"Tienes que llenar los campos", Toast.LENGTH_SHORT).show()
            }
            else{

                /*Cambio a la siguiente Actividad*/
                val lgin: Button = findViewById(R.id.btnLogin)
                lgin.setOnClickListener{

                    val intent: Intent = Intent(this, client::class.java)
                    startActivity(intent)
                }
            }
        }

       /*Boton utilizado para agregar registros nuevos a una base de datos. Actualmente esta invisible*/

        val btn1: Button = findViewById(R.id.articulos)
        btn1.setOnClickListener {

            val intent: Intent = Intent(this, Articulos:: class.java)
            startActivity(intent)
        }

    }

}