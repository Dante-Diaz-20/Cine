package com.cibertec.entidad

import java.io.Serializable

class Pelicula(
    var codped: Int,
    var nompel: String,
    var duracion: Int,
    var anio: Int,
    var genero: String,
    var foto: String?
): Serializable { }