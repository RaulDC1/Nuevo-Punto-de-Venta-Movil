package com.example.proyectodetitulacin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/*Creacion de tablas para la base de datos*/

class SQLite(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

/*Esta funcion crea las tablas necesarias dependiendo los nombres que se ingresen*/
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table articulos (codigo int primary key, nombre text, precio int)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}