package com.example.proyectodetitulacin

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Articulos : AppCompatActivity() {

/*Datos que se van a editar para agregar articulos a la base de datos. Estos datos se podran buscar por el codigo o sku*/

    var txtcodigo: EditText?=null
    var txtnombre: EditText?=null
    var txtprecio: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        /*Datos a sustituir*/

        txtcodigo = findViewById(R.id.codigo)
        txtnombre = findViewById(R.id.item_name)
        txtprecio = findViewById(R.id.precio)
    }

    /*Funcion que permite la sustitucion de datos*/

    fun articulos (view: View){
        var con=SQLite(this, "articulos", null, 1)
        var baseDatos=con.writableDatabase

        var Codigo=txtcodigo?.text.toString()
        var Nombre=txtnombre?.text.toString()
        var Precio=txtprecio?.text.toString()

        /*Busqueda e insercion de datos segun la fila en la que se encuentren*/

        if (Codigo.isEmpty()==false && Nombre.isEmpty()==false && Precio.isEmpty()==false){
            var registro= ContentValues()
            registro.put("codigo",Codigo)
            registro.put("nombre", Nombre)
            registro.put("precio", Precio)
            baseDatos.insert("articulos", null, registro)
            txtcodigo?.setText("")
            txtnombre?.setText("")
            txtprecio?.setText("")

            /*Cuando se hayan insertado correctamente los datos se hara la comprobacion con este mensaje*/

            Toast.makeText(this,"se ha insertado correctamente", Toast.LENGTH_SHORT).show()
        }

        /*Comprobacion de campos de texto*/

        else{
            Toast.makeText(this,"los campos deben tener texto", Toast.LENGTH_SHORT).show()
        }
    }

    /*Funcion que realiza la busqueda de los datos ingresados anteriormente*/

    fun sku (view: View) {
        val con = SQLite(this, "articulos", null, 1)
        val baseDatos = con.writableDatabase

        /*Se realiza la busqueda del codigo para obtener nombre y precio de este articulo*/

        val codigo = txtcodigo?.text.toString()
        if (codigo.isEmpty() == false) {
            val fila = baseDatos.rawQuery(
                "select nombre,precio from articulos where codigo='$codigo'",
                null
            )

            /*Se indica al programa donde buscar y en que filas*/

            if (fila.moveToFirst() == true) {
                txtnombre?.setText(fila.getString(0))
                txtprecio?.setText(fila.getString(1))
                baseDatos.close()
            }

            /*Comprobacion en caso de que el articulo no se encuentre en la base de datos*/

        } else {
            txtnombre?.setText("")
            txtprecio?.setText("")
            Toast.makeText(this, "El codigo no existe en el sistema", Toast.LENGTH_SHORT).show()
        }
    }
}