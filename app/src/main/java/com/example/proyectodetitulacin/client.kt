package com.example.proyectodetitulacin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class client : AppCompatActivity() {

/*Variables que seran utilizadas para la creacion y sustitucion de los datos del cliente*/

    var txtname: EditText?=null
    var txtnumber_client: EditText?=null
    var txtaddress: EditText?=null
    var txtpuntuality: EditText?=null
    var entername: EditText?=null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        txtname= findViewById(R.id.txtname)
        txtnumber_client= findViewById(R.id.txtnumber)
        txtaddress= findViewById(R.id.txtaddress)
        txtpuntuality= findViewById(R.id.txtpuntuality)
        entername= findViewById(R.id.entername)

    }
/*Insercion en la base de datos llamada "Clientes" mediante esta funcion. Se utilizo la actividad "activity_items" para el formulario*/

    fun insertar(view: View){
        var con=SQLite(this, "cliente", null, 1)
        var baseDatos=con.writableDatabase

        var Name=txtname?.text.toString()
        var Number_client=txtnumber_client?.text.toString()
        var Address=txtaddress?.text.toString()
        var Puntuality=txtpuntuality?.text.toString()

    /*Se realiza la insercion buscando por filas en la base datos*/

        if(Name.isEmpty()==false && Number_client.isEmpty()==false && Address.isEmpty()==false && Puntuality.isEmpty()==false){
            var registro= ContentValues()

            /*Se cambian los datos por los datos puestos en el formulario*/

            registro.put("nombre",Name)
            registro.put("numero", Number_client)
            registro.put("direccion", Address)
            registro.put("puntualidad", Puntuality)
            baseDatos.insert("cliente", null, registro)
            txtname?.setText("")
            txtnumber_client?.setText("")
            txtaddress?.setText("")
            txtpuntuality?.setText("")

            /*Se hace un anuncio al usuario cuando los datos son insertados de manera correcta*/

            Toast.makeText(this,"se ha insertado correctamente", Toast.LENGTH_SHORT).show()
        }
        /*Alerta en caso de que no se haya llenado ningun dato*/
        else{
            Toast.makeText(this,"los campos deben tener texto", Toast.LENGTH_SHORT).show()
        }
    }
/*Esta funcion sera para poder disponer de los datos agregados anteriormente en la base de datos*/

    fun buscar(view: View){
        val con=SQLite(this,"cliente", null, 1)
        val baseDatos= con.writableDatabase
        val nombre = entername?.text.toString()

        /*Sea realiza la busca en la base datos agregando el numero de fila en la que se encuentran los datos solicitados*/

        if (nombre.isEmpty()==false){
            val fila=baseDatos.rawQuery("select numero,direccion,puntualidad from cliente where nombre='$nombre'", null)
            if (fila.moveToFirst()==true){
                txtnumber_client?.setText(fila.getString(0))
                txtaddress?.setText(fila.getString(1))
                txtpuntuality?.setText(fila.getString(2))
                baseDatos.close()
            }

            /*En dado caso de que no se encuentren los datos; la aplicacion devolvera el siguiente mensaje*/

            else{
                txtnumber_client?.setText("")
                txtaddress?.setText("")
                txtpuntuality?.setText("")
                Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show()

            }
        }
        /*Boton para pasar a la siguiente actividad*/

        val btn: Button = findViewById(R.id.btnnext)
        btn.setOnClickListener {

            val intent: Intent = Intent(this, Onscreen:: class.java)
            startActivity(intent)
        }
    }
}