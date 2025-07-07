package com.cibertec.holder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.R

class VistaPelicula(vista: View): RecyclerView.ViewHolder(vista) {
    var tvNomPel: TextView
    var tvCodigoDuracion: TextView
    var tvAnioGenero: TextView
    var imgPel: ImageView
    var btnUpdate: Button
    var btnDelete: Button
    init {
        tvNomPel = vista.findViewById(R.id.tvNomPel)
        tvCodigoDuracion = vista.findViewById(R.id.tvCodigoDuracion)
        tvAnioGenero = vista.findViewById(R.id.tvAnioGenero)
        imgPel = vista.findViewById(R.id.imgPel)
        btnUpdate = vista.findViewById(R.id.btnUpdate)
        btnDelete = vista.findViewById(R.id.btnDelete)
    }
}