package com.cibertec.controlador

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.cibertec.entidad.Pelicula
import com.cibertec.utils.appConfig
import java.util.ArrayList

class PeliculaController {
    fun findAll(): ArrayList<Pelicula> {
        var lista = ArrayList<Pelicula>()
        var CN: SQLiteDatabase = appConfig.BD.readableDatabase
        var sql = "select * from Peliculas"
        var RS: Cursor = CN.rawQuery(sql, null)

        while (RS.moveToNext()) {
            val foto = if (!RS.isNull(5)) RS.getString(5) else "ic_launcher_background"
            var bean = Pelicula(RS.getInt(0), RS.getString(1), RS.getInt(2), RS.getInt(3), RS.getString(4), foto)
            lista.add(bean)
        }
        return lista
    }

    fun adicionarPelicula(bean: Pelicula): Int {
        var salida = -1
        var cn: SQLiteDatabase = appConfig.BD.writableDatabase
        var row = ContentValues()
        row.put("nompel", bean.nompel)
        row.put("duracion", bean.duracion)
        row.put("anio", bean.anio)
        row.put("genero", bean.genero)
        salida = cn.insert("Peliculas", "codpel", row).toInt()
        return salida
    }

    fun editarPelicula(bean: Pelicula): Int {
        var salida = -1
        var cn: SQLiteDatabase = appConfig.BD.writableDatabase
        var row = ContentValues()
        row.put("nompel", bean.nompel)
        row.put("duracion", bean.duracion)
        row.put("anio", bean.anio)
        row.put("genero", bean.genero)
        salida = cn.update("Peliculas", row, "codpel=?", arrayOf(bean.codped.toString()))
        return salida
    }

    fun eliminarPelicula(codigo: Int): Int {
        var salida = -1
        val cn: SQLiteDatabase = appConfig.BD.writableDatabase
        salida = cn.delete("Peliculas", "codpel=?", arrayOf(codigo.toString()))
        return salida
    }


}