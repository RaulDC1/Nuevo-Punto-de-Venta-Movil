package com.example.proyectodetitulacin

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

public class items : AppCompatActivity() {
    /*Variables necesarias para la insercion y busqueda de datos*/
    var txtname: EditText?=null
    var txtnumber_client: EditText?=null
    var txtaddress: EditText?=null
    var txtpuntuality: EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        /*Datos a sustituir*/

        txtname= findViewById(R.id.txtname)
        txtnumber_client= findViewById(R.id.txtnumber)
        txtaddress= findViewById(R.id.txtaddress)
        txtpuntuality= findViewById(R.id.txtpuntuality)
    }
/*Funcion para insertar datos dentro de una base de datos. Se leen los datos del formulario. Actualmente el formulario el formulario se cambio para agregar datos de clientes*/
    fun insertar(view: View){
        var con=SQLite(this, "cliente", null, 1)
        var baseDatos=con.writableDatabase

        var Name=txtname?.text.toString()
        var Number_client=txtnumber_client?.text.toString()
        var Address=txtaddress?.text.toString()
        var Puntuality=txtpuntuality?.text.toString()

    /*Busqueda de filas para la insercion de datos*/

        if(Name.isEmpty()==false && Number_client.isEmpty()==false && Address.isEmpty()==false && Puntuality.isEmpty()==false){
            var registro=ContentValues()

            /*Creacion de registro segun el nombre de la fila*/

            registro.put("nombre",Name)
            registro.put("numero", Number_client)
            registro.put("direccion", Address)
            registro.put("puntualidad", Puntuality)
            baseDatos.insert("cliente", null, registro)
            txtname?.setText("")
            txtnumber_client?.setText("")
            txtaddress?.setText("")
            txtpuntuality?.setText("")

            /*Se presenta ese mensaje cuando los articulos se insertan de manera correcta*/

            Toast.makeText(this,"se ha insertado correctamente", Toast.LENGTH_SHORT).show()
        }

        /*Comprobacion de campos*/
        else{
            Toast.makeText(this,"los campos deben tener texto", Toast.LENGTH_SHORT).show()
        }
    }
}

