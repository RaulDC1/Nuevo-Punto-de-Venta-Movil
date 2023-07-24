package com.example.proyectodetitulacin

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast

class Login(mainActivity: MainActivity) : AppCompatActivity() {

    /*Aqui se realiza la comprobacion de inicio de sesion. Que el usuario se encuentre registrado*/

    var txtemail: EditText?=null
    var txtpassword: EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        txtemail= findViewById(R.id.txtemail)
        txtpassword= findViewById(R.id.txtpassword)
    }

    /*Registro de usuarios se utilizo el formulario "activity_login"*/

    fun login (view: View){
        var con=SQLite(this,"login",null,1)
        var baseDatos=con.writableDatabase

        var Email=txtemail?.text.toString()
        var Password=txtpassword?.text.toString()

        /*Busqueda e insercion de datos en las filas correspondientes*/

        if(Email.isEmpty()==false && Password.isEmpty()==false){
            var registro= ContentValues()
            registro.put("correo",Email)
            registro.put("contrasena", Password)
            baseDatos.insert("login", null, registro)
            txtemail?.setText("")
            txtpassword?.setText("")
            Toast.makeText(this, "Esta registrado", Toast.LENGTH_SHORT).show()
        }

        /*Comprobacion de llenado de campos*/

        else{
            Toast.makeText(this,"los campos deben tener texto", Toast.LENGTH_SHORT).show()
        }

/*Funcion que realiza la comprobacion de que el usuario ya se encuentra registrado*/
        fun checking(view: View): Boolean {
            var con=SQLite(this,"login",null,1)
            val baseDatos= con.writableDatabase
            val query = "select * from login where correo= '$Email' and contrasena= '$Password'"
            val cursor = baseDatos.rawQuery(query, null)
            if (cursor.count<=0){
                cursor.close()
                return false
            }
            cursor.close()
            return false

        }
    }
}